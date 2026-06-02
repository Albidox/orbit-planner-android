# Phase 5 Add Task UI Plan

This plan describes the safest beginner-friendly path for the first real Add Task flow. Phase 5F is planning only and does not implement UI or task creation behavior.

## 1. Current Completed Foundation

- Room database foundation exists with `TaskEntity`, `TaskDao`, `OrbitPlannerDatabase`, and `DatabaseProvider`.
- `TaskRepository` wraps the DAO methods.
- `TaskViewModel` exposes task data and basic future-safe task methods.
- Today screen reads Room tasks through the ViewModel.
- Sample seed tasks work for local testing and are guarded so they do not duplicate endlessly.
- Static screen switching works for Today, Week, Month, Year, and Settings without Navigation Compose.

## 2. Goal Of Add Task V1

- Let the user add a simple task to Room.
- Show the saved task on the Today screen after saving.
- Keep the flow beginner-friendly and easy to review.
- Avoid advanced scheduling, reminders, rescue behavior, and edit/delete behavior for now.

## 3. Recommended Add Task UI Approach

Recommended option: Option B, a simple modal/dialog.

Reason:

- It avoids adding Navigation Compose.
- It avoids adding a temporary extra screen to `MainActivity`.
- It keeps the user on Today while adding a quick task.
- It can use Material 3 components already in the project.
- It keeps Phase 5G small: one dialog, one save callback, one ViewModel insert path.

Option A, an inline add card on Today, is also simple, but it may crowd the approved Today layout too early.

Option C, a separate temporary Add Task screen, would make the temporary screen switching logic larger before real navigation is ready.

## 4. Minimal Fields For Add Task V1

- `title`: required.
- `linkedMission`: optional.
- `energyLabel`: optional, default `"Normal"`.
- `plannedDate`: default today/current date.
- `orbitLevel`: default `"Daily"`.
- `rescueState`: default `null` or `"None"`.
- `isCompleted`: default `false`.
- `createdAt`: current time.

## 5. ViewModel Changes Needed Later

- Add a beginner-readable `addTaskFromInput(...)` method.
- Validate that `title` is not blank.
- Build a `TaskEntity` inside the ViewModel.
- Insert the task through `TaskRepository`.
- Keep UI code from directly calling the DAO or database.

## 6. UI Behavior Later

- User taps the existing Add Task visual action on Today.
- A simple Add Task dialog opens.
- User enters a required title.
- User can optionally enter a linked mission.
- User can optionally choose or enter an energy label, defaulting to `"Normal"`.
- User taps Save.
- The task saves to Room through `TaskViewModel`.
- The task appears on Today from the existing Room `Flow`.
- The static/sample fallback reduces or disappears when real tasks exist.

## 7. What Phase 5G Should Implement

- The simple Add Task dialog on Today.
- A Save action that inserts one task into Room through `TaskViewModel`.
- Basic blank-title validation.
- A Cancel action that closes the dialog.
- No edit/delete/complete behavior yet.

## 8. What Phase 5G Must Not Implement

- No edit task.
- No delete task.
- No complete task.
- No Rescue logic.
- No reminders.
- No notifications.
- No WorkManager.
- No Navigation Compose.
- No Hilt.

## 9. Risks

- `MainActivity` can become too large if more manual wiring is added there.
- Sample seed tasks and real user tasks can become confusing if the temporary seed is not removed later.
- Adding too much UI in one phase could make the beginner flow hard to debug.
- Validation can become overcomplicated if Phase 5G tries to handle too many fields.

## 10. Acceptance Checklist For Phase 5G

- App builds successfully.
- User can add a simple task.
- Task saves to Room.
- Task appears on Today.
- No duplicate weirdness appears after repeated app starts.
- No advanced features are added.
- Code remains beginner-readable.
