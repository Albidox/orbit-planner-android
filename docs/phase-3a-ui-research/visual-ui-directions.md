# Orbit Planner - Visual UI Directions

Phase 3A Step 4 research and design planning only.

Status:
- Figma/FigJam visual board/page was created successfully.
- The board contains four side-by-side concept sections with summaries, palettes, component style previews, mini screen previews, and pros/risks.
- This local file mirrors the FigJam board and records what the user should review before selecting a direction.

Requested Figma/FigJam board/page name:

`Orbit Planner — Visual UI Directions`

Existing research board:

https://www.figma.com/board/DSJUKkHtk390Ve4a0iZSdF

Visual direction board:

https://www.figma.com/board/NVV5LESqiCptV0UbwToyOZ

Input research files:
- `docs/phase-3a-ui-research/ui-reference-collection.md`
- `docs/phase-3a-ui-research/ui-website-access-check.md`
- `docs/phase-3a-ui-research/reference-screenshots/`

Originality rules:
- Do not copy any reference screen exactly.
- Use screenshots only as private inspiration/reference.
- Use free/already-owned references only.
- Build Orbit Planner's identity from Material Design rules, real product patterns, orbit/ring metaphor, Rescue Mode recovery, and the Daily Task -> Weekly Mission -> Monthly Focus -> Yearly Goal system.

## Visual Direction Overview

| Direction | Best for | Main feeling | Compose difficulty | Main risk |
| --- | --- | --- | --- | --- |
| Concept A - Calm Space Command Center | Memorable identity and premium dashboard mood | Focused, futuristic, personal command center | Medium | Can become too sci-fi or glow-heavy |
| Concept B - Soft Orbit Garden | Recovery-first emotional support | Gentle, calm, restorative | Medium | May feel less premium or less futuristic |
| Concept C - Minimal Mission Control | Practical daily use and Material 3 implementation | Clean, efficient, reliable | Easy | May feel too close to normal productivity apps |
| Recommended Hybrid - Calm Orbit Command | Best overall product identity | Premium, usable, memorable, emotionally supportive | Medium | Needs discipline so orbit visuals stay meaningful |

## Concept A - Calm Space Command Center

### Concept Summary

Calm Space Command Center is a dark premium productivity dashboard with subtle orbit/ring language. It should feel like a personal command center where users can see today, understand progress, and recover unfinished work without guilt.

Emotional feeling:
- Focused
- Premium
- Futuristic
- Capable

Why users may remember it:
- The orbit/ring metaphor makes the product feel distinct.
- Today, Year Orbit, and Rescue Mode can become memorable identity screens.
- It connects daily work to the larger year in a visual way.

### Color Palette

| Role | Hex | Swatch | Use |
| --- | --- | --- | --- |
| Background | `#07111F` | <span style="display:inline-block;width:48px;height:18px;background:#07111F;border:1px solid #22324A;"></span> | Main app background |
| Surface/card | `#101C2F` | <span style="display:inline-block;width:48px;height:18px;background:#101C2F;border:1px solid #2A3B55;"></span> | Cards, bottom nav, sheets |
| Primary accent | `#38DDF8` | <span style="display:inline-block;width:48px;height:18px;background:#38DDF8;border:1px solid #38DDF8;"></span> | Progress ring, active nav, main action |
| Secondary accent | `#8B5CF6` | <span style="display:inline-block;width:48px;height:18px;background:#8B5CF6;border:1px solid #8B5CF6;"></span> | Year Orbit, highlights |
| Success/completed | `#6EE7B7` | <span style="display:inline-block;width:48px;height:18px;background:#6EE7B7;border:1px solid #6EE7B7;"></span> | Completed state |
| Warning/energy | `#FBBF24` | <span style="display:inline-block;width:48px;height:18px;background:#FBBF24;border:1px solid #FBBF24;"></span> | Energy and attention states |

### Component Style Preview

Orbit card:
```text
+ Today Orbit --------------------+
| 68% daily progress      [ring]  |
| Weekly Mission: Build momentum  |
+---------------------------------+
```

Task card:
```text
+ Draft portfolio section --------+
| Linked to: Monthly Focus        |
| Energy: Normal    Status: Today |
+---------------------------------+
```

Progress ring:
```text
Outer ring: yearly progress
Inner ring: today progress
Accent: cyan glow, violet secondary arc
```

Bottom navigation:
```text
Today | Week | Month | Orbit | Settings
Active state: cyan icon + soft halo
```

Rescue Mode card:
```text
+ Let's rescue this task ----------+
| Finish onboarding notes          |
| [Do today] [Move tomorrow] [...] |
+---------------------------------+
```

### Mini Screen Previews

