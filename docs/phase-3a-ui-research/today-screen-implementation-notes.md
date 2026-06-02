# Orbit Planner - Today Screen Implementation Notes

Phase 3A Step 12 documentation and implementation planning only.

Status:
- Final high-fidelity UI is approved for the Today Screen.
- Approved direction is `Calm Orbit Command`.
- This file prepares the first Android implementation pass, but it is not Android implementation.
- Android source code must not be edited until the user explicitly starts Phase 3B implementation.

Approved high-fidelity Figma file:

https://www.figma.com/design/P7qwhn62eqY4UZz4E5qmib

Approved screen inspected:

`Today Screen - High Fidelity`

## 1. Purpose

The Today Screen should communicate what matters today and why it matters in the larger Orbit Planner system.

It should show:
- Today's mission before progress or task volume.
- The Daily -> Weekly -> Monthly -> Yearly connection in a subtle, readable way.
- Simple task cards with only the information needed for the first version.
- A conditional Rescue Mode entry when unfinished tasks exist.
- A clear add task affordance.
- A bottom navigation preview for the larger app structure.

The screen should feel like a calm daily command center, not a normal to-do list. The user should understand: "This task belongs to a mission, and this mission belongs to a bigger goal."

## 2. Approved Visual Direction

Direction:

`Calm Orbit Command`

Design meaning:
- Dark premium Material 3 base.
- Calm cyan primary accent.
- Soft violet orbit and Rescue Mode accent.
- Warm amber energy accent.
- Gentle mint success accent.
- Rounded cards with subtle outlines.
- Orbit/ring identity used sparingly and meaningfully.
- Mission-first structure.

The progress ring supports the mission. It should not dominate the top of the screen.

Glow should stay restrained. Use it around the progress ring, orbit identity moments, and the conditional Rescue Mode entry only when it helps meaning.

## 3. Layout Breakdown

The approved Today Screen uses a 360 x 800 Android phone frame. Build the first static implementation from top to bottom.

### Header / Greeting

Purpose:
- Establish the screen as `Today Orbit`.
- Show the current date or a static sample date.
- Provide an add task affordance in the top-right area.

Approved Figma content:
- `Today Orbit`
- `Thu, May 28 - Good evening`
- Header add button with a plus symbol.

Implementation note:
- Use static text first.
- The plus button can be visual only in the first implementation.
- Do not wire any click behavior yet unless the user explicitly approves it later.

### Daily / Weekly / Monthly / Yearly Chain

Purpose:
- Make the app's core differentiator visible without overwhelming the screen.

Approved Figma content:

`Daily -> Weekly -> Monthly -> Yearly`

Implementation note:
- Keep this row subtle.
- It should look like a small connection label, not a main call-to-action.

### Today Mission Card

Purpose:
- Make the screen mission-first.
- Explain what the user is focusing on today.
- Connect the daily mission to weekly and monthly context.

Approved Figma content:
- Label: `TODAY MISSION`
- Title: `Build portfolio foundations`
- Copy: `Small focused work that moves this month's career goal forward.`
- Chip: `Weekly: Finish portfolio`
- Chip: `May focus`

Implementation note:
- This card should appear before the task list.
- Keep copy short and supportive.
- Use static sample values in the first implementation.

### Weekly Mission / Monthly Focus Connection

Purpose:
- Show that today's work is not isolated.
- Make the Life Orbit system understandable from the first screen.

Implementation note:
- Use chips or small labels inside the Today Mission card.
- Keep the chips readable at 9 px minimum in design terms.
- Do not add data models for missions or goals yet.

### Progress Ring Support Area

Purpose:
- Give gentle progress feedback without turning the screen into an analytics dashboard.

Approved Figma content:
- Progress value: `68%`
- Progress note: `steady`

Implementation note:
- The progress ring can be a simple static circular indicator first.
- If a custom drawn ring is too much for the first pass, use a simple static layout that visually suggests the ring.
- Do not calculate real progress yet.

