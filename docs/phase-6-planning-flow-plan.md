# Phase 6 Planning Flow Plan

This plan describes a beginner-safe path for connecting daily tasks to Week, Month, and Year Orbit. Phase 6A is planning only and does not implement Week, Month, or Year database logic.

## 1. Current Completed Foundation

- Static UI screens exist for Today, Week, Month, Year Orbit, Rescue Mode, and Settings.
- Room database foundation exists with `TaskEntity`, `TaskDao`, `OrbitPlannerDatabase`, and `DatabaseProvider`.
- `TaskRepository` wraps local task DAO access.
- `TaskViewModel` exposes task data and beginner-readable task actions.
- Today reads Room tasks through `TaskViewModel`.
- The Add Task dialog saves a basic task to Room.
- Complete/uncomplete and delete actions update Room from Today.
- Temporary sample seed data exists for local testing and is guarded by a task count check.

## 2. Goal Of Phase 6

Phase 6 should make Week, Month, and Year Orbit gradually reflect real local task data.

The goal is not to build advanced planning analytics yet. The goal is to help the app show a simple, honest connection:

`Daily Task -> Weekly Mission -> Monthly Focus -> Yearly Goal`

Phase 6 should:

- Keep the app offline-first.
- Keep changes small and beginner-friendly.
- Reuse the existing Room, repository, and ViewModel path.
- Let Week, Month, and Year screens show real task summaries over time.
- Avoid overbuilding goal analytics before the task planning flow is stable.

## 3. Existing TaskEntity Fields And Planning Use

The current `TaskEntity` can support the first planning flow without a schema change:

- `plannedDate`: can decide which day, week, or month a task belongs to.
- `orbitLevel`: can label a task as `"Daily"`, `"Weekly"`, `"Monthly"`, or `"Yearly"` later.
- `linkedMission`: can show how a task connects to a weekly mission or larger focus.
- `energyLabel`: can help Week and Month show gentle workload or energy summaries.
- `rescueState`: can stay unused for Phase 6 and support Rescue Mode later.
- `isCompleted`: can power completion counts and simple progress summaries.
- `createdAt`: can keep tasks ordered and act as a fallback when `plannedDate` is missing.

Important: Phase 6 should try to use these fields first. A schema change should happen only after a separate approved plan.

## 4. Recommended Phase 6 Breakdown

- Phase 6A: Create this planning document.
- Phase 6B: Let Week screen read tasks from Room.
- Phase 6C: Let Month screen read tasks from Room.
- Phase 6D: Let Year Orbit read simple task and month summaries from Room.
- Phase 6E: Remove or disable temporary sample seed logic.
- Phase 6F: Review the full planning flow before adding more advanced planning behavior.

This keeps each phase small enough to test and explain.

## 5. Week Screen Approach

Phase 6B should start with Week because it is closest to Today.

Recommended approach:

- Read tasks for the current week from Room.
- Group tasks by day in simple UI data outside the composable.
- Show real tasks if any tasks exist for the week.
- Keep the static fallback if no weekly tasks exist.
- Keep the current Calm Orbit Command visual style.
- Keep Add, complete, and delete actions on Today only unless explicitly approved.

The Week screen should not directly import `TaskEntity`, `TaskDao`, or database classes. It should receive a small UI model from `MainActivity` or a helper mapper, similar to `TodayTaskPreview`.

## 6. Month Screen Approach

Phase 6C should build on the same pattern as Week.

Recommended approach:

- Read tasks for the current month from Room.
- Use `plannedDate` when it exists.
- Use `createdAt` only as a fallback if a task does not have `plannedDate`.
- Show a simple monthly task summary first.
- Avoid complex calendar logic at the beginning.
- Keep weekly mission preview cards simple and data-backed later.
- Keep the static fallback when no monthly data exists.

The first Month data version should answer simple questions:

- How many tasks are planned this month?
- How many are complete?
- Which focus or mission labels appear most often?

## 7. Year Orbit Approach

Phase 6D should keep Year Orbit visually familiar while adding simple data.

Recommended approach:

- Keep the large orbit visual.
- Keep 4 quarter arcs as the main structure.
- Keep 12 month nodes as secondary detail.
- Use monthly task counts and completion counts.
- Show simple completion percentages only when the data is easy to explain.
- Avoid advanced analytics, scoring, predictions, or goal progress math at first.

Year Orbit can start with summaries such as:

- Tasks planned this year.
- Tasks completed this year.
- Most active month.
- Simple quarter completion count.

## 8. Data And Query Needs

Minimal DAO additions can support Week, Month, and Year without changing the schema:

- `getTasksBetween(startDate: Long, endDate: Long)`
- Maybe `getCompletedTasksBetween(startDate: Long, endDate: Long)`

The first query can probably be enough:

```kotlin
@Query(
    "SELECT * FROM tasks WHERE plannedDate BETWEEN :startDate AND :endDate ORDER BY plannedDate ASC, createdAt DESC"
)
fun getTasksBetween(startDate: Long, endDate: Long): Flow<List<TaskEntity>>
```

Repository and ViewModel should wrap this query with beginner-readable methods.

Date range calculation should stay outside composables where possible. A small helper can be added later if `MainActivity` starts getting too large.

## 9. Risks

- Date handling can become tricky because days, weeks, months, and years need clear start and end times.
- Temporary sample seed tasks can confuse testing once Week and Month read real data.
- Year Orbit can become overbuilt too early if it tries to calculate goal analytics before goals exist.
- Week and Month screens can become too stateful if they manage data transformation inside composables.
- `MainActivity` can become too large if all mapping and date range work stays there.

## 10. What Phase 6 Must Not Include Yet

- No notifications.
- No WorkManager.
- No Rescue logic.
- No advanced recurring tasks.
- No cloud sync.
- No login.
- No complex analytics.
- No schema change unless explicitly approved.
- No new Room entities unless explicitly approved.
- No Navigation Compose unless explicitly approved for that phase.
- No Hilt or dependency injection.

## 11. Acceptance Checklist For Phase 6B

- Week screen reads Room tasks through the existing ViewModel path.
- Week screen does not directly access database classes.
- Build passes.
- No forbidden features are added.
- Today still reads Room tasks.
- Add Task still saves to Room.
- Complete/uncomplete still updates Room.
- Delete still removes tasks from Room.
- Bottom nav still switches between Today, Week, Month, Year, and Settings.
- Code remains beginner-readable.
