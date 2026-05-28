# Orbit Planner - High Fidelity Identity Screens

Phase 3A Step 8 research and design planning only.

Status:
- High-fidelity Figma UI screens were created successfully.
- The screens use the approved visual direction: Calm Orbit Command.
- The screens apply the approved wireframe decisions from Phase 3A Step 7.
- This is final UI design for review, not Android implementation.

Figma file:

https://www.figma.com/design/P7qwhn62eqY4UZz4E5qmib

Figma file/page name:

`Orbit Planner - High Fidelity Identity Screens`

Approved wireframe file:

https://www.figma.com/design/QRnnilawtju1kFgJQvRyPG

Approved visual direction board:

https://www.figma.com/board/NVV5LESqiCptV0UbwToyOZ

## Screens Created

1. Today Screen - High Fidelity
2. Rescue Mode - High Fidelity
3. Year Orbit - High Fidelity
4. Design System Notes

All three app screens use a 360 x 800 Android phone frame.

## Visual Style Summary

Direction:

`Calm Orbit Command`

Style:
- Dark premium Material 3 base
- Deep navy and near-black background
- Calm cyan primary accent
- Soft violet orbit accent
- Warm amber energy accent
- Gentle mint success accent
- Rounded cards
- Restrained orbit/ring glow
- Clear readable typography
- Minimal, futuristic, friendly, and emotionally supportive

Originality rule:

The UI is original Orbit Planner design. Research references were used only for private inspiration and pattern learning.

## Color Tokens

| Token | Hex | Use |
| --- | --- | --- |
| Background | `#08111F` | Main app background |
| Deep background | `#050A14` | Dark vignette and depth |
| Surface/card | `#121D2E` | Main cards and panels |
| Surface raised | `#18263A` | Chips and raised controls |
| Outline | `#2B3C55` | Card borders and separators |
| Text primary | `#EEF7FF` | Main readable text |
| Text secondary | `#A9B8CC` | Supporting text and metadata |
| Text muted | `#8AA0BB` | Small labels and inactive nav after readability revision |
| Primary accent | `#2DD4F8` | Primary action, active nav, progress |
| Secondary accent | `#A78BFA` | Orbit highlights and Rescue tone |
| Success | `#5EEAD4` | Completed or steady state |
| Energy | `#FBBF24` | Energy and gentle attention |
| Quiet danger | `#FB7185` | Delete/cancel as quiet destructive action |

## Component Decisions

### Navigation

Bottom navigation excludes Rescue as a permanent tab.

Tabs shown:
1. Today
2. Week
3. Month
4. Year
5. Settings

Rescue Mode is opened conditionally from Today when unfinished tasks exist.

### Today Screen

Decisions applied:
- Mission appears before progress.
- Progress ring is smaller and supports the mission.
- Daily -> Weekly -> Monthly -> Yearly chain is visible but subtle.
- Task cards show only task title, linked mission/goal, and energy/status.
- Conditional Rescue Mode card appears when unfinished tasks exist.
- Add task has a normal action affordance and a future FAB.
- Empty state should show a visible `Add your first task` card/button.

### Rescue Mode

Decisions applied:
- Screen uses supportive copy: `Let's rescue these tasks.`
- Supporting copy says: `Nothing is lost. Choose what happens next.`
- It is framed as a gentle recovery queue opened from Today.
- Action chips are included for:
  - Do today
  - Move tomorrow
  - Move weekend
  - Convert to goal
  - Delete/cancel
- Empty state note is included.
- Tone avoids shame or failure language.

### Year Orbit

Decisions applied:
- 4 quarter arcs are the main high-level structure.
- 12 month nodes are secondary detail.
- Selected month focus card links the month to weekly mission work.
- Progress/rescue insight explains that rescued tasks still count toward the larger orbit.
- Bottom navigation excludes Rescue and highlights Year.

## Typography Notes

Use Inter:
- Screen title: 24 px / Bold
- Major card title: 14-19 px / Semi Bold or Bold
- Metadata: 9-11 px / Regular
- Bottom navigation labels: 9.5 px / Medium or Semi Bold
- Month node labels: 8.5 px / Semi Bold
- Chip/status labels: 9 px minimum / Medium or Semi Bold

Keep letter spacing at 0.

## Card Style Notes

Cards use:
- 18-24 px corner radius
- Deep navy surfaces
- Subtle outlines
- Soft shadows
- Glow only for orbit/progress/rescue emphasis

Avoid:
- Making every card glow
- Nested card stacks
- Overcrowded analytics
- Heavy sci-fi decoration

## Button And Chip Notes

Primary actions:
- Cyan fill
- Dark text
- Used for clear positive actions

Secondary actions:
- Dark raised surface
- Subtle outline
- Muted text

Rescue Mode:
- `Do today` is the strongest action.
- Delete/cancel stays quiet and visually secondary.

## Progress And Orbit Notes

Today:
- Progress supports the mission.
- The progress ring should not dominate the first view.