Today Screen:
```text
+--------------------------------+
| Today Orbit        68% ring     |
| Weekly Mission: Portfolio       |
|                                |
| Task card                      |
| Task card                      |
|                                |
| Rescue entry if needed          |
| Today Week Month Orbit Settings |
+--------------------------------+
```

Year Orbit Screen:
```text
+--------------------------------+
| 2026 Life Orbit                 |
|                                |
|      Jan -- Feb -- Mar          |
|   Dec      YEAR GOAL      Apr   |
|      Nov -- Oct -- Sep          |
|                                |
| Monthly Focus details           |
+--------------------------------+
```

Rescue Mode Screen:
```text
+--------------------------------+
| Let's rescue these tasks        |
| No guilt. Choose what fits.     |
|                                |
| Rescue card                    |
| Rescue card                    |
|                                |
| [Plan my day]                   |
+--------------------------------+
```

### Pros And Risks

Why this direction is strong:
- Strongest visual identity.
- Best fit for the "life-orbit command center" differentiator.
- Can make the app feel premium and memorable.

Risk:
- Too much glow can reduce readability.
- Too much space styling can feel gimmicky.
- Needs careful contrast testing.

Difficulty to implement later in Jetpack Compose:
- Medium

## Concept B - Soft Orbit Garden

### Concept Summary

Soft Orbit Garden makes Orbit Planner feel restorative and emotionally safe. The orbit metaphor becomes softer, like growth rings or seasonal planning, with Rescue Mode as the emotional center.

Emotional feeling:
- Gentle
- Calm
- Supportive
- Recovery-first

Why users may remember it:
- It feels kinder than normal productivity apps.
- It is especially strong for people who avoid apps after unfinished tasks.
- Rescue Mode can feel like relief instead of pressure.

### Color Palette

| Role | Hex | Swatch | Use |
| --- | --- | --- | --- |
| Background | `#132322` | <span style="display:inline-block;width:48px;height:18px;background:#132322;border:1px solid #2C4742;"></span> | Main app background |
| Surface/card | `#1F3432` | <span style="display:inline-block;width:48px;height:18px;background:#1F3432;border:1px solid #3D5E58;"></span> | Cards and sheets |
| Primary accent | `#8DEBCF` | <span style="display:inline-block;width:48px;height:18px;background:#8DEBCF;border:1px solid #8DEBCF;"></span> | Gentle progress, primary action |
| Secondary accent | `#C4B5FD` | <span style="display:inline-block;width:48px;height:18px;background:#C4B5FD;border:1px solid #C4B5FD;"></span> | Orbit markers and calm highlights |
| Success/completed | `#A7F3D0` | <span style="display:inline-block;width:48px;height:18px;background:#A7F3D0;border:1px solid #A7F3D0;"></span> | Completed state |
| Warning/energy | `#FDBA74` | <span style="display:inline-block;width:48px;height:18px;background:#FDBA74;border:1px solid #FDBA74;"></span> | Energy and soft attention |

### Component Style Preview

Orbit card:
```text
+ Today's Focus ------------------+
| Soft ring: steady progress      |
| Weekly Mission: Keep growing    |
+---------------------------------+
```

Task card:
```text
+ Read Kotlin notes --------------+
| Energy: Low    Good for today   |
| Linked focus: Learning          |
+---------------------------------+
```

Progress ring:
```text
Soft arcs, no harsh neon.
Progress feels like growth, not performance pressure.
```

Bottom navigation:
```text
Today | Week | Month | Orbit | Settings
Active state: mint pill, soft icon
```

Rescue Mode card:
```text
+ Nothing is lost ----------------+
| Choose what this task becomes   |
| [Do today] [Move] [Convert]     |
+---------------------------------+
```

### Mini Screen Previews

Today Screen:
```text
+--------------------------------+
| Today's Focus      soft ring    |
| A small plan is enough          |
|                                |
| Gentle task card               |
| Gentle task card               |
|                                |
| Recovery note                  |
| Today Week Month Orbit Settings |
+--------------------------------+
```

Year Orbit Screen:
```text
+--------------------------------+
| Year Garden                     |
|                                |
| Spring arc    Summer arc        |
|   yearly goal at center         |
| Autumn arc    Winter arc        |
|                                |
| Monthly focus cards             |
+--------------------------------+
```

Rescue Mode Screen:
```text
+--------------------------------+
| Nothing is lost                 |
| Pick a kinder next step         |
|                                |
| Rescue card                    |
| Rescue card                    |
|                                |
| [Choose for me] [Review all]    |
+--------------------------------+
```

### Pros And Risks

Why this direction is strong:
- Best emotional fit for Rescue Mode.
- Most supportive for stressed users.
- Distinct from cold corporate productivity tools.

