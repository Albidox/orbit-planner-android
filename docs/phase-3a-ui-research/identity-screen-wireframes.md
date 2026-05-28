# Orbit Planner - Identity Screen Wireframes

Phase 3A Step 6 research and design planning only.

Status:
- Low-fidelity Figma wireframes were created successfully.
- The wireframes use the approved visual direction: Calm Orbit Command.
- The work focuses on layout, hierarchy, product meaning, and review questions.
- This is not final high-fidelity UI and is not Android implementation.
- Phase 3A Step 7 review decisions have been applied to the Figma wireframes.

Figma file:

https://www.figma.com/design/QRnnilawtju1kFgJQvRyPG

Figma file/page name:

`Orbit Planner - Identity Screen Wireframes`

Related approved direction board:

https://www.figma.com/board/NVV5LESqiCptV0UbwToyOZ

Source research:
- `docs/phase-3a-ui-research/ui-reference-collection.md`
- `docs/phase-3a-ui-research/visual-ui-directions.md`
- `docs/phase-3a-ui-research/reference-screenshots/`

## Screens Created

1. Today Screen Wireframe
2. Rescue Mode Screen Wireframe
3. Year Orbit Screen Wireframe
4. Wireframe Review Notes

All mobile screen wireframes use a 360 x 800 Android phone frame.

## Approved Direction Used

Direction:

`Calm Orbit Command`

Meaning:
- Use the orbit/ring identity from the stronger visual concept.
- Keep the screen structure practical and Material 3-friendly.
- Keep Rescue Mode emotionally supportive and recovery-first.
- Keep orbit visuals meaningful instead of decorative everywhere.

## Approved Wireframe Decisions

These decisions are approved for the next high-fidelity UI step:

1. Rescue Mode is a conditional entry first.
   - It appears on Today only when unfinished tasks exist.
   - It is not a permanent bottom navigation tab in the first version.
2. Year Orbit uses both planning layers.
   - 4 quarter arcs are the main high-level structure.
   - 12 month nodes are secondary detail.
3. Task cards show minimal metadata only.
   - Task title
   - Linked mission/goal
   - Energy/status label
4. Today Screen shows mission before progress.
   - `Today Mission` and `Weekly Mission` appear before the progress ring.
   - The progress ring supports the mission instead of dominating the screen.
5. Add Task supports two states.
   - Normal usage can use a FAB later.
   - Empty state should show a visible `Add your first task` card/button.

High-fidelity UI can start after this wireframe decision pass.

Reminder:

Do not implement Android UI until the final Figma UI is approved.

## Today Screen

Purpose:

Help the user understand today's plan and how it connects to larger goals.

Key layout decisions:
- Header starts with `Today Orbit`, date/greeting, and the planning chain.
- Top summary card is mission-first: `Today Mission` and `Weekly Mission` come before progress.
- Progress ring is smaller and supports the mission instead of dominating the first view.
- Task cards show title, linked mission/goal, and energy/status labels.
- Rescue Mode appears as a gentle entry card when unfinished tasks exist.
- Empty state includes a visible `Add your first task` card/button.
- Normal usage can later use a FAB for adding tasks.
- Bottom navigation is included as a placeholder.

Decision applied:

Today should prioritize mission clarity first, then show progress as supporting feedback.

## Rescue Mode Screen

Purpose:

Help the user recover unfinished tasks without guilt.

Key layout decisions:
- Supportive title uses the line `Let's rescue these tasks.`
- Explanation uses calm recovery copy: `Nothing is lost. Choose what happens next.`
- Rescue Mode is a conditional flow opened from Today when unfinished tasks exist.
- Rescue Mode is not a permanent bottom navigation tab in the first version.
- Unfinished task cards include direct recovery actions.
- Quick actions are shown for:
  - Do today
  - Move tomorrow
  - Move weekend
  - Convert to goal
  - Delete/cancel
- Empty state note is included so the final UI can support the no-pending-task state.
- Bottom navigation placeholder excludes Rescue as a permanent tab.

Decision applied:

Rescue Mode should appear conditionally from Today first.

## Year Orbit Screen

Purpose:

Show yearly goals and how months, weeks, and tasks connect.

Key layout decisions:
- Header introduces `Year Orbit` and the current yearly goal.
- Large circular orbit placeholder shows the year as a visual system.
- 4 quarter arcs are the main high-level planning structure.
- 12 month markers are shown as secondary detail around the orbit.
- A selected month focus card connects the orbit to monthly focus and weekly missions.
- A progress/rescue insight card shows that rescued tasks still count toward progress.
- Bottom navigation is included as a placeholder.

Decision applied:

Year Orbit should use both 4 quarter arcs and 12 month nodes.

## Wireframe Review Notes

What to review:
- Whether the three identity screens feel connected.
- Whether Today clearly explains why today's work matters.
- Whether Rescue Mode feels supportive instead of shameful.
- Whether Year Orbit feels understandable at first glance.
- Whether the bottom navigation labels feel right.

Approved decisions now shown in the Figma notes:
1. Rescue is conditional from Today, not permanent navigation.
2. Year Orbit uses 4 quarter arcs plus 12 month nodes.
3. Task cards keep title, linked mission/goal, and energy/status only.
4. Today shows mission before progress.
5. Add Task uses a FAB later plus a visible empty-state add card.

Intentionally not final yet:
- Final colors
- Gradients
- Icons
- Illustrations
- Animations
- Exact spacing tokens
- Production copy
- Data model behavior
- Android implementation details

## Next Step After This Update

High-fidelity UI can start next for:

1. Today Screen
2. Rescue Mode Screen
3. Year Orbit Screen

Reminder:

Do not implement Android UI until the final Figma UI screens are approved.

## Safety Notes

- Android source code was not edited.
- `MainActivity.kt` was not edited.
- Theme files were not edited.
- Room, Navigation Compose, WorkManager, notifications, and real task features were not added.
- No commit or push was performed.
