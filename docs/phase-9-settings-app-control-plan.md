# Phase 9A Settings And App Control Plan

This plan is documentation only. It prepares a beginner-safe path for turning the static Settings screen into a real app-control screen without changing Android source code, notification scheduling, Room schema, database version, Navigation Compose setup, Hilt, login, cloud sync, or backend behavior in Phase 9A.

## 1. Current Completed Foundation

- Local Room task CRUD exists through `TaskDao`, `TaskRepository`, and `TaskViewModel`.
- Today can read, add, complete, uncomplete, and delete local tasks.
- Week, Month, and Year screens read real Room task data.
- Rescue Mode reads real overdue unfinished tasks.
- Rescue actions work:
  - Do today moves an overdue task to today.
  - Tomorrow moves an overdue task to tomorrow.
  - Weekend moves an overdue task to the next Saturday.
  - Delete requires confirmation before removing the task.
- Rescue opens from Today and stays out of the permanent bottom nav.
- Notification permission/channel foundation exists.
- Android 13+ `POST_NOTIFICATIONS` permission is declared.
- Android 8+ pending-task notification channel is created.
- WorkManager daily pending-task reminder exists.
- The reminder sends one gentle notification only when incomplete today or overdue tasks exist.
- Static Settings screen exists, but it does not control real app behavior yet.

## 2. Goal Of Settings Phase

Settings should become the place where the user understands and controls app behavior.

The first real Settings version should:

- Give the user control over notifications and app behavior.
- Explain notification permission and reminder state clearly.
- Keep controls simple and beginner-friendly.
- Avoid overbuilding preferences before the app needs them.
- Keep the tone calm, helpful, and non-shaming.
- Preserve the app's local-first MVP direction.

Settings should not become a large dashboard in this phase. It should answer the user's most basic questions:

- Are reminders allowed?
- Is the daily pending-task reminder enabled?
- Is my data local?
- What app am I using?

## 3. Recommended Phase 9 Breakdown

### Phase 9A: Settings Plan

- Create this planning document.
- Do not edit Android source code.
- Do not implement Settings UI yet.
- Do not change notification scheduling yet.

### Phase 9B: Settings Reads Notification Permission/Status

- Update Settings screen so it can show notification permission status.
- Keep this read-only.
- Do not add toggle behavior yet unless separately approved.
- Confirm the existing daily reminder still builds and schedules normally.

### Phase 9C: Notification Enable/Disable Toggle

- Add a simple daily reminder enabled/disabled toggle.
- Save the toggle state in a small local preference.
- Toggle off should cancel the daily reminder WorkManager job.
- Toggle on should schedule the daily reminder only when permission allows.
- If permission is denied, explain the state and avoid crashing.

### Phase 9D: Basic About/App Info Section

- Add app information such as app name and version placeholder.
- Add a short offline/local-first note.
- Add a simple data safety note.
- Avoid account, sync, or cloud language until those features are actually planned.

### Phase 9E: Settings Review

- Review notification permission display.
- Review reminder toggle behavior.
- Review WorkManager duplicate-worker safety.
- Review app info and local-data copy.
- Confirm no task CRUD, Today, Week, Month, Year, Rescue, schema, or database version regression.

## 4. Settings V1 Sections

### Notifications

Recommended content:

- Permission status:
  - Allowed
  - Not allowed
  - Not needed on this Android version
- Daily reminder status:
  - Enabled
  - Disabled
- Short supportive explanation:
  - "Orbit Planner can send one gentle reminder when unfinished tasks need attention."

Rules:

- Do not shame the user for pending work.
- Do not imply notifications are required.
- Do not repeatedly ask for permission.
- Make it clear the app still works without notifications.

### App Info

Recommended content:

- App name: Orbit Planner.
- Version placeholder, such as `Version 1.0`.
- Short MVP note, such as "Built for local planning and daily recovery."

Keep this small. Settings should not become a marketing page.

### Data Safety Note

Recommended content:

