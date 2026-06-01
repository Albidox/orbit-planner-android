# Phase 10A App Polish And Stabilization Plan

This plan is documentation only. It prepares a beginner-safe path for polishing and stabilizing Orbit Planner before any larger feature work.

Phase 10A should not change Android source code, Room schema, database version, app navigation architecture, notification scheduling behavior, or feature scope. It is a pause-and-review step so the project can become easier to test, demo, and maintain.

## 1. Current Completed Foundation

Orbit Planner now has the main local/offline MVP foundation in place:

- Static UI foundation for the main app screens.
- Local Room task CRUD through the current task data layer and ViewModel flow.
- Today planning flow with local task reading, adding, completing, and deleting.
- Week planner reading local task data.
- Month planner reading local task data.
- Year planner and orbit summary reading local task data.
- Rescue Mode for unfinished past tasks.
- Daily pending task notification foundation.
- Settings app-control screen with reminder status, reminder toggle, and local data information.

This gives the app enough real behavior to start stabilizing before adding anything bigger.

## 2. Goal Of Phase 10

Phase 10 should make the current app feel safer, clearer, and more ready for a portfolio or demo review.

The goal is to:

- Improve stability.
- Reduce rough edges in the existing MVP.
- Improve beginner code organization.
- Prepare the app for local demo and portfolio use.
- Avoid big new features until the current foundation is reviewed.

Phase 10 should feel like careful cleanup, not a rewrite.

## 3. Recommended Phase 10 Breakdown

### Phase 10A: Polish And Stabilization Plan

- Create this planning document.
- Confirm the current completed foundation.
- List manual QA areas.
- List small polish ideas.
- List code organization risks.
- Do not edit Android source code.
- Do not implement UI changes.

### Phase 10B: Manual QA Checklist

- Create a beginner-friendly manual QA checklist document.
- Include exact test steps for the existing local/offline MVP.
- Focus on Today, Week, Month, Year, Rescue Mode, notifications, Settings, and app restart persistence.
- Run a build after creating the checklist.
- Do not change app behavior.

### Phase 10C: Small UI Text And Accessibility Polish

- Review touch targets.
- Review text contrast.
- Improve empty states where needed.
- Improve button labels where needed.
- Keep Rescue Mode copy gentle and non-shaming.
- Keep bottom navigation consistent.
- Make only small approved source changes in this phase.

### Phase 10D: Code Organization Cleanup Plan

- Review which files are getting large.
- Identify duplicated date helper logic.
- Identify notification/settings logic that may be too close to UI code.
- Plan small cleanup steps without adding Hilt or Navigation Compose.
- Do not perform a major architecture rewrite.

### Phase 10E: Final Local Demo Review

- Run the full manual QA checklist.
- Run a build.
- Review app copy and beginner readability.
- Confirm no forbidden features were added.
- Confirm the app can be shown as a local/offline MVP demo.

## 4. Manual QA Areas

Phase 10B should turn these areas into exact manual test steps.

### Add Task

- Add a task for today.
- Confirm the title and optional details display correctly.
- Confirm the task stays after closing and reopening the app.

### Complete Task

- Mark a task complete.
- Confirm the task completion state updates.
- Confirm the daily progress indicator updates.
- Confirm the task no longer counts as pending.

### Delete Task

- Delete a task.
- Confirm the task disappears.
- Restart the app and confirm the deleted task does not return.

### Today Filtering

- Add one task for today.
- Add one task for another date.
- Confirm Today shows the correct tasks for the current day.
- Confirm unfinished older tasks are handled through Rescue Mode instead of confusing the Today list.

### Week, Month, And Year Summaries

- Add and complete tasks on different dates.
- Confirm Week summary values update.
- Confirm Month summary values update.
- Confirm Year Orbit loads and reflects the current local data without crashing.

### Rescue Entry, Action, And Exit Flow

