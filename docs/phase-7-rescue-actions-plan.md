# Phase 7D Rescue Actions Plan

This plan is documentation only. It prepares the next small implementation step for Rescue Mode actions without changing Android source code, the Room schema, database version, notifications, WorkManager, Navigation Compose, or dependency setup.

## 1. Current Rescue Foundation

- Rescue candidate data methods already exist in the DAO, repository, and ViewModel path:
  - `TaskDao.getRescueCandidateTasks(beforeDate)`
  - `TaskRepository.getRescueCandidateTasks(beforeDate)`
  - `TaskViewModel.getRescueCandidateTasks(beforeDate)`
- The Rescue screen now reads real overdue tasks from Room.
- Rescue Mode uses `RescueTaskPreview` as a UI model instead of passing `TaskEntity` directly into the screen.
- `RescueTaskPreview` already keeps the task `id`, which is required for future action callbacks.
- Rescue action buttons are visible in the UI, but they are not implemented yet.
- Rescue Mode is not in the bottom navigation as a permanent item. It is still treated as a conditional recovery flow.

## 2. Goal Of Rescue Actions

Rescue actions should let the user safely decide what happens to overdue tasks.

The first version should:

- Keep the user in control.
- Use calm, supportive language.
- Avoid shame, blame, or failure wording.
- Avoid automatic destructive actions.
- Keep each action simple enough for a beginner to understand and test.
- Prefer reversible choices where possible.

Good tone:

- "Let's rescue these tasks."
- "Choose what happens next."
- "Move unfinished work without pressure."

Avoid tone:

- "You failed."
- "Overdue because you did not finish."
- "Clean up your failed tasks."

## 3. Recommended First Rescue Actions

Implement these first in Phase 7E:

1. Do today
2. Move tomorrow
3. Move weekend
4. Delete/cancel, only if the wording and behavior are safe

Defer or carefully discuss:

- Convert to goal

`Convert to goal` sounds useful, but it touches a larger product concept. It may require a real `Goal` flow, extra mapping rules, and clearer UX. It should not be mixed into the first safe Rescue action implementation unless the user explicitly approves the behavior.

## 4. Action Behavior Definitions

### Do Today

Behavior:

- Update the task's `plannedDate` to the start of today.
- Keep the task incomplete.
- Do not change the schema.
- Do not create a reminder.

Expected result:

- The task disappears from Rescue Mode because it is no longer before today.
- The task appears in Today.

### Move Tomorrow

Behavior:

- Update the task's `plannedDate` to the start of tomorrow.
- Keep the task incomplete.
- Do not change the schema.
- Do not create a reminder.

Expected result:

- The task disappears from Rescue Mode.
- The task appears in Week and Month according to its new date.

### Move Weekend

Behavior:

- Update the task's `plannedDate` to the next Saturday.
- Use a simple date rule first:
  - If today is before Saturday, use the upcoming Saturday.
  - If today is Saturday or Sunday, use the next Saturday.
- Set the time to the start of that day for consistency with the existing date filters.

Expected result:

- The task disappears from Rescue Mode.
- The task appears in Week or Month depending on the selected Saturday.

### Delete/Cancel

Recommendation:

- Prefer `Cancel task` over `Delete` for the first user-facing wording if possible.
- If Phase 7E reuses the existing `deleteTaskById(taskId)` method, label the action clearly because it permanently removes the local task.
- Prefer a confirmation step before deleting:
  - Title: "Delete this task?"
  - Body: "This removes it from your planner. You can choose another rescue option instead."
  - Buttons: "Keep task" and "Delete task"
- Do not silently delete.
- Do not use bulk delete.

Safety note:

- A true cancel action would ideally mark the task as cancelled, but the current schema uses `isCompleted` and does not yet have the full status enum from AGENTS.md.
- Because the user explicitly said not to change the schema or database version, Phase 7E should either:
  - reuse existing delete behavior with a clear destructive label, or
  - defer cancel/delete until a safer status model is planned.

## 5. Data Method Needs

Phase 7E should use the smallest possible data path.

