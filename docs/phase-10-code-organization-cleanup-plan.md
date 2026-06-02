# Phase 10D Code Organization Cleanup Plan

This plan is documentation only. It prepares a beginner-safe cleanup path for Orbit Planner before any refactor is attempted.

Do not refactor in Phase 10D. Do not move files, rename files, change app behavior, change Room schema, change the database version, add dependencies, add Navigation Compose, add Hilt, or add new features.

## 1. Current Code Organization Summary

Orbit Planner currently has a small local Android MVP with most app behavior wired through simple files.

### Main App Entry Point

- `app/src/main/java/com/ashfaq/orbitplanner/MainActivity.kt` is the main entry point.
- It creates the notification channel.
- It reads Settings preferences.
- It schedules or cancels the daily reminder.
- It creates the Room database, repository, and `TaskViewModel`.
- It owns the temporary tab switching state.
- It collects Today, Week, Month, Year, and Rescue task flows.
- It maps `TaskEntity` values into screen preview models.
- It contains several date/time helper functions.

This is understandable for the MVP, but it is the first file to watch as the app grows.

### Screen Files

Current screen files live in:

- `app/src/main/java/com/ashfaq/orbitplanner/ui/screens/`

Important current files include:

- `TodayScreen.kt`
- `WeekScreen.kt`
- `MonthScreen.kt`
- `YearOrbitScreen.kt`
- `RescueModePlaceholderScreen.kt`
- `SettingsPlaceholderScreen.kt`
- `TaskViewModel.kt`
- `TaskViewModelFactory.kt`

The screen files are beginner-readable, but several are now large because each file contains the full screen plus many small private composables.

### Shared UI Components

Shared UI components currently live in:

- `app/src/main/java/com/ashfaq/orbitplanner/ui/components/OrbitBottomBar.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/ui/components/OrbitCard.kt`

`OrbitBottomBar.kt` is reused by the main screens. `OrbitCard.kt` is a simple reusable card pattern that may be useful as the design system grows.

### Room Database And Data Files

Room and local data files currently live in:

- `app/src/main/java/com/ashfaq/orbitplanner/data/local/TaskEntity.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/data/local/TaskDao.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/data/local/OrbitPlannerDatabase.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/data/local/DatabaseProvider.kt`

The current Room database version is `1`. Phase 10D must not change it.

The `data/model/` folder currently exists for future non-Room models, but it is not actively used yet.

### Repository And ViewModel Flow

The current data flow is:

`TaskDao` -> `TaskRepository` -> `TaskViewModel` -> `MainActivity` -> screen preview models -> composables

Important files:

- `app/src/main/java/com/ashfaq/orbitplanner/data/repository/TaskRepository.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/ui/screens/TaskViewModel.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/ui/screens/TaskViewModelFactory.kt`

This is good enough for the MVP. A future cleanup can make the UI model boundary clearer without changing behavior.

### Notification And WorkManager Files

Notification files currently live in:

- `app/src/main/java/com/ashfaq/orbitplanner/notifications/NotificationHelper.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/notifications/PendingTaskReminderScheduler.kt`
- `app/src/main/java/com/ashfaq/orbitplanner/notifications/PendingTaskReminderWorker.kt`

There is also an older placeholder folder:

- `app/src/main/java/com/ashfaq/orbitplanner/notification/`

The active code uses `notifications/`, not `notification/`.

### Settings And Shared Preferences Files

Settings preference storage currently lives in:

- `app/src/main/java/com/ashfaq/orbitplanner/settings/AppSettingsStore.kt`

Settings UI currently lives in:

- `app/src/main/java/com/ashfaq/orbitplanner/ui/screens/SettingsPlaceholderScreen.kt`

Settings behavior is still simple: it stores the daily pending-task reminder preference in `SharedPreferences`.

## 2. Code Organization Risks

These are risks to watch before the app grows further.

### MainActivity Becoming Too Large

`MainActivity.kt` currently handles app startup, notification setup, settings preference access, manual tab switching, Flow collection, date ranges, Rescue target dates, and mapping Room entities into UI preview models.

This is acceptable for learning and MVP speed, but future work may make it hard for a beginner to follow.

