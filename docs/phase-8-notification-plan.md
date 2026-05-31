# Phase 8A Pending Task Notification Plan

This plan is documentation only. It prepares a beginner-safe path for adding pending task notifications later without changing Android source code, Room schema, database version, WorkManager setup, AlarmManager setup, Navigation Compose, Hilt, or dependency injection in Phase 8A.

## 1. Current Completed Foundation

- Local Room task CRUD exists through `TaskDao`, `TaskRepository`, and `TaskViewModel`.
- Today reads real tasks from Room for the current day.
- Today can add, complete, uncomplete, and delete tasks.
- Week, Month, and Year screens read real task data from Room.
- Rescue Mode reads real overdue unfinished tasks.
- Rescue actions work:
  - Do today moves an overdue task to today.
  - Tomorrow moves an overdue task to tomorrow.
  - Weekend moves an overdue task to the next Saturday.
  - Delete uses a confirmation dialog before removing the task.
- Rescue can be opened from Today without adding Rescue to the bottom nav.
- There is no notification system yet.
- There is no notification permission flow yet.
- There is no WorkManager or AlarmManager scheduling yet.

## 2. Goal Of Notifications

Pending task notifications should gently remind the user when unfinished work needs attention.

The first version should:

- Remind the user about pending tasks.
- Support the Life Orbit planning flow by bringing the user back to Today or Rescue.
- Avoid spammy or annoying behavior.
- Use calm, non-shaming language.
- Keep scheduling simple enough for a beginner to understand and test.
- Work locally without backend, login, or cloud sync.

Good tone:

- "You have pending tasks waiting in Orbit Planner."
- "A few tasks are ready for your attention."
- "Open Orbit Planner when you are ready to continue."

Avoid tone:

- "You failed to finish your tasks."
- "You missed your goals."
- "Your backlog is overdue."

## 3. Android Notification Requirements

Android notifications need a few platform pieces before any reminders are sent.

### Runtime Permission On Android 13+

- Android 13 and newer require the `POST_NOTIFICATIONS` runtime permission.
- The app should ask only when notifications are relevant, not immediately on first launch.
- If the user denies permission, the app should still work normally.

### Notification Channel On Android 8+

- Android 8 and newer require a notification channel before showing notifications.
- A first channel could be named something like `Pending task reminders`.
- The channel should be created once during notification setup.

### PendingIntent Target Later

- A notification tap should eventually open Orbit Planner.
- The first safe target can be `MainActivity`.
- Later, if the app has approved navigation support, the tap can open Today or Rescue more directly.

### Scheduling Mechanism

- The app needs a safe way to decide when to check for pending tasks.
- Scheduling should be added in a later phase, after permission and channel setup are planned and reviewed.

## 4. Scheduling Approach Options

### Option A: WorkManager

WorkManager is designed for deferrable background work.

Pros:

- Good fit for a daily reminder that does not need exact timing.
- Handles many Android background limits better than a hand-rolled approach.
- Can run even if the app is not currently open.
- Better beginner path than exact alarm APIs for this use case.

Cons:

- Timing is not exact.
- Needs a Worker class and dependency setup.
- Notification permission still needs separate handling.

Recommendation:

- Use WorkManager for the first real scheduled pending-task reminder.
- Keep the first reminder flexible, such as "once daily in the evening", instead of promising an exact minute.

### Option B: AlarmManager

AlarmManager can schedule alarms at specific times.

Pros:

- Useful when exact timing is truly required.
- Can be appropriate for alarm-clock-style features.

Cons:

- Exact alarms add permission and policy complexity.
- More moving parts for a beginner project.
- Easier to accidentally create annoying or brittle reminders.

Recommendation:

- Do not use AlarmManager in the first notification version.
- Avoid exact alarms initially.

### Option C: Simple In-App Reminder Check Only

The app could check pending tasks only while the user is already inside the app.

Pros:

- Very simple.
- No background scheduling.
- Helpful for testing notification copy or reminder logic.

Cons:

- It does not remind the user when the app is closed.
- It is not a complete notification feature.

Recommendation:

- This can be used as a learning or debug step, but it should not be the main Phase 8 notification behavior.

### Recommended First Path

- Phase 8B: Add notification permission and channel foundation only.
- Phase 8C: Add WorkManager foundation only.
- Phase 8D: Schedule one daily pending-task reminder.
- Avoid exact alarms initially.
- Avoid per-task reminders initially.

## 5. First Notification Behavior

The first notification should be simple and gentle.

Recommended behavior:

