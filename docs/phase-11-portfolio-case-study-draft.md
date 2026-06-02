# Orbit Planner — Local-First Android Productivity MVP

## Short Intro

Orbit Planner is a local/offline Android productivity MVP for planning daily tasks, reviewing weekly and monthly progress, seeing a Year Orbit summary, and rescuing overdue tasks.

The app is designed around a simple idea: today's tasks should connect to a bigger planning flow, and unfinished work should be handled gently instead of creating guilt.

This is a portfolio/local MVP. It is not a Play Store release and does not include login, cloud sync, production users, downloads, reviews, or business results.

## Problem

Many normal to-do apps focus only on the current task list. That can make daily work feel disconnected from weekly progress, monthly focus, and yearly goals.

Unfinished tasks can also create guilt or stress. When a task is overdue, the user often needs a calm way to decide what should happen next instead of feeling like they failed.

The problem I wanted to explore:

- Normal to-do apps can feel disconnected from long-term goals.
- Unfinished tasks can create guilt or stress.
- Users need a simple way to plan today and recover overdue work.

## Goal

The MVP goal was to build a local-first Android planner that stays small, useful, and beginner-safe.

The main goals were:

- Build a local-first Android planner.
- Connect Today, Week, Month, and Year planning.
- Add Rescue Mode for overdue incomplete tasks.
- Keep the app beginner-safe and offline.

I intentionally kept the MVP focused. I did not add login, cloud sync, analytics, advanced recurring tasks, exact alarms, per-task reminders, Play Store release work, or a large architecture rewrite.

## My Role

I planned the app structure and implemented the Android MVP step by step.

I used Kotlin, Jetpack Compose, Room, ViewModel, WorkManager, and Material-style UI to build the local/offline app foundation.

I used Codex for guided implementation and review prompts. I kept the work phase-based so each step could be reviewed before moving forward.

This was not team, company, or client work. It was a portfolio MVP built to practice and demonstrate Android development.

## Core Features Built

The current local/offline MVP includes:

- Add task.
- Blank task validation.
- Complete and uncomplete task.
- Delete task.
- Today filtering.
- Week summary.
- Month summary.
- Year Orbit summary.
- Rescue Mode.
- Daily pending reminder.
- Settings reminder toggle and status.
- Local data and no cloud explanation.

These features are designed to show the full local task flow: create work, track it, review it across planning screens, recover overdue work, and control the daily reminder.

## Technical Implementation

Orbit Planner uses a beginner-friendly Android structure:

- Jetpack Compose for UI.
- Room for local persistence.
- Repository and ViewModel for data flow.
- WorkManager for the daily pending reminder.
- SharedPreferences for simple settings.
- Simple Compose state for screen switching.
- No Navigation Compose yet by design.

The current app stores tasks locally with Room. Compose screens read task data through ViewModel and repository flow. The notification foundation uses WorkManager for a daily pending-task reminder instead of exact alarms or per-task reminders.

Settings uses SharedPreferences for the daily reminder toggle, keeping the MVP simple and easy to understand.

## Design Decisions

Important design decisions:

- Offline-first MVP.
- Rescue Mode as the differentiator.
- Gentle, no-shame copy.
- No login or cloud sync in the MVP.
- No exact alarms or per-task reminders yet.
- Simple architecture before advanced DI or navigation.

Rescue Mode was the main product differentiator. Instead of treating overdue tasks as failure, the app gives the user calm options like doing the task today, moving it tomorrow, moving it to the weekend, or deleting it with confirmation.

I also kept the app local-first so the core MVP could work without accounts, backend setup, or network dependency.

## Challenges and Solutions

### Keeping Scope Small

Challenge: A productivity app can quickly grow into accounts, sync, recurring tasks, analytics, reminders, and many screens.

Solution: I kept the MVP focused on local persistence, basic task CRUD, planning summaries, Rescue Mode, notifications, Settings, QA, and portfolio preparation.

### Avoiding Feature Creep

Challenge: It was tempting to add larger features before stabilizing the local MVP.

Solution: I used phase-based work and review steps. Each phase had clear "do not add yet" rules so the project stayed beginner-safe.

### Connecting Planning Screens To Room Data

Challenge: Today, Week, Month, and Year needed to feel connected instead of being only static screens.

Solution: I used Room, repository methods, ViewModel flows, and simple date filtering so planning screens could summarize local task data.

### Handling Overdue Tasks Safely

Challenge: Overdue tasks needed recovery actions without making the user feel blamed.

Solution: Rescue Mode reads overdue incomplete tasks and offers gentle actions. The copy avoids shaming language and the delete path uses confirmation.

### Keeping Notifications Gentle

Challenge: Notifications can easily become noisy or misleading.

Solution: I kept the MVP to one daily pending-task reminder through WorkManager and included a Settings toggle/status area so the user has control.

### Maintaining Clean Phase-Based Commits

Challenge: A beginner project can become difficult to review if too many things change at once.

Solution: I kept phases small, used review prompts before commits, and separated planning, implementation, QA, and portfolio documentation.

## What I Learned

Building Orbit Planner helped me practice:

- Building a local Android MVP.
- Room and ViewModel flow.
- WorkManager basics.
- Compose UI state.
- Planning safe phases.
- Reviewing code before commits.
- Writing honest portfolio documentation.

I also learned how important scope control is. The app became stronger when I stabilized the local/offline MVP before adding larger features.

## Current Status

Current project status:

- Local/offline MVP completed.
- Manual QA passed.
- Portfolio/demo-ready.
- Not on Play Store yet.
- No login or cloud sync yet.

The app is ready to be presented as a local Android portfolio MVP. It should not be described as a production app, commercial product, Play Store release, or cloud-enabled app.

## Future Improvements

Optional future improvements:

- Screenshot/demo assets.
- Portfolio README visuals.
- Advanced task features.
- Better architecture later.
- Cloud sync/login later.
- Play Store preparation later.

These are future ideas, not current features or promises.

## Honest Closing Summary

Orbit Planner demonstrates my ability to plan, build, test, and document a local-first Android MVP with real persistence, reminders, and a differentiated productivity flow.

The project shows practical Android development skills while staying honest about its current scope: a local/offline portfolio MVP with no fake production claims, no fake users, and no fake metrics.

