-- =============================================================================
-- ACT SERVICE — Supabase Production Database Schema & Security Migration
-- =============================================================================

-- 1. Enable UUID Extension
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 2. Profiles Table
CREATE TABLE IF NOT EXISTS public.profiles (
    id UUID PRIMARY KEY REFERENCES auth.users(id) ON DELETE CASCADE,
    display_name TEXT NOT NULL DEFAULT 'Lovely',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 3. Menstrual Cycle Entries
CREATE TABLE IF NOT EXISTS public.cycle_entries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    period_start_date DATE NOT NULL,
    period_end_date DATE,
    flow_level TEXT CHECK (flow_level IN ('Spotting', 'Light', 'Medium', 'Heavy')),
    cramp_intensity TEXT CHECK (cramp_intensity IN ('None', 'Mild', 'Moderate', 'Strong')),
    notes TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_cycle_entries_user_date ON public.cycle_entries(user_id, period_start_date DESC);

-- 4. Wellbeing Options (Moods, Symptoms, Cravings, Support catalog)
CREATE TABLE IF NOT EXISTS public.wellbeing_options (
    id TEXT PRIMARY KEY,
    category TEXT NOT NULL,
    label TEXT NOT NULL,
    option_type TEXT DEFAULT 'tag',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_wellbeing_options_cat ON public.wellbeing_options(category, is_active);

-- 5. Daily Check-ins
CREATE TABLE IF NOT EXISTS public.daily_checkins (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    checkin_date DATE NOT NULL DEFAULT CURRENT_DATE,
    notes TEXT,
    intensity INT CHECK (intensity BETWEEN 1 AND 4),
    share_with_partner BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_daily_checkins_user_date ON public.daily_checkins(user_id, checkin_date DESC);

-- 6. Daily Check-in Selections
CREATE TABLE IF NOT EXISTS public.daily_checkin_selections (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    checkin_id UUID NOT NULL REFERENCES public.daily_checkins(id) ON DELETE CASCADE,
    option_id TEXT NOT NULL REFERENCES public.wellbeing_options(id) ON DELETE CASCADE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    UNIQUE(checkin_id, option_id)
);

CREATE INDEX IF NOT EXISTS idx_checkin_selections_checkin ON public.daily_checkin_selections(checkin_id);

-- 7. Vegetarian Food Categories
CREATE TABLE IF NOT EXISTS public.food_categories (
    id TEXT PRIMARY KEY,
    name TEXT NOT NULL,
    display_order INT DEFAULT 0,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

-- 8. Vegetarian Food Catalog (STRICT NO PRICES, NO PAYMENTS)
CREATE TABLE IF NOT EXISTS public.food_items (
    id TEXT PRIMARY KEY,
    category_id TEXT REFERENCES public.food_categories(id) ON DELETE SET NULL,
    name TEXT NOT NULL,
    description TEXT NOT NULL,
    image_url TEXT,
    cuisine TEXT NOT NULL,
    region TEXT,
    is_vegetarian BOOLEAN NOT NULL DEFAULT TRUE CHECK (is_vegetarian = TRUE),
    is_vegan BOOLEAN NOT NULL DEFAULT FALSE,
    is_jain_friendly BOOLEAN NOT NULL DEFAULT FALSE,
    comfort_badge TEXT DEFAULT 'Comfort pick',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_food_items_cuisine ON public.food_items(cuisine, is_active);

-- 9. Food Requests (No price / payment columns)
CREATE TABLE IF NOT EXISTS public.food_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    reference_id TEXT NOT NULL,
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    status TEXT NOT NULL DEFAULT 'submitted' CHECK (status IN ('submitted', 'acknowledged', 'in_progress', 'completed', 'cancelled')),
    notes TEXT,
    timing TEXT DEFAULT 'ASAP (Craving!)',
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_food_requests_user ON public.food_requests(user_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_food_requests_status ON public.food_requests(status);

-- 10. Food Request Items (No price / payment columns)
CREATE TABLE IF NOT EXISTS public.food_request_items (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    food_request_id UUID NOT NULL REFERENCES public.food_requests(id) ON DELETE CASCADE,
    food_item_id TEXT NOT NULL REFERENCES public.food_items(id) ON DELETE RESTRICT,
    quantity INT NOT NULL CHECK (quantity > 0),
    item_notes TEXT,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE INDEX IF NOT EXISTS idx_food_request_items_req ON public.food_request_items(food_request_id);

-- 11. Ring the Bell Requests
CREATE TABLE IF NOT EXISTS public.bell_requests (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    user_id UUID NOT NULL REFERENCES auth.users(id) ON DELETE CASCADE,
    message TEXT,
    urgency TEXT NOT NULL DEFAULT 'Warm Support',
    status TEXT NOT NULL DEFAULT 'pending' CHECK (status IN ('pending', 'seen', 'responding', 'resolved', 'cancelled')),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    acknowledged_at TIMESTAMPTZ,
    resolved_at TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS idx_bell_requests_user ON public.bell_requests(user_id, created_at DESC);
CREATE INDEX IF NOT EXISTS idx_bell_requests_status ON public.bell_requests(status);

-- 12. Administrator Notifications
CREATE TABLE IF NOT EXISTS public.admin_notifications (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    event_type TEXT NOT NULL CHECK (event_type IN ('food_request', 'bell_request')),
    food_request_id UUID REFERENCES public.food_requests(id) ON DELETE CASCADE,
    bell_request_id UUID REFERENCES public.bell_requests(id) ON DELETE CASCADE,
    title TEXT NOT NULL,
    message TEXT NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    read_at TIMESTAMPTZ
);

CREATE INDEX IF NOT EXISTS idx_admin_notif_unread ON public.admin_notifications(read_at) WHERE read_at IS NULL;

-- =============================================================================
-- AUTOMATED TRIGGERS FOR ADMIN NOTIFICATIONS
-- =============================================================================

CREATE OR REPLACE FUNCTION public.handle_new_food_request()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO public.admin_notifications (event_type, food_request_id, title, message)
    VALUES (
        'food_request',
        NEW.id,
        'A new food request is waiting 🍕',
        COALESCE(NEW.notes, 'She has requested comfort dishes with love!')
    );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

DROP TRIGGER IF EXISTS trigger_new_food_request ON public.food_requests;
CREATE TRIGGER trigger_new_food_request
    AFTER INSERT ON public.food_requests
    FOR EACH ROW EXECUTE FUNCTION public.handle_new_food_request();

CREATE OR REPLACE FUNCTION public.handle_new_bell_request()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO public.admin_notifications (event_type, bell_request_id, title, message)
    VALUES (
        'bell_request',
        NEW.id,
        'Someone has rung your bell 🔔',
        COALESCE(NEW.message, 'Need some devoted partner attention ❤️ (' || NEW.urgency || ')')
    );
    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

DROP TRIGGER IF EXISTS trigger_new_bell_request ON public.bell_requests;
CREATE TRIGGER trigger_new_bell_request
    AFTER INSERT ON public.bell_requests
    FOR EACH ROW EXECUTE FUNCTION public.handle_new_bell_request();

-- Profile Creation on User Signup
CREATE OR REPLACE FUNCTION public.handle_new_user()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO public.profiles (id, display_name)
    VALUES (NEW.id, COALESCE(NEW.raw_user_meta_data->>'display_name', 'Lovely'));
    RETURN NEW;
END;
$$ LANGUAGE plpgsql SECURITY DEFINER;

DROP TRIGGER IF EXISTS on_auth_user_created ON auth.users;
CREATE TRIGGER on_auth_user_created
    AFTER INSERT ON auth.users
    FOR EACH ROW EXECUTE FUNCTION public.handle_new_user();

-- =============================================================================
-- ROW LEVEL SECURITY (RLS) POLICIES
-- =============================================================================

ALTER TABLE public.profiles ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.cycle_entries ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.wellbeing_options ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.daily_checkins ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.daily_checkin_selections ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.food_categories ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.food_items ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.food_requests ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.food_request_items ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.bell_requests ENABLE ROW LEVEL SECURITY;
ALTER TABLE public.admin_notifications ENABLE ROW LEVEL SECURITY;

-- Helper function to check if the caller is an authenticated admin
CREATE OR REPLACE FUNCTION public.is_admin()
RETURNS BOOLEAN AS $$
BEGIN
    RETURN (auth.jwt() -> 'app_metadata' ->> 'role') = 'admin';
END;
$$ LANGUAGE plpgsql STABLE SECURITY DEFINER;

-- Profiles: users read & update their own profile; admins can read
CREATE POLICY "Users can view own profile" ON public.profiles
    FOR SELECT TO authenticated USING (auth.uid() = id OR public.is_admin());

CREATE POLICY "Users can update own profile" ON public.profiles
    FOR UPDATE TO authenticated USING (auth.uid() = id) WITH CHECK (auth.uid() = id);

-- Cycle Entries: Strictly private to user; admins NEVER have access
CREATE POLICY "Users can manage own cycle entries" ON public.cycle_entries
    FOR ALL TO authenticated USING (auth.uid() = user_id) WITH CHECK (auth.uid() = user_id);

-- Wellbeing Options: Read-only for authenticated users, admins can insert/update
CREATE POLICY "Anyone can read active wellbeing options" ON public.wellbeing_options
    FOR SELECT TO authenticated USING (is_active = TRUE);

CREATE POLICY "Admins can manage wellbeing options" ON public.wellbeing_options
    FOR ALL TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());

-- Daily Check-ins: Private by default; admin can view only if share_with_partner is true
CREATE POLICY "Users can manage own daily checkins" ON public.daily_checkins
    FOR ALL TO authenticated USING (auth.uid() = user_id) WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Admins can view shared checkins" ON public.daily_checkins
    FOR SELECT TO authenticated USING (public.is_admin() AND share_with_partner = TRUE);

-- Daily Check-in Selections
CREATE POLICY "Users can manage own checkin selections" ON public.daily_checkin_selections
    FOR ALL TO authenticated
    USING (EXISTS (SELECT 1 FROM public.daily_checkins WHERE id = checkin_id AND user_id = auth.uid()))
    WITH CHECK (EXISTS (SELECT 1 FROM public.daily_checkins WHERE id = checkin_id AND user_id = auth.uid()));

-- Food Categories & Items: Read-only for all, managed by admins
CREATE POLICY "Authenticated users can read food categories" ON public.food_categories
    FOR SELECT TO authenticated USING (TRUE);

CREATE POLICY "Admins can manage food categories" ON public.food_categories
    FOR ALL TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());

CREATE POLICY "Authenticated users can read active food items" ON public.food_items
    FOR SELECT TO authenticated USING (is_active = TRUE);

CREATE POLICY "Admins can manage food items" ON public.food_items
    FOR ALL TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());

-- Food Requests: Users can view & create own; admins can view all and update status
CREATE POLICY "Users can view own food requests" ON public.food_requests
    FOR SELECT TO authenticated USING (auth.uid() = user_id OR public.is_admin());

CREATE POLICY "Users can insert own food requests" ON public.food_requests
    FOR INSERT TO authenticated WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can cancel own pending food requests" ON public.food_requests
    FOR UPDATE TO authenticated USING (auth.uid() = user_id AND status = 'submitted')
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Admins can update food requests" ON public.food_requests
    FOR UPDATE TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());

-- Food Request Items
CREATE POLICY "Users and admins can view request items" ON public.food_request_items
    FOR SELECT TO authenticated
    USING (EXISTS (SELECT 1 FROM public.food_requests WHERE id = food_request_id AND (user_id = auth.uid() OR public.is_admin())));

CREATE POLICY "Users can insert request items" ON public.food_request_items
    FOR INSERT TO authenticated
    WITH CHECK (EXISTS (SELECT 1 FROM public.food_requests WHERE id = food_request_id AND user_id = auth.uid()));

-- Bell Requests: Users can view & create own; admins can view all & update status
CREATE POLICY "Users and admins can view bell requests" ON public.bell_requests
    FOR SELECT TO authenticated USING (auth.uid() = user_id OR public.is_admin());

CREATE POLICY "Users can insert own bell requests" ON public.bell_requests
    FOR INSERT TO authenticated WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Users can cancel own pending bell requests" ON public.bell_requests
    FOR UPDATE TO authenticated USING (auth.uid() = user_id AND status = 'pending')
    WITH CHECK (auth.uid() = user_id);

CREATE POLICY "Admins can update bell requests" ON public.bell_requests
    FOR UPDATE TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());

-- Administrator Notifications: Admins only
CREATE POLICY "Admins can manage admin notifications" ON public.admin_notifications
    FOR ALL TO authenticated USING (public.is_admin()) WITH CHECK (public.is_admin());
