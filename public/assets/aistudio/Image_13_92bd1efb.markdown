---
name: Romantic Care Sanctuary
colors:
  surface: '#fff7fa'
  surface-dim: '#ebd3e5'
  surface-bright: '#fff7fa'
  surface-container-lowest: '#ffffff'
  surface-container-low: '#ffeff9'
  surface-container: '#ffe7f8'
  surface-container-high: '#fae1f3'
  surface-container-highest: '#f4dced'
  on-surface: '#251723'
  on-surface-variant: '#514347'
  inverse-surface: '#3b2c39'
  inverse-on-surface: '#ffebf9'
  outline: '#837377'
  outline-variant: '#d5c2c6'
  surface-tint: '#874d5f'
  primary: '#874d5f'
  on-primary: '#ffffff'
  primary-container: '#e8a0b5'
  on-primary-container: '#6b3547'
  inverse-primary: '#fcb2c7'
  secondary: '#605c6b'
  on-secondary: '#ffffff'
  secondary-container: '#e6e0f2'
  on-secondary-container: '#666271'
  tertiary: '#50634f'
  on-tertiary: '#ffffff'
  tertiary-container: '#a5baa2'
  on-tertiary-container: '#384b38'
  error: '#ba1a1a'
  on-error: '#ffffff'
  error-container: '#ffdad6'
  on-error-container: '#93000a'
  primary-fixed: '#ffd9e2'
  primary-fixed-dim: '#fcb2c7'
  on-primary-fixed: '#370a1d'
  on-primary-fixed-variant: '#6c3648'
  secondary-fixed: '#e6e0f2'
  secondary-fixed-dim: '#c9c4d5'
  on-secondary-fixed: '#1c1a26'
  on-secondary-fixed-variant: '#484553'
  tertiary-fixed: '#d3e8cf'
  tertiary-fixed-dim: '#b7ccb4'
  on-tertiary-fixed: '#0e1f10'
  on-tertiary-fixed-variant: '#394b39'
  background: '#fff7fa'
  on-background: '#251723'
  surface-variant: '#f4dced'
typography:
  display-lg:
    fontFamily: Playfair Display
    fontSize: 40px
    fontWeight: '600'
    lineHeight: 48px
    letterSpacing: -0.02em
  display-lg-mobile:
    fontFamily: Playfair Display
    fontSize: 32px
    fontWeight: '600'
    lineHeight: 40px
    letterSpacing: -0.01em
  headline-lg:
    fontFamily: Playfair Display
    fontSize: 28px
    fontWeight: '600'
    lineHeight: 36px
  headline-md:
    fontFamily: Playfair Display
    fontSize: 22px
    fontWeight: '500'
    lineHeight: 30px
  headline-sm:
    fontFamily: Playfair Display
    fontSize: 18px
    fontWeight: '500'
    lineHeight: 24px
  title-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '600'
    lineHeight: 22px
  body-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 16px
    fontWeight: '400'
    lineHeight: 24px
  body-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '400'
    lineHeight: 20px
  body-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '400'
    lineHeight: 16px
  label-lg:
    fontFamily: Plus Jakarta Sans
    fontSize: 14px
    fontWeight: '600'
    lineHeight: 18px
    letterSpacing: 0.01em
  label-md:
    fontFamily: Plus Jakarta Sans
    fontSize: 12px
    fontWeight: '600'
    lineHeight: 16px
    letterSpacing: 0.02em
  label-sm:
    fontFamily: Plus Jakarta Sans
    fontSize: 11px
    fontWeight: '500'
    lineHeight: 14px
    letterSpacing: 0.03em
rounded:
  sm: 0.5rem
  DEFAULT: 1rem
  md: 1.5rem
  lg: 2rem
  xl: 3rem
  full: 9999px
spacing:
  gutter: 1rem
  gutter-tablet: 1.5rem
  margin: 1.25rem
  margin-tablet: 2rem
  space-xs: 0.25rem
  space-sm: 0.5rem
  space-md: 1rem
  space-lg: 1.5rem
  space-xl: 2.25rem
