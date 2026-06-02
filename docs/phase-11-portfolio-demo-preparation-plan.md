# Phase 11A - Portfolio Demo Preparation Plan

## Purpose

Phase 11A is a documentation-only plan for preparing Orbit Planner as a portfolio/demo project after the local/offline MVP has passed manual QA.

This phase does not change Android source code, UI, app behavior, Room schema, database version, dependencies, or architecture. It also does not start Play Store release work.

## 1. Current MVP Status

Orbit Planner is ready to move from MVP stabilization into portfolio/demo preparation.

Current status:

- Phase 10 is completed.
- Manual QA has passed for the local/offline MVP.
- The local/offline MVP is demo-ready.
- The app has no login or cloud sync yet.
- The app has no Play Store release work yet.

What this means:

- Orbit Planner can now be presented as a working local Android MVP.
- The next work should explain and showcase the app clearly.
- The next work should not add new app features yet.

## 2. Portfolio Goal

Orbit Planner should be presented as:

- A local/offline Android productivity MVP.
- Built with Kotlin, Jetpack Compose, Room, ViewModel, WorkManager, and Material-style UI.
- Focused on daily planning, long-term planning, Rescue Mode, and local task persistence.

The portfolio version should make the project easy to understand for:

- Recruiters.
- Beginner developers.
- Friends or mentors reviewing the app.
- Anyone opening the GitHub repository for the first time.

The main message:

Orbit Planner is a calm local-first planner that connects daily work to weekly, monthly, and yearly goals. Its special MVP feature is Rescue Mode, which helps users recover overdue tasks without guilt.

## 3. Demo Story

Use a simple story when showing the app:

1. The user opens Orbit Planner to plan today.
   - Show the Today screen as the main daily workspace.

2. The user adds a task.
   - Show the add task dialog.
   - Save a normal task.
   - Mention that the task is stored locally.

3. The user completes and uncompletes the task.
   - Show the completed visual state.
   - Show that the task can return to active state.

4. The user views the bigger planning flow.
   - Open Week.
   - Open Month.
   - Open Year Orbit.
   - Explain how local tasks roll up into longer planning views.

5. The user recovers overdue work with Rescue Mode.
   - Show an overdue incomplete task.
   - Open Rescue Mode.
   - Demonstrate Do today, Move tomorrow, Move weekend, and Delete with confirmation.

6. The user controls the daily reminder from Settings.
   - Open Settings.
   - Show notification status.
   - Toggle the daily reminder off and on.

7. The user understands the app is local/offline.
   - Show the Settings text explaining local data and no cloud sync.
   - Explain that this MVP does not require login or a backend.

## 4. Screenshot and Video Checklist

Capture clean screenshots or short clips for:

- Today screen.
- Add task dialog.
- Task completed state.
- Week screen.
- Month screen.
- Year Orbit screen.
- Rescue Mode screen.
- Settings screen.
- Notification/reminder setting.

Suggested screenshot notes:

- Use a clean test dataset with realistic but simple task names.
- Avoid personal or private task text.
- Prefer a consistent emulator/device size for every screenshot.
- Use the same visual theme across all screenshots.
- Capture one screenshot per core screen before recording long videos.

Suggested video notes:

- Keep the first demo clip short, around 30 to 60 seconds.
- Show one complete flow instead of every edge case.
- Avoid fast clicking so viewers can follow the story.
- Focus on the app's differentiator: planning flow plus Rescue Mode.

## 5. GitHub README Improvement Plan

Future README updates should include:

- App summary.
- Feature list.
- Tech stack.
- Screenshots section.
- Demo GIF/video section.
- Current MVP status.
- What is not included yet.
- How to run locally.
- Beginner-safe architecture summary.

Suggested README structure:

1. Project title and short summary.
2. Why Orbit Planner exists.
3. Core MVP features.
4. Screenshots or demo GIF.
5. Tech stack.
6. Architecture overview.
7. How to run locally.
8. Current status.
9. Not included yet.
10. Future ideas.

Keep the README honest. It should say the app is a local/offline MVP and should not imply cloud sync, accounts, Play Store release, monetization, or advanced features exist.

## 6. Portfolio and LinkedIn Description Draft Ideas

These are draft points only, not final marketing copy.

Problem Orbit Planner solves:

- Many task apps focus only on today's list.
- Orbit Planner helps connect daily work to weekly, monthly, and yearly planning.
- Rescue Mode helps users recover overdue work without shame.

Technical stack:

- Kotlin.
- Jetpack Compose.
- Material-style UI.
- Room for local persistence.
- ViewModel and repository flow.
- WorkManager for a daily pending-task reminder.

What makes it different:

- Life Orbit planning model: Today, Week, Month, and Year.
- Rescue Mode for overdue incomplete tasks.
- Offline-first MVP with no account requirement.
- Calm, recovery-focused product tone.

What I learned building it:

- Building a native Android app in small phases.
- Wiring Compose screens to Room data.
- Keeping beginner-safe architecture readable.
- Designing a product flow around user emotion, not only task CRUD.
- Using WorkManager for safe local reminder behavior.
- Stabilizing an MVP before adding larger features.

## 7. Do Not Do Yet

Do not start these yet:

- Play Store release.
- Login or cloud sync.
- Advanced features.
- Major refactor.
- Paid marketing.
- Analytics.
- App monetization.

Also avoid:

- Navigation Compose migration.
- Hilt/DI migration.
- Exact alarms.
- Per-task reminders.
- Advanced recurring tasks.
- Room schema changes.
- Database version changes.

Phase 11A is for planning the portfolio/demo path, not expanding the app.

## 8. Recommended Phase 11 Breakdown

Recommended next phases:

- Phase 11A: Portfolio/demo preparation plan.
- Phase 11B: README portfolio upgrade.
- Phase 11C: Screenshot/demo asset checklist.
- Phase 11D: Short portfolio case-study draft.
- Phase 11E: Final portfolio review.

Beginner-safe order:

1. Plan the portfolio work.
2. Upgrade README.
3. Capture screenshots and demo assets.
4. Draft a short case study.
5. Review the whole portfolio presentation.

The next step should be README upgrade, not new app features.

## 9. Acceptance Checklist

- [x] Docs-only.
- [x] No Android source code changes.
- [x] No feature changes.
- [x] No UI changes.
- [x] No refactor.
- [x] No Room schema changes.
- [x] No database version changes.
- [x] No forbidden features added.
- [x] Clear portfolio/demo plan created.
- [x] Next step is README upgrade, not new app features.