### Task List Section

Purpose:
- Show today's tasks in a calm, minimal way.

Approved Figma content:
- Heading: `Today tasks`
- Helper text: `Minimal cards: title, link, energy/status`
- Inline action: `+ Add task`

Task card examples:
- `Draft project summary`
- `Mission: Portfolio foundations`
- Status: `Normal`
- `Review Kotlin notes`
- `Goal: Android career growth`
- Status: `Low`

Implementation note:
- Task cards should show minimal metadata only:
  - Task title
  - Linked mission or goal
  - Energy/status label
- Do not show description, date, time, priority, repeat, database id, or advanced controls in the first static layout.

### Rescue Mode Conditional Card

Purpose:
- Show that Orbit Planner helps users recover unfinished work without guilt.

Approved Figma content:
- `Rescue Mode available`
- `1 unfinished task can be moved gently.`
- Label: `Conditional`

Implementation note:
- Rescue Mode is not a permanent bottom navigation tab.
- The card appears on Today only when unfinished tasks exist in the future app.
- In the first implementation, it may be shown as static sample content to communicate the approved design.
- Do not implement overdue detection yet.
- Do not implement Rescue Mode actions yet.

### Add Task Action / Future FAB

Purpose:
- Make adding work feel easy and reachable.

Approved Figma content:
- Header plus button
- Inline `+ Add task` chip
- Future FAB plus button
- Empty-state note: `Empty state: show Add your first task card/button`

Implementation note:
- Normal usage can later use a FAB.
- Empty state should later show a visible `Add your first task` card or button.
- In the first static implementation, add actions can be visual only.
- Do not implement real task creation yet.

### Bottom Navigation

Purpose:
- Preview the MVP app structure.

Approved bottom navigation:
- Today
- Week
- Month
- Year
- Settings

Implementation note:
- Rescue must not appear as a permanent bottom navigation tab.
- If Navigation Compose is not added yet, the bottom navigation should be visual/static only.
- Do not add Navigation Compose for this first Today Screen pass.

## 4. Component Mapping For Future Compose Implementation

Use beginner-safe component names. Keep each component small and readable.

Suggested components:
- `TodayScreen`
- `TodayHeader`
- `MissionSummaryCard`
- `OrbitProgressCard`
- `TaskPreviewCard`
- `RescueEntryCard`
- `OrbitBottomNavigation`
- `AddTaskAction`

Optional helpers only if they keep the code simpler:
- `OrbitChainRow`
- `StatusChip`
- `SectionHeader`

Beginner rule:
- Do not create too many tiny abstractions at once.
- Start with the obvious components above.
- Extract smaller helpers only after the static layout is easy to read.

## 5. Static Data Only For First Implementation

The first Android implementation of this screen should be static UI only.

Do not add:
- Database
- Room
- Real task saving
- ViewModel
- Repository
- Navigation actions
- WorkManager
- Notifications
- Real task CRUD
- Overdue-task detection
- Rescue Mode behavior

Use static sample content only:
- Static greeting/date.
- Static mission.
- Static weekly mission and monthly focus chips.
- Static 68 percent progress.
- Static sample task cards.
- Static conditional-looking Rescue Mode card.
- Static bottom navigation.

The first implementation goal is visual alignment with the approved Figma Today Screen, not app behavior.

## 6. Color Tokens

Use the approved high-fidelity tokens.

| Token | Hex | Use |
| --- | --- | --- |
| Background | `#08111F` | Main app background |
| Deep background | `#050A14` | Dark vignette and depth |
| Surface/card | `#121D2E` | Main cards and panels |
| Surface raised | `#18263A` | Chips and raised controls |
| Outline | `#2B3C55` | Card borders and separators |
| Text primary | `#EEF7FF` | Main readable text |
| Text secondary | `#A9B8CC` | Supporting text and metadata |
| Text muted | `#8AA0BB` | Helper text and inactive nav labels |
| Primary accent | `#2DD4F8` | Primary action, active nav, progress |
| Secondary accent | `#A78BFA` | Orbit highlights and Rescue tone |
| Success | `#5EEAD4` | Completed or steady state |
| Energy | `#FBBF24` | Energy and gentle attention states |
| Quiet danger | `#FB7185` | Delete/cancel as quiet destructive action later |