---

## Brand & Style

This design system embodies an intimate, restorative, and devotion-centered wellness experience crafted specifically for romantic partners. It balances the structural rigor of modern Android Material 3 with the delicate, tactile grace of boutique stationery and gentle personal wellness journals.

The emotional tone is tender, safe, reassuring, and playful without lapsing into juvenile aesthetics. Visual pacing is deliberate and unhurried: generous padding, pill-shaped interactions, organic curves, and whispered micro-details (such as delicate petal counters, heart-accented badges, and soft tinted halos) communicate affection and attentive care. 

Tactile affordances draw inspiration from smooth pebble forms, soft-pressed paper stock, and weightless ambient warmth. Interactive surfaces respond with gentle, cushioned springs rather than sharp mechanical clicks.

## Colors

The palette leverages high-warmth tones that eliminate clinical harshness and screen fatigue.

- **Primary (`#E8A0B5` - Soft Rose Pink):** Used for focal interactive touchpoints, heart toggles, active indicator pills, and emotional milestones.
- **Background (`#FFF9F5` - Warm Ivory):** The structural canvas. Serves as a soothing, parchment-warm backdrop that contrasts softly with elevated cards.
- **Secondary (`#E9E3F5` - Muted Lavender):** Applied to calm resting containers, secondary action groups, mood tag chips, and reciprocal partner status surfaces.
- **Accent / Deep Tone (`#82465F` - Deep Romantic Burgundy):** Provides romantic richness, prominent display lettering, active states, and focused call-to-action fills where high legibility is paramount.
- **Supporting Accent (`#A8BDA5` - Sage Green):** Designates growth, wellness check-ins, hydration, cycle tracking, and positive affirmations.
- **Main Text (`#382936` - Deep Plum):** Replaces cold blacks with a deep chromatic neutral, preserving warmth across all text hierarchies.
- **Surface Cards (`#FFFFFF` - Crisp Clean White):** Clean islands resting upon the ivory base, delivering immediate visual clarity and content framing.

## Typography

The typography pairing contrasts poetic expression with ergonomic clarity.

- **Headlines & Expressive Greetings:** Rendered in `Playfair Display`. The high-contrast editorial serifs lend an intimate, love-note feel to partner greetings, milestones, and romantic chapter titles. Use italic variants sparingly for romantic emphasis (e.g., *"Thinking of you"*).
- **Body & Functional UI:** Rendered in `Plus Jakarta Sans`. Its open apertures, geometric friendliness, and optimized mobile legibility ensure clear reading across wellness metrics, reminder lists, and chat bubbles.
- **Hierarchy Rules:** Large display text switches gracefully down to `display-lg-mobile` on smaller viewports to prevent awkward line breaks on compact Android handsets.

## Layout & Spacing

The layout is built around a mobile-first fluid single-column flow that transitions to an adaptive 4-to-8 column structure on foldables and tablets. 

- **Canvas Insets:** Default edge margin is `1.25rem` (20px) on mobile viewports, ensuring content breathes against system gestures, camera cutouts, and bottom navigation handles.
- **Vertical Rhythm:** Elements follow an 8pt spatial grid. Cards and sections sit `1rem` to `1.5rem` apart, generating a relaxed, uncluttered vertical pacing that avoids feeling data-dense.
- **Safe Area Anchoring:** Primary relationship action buttons (such as sending a quick heartbeat or care request) remain anchored to the bottom sheet safe-zone with floating pill contours.

## Elevation & Depth

Visual depth avoids harsh synthetic drop shadows, relying instead on warm, color-tinted ambient glows and layered tonality that match the ivory environment:

- **Level 0 (Flat / Canvas):** Warm Ivory (`#FFF9F5`) base canvas.
- **Level 1 (Default Card Surface):** Pure White (`#FFFFFF`) cards layered with an ambient shadow tinted with Deep Romantic Burgundy: `0 4px 20px -2px rgba(130, 70, 95, 0.06), 0 2px 6px -1px rgba(130, 70, 95, 0.04)`.
- **Level 2 (Interactive Cards & Floating Modals):** Pill menus and active wellness widgets elevate slightly higher: `0 10px 30px -4px rgba(130, 70, 95, 0.10), 0 4px 10px -2px rgba(130, 70, 95, 0.05)`.
- **Level 3 (BottomSheet & Toast Prompts):** `0 16px 40px -6px rgba(56, 41, 54, 0.14)`.
- **Borders:** When borders are needed, use an ultra-soft hairline stroke: `1px solid rgba(232, 160, 181, 0.25)`. Never use cold gray borders.

## Shapes

The design system embraces an ultra-rounded, organic geometry (Level 3 - Pill / Fluid Curves) to reinforce warmth, comfort, and tenderness.

- **Buttons & Interactive Tags:** Strict full-pill contours (`border-radius: 9999px`).
- **Cards & Primary Modules:** Generous `rounded-3xl` (24px to 32px) radii create soft, cushion-like presentation trays for relationship logs, wellness timers, and reminders.
- **Nested Surface Containers:** Smaller inner modules (e.g., secondary mood tags inside cards) scale down to `rounded-2xl` (16px) to maintain harmonic concentricity.

## Components

### Buttons
- **Primary Pill:** Fill `#82465F` (Burgundy) with `#FFF9F5` (Ivory) text and a subtle warm glow on tap. Minimum height `48px` to satisfy touch target ergonomics.
- **Romantic Tint Pill:** Fill `#E8A0B5` (Soft Rose) with `#382936` (Plum) text for daily affectionate actions and hugs.
- **Secondary / Soft Pill:** Fill `#E9E3F5` (Muted Lavender) with `#382936` text for secondary choices.
- **Outlined / Ghost:** Transparent background with `1.5px` border in `#E8A0B5`, text `#82465F`.

### Chips & Mood Badges
- Height `32px` to `36px` with full pill radius. 
- Inactive state: `#FFFFFF` with hairline `#E8A0B5` border and `#382936` text.
- Active/Selected state: Tinted fill in `#E8A0B5` or `#A8BDA5` with small prefix icons (heart, flower, drop, crescent moon).

### Cards & Care Modules
- Background: `#FFFFFF` with Level 1 warm shadow.
- Border radius: `28px` (`rounded-3xl`).
- Padding: `1.25rem` to `1.5rem`.
- Header area: Playfair Display title accompanied by a micro-action (e.g., heart bookmark or reciprocal care avatar).

### Input Fields & Love Notes
- Background: `#FFFFFF` or `#FFF9F5` with `rounded-2xl` contouring.
- Border: `1.5px` solid `rgba(232, 160, 181, 0.4)`. Focus border shifts to `#82465F` with an ultra-soft rose focus ring (`0 0 0 4px rgba(232, 160, 181, 0.25)`).
- Placeholder text: Deep Plum at 40% opacity (`rgba(56, 41, 54, 0.4)`).

### Checkboxes, Radios & Switches
- **Checkboxes & Radios:** Shaped as soft rounded squares (`8px` radius) or circles, transitioning to filled `#82465F` with ivory checkmarks or heart glyphs when selected.
- **Switches:** Pill tracks with a 4px inset thumb. Active track: `#E8A0B5`; active thumb: `#82465F` or `#FFFFFF`.

### Specialized Domain Components
- **Partner Pulse Status Bar:** Pill-shaped header status card indicating current emotional energy, presence, and affection status using soft lavender and rose gradients.
- **Care Request Tile:** Split card pairing an illustrative flower/heart indicator with a gentle nudge action button (e.g., *"Cuddle time"*, *"Hydration check"*, *"Rest together"*).
- **Affirmation Scroller:** Edge-to-edge carousel using white cards with Playfair Display italic pull-quotes and sage green progress dots.