Risk:
- May feel less premium or futuristic.
- Orbit identity could become too subtle.
- Could drift toward wellness-app softness.

Difficulty to implement later in Jetpack Compose:
- Medium

## Concept C - Minimal Mission Control

### Concept Summary

Minimal Mission Control focuses on everyday usability, clean hierarchy, and Material 3-friendly structure. It is the most practical concept and the easiest to implement, with orbit identity used sparingly.

Emotional feeling:
- Clear
- Efficient
- Reliable
- Calm

Why users may remember it:
- It feels easy enough to use every day.
- It keeps planning fast and low friction.
- It can still become memorable if Year Orbit and Rescue Mode are distinctive.

### Color Palette

| Role | Hex | Swatch | Use |
| --- | --- | --- | --- |
| Background | `#0F172A` | <span style="display:inline-block;width:48px;height:18px;background:#0F172A;border:1px solid #26344F;"></span> | Main app background |
| Surface/card | `#1E293B` | <span style="display:inline-block;width:48px;height:18px;background:#1E293B;border:1px solid #334155;"></span> | Cards, sheets, nav |
| Primary accent | `#22D3EE` | <span style="display:inline-block;width:48px;height:18px;background:#22D3EE;border:1px solid #22D3EE;"></span> | Primary action and active state |
| Secondary accent | `#818CF8` | <span style="display:inline-block;width:48px;height:18px;background:#818CF8;border:1px solid #818CF8;"></span> | Orbit markers |
| Success/completed | `#34D399` | <span style="display:inline-block;width:48px;height:18px;background:#34D399;border:1px solid #34D399;"></span> | Completed state |
| Warning/energy | `#F59E0B` | <span style="display:inline-block;width:48px;height:18px;background:#F59E0B;border:1px solid #F59E0B;"></span> | Priority and energy |

### Component Style Preview

Orbit card:
```text
+ Daily Mission ------------------+
| Today 5/8 tasks       62%       |
| Year link: Skill growth         |
+---------------------------------+
```

Task card:
```text
+ Write app notes ----------------+
| Today | Normal energy | Medium  |
+---------------------------------+
```

Progress ring:
```text
Small, readable ring.
No decorative glow unless active.
```

Bottom navigation:
```text
Today | Week | Month | Year | Settings
Active state: simple cyan icon/text
```

Rescue Mode card:
```text
+ Unfinished task ----------------+
| Recommended: Move to tomorrow   |
| [Accept] [Change]               |
+---------------------------------+
```

### Mini Screen Previews

Today Screen:
```text
+--------------------------------+
| Today       5 of 8 complete     |
| Mission: Portfolio launch       |
|                                |
| Now                            |
| Task card                      |
| Later                          |
| Task card                      |
| Today Week Month Year Settings |
+--------------------------------+
```

Year Orbit Screen:
```text
+--------------------------------+
| Year Overview                   |
|                                |
| Month dots around center goal   |
| Simple progress states          |
|                                |
| Selected month details          |
+--------------------------------+
```

Rescue Mode Screen:
```text
+--------------------------------+
| Rescue Queue                    |
| 3 tasks need a next step        |
|                                |
| Task + recommended action       |
| Task + recommended action       |
|                                |
| [Finish planning]               |
+--------------------------------+
```

### Pros And Risks

Why this direction is strong:
- Easiest to use daily.
- Strongest implementation fit for Jetpack Compose and Material 3.
- Clear hierarchy reduces beginner confusion.

Risk:
- Could feel like a normal to-do app.
- Needs stronger visual identity in Today, Year Orbit, and Rescue Mode.
- May need more emotional warmth in copy.

Difficulty to implement later in Jetpack Compose:
- Easy

## Recommended Hybrid - Calm Orbit Command

### Concept Summary

Calm Orbit Command combines Concept A's memorable orbit identity, Concept C's practical Material 3 structure, and Concept B's gentle Rescue Mode tone. It should feel premium, usable, original, and emotionally supportive.

Emotional feeling:
- Calm
- Premium
- Focused
- Friendly
- Recovery-oriented

Why users may remember it:
- The app has a clear signature: Today Orbit, Year Orbit, and Rescue Mode.
- It feels useful every day, not only pretty in screenshots.
- It turns unfinished tasks into recovery decisions instead of guilt.

### Color Palette