Year Orbit:
- Quarter arcs communicate the large planning structure.
- Month nodes communicate detail.
- The orbit visual should feel meaningful, not decorative noise.

## Accessibility Reminders

Before implementation:
- Check contrast on all dark surfaces.
- Keep touch targets at least 48 dp.
- Do not rely only on color for energy/status.
- Keep metadata readable.
- Avoid tiny text in final Android UI.
- Test small-screen legibility.
- Use the revised muted text color `#8AA0BB` for helper text when `#6F829E` feels too low contrast.

## Android / Compose Notes For Later

These are notes only, not implementation instructions for this phase.

Likely future Compose mapping:
- `Scaffold`
- `NavigationBar`
- `Card`
- `AssistChip` / `FilterChip`
- `FloatingActionButton`
- `Canvas` for orbit arcs and progress rings
- Material 3 color scheme based on the tokens above

Do not implement Android UI until final Figma UI approval.

## Open Review Questions

1. Does the Today screen feel mission-first enough?
2. Is the Rescue Mode tone gentle and useful, or too soft?
3. Should the Year Orbit visual stay this prominent on the first version?
4. Are the 5 bottom navigation tabs correct for MVP review?
5. Should the cyan/violet glow be reduced or kept as shown?
6. Are the task cards simple enough for beginner users?

## High-Fidelity UI Review

Review status:

Completed for Phase 3A Step 9.

Screens reviewed:
1. Today Screen - High Fidelity
2. Rescue Mode - High Fidelity
3. Year Orbit - High Fidelity
4. Design System Notes

Checklist result:
- Today screen is mission-first.
- The progress ring supports the mission and does not dominate the screen.
- Rescue Mode feels gentle and useful, not shameful.
- Year Orbit clearly uses 4 quarter arcs plus 12 month nodes.
- Bottom navigation is correct for MVP: Today, Week, Month, Year, Settings.
- Rescue is correctly excluded from permanent bottom navigation.
- Task cards are simple enough and use minimal metadata.
- Dark color contrast is generally strong based on the defined tokens.
- Glow is restrained and used mainly around progress/orbit/rescue identity moments.
- UI feels original and memorable.
- UI is realistic to build later with Jetpack Compose and Material 3.

Issues found:
- Minor readability risk: several helper labels are very small at 7-8 px, especially bottom navigation labels, status chips, and month node initials.
- Muted text contrast is acceptable but close to the lower edge on some dark surfaces; it should be checked again during final implementation.

Recommended changes:
1. Increase bottom navigation labels from 8 px to around 9-10 px.
2. Increase month node labels from 7 px to around 8-9 px, or rely more on larger selected-month detail.
3. Keep chip/status labels at 9 px or larger where possible.
4. During Android implementation, use proper Material 3 touch targets even if the Figma visual icons look compact.

Approval status:

Resolved by the Phase 3A Step 10 readability revision.

## High-Fidelity Readability Revision

Revision status:

Completed for Phase 3A Step 10.

What was revised:
- Bottom navigation labels were increased from 8 px to 9.5 px.
- Year Orbit month node labels were increased from 7 px to 8.5 px.
- Chip/status labels were checked and kept at 9 px or larger where possible.
- Remaining Today helper labels under 9 px were increased to 9 px.
- Muted helper text contrast was lifted from `#6F829E` to `#8AA0BB` where needed.
- Figma design-system notes were updated to reflect the safer minimum sizes.

Verification:
- All app frames remain 360 x 800.
- Bottom navigation remains: Today, Week, Month, Year, Settings.
- Rescue remains excluded from permanent bottom navigation.
- Today remains mission-first.
- The progress ring still supports the mission instead of dominating it.
- Rescue Mode remains conditional from Today.
- Year Orbit still uses 4 quarter arcs plus 12 month nodes.
- Task cards still use minimal metadata.
- Glow remains restrained.

Text size safety:

Text sizes are now safer for final UI review. No non-month helper label remains below 9 px, and month node labels are now 8.5 px.

Final approval recommendation:

Final UI approval is now recommended.

Reminder:

No Android implementation until the final Figma UI is approved.

## Final Approval Status

Status:

Approved for Phase 3A Step 11.

Final high-fidelity UI approved for:
1. Today Screen
2. Rescue Mode Screen
3. Year Orbit Screen

Approved direction:

`Calm Orbit Command`

Approval condition:

Android implementation may start only after final implementation notes are created for the first screen.

Next step:

Create implementation notes for the Today Screen.

Reminder:

No Android implementation until implementation notes are prepared.

## Next Step After Approval

After this final UI approval, the next design step is to add final implementation notes for the first screen to implement.

Recommended first implementation screen after approval:

1. Today Screen

Reminder:

No Android implementation until the Today Screen implementation notes are prepared.

## Safety Notes

- Android source code was not edited.
- `MainActivity.kt` was not edited.
- Theme files were not edited.
- Room, Navigation Compose, WorkManager, notifications, and real task features were not added.
- No commit or push was performed.
