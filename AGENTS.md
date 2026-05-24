# AGENTS.md — Orbit Planner Android App

## Project Name

Orbit Planner

## Product Idea

Orbit Planner is a native Android productivity app that connects daily tasks with weekly missions, monthly focus areas, and yearly goals.

The app should not feel like a normal boring to-do list. It should feel like a calm personal life command center where the user can plan today, rescue pending work, and see how daily effort connects to the bigger year.

## Target User

Beginner students, developers, freelancers, workers, and self-improvement users who want a simple but meaningful planner.

## Main Differentiator

The app’s special feature is the “Life Orbit” system:

Daily Task → Weekly Mission → Monthly Focus → Yearly Goal

The user should feel that every small task is connected to a bigger life path.

## Core MVP Features

Build only these first:

1. Today task list
2. Add/edit/delete task
3. Mark task complete
4. Daily progress indicator
5. Week planner
6. Month planner
7. Year planner
8. Pending task notification
9. Rescue Mode for unfinished tasks
10. Local offline storage

## Do Not Build in MVP

Do not add these unless explicitly requested later:

- Login
- Backend
- Cloud sync
- Payment
- Social sharing
- AI assistant
- Team collaboration
- Complex analytics
- Subscription system

## Tech Stack

Use:

- Kotlin
- Jetpack Compose
- Material 3
- MVVM architecture
- Room database
- Kotlin Coroutines
- Flow / StateFlow
- WorkManager for scheduled background checks
- NotificationManager for local notifications
- Navigation Compose

## Design Direction

Style should be:

- Premium
- Calm
- Futuristic
- Friendly
- Minimal
- Not childish
- Not too corporate

Suggested visual language:

- Deep navy or dark background
- Cyan/violet accent
- Rounded cards
- Soft glow
- Smooth micro animations
- Orbit/ring visual metaphor
- Clean typography
- Large readable buttons

## Main Screens

Create the app with these screens:

1. Splash Screen
2. Onboarding Screen
3. Today Screen
4. Add/Edit Task Screen
5. Task Detail Screen
6. Week Planner Screen
7. Month Planner Screen
8. Year Orbit Screen
9. Rescue Mode Screen
10. Settings Screen

## Architecture Rules

Use this structure:

app/src/main/java/com/orbitplanner/

- ui/
  - screens/
  - components/
  - theme/
- data/
  - local/
  - model/
  - repository/
- domain/
  - usecase/
- notification/
- navigation/
- util/

Rules:

1. UI screens should not directly access database.
2. Use ViewModel between UI and repository.
3. Keep business logic outside composables when possible.
4. Keep files small and beginner-readable.
5. Add simple comments where a beginner may get confused.
6. Do not over-engineer.

## Database Entities

Start with these entities:

### Task

Fields:

- id
- title
- description
- date
- time
- priority
- status
- energyLevel
- goalId
- repeatType
- createdAt
- completedAt

### Goal

Fields:

- id
- title
- description
- year
- month
- category
- progress
- status

### Reminder

Fields:

- id
- taskId
- reminderTime
- reminderType
- isEnabled

## Task Status Values

Use:

- PENDING
- COMPLETED
- MOVED
- CANCELLED

## Priority Values

Use:

- LOW
- MEDIUM
- HIGH

## Energy Values

Use:

- LOW_ENERGY
- NORMAL
- HIGH_ENERGY

## Notification Rules

The app should support:

1. Morning planning reminder
2. Evening pending task reminder
3. Weekly review reminder
4. Goal reminder

Important:

- Ask notification permission on Android 13+
- Create notification channel
- Notification tap should open the correct screen
- Do not spam user
- Let user disable reminders in Settings

## Rescue Mode Rules

If a task is pending after the planned day, show it in Rescue Mode.

Rescue actions:

1. Do today
2. Move to tomorrow
3. Move to weekend
4. Convert to goal
5. Delete

Tone should be gentle. Do not shame the user.

Use copy like:

“You have unfinished tasks. Let’s rescue them.”

Do not use negative copy like:

“You failed your tasks.”

## Figma Workflow

Before coding a screen:

1. Create wireframe in Figma
2. Create final UI screen
3. Add component names
4. Add spacing notes
5. Add color/token notes
6. Then ask Codex to implement only that screen

## Codex Working Rules

When working with Codex:

1. Always plan first.
2. Do not generate code until the user says BEGIN.
3. Explain which files will be changed before changing them.
4. Make one small feature at a time.
5. After every feature, run build/check.
6. Never silently delete existing code.
7. Never rewrite the whole app unless explicitly asked.
8. Keep code beginner-friendly.
9. After coding, explain what changed in simple language.

## Subagent Usage

Use Codex subagents explicitly.

Recommended agents:

- product-manager: product decisions and feature scope
- ui-designer: screen layout and user experience
- mobile-developer: Android implementation planning
- kotlin-specialist: Kotlin and Compose correctness
- code-reviewer: review generated code
- qa-expert: testing checklist
- documentation-engineer: update docs and beginner notes

Example delegation prompt:

“Use product-manager, ui-designer, and mobile-developer subagents to review this feature. Do not code. Return a beginner-friendly implementation plan with files to create or edit.”

## Development Phases

### Phase 1

Create project skeleton.

### Phase 2

Create theme, navigation, and placeholder screens.

### Phase 3

Create Today task screen.

### Phase 4

Add Room database.

### Phase 5

Add task CRUD.

### Phase 6

Add week planner.

### Phase 7

Add month planner.

### Phase 8

Add year planner.

### Phase 9

Add notifications.

### Phase 10

Add Rescue Mode.

### Phase 11

Add polish and animations.

### Phase 12

Prepare beta release.

## Beginner Safety Rule

This project is for a complete beginner using vibe coding.

So always:

- Keep steps small
- Explain simply
- Avoid unnecessary complexity
- Give exact file paths
- Give testing steps
- Ask before large changes
- Prefer working MVP over perfect architecture

## Product Quality Rule

The app should feel original, useful, and emotionally friendly.

The user should feel:

“I opened this app because it helps me recover my day, not because it makes me feel guilty.”