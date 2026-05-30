# Phase 7 Rescue Mode Logic Plan

This plan describes a beginner-safe path for turning the existing static Rescue Mode screen into a real local task recovery flow. Phase 7A is planning only and does not implement Rescue logic.

## 1. Current Completed Foundation

- Room database exists with `TaskEntity`, `TaskDao`, `OrbitPlannerDatabase`, and `DatabaseProvider`.
- `TaskRepository` wraps the DAO.
- `TaskViewModel` sits between UI and repository.
- Today can read real Room tasks.
- Today can add a task with a simple dialog.
- Today can mark tasks complete or incomplete.
- Today can delete tasks.
- Week, Month, and Year Orbit can read Room task data through UI models.
- Temporary sample seed logic is disabled, so deleted sample tasks do not come back automatically.
- Static Rescue Mode UI exists and already uses gentle, non-shaming copy.

## 2. Goal Of Rescue Mode

Rescue Mode should help the user recover unfinished work without guilt.

The first real version should:

- Find unfinished overdue tasks.
- Show those tasks in a calm recovery queue.
- Let the user choose what happens next.
- Avoid automatic destructive actions.
- Keep the tone supportive, not blaming.

Good copy direction:

- "Let's rescue these tasks."
- "Nothing is lost. Choose what happens next."
- "Move unfinished work without pressure."

Avoid copy like:

- "You failed."
- "Overdue because you missed it."
- "Fix your backlog."

## 3. Existing TaskEntity Fields And Gaps

Current `TaskEntity` fields:

- `id`: identifies the task for updates and deletes.
- `title`: shows the task name in Rescue Mode.
- `linkedMission`: can show the bigger mission or focus connection.
- `energyLabel`: can help keep the task card tone useful and familiar.
- `isCompleted`: decides whether the task still needs rescue.
- `createdAt`: can support ordering and fallback display.
- `plannedDate`: decides whether a task is overdue.
- `orbitLevel`: can help classify a task as Daily, Weekly, Monthly, or Yearly.
- `rescueState`: can store a simple rescue label later, such as `"Moved to today"` or `"Moved to weekend"`.

These fields are enough for the first Rescue Mode version. No schema change is needed for Phase 7B or the first read-only Rescue screen.

Possible future fields, but defer them:

- `completedAt`: useful for history and analytics.
- `cancelledAt`: useful if cancel should be different from delete.
- `rescuedAt`: useful for tracking when a task was rescued.
- `originalPlannedDate`: useful for showing where the task came from.

Those fields should only be added after a separate approved migration plan.

## 4. Recommended Phase 7 Breakdown

- Phase 7A: Create this Rescue Mode logic plan.
- Phase 7B: Add DAO, repository, and ViewModel methods for rescue candidates.
- Phase 7C: Make Rescue Mode read real overdue tasks through a UI model.
- Phase 7D: Create a safe implementation plan for Rescue actions.
- Phase 7E: Implement approved safe Rescue actions.
- Phase 7F: Review the full Rescue Mode foundation.

This keeps each step small and easier for a beginner to review.

## 5. Rescue Candidate Rule For First Version

A task should appear in Rescue Mode when:

- `isCompleted` is `false`.
- `plannedDate` is not null.
- `plannedDate` is before the start of today.

Deleted tasks do not need special handling because delete removes them from Room.

Tasks with `plannedDate = null` should not appear in the first Rescue version. That keeps the rule clear and avoids guessing whether an unscheduled task is overdue.

## 6. Rescue Actions For First Version

These actions are planned for later phases. Do not implement them in Phase 7A.

### Do Today

- Set `plannedDate` to today.
- Consider setting `rescueState` to `"Moved to today"` or clearing it to null.
- Keep the task incomplete so it appears on Today.

### Move Tomorrow

- Set `plannedDate` to tomorrow.
- Consider setting `rescueState` to `"Moved to tomorrow"`.
- Keep the task incomplete.

### Move Weekend

- Set `plannedDate` to the next Saturday.
- Keep date math simple at first.
- Consider setting `rescueState` to `"Moved to weekend"`.

### Convert To Goal

- This is useful but riskier.
- A safe first version could set `orbitLevel` to `"Year"` and append or update `linkedMission`.
- Avoid creating a separate Goal entity until there is an approved goal schema and migration plan.
- Consider deferring this action if it makes Phase 7E too large.

### Delete Or Cancel

- Delete is destructive, so use careful wording.
- Recommended first label: `"Delete task"` only if the action really deletes from Room.
- A safer future alternative is `"Dismiss from rescue"`, but that would need a clear field or state.
- Do not auto-delete anything without a user tap.

## 7. Schema Decision

Avoid a schema change initially.

The first Rescue version can use:

- `plannedDate` to find overdue tasks.
- `isCompleted` to exclude finished tasks.
- `rescueState` to store a simple action label if needed.
- `orbitLevel` and `linkedMission` for context.

Because `rescueState` already exists, Phase 7B and Phase 7C can proceed without a migration. If future history tracking is needed, plan a separate Room migration before adding fields.

## 8. UI Wiring Approach

- Keep Rescue Mode out of the permanent bottom nav.
- Add access from Today later, likely from the existing Rescue entry card.
- Rescue screen should receive a UI model, not `TaskEntity`.
- Suggested UI model:

```kotlin
data class RescueTaskPreview(
    val id: Long,
    val title: String,
    val meta: String,
    val energyLabel: String,
    val plannedDateLabel: String
)
```

- Map `TaskEntity` to `RescueTaskPreview` outside `RescueModeScreen`.
- Rescue actions should flow through `TaskViewModel -> TaskRepository -> TaskDao`.
- Keep date calculations in `MainActivity` or a small helper until a cleaner structure is approved.

## 9. Risks

- Date handling can be confusing if "before today" is not calculated from the start of the local day.
- Moving tasks incorrectly can make the user lose trust.
- Delete is destructive and should be clearly labeled.
- Rescue Mode can become too complex if every action is implemented at once.
- `MainActivity` can become too large if it keeps accumulating all mapping and date helper logic.

## 10. What Phase 7 Must Not Include Yet

- No notifications.
- No WorkManager.
- No recurring tasks.
- No cloud sync.
- No login.
- No advanced AI suggestions.
- No automatic task movement without user choice.
- No schema change unless explicitly approved.
- No Navigation Compose unless explicitly approved.
- No Hilt or dependency injection.

## 11. Acceptance Checklist For Phase 7B

- Build passes.
- DAO can fetch rescue candidate tasks.
- Repository wraps the DAO method.
- ViewModel exposes rescue candidates safely.
- No UI actions are implemented yet.
- No schema change unless explicitly approved.
- Today still reads, adds, completes, and deletes tasks.
- Week, Month, and Year still read Room data.
- Code remains beginner-readable.