Option A, reuse what already exists:

- Load the task with `getTaskById(taskId)`.
- Copy it with a new `plannedDate`.
- Save it with the existing `updateTask(task)`.

Option B, add a beginner-readable direct update method:

```kotlin
suspend fun updateTaskPlannedDate(taskId: Long, newDate: Long)
```

If Option B is chosen:

- Add `updateTaskPlannedDate(taskId, newDate)` to `TaskDao`.
- Add the same beginner-readable method to `TaskRepository`.
- Add a ViewModel method that launches the update from `viewModelScope`.

Possible DAO query:

```kotlin
@Query("UPDATE tasks SET plannedDate = :newDate WHERE id = :taskId")
suspend fun updateTaskPlannedDate(taskId: Long, newDate: Long)
```

Delete/cancel:

- `deleteTaskById(taskId)` already exists in DAO, repository, and ViewModel.
- Reuse it only if the UI wording makes the destructive behavior clear.
- Do not add a schema field just for cancel in Phase 7E.

No schema change is needed initially.

## 6. UI Behavior

Phase 7E should keep UI changes small:

- Keep `RescueTaskPreview.id`.
- Add callbacks to the Rescue screen:
  - `onDoToday(taskId)`
  - `onMoveTomorrow(taskId)`
  - `onMoveWeekend(taskId)`
  - maybe `onDeleteOrCancel(taskId)`
- Rescue task action buttons should call those callbacks.
- `MainActivity` can pass the callbacks to `TaskViewModel` for now.
- Keep copy supportive and recovery-focused.
- When the Rescue list becomes empty, show the existing calm fallback state instead of leaving a blank screen.
- Later UI polish can replace the current empty-state chip text with clearer copy like "Nothing needs rescue" and "Your recovery queue is clear. Today can stay light."

Important:

- The screen should still receive a UI model, not `TaskEntity`.
- The composables should not talk directly to Room or the repository.
- If `MainActivity` starts getting too large, note it as a future cleanup, but do not introduce Navigation Compose or Hilt in Phase 7E.

## 7. Safety Rules

- No automatic moving.
- No bulk actions yet.
- No destructive delete without a clear label.
- No shame or blame copy.
- No hidden task changes.
- Keep the user in control of every Rescue decision.
- Keep each action easy to test one task at a time.

## 8. What Phase 7E Should Implement

Phase 7E should implement:

- Do today.
- Move tomorrow.
- Move weekend.
- Maybe Delete/cancel only if the wording is safe and the behavior is clearly explained.
- Room updates through DAO, repository, and ViewModel.
- Existing Rescue screen callbacks.
- No notifications or WorkManager.

## 9. What Phase 7E Must Not Implement

Phase 7E must not add:

- Reminders.
- Notifications.
- WorkManager.
- Navigation Compose.
- Hilt or dependency injection.
- Recurring tasks.
- AI suggestions.
- Cloud sync.
- Login.
- Goal conversion unless explicitly approved later.
- Any Room schema or database version change.

## 10. Risks

- Date calculations can be off if today, tomorrow, and weekend dates are not normalized to the start of day.
- `Delete` is destructive and may surprise beginners if the label is too soft.
- `MainActivity` can get larger as more callbacks are added.
- Changing `plannedDate` affects Today, Week, Month, and Year screens, so Phase 7E must check that each screen still reads tasks correctly.
- `Move weekend` needs a simple, predictable rule so users are not confused.

## 11. Acceptance Checklist For Phase 7E

- Build passes.
- Rescue actions update Room through ViewModel and repository.
- `Do today` moves the task to Today.
- `Move tomorrow` moves the task to tomorrow.
- `Move weekend` moves the task to the next simple weekend date.
- Rescheduled tasks disappear from Rescue Mode.
- Rescheduled tasks appear in Today, Week, Month, or Year based on the new `plannedDate`.
- No Room schema change.
- No database version change.
- No reminders.
- No notifications.
- No WorkManager.
- No Navigation Compose.
- No Hilt.
- No recurring tasks.
- No AI suggestions.
- No cloud sync or login.