### Screen Files Becoming Too Large

Some screen files are already large:

- `TodayScreen.kt`
- `MonthScreen.kt`
- `YearOrbitScreen.kt`
- `WeekScreen.kt`
- `RescueModePlaceholderScreen.kt`
- `SettingsPlaceholderScreen.kt`

Large files are not automatically bad, but they can become harder to review when they mix screen layout, cards, chips, empty states, dialogs, and helper composables all in one place.

### UI And Business Logic Mixing

Some UI-facing decisions happen close to app wiring, especially in `MainActivity.kt` and `TaskViewModel.kt`.

Examples to watch:

- Task completion mapping.
- Date range decisions.
- Rescue target dates.
- Notification permission status copy.
- UI preview model mapping.

These should stay simple until a small cleanup branch is approved.

### Date And Time Helper Duplication

Date logic appears in more than one area:

- `MainActivity.kt` calculates today, tomorrow, weekend, week, month, and year ranges.
- `PendingTaskReminderWorker.kt` calculates today's start time for notification checks.
- `PendingTaskReminderScheduler.kt` calculates the next reminder window.

This creates a future risk where Today, Rescue, and notifications disagree about dates.

### Notification And Settings Logic Mixing With UI

Settings UI reads status values from `MainActivity.kt`, while `MainActivity.kt` also controls the schedule update.

This works for the MVP, but later changes could become confusing if Settings UI, Settings storage, notification permission checks, and WorkManager scheduling become mixed together.

### Direct Database Or Entity Usage Leaking Into UI

`TaskViewModel` exposes `Flow<List<TaskEntity>>`, and `MainActivity.kt` maps `TaskEntity` into UI preview models.

This is simple and beginner-friendly for now, but future screens may become easier to maintain if UI-facing models are kept separate from Room entities in a consistent place.

### Future Navigation Compose Or Hilt Difficulty

The app currently uses temporary manual tab state in `MainActivity.kt`, and dependencies are created manually.

That is correct for the current phase. The risk is that adding Navigation Compose or Hilt later will be harder if more app wiring continues to collect in `MainActivity.kt`.

Do not add Navigation Compose or Hilt in Phase 10D.

## 3. Beginner-Safe Cleanup Opportunities

These are small future cleanup ideas only. Do not implement them in Phase 10D.

### Extract Repeated Date Helpers Into Util Files

Possible future file:

- `app/src/main/java/com/ashfaq/orbitplanner/util/DateTimeHelpers.kt`

Possible helpers:

- Start of today.
- Start of tomorrow.
- Next Saturday.
- Current week range.
- Current month range.
- Current year range.
- User-facing date labels.

Keep the first cleanup small. Start with duplicated helpers used by Today, Rescue, and notifications.

### Keep UI Models Separate From Room Entities Where Useful

The app already has screen preview data classes such as `TodayTaskPreview`, `WeekTaskPreview`, `MonthTaskPreview`, `YearOrbitSummary`, and `RescueTaskPreview`.

A future cleanup could move mapping functions out of `MainActivity.kt` into a small mapper file, but only if it makes the code easier to read.

Possible future file:

- `app/src/main/java/com/ashfaq/orbitplanner/ui/screens/TaskPreviewMappers.kt`

Do not create it until a cleanup branch is approved.

### Keep Screen-Level State Handling Clear

Screen files should keep local UI state close to the composable when the state is only visual, such as dialog open/closed state or temporary text field values.

Business decisions should stay in ViewModel or repository code when they become more than simple UI display.

### Keep Notification Scheduling Separate From Settings UI

Future settings changes should keep these responsibilities separate:

- Settings screen shows and changes the user's preference.
- Settings storage saves the preference.
- Notification helper checks permission/channel state.
- Reminder scheduler handles WorkManager scheduling.
- Worker checks local tasks and sends one gentle reminder.

This avoids putting scheduling logic directly inside the Settings composable.

### Keep Shared Components Reusable And Simple

Shared components should stay small:

- Bottom navigation.
- Common cards.
- Common chips only when repeated enough to be worth extracting.

Avoid creating a large design system too early. Extract only when reuse is real.

### Add Small Comments Where Beginner Readability Improves