- Send one daily reminder, not one notification per task.
- Only send it if there are incomplete tasks for today or overdue incomplete tasks.
- Keep the message short and supportive.
- Do not send repeated reminders throughout the day.
- Do not notify when there is nothing pending.

Suggested first copy:

- Title: "Orbit Planner"
- Body: "You have pending tasks waiting in Orbit Planner."

Possible later copy:

- Title: "A gentle planning check-in"
- Body: "A few tasks are ready when you are."

Do not include task titles in the first version. That keeps the notification private, simple, and less risky.

## 6. Data And Query Needs

The first notification worker needs to know whether any local tasks need attention.

Possible query needs:

- Incomplete tasks planned for today.
- Overdue incomplete tasks.
- Count of pending tasks for today and overdue tasks.

Prefer reusing existing patterns before adding new schema:

- Today already uses `getTasksForDate(todayStartMillis)`.
- Rescue already uses `getRescueCandidateTasks(beforeDate = todayStartMillis)`.
- The first reminder can use those same ideas and count incomplete tasks.

Possible future DAO methods, if needed:

```kotlin
fun getIncompleteTasksForDate(plannedDate: Long): Flow<List<TaskEntity>>
fun getOverdueIncompleteTasks(beforeDate: Long): Flow<List<TaskEntity>>
suspend fun getPendingTaskCountForReminder(todayStart: Long): Int
```

Beginner-safe guidance:

- Add only the query that the reminder actually needs.
- Do not change `TaskEntity`.
- Do not change the database version.
- Do not add reminder tables in the first notification version.

## 7. Permission UX Plan

Notifications should feel optional and helpful.

Recommended permission flow:

- Explain why notifications help before asking for permission.
- Ask only when the user enables reminders or reaches the notification setup step.
- Keep the explanation short and calm.
- If permission is denied, keep the app fully usable.
- Let future Settings controls turn reminders on or off.

Suggested explanation:

"Orbit Planner can send one gentle reminder when unfinished tasks need attention."

Important:

- Do not block task CRUD if permission is denied.
- Do not ask repeatedly after denial.
- Do not send notifications before the user has granted permission on Android 13+.

## 8. What Phase 8 Must Not Include Yet

Phase 8 should avoid:

- Spammy repeated notifications.
- Exact alarm complexity.
- Per-task reminders.
- Calendar integration.
- Cloud sync.
- Login.
- AI suggestions.
- Backend services.
- Notification action buttons.
- Complex reminder settings.
- Room schema changes unless separately planned and approved.
- Database version changes unless separately planned and approved.

## 9. Risks

- Android notification permission behavior differs by OS version.
- Battery restrictions can delay background work.
- WorkManager timing is not exact.
- Notification spam can make the app feel stressful.
- Testing notifications can be tricky on emulators and physical devices.
- Notification tap behavior can get complex if the app later adds deeper navigation.
- Date calculations must match the existing day-start timestamp behavior.

## 10. Recommended Phase 8 Breakdown

### Phase 8A: Notification Plan

- Create this documentation plan.
- Do not edit Android source code.
- Do not implement notifications.

### Phase 8B: Notification Permission And Channel Foundation

- Add the minimum notification permission and channel setup.
- Keep code isolated, likely in a small notification helper package.
- Do not send real reminders yet unless explicitly approved.
- Build and review.

### Phase 8C: WorkManager Foundation

- Add the WorkManager dependency and a beginner-readable Worker skeleton.
- Do not schedule repeated reminders until the worker foundation is reviewed.
- Build and review.

### Phase 8D: Daily Pending-Task Reminder

- Schedule one daily flexible reminder.
- Query for incomplete today or overdue tasks.
- Send one notification only when pending work exists.
- Keep notification copy supportive.
- Build and review.

### Phase 8E: Notification Review

- Review permission behavior.
- Review channel setup.
- Review WorkManager scheduling.
- Review notification copy and spam safety.
- Confirm no task CRUD, Today, Week, Month, Year, or Rescue regression.

## 11. Acceptance Checklist For Phase 8B

- Build passes.
- Notification permission code is isolated and beginner-readable.
- Notification channel code is isolated and beginner-readable.
- No notifications are sent yet unless explicitly planned.
- App works normally if notification permission is denied.
- Today Add, Complete, and Delete still work.
- Today, Week, Month, Year, and Rescue still read Room data.
- Rescue actions still work.
- No `TaskEntity` schema change.
- No database version change.
- No WorkManager scheduling yet unless Phase 8B scope is explicitly expanded.
- No AlarmManager.
- No Navigation Compose.
- No Hilt or dependency injection.
- No backend, login, cloud sync, or AI suggestions.
