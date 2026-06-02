# Phase 5 ViewModel Wiring Plan

This plan describes how to safely connect the existing Room database foundation to the UI in the next phase. Phase 5C does not wire the UI yet.

## 1. Current Completed Foundation

- Static UI screens are complete for Today, Week, Month, Year Orbit, Rescue Mode, and Settings.
- `TaskEntity` defines the local Room task table.
- `TaskDao` provides basic task database operations.
- `OrbitPlannerDatabase` registers `TaskEntity` as the first Room entity.
- `DatabaseProvider` creates one shared Room database instance.
- `TaskRepository` wraps `TaskDao` with beginner-readable methods.
- `TaskViewModel` accepts `TaskRepository` and exposes basic future task state and methods.

## 2. Planned Dependency Chain

The next wiring phase should keep the data flow simple:

`DatabaseProvider -> OrbitPlannerDatabase -> TaskDao -> TaskRepository -> TaskViewModel -> UI`

Each layer should only know about the layer directly before it.

## 3. Recommended Wiring Approach Without Hilt

- Use a simple `ViewModelProvider.Factory`.
- Create the database with `DatabaseProvider.getDatabase(applicationContext)`.
- Get the DAO from `database.taskDao()`.
- Create `TaskRepository(taskDao)`.
- Create `TaskViewModel(taskRepository)` from the factory.
- Keep the setup beginner-readable and local to the app entry point or a small helper file.
- Avoid global mutable state except the existing database singleton in `DatabaseProvider`.

## 4. Where To Create The ViewModel

Recommended location: create the ViewModel in `MainActivity` using a small helper factory file.

Reason:

- `MainActivity` already owns the temporary app-level screen switching.
- A helper factory keeps `MainActivity` from becoming too large.
- This avoids Hilt or dependency injection while the project is still beginner-focused.
- The setup can later move into a cleaner app container or DI system if the project grows.

Possible helper file:

`app/src/main/java/com/ashfaq/orbitplanner/ui/screens/TaskViewModelFactory.kt`

## 5. What Phase 5D Should Do

- Wire the Today screen to read tasks from `TaskViewModel`.
- Show database tasks if any tasks exist.
- Show the current static/sample empty state when there are no database tasks.
- Keep the static screen style intact.
- Keep add/edit/delete UI out of scope unless explicitly approved.

## 6. What Phase 5D Must Not Do

- No Add Task screen yet.
- No real create, edit, or delete buttons yet.
- No Rescue logic.
- No reminders.
- No notifications.
- No Navigation Compose.
- No Hilt or dependency injection.

## 7. Risks

- Manual ViewModel creation can get messy if repeated in many places.
- `MainActivity` can become too large if setup logic is not kept small.
- Mixing sample UI data and database data can confuse beginners if the code is not clearly separated.
- The app should avoid showing both sample tasks and database tasks at the same time without a clear rule.

## 8. Acceptance Checklist For Phase 5D

- App builds successfully.
- Today UI can read tasks from Room through `TaskViewModel`.
- No CRUD UI is added yet.
- Static Week, Month, Year Orbit, Rescue Mode, and Settings screens remain intact.
- No new dependencies are added unless absolutely necessary.
- Code stays beginner-readable.
