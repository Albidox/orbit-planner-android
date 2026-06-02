# Phase 11C - Screenshot Demo Asset Checklist

## Purpose

Phase 11C plans which screenshots and short demo assets should be captured for the Orbit Planner portfolio and README.

This phase is documentation/planning only. It does not capture screenshots, add image files, add videos, edit README, change Android source code, add features, change UI, refactor code, or start release work.

## 1. Screenshot Capture Rules

Use these rules before capturing any future portfolio assets:

- Use real app screens only.
- Do not fake data that implies real users, customers, downloads, reviews, or production usage.
- Use simple demo tasks only.
- Avoid personal or private data.
- Keep screenshots visually consistent.
- Prefer one emulator or device size for all screenshots.
- Do not over-edit screenshots.
- Do not claim Play Store release.
- Do not add Play Store badges unless a real release exists later.
- Keep the app state aligned with the current local/offline MVP behavior.

Good screenshot data should feel realistic, but clearly safe and generic.

## 2. Recommended Demo Data

Use simple sample tasks such as:

- Review Kotlin notes.
- Finish portfolio README.
- Plan weekly learning session.
- Clean up pending tasks.
- Prepare project demo.

Optional linked mission examples:

- Android learning.
- Portfolio polish.
- Weekly review.
- Project demo.

Avoid:

- Real names.
- Private client work.
- School, job, or personal deadlines that reveal private information.
- Fake business/customer data.
- Fake metrics or production usage claims.

## 3. Screenshot Checklist

Planned screenshots:

| Asset | What to show | Capture status | Notes |
| --- | --- | --- | --- |
| Today screen with task | Today screen with one or more simple local tasks visible. | Not captured | Use clean demo task names. |
| Add task dialog | Add task dialog with a safe sample task title. | Not captured | Do not include private task text. |
| Blank task validation | Add task dialog after trying to save a blank title. | Not captured | Show the validation message clearly. |
| Completed task state | A task marked complete on Today. | Not captured | Make sure completed state is visible. |
| Week screen | Week planning summary with local task context or safe preview state. | Not captured | Keep text readable. |
| Month screen | Month planning summary with local task context or safe preview state. | Not captured | Avoid blurry month visuals. |
| Year Orbit screen | Year Orbit summary and orbit visual. | Not captured | Center the visual cleanly. |
| Rescue Mode screen | Rescue Mode with overdue demo task or safe empty state. | Not captured | Prefer showing the core Rescue flow if test data is ready. |
| Settings screen | Notification status, daily reminder control, local/no-cloud info. | Not captured | Avoid implying cloud sync exists. |
| Notification/reminder toggle | Settings daily reminder toggle visible and understandable. | Not captured | Use the real app setting only. |

Recommended minimum set for README:

- Today screen.
- Year Orbit screen.
- Rescue Mode screen.
- Settings screen.

Recommended full set for portfolio:

- Today screen with task.
- Add task dialog.
- Completed task state.
- Week screen.
- Month screen.
- Year Orbit screen.
- Rescue Mode screen.
- Settings screen.

## 4. Optional Short Demo Video or GIF Checklist

Possible short clips:

| Clip | What to show | Capture status | Notes |
| --- | --- | --- | --- |
| Add a task | Open add task dialog, enter a sample task, save it. | Not captured | Keep the clip slow enough to follow. |
| Complete/uncomplete task | Toggle a task complete, then incomplete. | Not captured | Show visual state change clearly. |
| Planning flow | Move through Today -> Week -> Month -> Year. | Not captured | Use bottom navigation at a calm pace. |
| Rescue entry | Open Rescue Mode from Today when overdue tasks exist. | Not captured | Use safe demo task data only. |
| Settings reminder toggle | Open Settings and toggle the daily reminder. | Not captured | Do not imply per-task reminders exist. |

Suggested video/GIF rules:

- Keep clips short, around 10 to 30 seconds each.
- Prefer one focused action per clip.
- Avoid fast tapping.
- Avoid private data.
- Do not add music, heavy effects, or misleading edits.
- Do not imply a production release, real users, or Play Store availability.

## 5. File and Folder Plan

Suggested future folders:

- `docs/assets/screenshots/`
- `docs/assets/demo/`

Do not create these folders in Phase 11C unless a future phase explicitly asks for assets to be added.

Suggested future file naming:

- `today-screen.png`
- `add-task-dialog.png`
- `completed-task-state.png`
- `week-screen.png`
- `month-screen.png`
- `year-orbit-screen.png`
- `rescue-mode-screen.png`
- `settings-screen.png`
- `daily-reminder-toggle.png`
- `orbit-planner-demo.gif`

Keep names lowercase and descriptive.

## 6. README Usage Plan

Screenshots may later be added to README in these areas:

- Screenshot section.
- Demo GIF/video section.
- Feature highlights section.

Suggested README usage:

- Use one main screenshot near the top if the image is polished.
- Use a small grid for core screens.
- Put demo GIF/video after the feature summary.
- Keep captions short and honest.
- Say screenshots are from the local/offline MVP.

Do not update README in Phase 11C. README updates should happen only in a later phase after assets exist.

## 7. Quality Checklist

Before accepting future screenshots or demo clips, check:

- App text is readable.
- No keyboard covers important UI.
- Status bar is acceptable.
- Screens are not blurry.
- Cropping is clean.
- Demo data looks professional.
- Screenshots match current MVP behavior.
- No fake data implies real customers or production usage.
- No private data is visible.
- No Play Store release is implied.
- No forbidden feature is shown or suggested.

## 8. What Not To Do Yet

Do not do these in Phase 11C:

- Do not edit README in this phase.
- Do not add fake screenshots.
- Do not add Play Store badges.
- Do not add fake metrics, downloads, users, reviews, or customer claims.
- Do not start feature development.
- Do not start release work.
- Do not add image files.
- Do not add video files.
- Do not add GIF files.
- Do not change Android source code.
- Do not change Room schema or database version.
- Do not add login, cloud sync, Navigation Compose, Hilt/DI, analytics, exact alarms, advanced recurring tasks, or per-task reminders.

## 9. Acceptance Checklist

- [x] Docs-only.
- [x] No Android source code changes.
- [x] No image files added yet.
- [x] No video files added yet.
- [x] No GIF files added yet.
- [x] No README changes.
- [x] No feature changes.
- [x] No Room schema changes.
- [x] No database version changes.
- [x] Clear screenshot plan.
- [x] Clear demo asset plan.
- [x] README update deferred to a later phase.