- Tasks are stored locally on the device.
- No login exists yet.
- No cloud sync exists yet.
- No backend account is used yet.

This gives the user confidence without promising future features.

## 5. Notification Toggle Behavior

This section is planning only. Do not implement the toggle in Phase 9A.

Planned Phase 9C behavior:

- Toggle off:
  - Save daily reminder preference as disabled.
  - Cancel the unique daily pending-task WorkManager job.
  - Leave notification permission unchanged.
- Toggle on:
  - If permission is allowed, save preference as enabled and schedule the daily reminder.
  - If permission is denied, show a simple explanation and do not crash.
  - If permission needs to be requested, use the approved Android 13+ permission flow.
- No custom reminder time yet.
- No multiple notification schedules yet.
- No per-task reminder controls yet.

Recommended copy:

- Enabled: "Daily pending-task reminder is on."
- Disabled: "Daily pending-task reminder is off."
- Permission denied: "Notifications are off for Orbit Planner. You can still use the app normally."

Important safety rule:

- The toggle state and Android permission state are separate. Settings should make that clear so the user is not confused.

## 6. Data And Storage Needs

Settings needs a small local place to remember whether the daily reminder is enabled.

Options:

### SharedPreferences

Pros:

- Simple for a beginner.
- Built into Android.
- Good enough for one or two small app settings.
- No Room schema change.
- No new architecture layer required.

Cons:

- Less modern than DataStore.
- Not ideal for complex settings.

### DataStore

Pros:

- Modern preference storage.
- Coroutine-friendly.
- Better long-term settings foundation.

Cons:

- Adds a new dependency and more concepts.
- More setup for a beginner.
- More code than this phase needs.

Recommendation:

- Use `SharedPreferences` for Settings v1.
- Store only simple values, such as `daily_pending_reminder_enabled`.
- Keep preference access in a small helper, such as a future `SettingsPreferences` or `NotificationSettingsPreferences`.
- Do not store settings in Room.
- Do not change `TaskEntity`.
- Do not change the database version.

This keeps the first Settings behavior easy to understand and easy to review.

## 7. What Phase 9 Must Not Include Yet

Phase 9 should avoid:

- Custom reminder times.
- Multiple notification schedules.
- Per-task reminder settings.
- Calendar integration.
- Cloud sync.
- Login.
- Backend accounts.
- Destructive reset/delete all data behavior.
- Theme customization unless later approved.
- Navigation Compose.
- Hilt or dependency injection.
- Room schema changes.
- Database version changes.
- Complex analytics.
- AI suggestions.

## 8. Risks

- Permission state and toggle state can be confused:
  - The app may have the reminder toggle on, but Android notifications may still be denied.
  - Settings should explain this calmly.
- WorkManager duplicate scheduling:
  - Future toggle-on behavior must reuse unique work names.
  - It should not create multiple daily reminder jobs.
- Settings screen becoming too large:
  - Keep v1 to Notifications, App info, and Data safety.
- Accidentally breaking notification scheduling:
  - Toggle work should use the existing scheduler/helper rather than duplicating scheduling logic.
- Asking for permission too aggressively:
  - Permission request should happen only when the user chooses notification behavior or when a phase explicitly plans it.
- Overbuilding storage:
  - SharedPreferences is enough for the first reminder toggle.

## 9. Acceptance Checklist For Phase 9B

- Build passes.
- Settings screen shows notification permission/status.
- Settings copy is calm and beginner-readable.
- No actual toggle behavior yet unless approved.
- Daily pending-task reminder still works.
- Notification channel creation still works.
- Android 13+ permission denial remains safe.
- Today Add, Complete, and Delete still work.
- Today, Week, Month, Year, and Rescue still read Room data.
- Rescue actions still work.
- No `TaskEntity` schema change.
- No database version change.
- No WorkManager scheduling change unless explicitly approved.
- No Navigation Compose.
- No Hilt or dependency injection.
- No backend, login, cloud sync, or AI suggestions.