Comments should explain why a small piece exists, not repeat what the code already says.

Good future comment examples:

- Why manual tab state exists until Navigation Compose is approved.
- Why WorkManager timing is flexible.
- Why Rescue Mode uses gentle copy.

### Organize Future Docs And QA Notes Clearly

Future docs should stay in `docs/` and keep the phase number in the filename.

Recommended pattern:

- `docs/phase-10-final-local-demo-review.md`
- `docs/phase-10-date-time-helper-cleanup-plan.md`

## 4. What Should Not Be Refactored Now

Do not refactor immediately in Phase 10D.

Avoid:

- Adding Navigation Compose.
- Adding Hilt or dependency injection.
- Rewriting the architecture.
- Changing Room schema.
- Changing database version.
- Moving many files at once.
- Renaming many files at once.
- Changing task CRUD behavior.
- Changing Today, Week, Month, or Year behavior.
- Changing Rescue Mode behavior.
- Changing notification scheduling behavior.
- Changing Settings behavior.
- Adding login.
- Adding cloud sync.
- Adding account systems.
- Adding analytics.
- Adding exact alarms.
- Adding advanced recurring tasks.
- Adding per-task reminders.

The safest next step is a demo review, not a refactor.

## 5. Recommended Future Cleanup Phases

These phases are optional future work. Each should happen only after review and approval.

### Phase 10D1: Date And Time Helper Cleanup

Goal:

- Move repeated date helper logic into one simple util file.

Rules:

- No behavior change.
- No schema change.
- No notification timing change.
- Add focused manual checks for Today, Rescue, Week, Month, Year, and notification pending-count behavior.

### Phase 10D2: MainActivity Readability Cleanup

Goal:

- Make `MainActivity.kt` easier to scan.

Possible future changes:

- Move preview model mapping into a small mapper file.
- Keep temporary tab switching clearly labeled.
- Keep startup setup readable.

Rules:

- No Navigation Compose yet.
- No Hilt yet.
- No behavior change.

### Phase 10D3: Screen File Readability Cleanup

Goal:

- Make large screen files easier to review.

Possible future changes:

- Extract repeated card/chip composables only when they are clearly reused.
- Keep each screen beginner-readable.
- Avoid moving too many composables in one commit.

Rules:

- One screen or one component family per cleanup branch.
- No visual redesign.
- No feature changes.

### Phase 10D4: UI Model Boundary Review

Goal:

- Decide where UI preview models and mapping functions should live.

Possible future changes:

- Keep Room entities in the data layer.
- Keep UI models close to screens or in a clearly named UI model file.
- Avoid passing `TaskEntity` directly deeper into composables.

Rules:

- No database change.
- No DAO change.
- No behavior change.

### Phase 10D5: Notification And Settings Boundary Review

Goal:

- Keep Settings storage, notification permission checks, scheduling, and worker behavior easy to understand.

Possible future changes:

- Keep `AppSettingsStore` focused on preferences.
- Keep `NotificationHelper` focused on channel and permission helpers.
- Keep `PendingTaskReminderScheduler` focused on WorkManager scheduling.
- Keep Settings composables focused on display and user actions.

Rules:

- No exact alarms.
- No per-task reminders.
- No custom reminder time.
- No scheduling behavior change unless a separate phase explicitly approves it.

## 6. Acceptance Checklist For Any Future Cleanup

Any future cleanup branch should pass this checklist:

- Build passes.
- No Room schema change.
- No database version change.
- No task behavior change.
- No Rescue Mode behavior change.
- No notification behavior change.
- No dependency change unless explicitly approved.
- Manual QA checklist still passes.
- Cleanup is small and easy to review.
- Commit touches only related files.
- No login, cloud sync, analytics, Hilt, Navigation Compose, exact alarms, advanced recurring tasks, or per-task reminders are added.

## 7. Final Recommendation

Do not refactor immediately.

Finish Phase 10E final local demo review first, then decide whether a small cleanup branch is needed.

Reason:

- The app is now stable enough for a local MVP review.
- Refactoring before the final demo review could hide simple QA issues.
- A final demo review will show which cleanup would actually help most.
- Beginner safety is better served by one small, evidence-based cleanup branch later.