- Create or keep an unfinished task from a previous day.
- Confirm Rescue Mode can be opened from the intended entry point.
- Confirm the copy stays gentle, such as "You have unfinished tasks. Let's rescue them."
- Test currently available rescue actions, such as Do today, Move to tomorrow, Move to weekend, and Delete.
- If Convert to goal is added in a later approved phase, add a separate QA check for it.
- Confirm the user can leave Rescue Mode and return to normal planning.

### Notification Toggle

- Open Settings.
- Turn the daily reminder off.
- Confirm the toggle state remains off after app restart.
- Turn the daily reminder on.
- Confirm the app does not create repeated or spammy reminder behavior.
- Confirm notification permission denial does not break the app.

### Settings Status And Info

- Confirm Settings explains reminder status clearly.
- Confirm Settings explains local data clearly.
- Confirm there is no broken placeholder copy.
- Confirm Settings does not imply login, cloud sync, or accounts exist.

### App Restart Persistence

- Add, complete, and delete tasks.
- Change the reminder toggle.
- Move a rescue task.
- Fully close and reopen the app.
- Confirm local task data, completion state, deleted state, rescue changes, and Settings state persist.

## 5. Small UI And Accessibility Polish Ideas

These are ideas for later approved source changes, not Phase 10A implementation.

- Review touch targets so important controls are easy to tap, aiming for at least 48dp by 48dp where practical.
- Review text contrast on dark navy backgrounds and cards.
- Add calm empty states for Today, Week, Month, Year, and Rescue Mode where needed.
- Use clear button labels such as Add task, Save changes, Do today, Move to tomorrow, and Delete task.
- Keep Rescue Mode copy non-shaming and recovery-focused.
- Avoid negative copy such as "You failed your tasks."
- Keep bottom navigation labels, order, selected state, and icons consistent across screens.
- Make sure selected navigation state is not communicated by color alone.
- Check that important text does not become too small or too dim.

## 6. Code Organization Risks

These risks should be reviewed carefully before future growth:

- `MainActivity` may become too large if more screen switching, setup, and app-level behavior are added there.
- Screen files may become large as UI, state handling, and action callbacks grow.
- Date helper logic may become duplicated across Today, Week, Month, Year, notifications, and Rescue Mode.
- Notification and Settings logic may become mixed with UI code if future changes are not kept small.
- Manual navigation logic may become harder to follow before a later approved Navigation Compose migration.
- Beginner readability may drop if too many abstractions are added at once.

Recommended cleanup direction:

- Plan small refactors before doing them.
- Move only one clear responsibility at a time.
- Prefer simple helper files over a large architecture rewrite.
- Do not add Hilt or dependency injection yet.
- Do not add Navigation Compose yet.
- Do not change Room schema as part of cleanup unless explicitly approved.

## 7. What Phase 10 Must Not Include Yet

Phase 10 must avoid:

- Login.
- Cloud sync.
- Backend accounts.
- Account system.
- Analytics.
- Payment or subscription features.
- Social sharing.
- AI assistant features.
- Team collaboration.
- Advanced recurring tasks.
- Exact alarms.
- Custom reminder schedules unless separately approved.
- Room schema changes unless explicitly approved.
- Database version changes unless explicitly approved.
- Major architecture rewrite.
- Hilt or dependency injection.
- Navigation Compose migration.
- New large features disguised as polish.

## 8. Acceptance Checklist For Phase 10B

Phase 10B is ready for review when:

- Build passes.
- A manual QA checklist document is created.
- The checklist has clear manual test steps.
- No Android source code changes are made.
- No feature changes are made.
- No Room schema changes are made.
- No database version changes are made.
- No forbidden features are added.
- The checklist covers Add task, Complete task, Delete task, Today filtering, Week/Month/Year summaries, Rescue flow, notification toggle, Settings info, and app restart persistence.

Phase 10B should give a beginner a clear path to test the app without guessing what to click or what result to expect.