| Role | Hex | Swatch | Use |
| --- | --- | --- | --- |
| Background | `#08111F` | <span style="display:inline-block;width:48px;height:18px;background:#08111F;border:1px solid #22324A;"></span> | Main app background |
| Surface/card | `#121D2E` | <span style="display:inline-block;width:48px;height:18px;background:#121D2E;border:1px solid #2B3C55;"></span> | Cards and nav surfaces |
| Primary accent | `#2DD4F8` | <span style="display:inline-block;width:48px;height:18px;background:#2DD4F8;border:1px solid #2DD4F8;"></span> | Primary action, progress, active nav |
| Secondary accent | `#A78BFA` | <span style="display:inline-block;width:48px;height:18px;background:#A78BFA;border:1px solid #A78BFA;"></span> | Year Orbit and focus highlights |
| Success/completed | `#5EEAD4` | <span style="display:inline-block;width:48px;height:18px;background:#5EEAD4;border:1px solid #5EEAD4;"></span> | Completed state |
| Warning/energy | `#FBBF24` | <span style="display:inline-block;width:48px;height:18px;background:#FBBF24;border:1px solid #FBBF24;"></span> | Energy level and gentle attention |

### Component Style Preview

Orbit card:
```text
+ Today Orbit --------------------+
| 68% done             ring       |
| Daily Task -> Yearly Goal       |
| Mission: Ship portfolio update  |
+---------------------------------+
```

Task card:
```text
+ Build UI direction board -------+
| Weekly Mission: Phase 3A        |
| Energy: Normal    Priority: Med |
| [Complete]                      |
+---------------------------------+
```

Progress ring:
```text
Today ring: cyan.
Year orbit markers: violet.
Completed segments: teal.
Glow only on current/active state.
```

Bottom navigation:
```text
Today | Week | Month | Orbit | Settings
Active state: cyan icon, small orbit dot, accessible label.
```

Rescue Mode card:
```text
+ Let's rescue this --------------+
| This still matters. Pick a next |
| step that fits today.           |
| [Do today] [Move] [Convert]     |
+---------------------------------+
```

### Mini Screen Previews

Today Screen:
```text
+--------------------------------+
| Today Orbit        68%          |
| Daily Task -> Weekly Mission    |
|                                |
| Orbit card                     |
| Task card                      |
| Task card                      |
|                                |
| Gentle rescue strip             |
| Today Week Month Orbit Settings |
+--------------------------------+
```

Year Orbit Screen:
```text
+--------------------------------+
| Year Orbit 2026                 |
|                                |
| 12 month nodes around goal      |
| current month glows softly      |
| completed months teal           |
|                                |
| Monthly Focus + Weekly Missions |
+--------------------------------+
```

Rescue Mode Screen:
```text
+--------------------------------+
| Let's rescue these tasks        |
| No shame. Just the next orbit.  |
|                                |
| Rescue task card               |
| Rescue task card               |
|                                |
| [Plan today] [Move all later]   |
+--------------------------------+
```

### Pros And Risks

Why this direction is strong:
- Most balanced direction.
- Strong visual identity without sacrificing usability.
- Best match for offline-first, beginner-friendly Android implementation later.
- Supports the product promise: recover and continue without guilt.

Risk:
- Needs discipline: if every screen uses orbit visuals, the metaphor becomes noise.
- Needs contrast checks for dark UI.
- Needs clear component rules before final UI.

Difficulty to implement later in Jetpack Compose:
- Medium

## Strongest Visual Direction

The strongest direction is:

`Recommended Hybrid - Calm Orbit Command`

Why:
- It keeps Concept A's memorable orbit/ring identity.
- It keeps Concept C's practical Material 3 structure.
- It uses Concept B's emotional softness where it matters most: Rescue Mode, empty states, and gentle copy.
- It gives Orbit Planner a clear product signature without making every screen overly decorative.

## Approved Direction

Approved direction:

`Calm Orbit Command`

Approval reason:

This direction combines a memorable orbit/ring identity, practical Material 3-friendly structure, and gentle recovery-first Rescue Mode tone.

Next wireframe screens:

1. Today Screen
2. Rescue Mode Screen
3. Year Orbit Screen

Reminder:

No Android implementation until Figma wireframes and final UI are approved.

## What The User Should Review Before Selecting

Review these choices before the next step:

1. Overall mood: premium command-center vs softer recovery-first vs minimal practical.
2. Color direction: deep navy/cyan/violet vs softer teal/mint/lavender.
3. Amount of glow: subtle only, medium identity glow, or very restrained.
4. Year Orbit strength: bold radial visual or simple month-dot overview.
5. Rescue Mode tone: practical queue, gentle recovery, or hybrid.
6. Implementation comfort: Concept C is easiest; Concept A and Hybrid need more custom drawing later.

## Next Recommended Step

Review the FigJam visual comparison board:

https://www.figma.com/board/NVV5LESqiCptV0UbwToyOZ

Then select one direction before creating wireframes.
