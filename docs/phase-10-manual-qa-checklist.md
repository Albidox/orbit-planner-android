# Phase 10B Manual QA Checklist

This checklist is documentation only. It gives a beginner-safe manual testing path for Orbit Planner before any polish changes are made.

Use this checklist on a local debug build. Mark each row as `Not tested`, `Pass`, or `Fail`, and add notes when something is confusing, broken, or worth polishing later.

## Testing Rules

- Do not add new features while testing.
- Do not change Android source code during this checklist step.
- Do not change Room schema or database version.
- Do not add login, cloud sync, Navigation Compose, Hilt, analytics, exact alarms, or major refactors.
- Use calm, non-shaming copy when writing notes about Rescue Mode or unfinished tasks.

## Checklist

| Test area | Test steps | Expected result | Status | Notes |
| --- | --- | --- | --- | --- |
| App launch | 1. Install or run the app.<br>2. Open Orbit Planner.<br>3. Look at the first visible screen.<br>4. Check the bottom navigation area. | App opens without crashing. The correct main screen appears. Bottom nav is visible and usable. | Not tested / Pass / Fail |  |
| Add task: valid task | 1. Open Today.<br>2. Add a new task with a valid title.<br>3. Add any available optional details, such as description, priority, or energy level.<br>4. Save the task. | The task is saved and appears in Today. The app does not crash. | Not tested / Pass / Fail |  |
| Add task: blank title validation | 1. Open the add task flow.<br>2. Leave the task title blank.<br>3. Try to save. | A beginner-readable validation message appears. A blank task is not saved. The app does not crash. | Not tested / Pass / Fail |  |
| Complete task | 1. Find an active task in Today.<br>2. Mark it complete.<br>3. Watch the task row/card and progress area. | The task shows a completed visual state. Daily progress updates. The task no longer feels active or pending. | Not tested / Pass / Fail |  |
| Uncomplete task | 1. Find a completed task.<br>2. Mark it incomplete again. | The task returns to the active state. Daily progress updates again. | Not tested / Pass / Fail |  |
| Delete task | 1. Create or select a task.<br>2. Delete the task.<br>3. Confirm any confirmation step if shown. | The task disappears. The app does not crash. Deleted task does not stay visible in Today. | Not tested / Pass / Fail |  |
| Today filtering | 1. Add or identify a task for today.<br>2. Add or identify a task for another date.<br>3. Open Today. | Today shows today's tasks. Tasks from other dates do not incorrectly appear in the Today list. | Not tested / Pass / Fail |  |
| Today empty state | 1. Use a state where there are no tasks for today.<br>2. Open Today. | Empty state works safely. The screen does not look broken or crash. Copy feels calm and helpful. | Not tested / Pass / Fail |  |
| Week screen opens | 1. Tap the Week bottom nav item or Week entry point.<br>2. Wait for the screen to load. | Week screen opens without crashing. | Not tested / Pass / Fail |  |
| Week summary | 1. Add tasks that belong to the current week.<br>2. Complete at least one task.<br>3. Open Week. | Weekly task summary appears and reflects local task data as expected. | Not tested / Pass / Fail |  |
| Week empty/static fallback | 1. Open Week with no useful weekly data, or after clearing test tasks if possible. | Empty or static fallback works safely. The screen does not crash or show broken content. | Not tested / Pass / Fail |  |
| Month screen opens | 1. Tap the Month bottom nav item or Month entry point.<br>2. Wait for the screen to load. | Month screen opens without crashing. | Not tested / Pass / Fail |  |
| Month summary | 1. Add tasks that belong to the current month.<br>2. Complete at least one task.<br>3. Open Month. | Monthly task summary appears and reflects local task data as expected. | Not tested / Pass / Fail |  |
| Month empty/static fallback | 1. Open Month with no useful monthly data, or after clearing test tasks if possible. | Empty or static fallback works safely. The screen does not crash or show broken content. | Not tested / Pass / Fail |  |
| Year screen opens | 1. Tap the Year bottom nav item or Year entry point.<br>2. Wait for the screen to load. | Year screen opens without crashing. | Not tested / Pass / Fail |  |
| Year Orbit summary | 1. Open Year after adding or completing a few local tasks.<br>2. Review the Year Orbit summary. | Year Orbit summary appears. It reflects local planning progress as expected for the current MVP. | Not tested / Pass / Fail |  |
| Year visuals | 1. Review month and quarter visuals on the Year screen.<br>2. Scroll if the screen supports scrolling. | Month and quarter visuals do not crash, overlap badly, or block normal use. | Not tested / Pass / Fail |  |
| Rescue entry | 1. Create or identify an overdue incomplete task from a previous day.<br>2. Open Today.<br>3. Look for the Rescue card or entry point. | Rescue card appears on Today when there is unfinished past work. Copy feels gentle, not shaming. | Not tested / Pass / Fail |  |
| Rescue opens | 1. Tap the Rescue card or entry point from Today. | Rescue Mode opens without crashing and shows overdue incomplete tasks. | Not tested / Pass / Fail |  |
| Rescue action: Do today | 1. In Rescue Mode, choose Do today for an overdue task.<br>2. Return to Today. | Task moves to today and no longer appears as overdue rescue work. | Not tested / Pass / Fail |  |
| Rescue action: Tomorrow | 1. In Rescue Mode, choose Tomorrow for an overdue task.<br>2. Check Today and Rescue after the action. | Task moves to tomorrow and is removed from the current Rescue list. | Not tested / Pass / Fail |  |
| Rescue action: Weekend | 1. In Rescue Mode, choose Weekend for an overdue task.<br>2. Check Today and Rescue after the action. | Task moves to the weekend target date and is removed from the current Rescue list. | Not tested / Pass / Fail |  |
| Rescue action: Delete with confirmation | 1. In Rescue Mode, choose Delete for an overdue task.<br>2. Confirm the delete action when asked. | Task is deleted only after confirmation. The app does not crash. | Not tested / Pass / Fail |  |
| Rescue bottom nav exit | 1. Open Rescue Mode.<br>2. Use bottom nav or available navigation to leave Rescue.<br>3. Open Today, Week, Month, Year, and Settings if available. | Bottom nav exits Rescue safely. Other screens still open normally. | Not tested / Pass / Fail |  |
| Settings opens | 1. Open Settings from bottom nav or the available entry point. | Settings opens without crashing. | Not tested / Pass / Fail |  |
| Settings notification status | 1. Open Settings.<br>2. Read the notification permission/status area. | Notification permission or status text appears and is understandable. | Not tested / Pass / Fail |  |
| Settings reminder toggle | 1. Open Settings.<br>2. Confirm the daily reminder toggle is visible. | Daily reminder toggle appears and does not look broken. | Not tested / Pass / Fail |  |
| Toggle reminder off | 1. Turn the daily reminder toggle off.<br>2. Stay on Settings and observe the state. | Toggle changes to off safely. The app does not crash. Reminder copy remains clear. | Not tested / Pass / Fail |  |
| Toggle reminder on | 1. Turn the daily reminder toggle on.<br>2. Watch for permission behavior if needed. | Toggle changes to on safely when allowed. If permission is denied or needed, the app handles it calmly. | Not tested / Pass / Fail |  |
| Settings local data info | 1. Open Settings.<br>2. Find local data or no-cloud information. | Settings clearly explains local data/no cloud behavior. It does not imply accounts or sync exist. | Not tested / Pass / Fail |  |
| Settings about/version info | 1. Open Settings.<br>2. Find the About or version area. | About/version information appears and has no broken placeholder text. | Not tested / Pass / Fail |  |
| Notification permission safety | 1. Test on Android 13+ if available.<br>2. Deny notification permission if asked.<br>3. Continue using the app. | Denying permission does not break task CRUD, Settings, or navigation. The app does not repeatedly pressure the user. | Not tested / Pass / Fail |  |
| Reminder toggle safety | 1. Toggle the daily reminder off and on.<br>2. Close and reopen Settings. | Reminder toggle does not crash and its state remains understandable. | Not tested / Pass / Fail |  |
| Notification scope | 1. Review current notification behavior during normal use.<br>2. Confirm task rows do not create individual notifications. | No per-task notifications exist in this MVP phase. | Not tested / Pass / Fail |  |
| Exact alarm safety | 1. Review app behavior and approved docs for notification scope.<br>2. Confirm reminders are not exact-alarm based. | No exact alarms are used for Phase 10B. | Not tested / Pass / Fail |  |
| Persistence: task remains | 1. Add a task.<br>2. Fully close the app.<br>3. Reopen the app. | The task remains after restart. | Not tested / Pass / Fail |  |
| Persistence: completed state remains | 1. Mark a task complete.<br>2. Fully close the app.<br>3. Reopen the app. | Completed state remains after restart. | Not tested / Pass / Fail |  |
| Persistence: delete remains deleted | 1. Delete a task.<br>2. Fully close the app.<br>3. Reopen the app. | Deleted task does not return after restart. | Not tested / Pass / Fail |  |
| Text readability | 1. Review Today, Week, Month, Year, Rescue, and Settings.<br>2. Look at headings, body text, helper text, and labels. | Text is readable on the current background and card colors. Important text is not too small or too dim. | Not tested / Pass / Fail |  |
| Touch targets | 1. Tap common controls, including task complete, delete, Rescue actions, Settings toggle, and bottom nav items. | Controls are easy to tap and do not feel cramped. Important targets should feel close to 48dp by 48dp where practical. | Not tested / Pass / Fail |  |
| Contrast | 1. Review dark backgrounds, cards, disabled text, secondary text, and accent buttons. | Text and controls have enough contrast to be readable. Selected state is clear. | Not tested / Pass / Fail |  |
| Button labels | 1. Review action buttons across Today, Rescue, and Settings.<br>2. Look for vague labels. | Button labels describe the action clearly, such as Add task, Do today, Move to tomorrow, or Delete task. | Not tested / Pass / Fail |  |
| Non-shaming copy | 1. Review Rescue Mode and pending task copy. | Copy feels gentle and recovery-focused. Avoids language like "failed" or blame. | Not tested / Pass / Fail |  |
| Bottom nav consistency | 1. Move between Today, Week, Month, Year, Rescue, and Settings where available.<br>2. Compare nav labels, order, and selected states. | Bottom nav remains consistent. The selected item is obvious and normal navigation does not feel surprising. | Not tested / Pass / Fail |  |
| Regression: no login/cloud sync | 1. Review visible app screens and Settings.<br>2. Confirm no account or cloud workflow appears. | No login, account system, cloud sync, backend, payment, or social feature was added. | Not tested / Pass / Fail |  |
| Regression: no Navigation Compose | 1. Review Phase 10B scope and any changed files. | No Navigation Compose migration was added in this phase. | Not tested / Pass / Fail |  |
| Regression: no schema/database version change | 1. Review changed files for this phase.<br>2. Confirm database files were not edited. | No Room schema or database version change was made. | Not tested / Pass / Fail |  |
| Regression: no destructive reset | 1. Review Settings and visible actions.<br>2. Look for reset/delete-all controls. | No destructive reset or delete-all action was added. | Not tested / Pass / Fail |  |

## Final Phase 10B Review Notes

Use this section after manual testing:

- Biggest issue found:
- Most confusing screen:
- Best small polish candidate:
- Any crash found:
- Any forbidden scope found:
- Ready for Phase 10C review: Yes / No