Implementation note:
- Align these with existing theme tokens only after inspecting the current theme in Phase 3B.
- Do not edit theme files during this Step 12 documentation task.

## 7. Typography Guidance

Approved typography direction:
- Screen title: 24 px / Bold
- Major card title: 14-19 px / Semi Bold or Bold
- Metadata: 9-11 px / Regular
- Bottom nav labels: 9.5 px / Medium or Semi Bold
- Chip/status labels: 9 px minimum

Today Screen Figma examples:
- `Today Orbit` uses large bold title treatment.
- `Build portfolio foundations` uses strong card-title treatment.
- Task titles use compact semi-bold text.
- Metadata and helper text stay readable on dark cards.
- Bottom nav labels were increased during readability revision.

Compose note:
- Use Material 3 typography where possible.
- Keep text readable on a 360 x 800 phone layout.
- Avoid very tiny text in Compose. Treat 9-10 sp as the practical lower edge for small labels.
- Keep letter spacing at 0 unless the existing theme already defines otherwise.

## 8. Android / Compose Guardrails

For the first Today Screen implementation:
- Use the existing Compose setup.
- Prefer Material 3 components.
- Do not add new dependencies unless absolutely necessary.
- Do not add Navigation Compose yet.
- Do not add Room yet.
- Do not add WorkManager yet.
- Do not add notifications yet.
- Do not add real CRUD yet.
- Do not add backend, login, or cloud sync.
- Keep code beginner-readable.
- Keep comments short and useful.
- Use the existing package and folder structure after inspecting the project in Phase 3B.

Likely Compose building blocks later:
- `Scaffold`
- `Card`
- `Surface`
- `AssistChip` or simple custom chips
- `FloatingActionButton` for the future add action
- `NavigationBar` visually, only if available in the existing setup
- Simple `Canvas` only if needed for the static progress ring

Guardrail:
- Do not let the progress ring or custom drawing make the first implementation hard to understand.
- If needed, approximate the ring first and improve later after review.

## 9. Future Implementation Order

Recommended order for Phase 3B Today Screen implementation:

1. Theme token alignment
2. Reusable static components
3. `TodayScreen` static layout
4. Preview/build check
5. Manual visual check against approved Figma
6. Commit only after approval

Important:
- Explain which Android files will change before making Phase 3B edits.
- Keep the first implementation small.
- Run the build/check after implementation.
- Do not commit until the user approves the implementation result.

## 10. Acceptance Checklist

The future Today Screen implementation should pass this checklist:

- App still builds.
- No new dependencies.
- No Room.
- No Navigation Compose.
- No WorkManager.
- No notification logic.
- No real task CRUD.
- No ViewModel or repository yet.
- UI uses static sample content.
- Looks close to the approved Figma Today Screen.
- Today is mission-first.
- Progress ring supports the mission and does not dominate.
- Daily -> Weekly -> Monthly -> Yearly chain is visible and subtle.
- Task cards show title, linked mission/goal, and energy/status only.
- Rescue card is static/conditional-looking only.
- Rescue is not a permanent bottom navigation tab.
- Bottom nav is visual/static only if navigation is not added yet.
- Add task affordance is visual only.
- Empty-state `Add your first task` behavior is planned but not functional yet.
- Android source code is edited only after the user starts Phase 3B implementation.
- No commit or push happens without explicit approval.

## Step 12 Safety Confirmation

For this documentation step:
- Android source code was not edited.
- `MainActivity.kt` was not edited.
- Theme files were not edited.
- Room, Navigation Compose, WorkManager, notifications, and real task features were not added.
- No Android UI was created.
- No commit or push was performed.
