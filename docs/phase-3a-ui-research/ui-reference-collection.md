# Orbit Planner UI Reference Collection

Phase 3A Step 3B research and design planning only.

Questionnaire is removed from the current plan. In this document, "survey" means UI inspiration research across design websites.

Safety notes:
- No Android source code was edited.
- No Google Forms questionnaire was created.
- No paid templates, subscriptions, trials, purchases, extensions, account changes, invites, or password entry were used.
- Screenshots are private inspiration references only.
- Do not copy any exact screen. Use the patterns to create an original Orbit Planner identity.

Screenshot folder:

`docs/phase-3a-ui-research/reference-screenshots/`

## Reference Summary

- References collected: 29
- Screenshots captured: 18
- Best free-first sources: Material Design 3, Android Developers docs, Behance, Dribbble, Figma Community free previews, Pinterest mood searches, Google Play Store screenshots.
- Best practical product sources: Google Play competitor screenshots, Refero, Android Developers Material 3 docs.
- Best mood sources: Pinterest, Dribbble, Behance, Awwwards, Godly.
- Paid or blocked areas avoided: paid Figma templates, Page Flows/Pttrns-style full access, paid trials, paid design libraries.

## References

| # | Source website | Search keyword used | Reference title/name | URL | Screenshot path | What looks good | Emotional feeling | Orbit Planner can learn | Avoid copying exactly | Original Orbit Planner angle |
| --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- |
| 1 | Material Design 3 | cards | Material 3 Cards | https://m3.material.io/components/cards/overview | `reference-screenshots/material-design-3-components-cards.png` | Clear card hierarchy, contained/elevated card rules, strong spacing guidance. | Calm and reliable. | Build task cards with predictable states and accessibility. | Do not make cards generic or plain. | Task cards can include orbit metadata: daily task, linked mission, energy level, and rescue state. |
| 2 | Material Design 3 | navigation bar | Material 3 Navigation Bar | https://m3.material.io/components/navigation-bar/overview | Not captured | Familiar mobile bottom navigation pattern. | Stable and easy to understand. | Use bottom navigation for Today, Week, Month, Year, Settings or Rescue entry. | Do not overcrowd nav with too many labels. | Orbit Planner can make Year Orbit a memorable primary tab without breaking Material patterns. |
| 3 | Android Developers | compose material 3 | Material Design 3 in Compose | https://developer.android.com/develop/ui/compose/designsystems/material3 | `reference-screenshots/android-compose-material-3-docs.png` | Confirms Compose Material 3 theming and component support. | Practical and buildable. | Later UI should map cleanly to Compose Material 3. | Do not design impossible custom controls first. | Use Compose-friendly cards, navigation, progress indicators, sheets, and dialogs before custom drawing. |
| 4 | Figma Community | android material 3 ui kit | Figma Community Material 3 UI Kit Search | https://www.figma.com/community/search?query=android%20material%203%20ui%20kit&resource_type=mixed | `reference-screenshots/figma-community-material-3-ui-kit-search.png` | Free previews show component systems and Android UI kit structure. | Organized and professional. | Use component naming, tokens, and system thinking. | Do not duplicate paid or unclear-license files. | Build our own simple Orbit Planner components: task card, orbit ring, rescue card, bottom nav item. |
| 5 | Figma Community | productivity app ui | Productivity app UI resources | https://www.figma.com/community/search?query=productivity%20app%20ui&resource_type=mixed | Not captured | Shows how productivity apps group dashboards, task cards, and charts. | Motivated and polished. | Compare density, section grouping, and empty states. | Do not use a template as the app identity. | Use only free previews as directional input, then create Orbit-specific screen plans. |
| 6 | Pinterest | dark productivity app ui | Dark productivity app UI mood | https://www.pinterest.com/search/pins/?q=dark%20productivity%20app%20ui&rs=typed | `reference-screenshots/pinterest-dark-productivity-app-ui.png` | Dark dashboard moods, glow cards, progress widgets. | Focused, premium, slightly futuristic. | Good for color mood and dashboard atmosphere. | Do not make every element glow. | Use glow only around progress/orbit moments, not every card. |
| 7 | Pinterest | orbit ui design | Orbit UI design mood | https://www.pinterest.com/search/pins/?q=orbit%20ui%20design&rs=typed | `reference-screenshots/pinterest-orbit-ui-design.png` | Circular layouts, rings, orbital visual language. | Distinct and memorable. | Use orbit/ring metaphor for year and progress. | Do not turn the app into a heavy space illustration. | Year Orbit can be a clean radial planner, while daily screens stay functional. |
| 8 | Pinterest | calm productivity aesthetic | Calm productivity aesthetic | https://www.pinterest.com/search/pins/?q=calm%20productivity%20aesthetic&rs=typed | Not captured | Softer palettes, quiet spacing, gentle planning mood. | Supportive and low-pressure. | Rescue Mode should feel like recovery, not failure. | Do not make it too decorative or lifestyle-only. | Pair calm language with practical action buttons. |
| 9 | Dribbble | productivity app ui | Productivity app UI search | https://dribbble.com/search/productivity%20app%20ui | `reference-screenshots/dribbble-productivity-app-ui.png` | Strong visual polish and app-card presentation. | Energetic and aspirational. | Learn card composition, progress summaries, and visual rhythm. | Do not copy showcase-only layouts that cannot support real task lists. | Keep the first screen usable: tasks, progress, rescue entry, and clear add action. |
| 10 | Dribbble | dark dashboard mobile app | Dark dashboard mobile app search | https://dribbble.com/search/dark%20dashboard%20mobile%20app | `reference-screenshots/dribbble-dark-dashboard-mobile-app.png` | Premium dark UI, metrics cards, glowing accents. | Command-center focus. | Useful for Today dashboard and monthly/yearly overview. | Avoid chart overload and tiny unreadable text. | One main orbit/progress visual should anchor the screen, with simple task cards below. |
| 11 | Dribbble | goal tracking app ui | Goal tracking app UI search | https://dribbble.com/search/goal%20tracking%20app%20ui | Not captured | Goal cards, progress rings, milestone visuals. | Encouraging and goal-oriented. | Connect small tasks to bigger outcomes. | Do not make goals feel like pressure. | Goals should answer "why today matters" without shaming unfinished work. |
| 12 | Behance | planner app ui ux | Planner App UI UX projects | https://www.behance.net/search/projects/planner%20app%20ui%20ux | `reference-screenshots/behance-planner-app-ui-ux.png` | Full case-study presentation and screen-set thinking. | Thoughtful and complete. | Use screen families, rationale, and brand consistency. | Do not copy brand styles or exact layouts. | Orbit Planner should present as one connected system: Today, Week, Month, Year, Rescue. |
| 13 | Behance | dark mobile app ui | Dark Mobile App UI projects | https://www.behance.net/search/projects/dark%20mobile%20app%20ui | Not captured | Dark UI branding and polished mobile screen sets. | Premium and immersive. | Useful for color contrast and atmospheric restraint. | Avoid dark UI that hides hierarchy. | Use dark surfaces with clear text contrast and purposeful cyan/violet accents. |
| 14 | Behance | goal tracker app | Goal tracker app projects | https://www.behance.net/search/projects/goal%20tracker%20app | Not captured | Goal dashboards, milestones, habit/productivity blends. | Motivating and structured. | Build a stronger bridge from tasks to yearly goals. | Avoid turning productivity into complex analytics. | Year Orbit should be memorable but simple enough for a beginner. |
| 15 | Refero | productivity | Refero productivity search | https://refero.design/search?q=productivity | `reference-screenshots/refero-productivity-search.png` | Real product screens and practical layouts. | Grounded and product-realistic. | Helps check what actual task/dashboard flows look like. | Do not clone any real product screen. | Use Refero as a realism check against overly fancy gallery inspiration. |
| 16 | Refero | task management calendar reminders | Refero task/calendar/reminder research | https://refero.design/search?q=task%20management | Not captured | Real patterns for onboarding, settings, reminders, dashboards. | Useful and familiar. | Settings/reminders should be simple and predictable. | Avoid borrowing exact screens. | Use practical flows, then apply Orbit Planner copy and hierarchy. |
| 17 | UIUX Showcase | mobile app design inspiration | Mobile App Design Inspiration | https://uiuxshowcase.com/mobile-app-design-inspiration/ | Not captured | Curated mobile examples and resource links. | Broad creative scan. | Good secondary source for mobile composition. | Avoid treating curated galleries as implementation specs. | Use it to widen the reference pool, then filter through Material 3 and Orbit goals. |
| 18 | Awwwards | mobile apps | Mobile and Apps gallery | https://www.awwwards.com/websites/mobile-apps/ | `reference-screenshots/awwwards-mobile-apps-gallery.png` | Premium presentation, motion mood, visual polish. | High-end and memorable. | Useful for branding and motion inspiration. | Do not copy experimental web layouts into Android. | Borrow restraint, spacing, and polish rather than web-specific interactions. |
| 19 | Landingfolio | product landing mood | Landingfolio product mood | https://www.landingfolio.com/ | Not captured | Product hero and brand presentation ideas. | Clean and marketable. | Useful later for launch/portfolio page tone. | Do not design Android screens like landing pages. | Keep app screens functional, but use the brand clarity for future app presentation. |
| 20 | Godly | premium web mood | Godly premium web inspiration | https://godly.website/ | `reference-screenshots/godly-premium-web-mood.png` | Premium color atmosphere and layout restraint. | Modern and curated. | Helps avoid cheap-looking futuristic styling. | Do not use web-heavy layouts in the app UI. | Translate premium mood into mobile surfaces, spacing, and accent discipline. |
| 21 | Google Play Store | Todoist | Todoist app screenshots | https://play.google.com/store/apps/details?id=com.todoist | `reference-screenshots/google-play-todoist-app-screenshots.png` | Simple task hierarchy, trusted productivity positioning. | Clear and dependable. | Learn clarity, fast add flow, and cross-platform polish. | Avoid becoming a generic task list. | Add visible task-to-goal connection and Rescue Mode identity. |
| 22 | Google Play Store | TickTick | TickTick app screenshots | https://play.google.com/store/apps/details?id=com.ticktick.task | `reference-screenshots/google-play-ticktick-app-screenshots.png` | Rich planner features, calendar/task blend. | Capable and complete. | Week/month planner expectations and task organization. | Avoid too many features on day one. | Start with a calm MVP hierarchy and grow later. |
| 23 | Google Play Store | Google Tasks | Google Tasks screenshots | https://play.google.com/store/apps/details?id=com.google.android.apps.tasks | `reference-screenshots/google-play-google-tasks-screenshots.png` | Minimal task list and clean add flow. | Lightweight and low friction. | Keep Add Task fast and beginner-friendly. | Avoid being too plain or forgettable. | Add emotional recovery and orbit progress to make it distinct. |
| 24 | Google Play Store | Microsoft To Do | Microsoft To Do screenshots | https://play.google.com/store/apps/details?id=com.microsoft.todos | `reference-screenshots/google-play-microsoft-to-do-screenshots.png` | List grouping, My Day concept, simple reminders. | Friendly and organized. | Today screen can focus on a small meaningful plan. | Avoid copying My Day structure directly. | Use "Today Orbit" or "Today Mission" with a yearly-goal connection. |
| 25 | Google Play Store | Notion | Notion app screenshots | https://play.google.com/store/apps/details?id=notion.id | `reference-screenshots/google-play-notion-app-screenshots.png` | Flexible blocks, clean information density. | Powerful and customizable. | Useful for hierarchy and notes/task blend. | Avoid blank-canvas complexity for beginners. | Orbit Planner should be guided, not open-ended. |
| 26 | Google Play Store | Habitica | Habitica app screenshots | https://play.google.com/store/apps/details?id=com.habitrpg.android.habitica | Not captured | Gamified habits and rewards. | Playful and motivating. | Learn how memorable identity helps retention. | Avoid childish RPG styling for this product. | Use orbit identity and gentle recovery instead of game rewards. |
| 27 | Google Play Store | Structured daily planner | Structured search screenshots | https://play.google.com/store/search?q=Structured%20daily%20planner&c=apps | `reference-screenshots/google-play-structured-search-screenshots.png` | Timeline/planner positioning and day structure. | Ordered and calm. | Add task flow can support time blocks later. | Avoid exact timeline imitation. | Combine simple task cards with optional time/energy planning. |
| 28 | Google Play Store | Any.do | Any.do app screenshots | https://play.google.com/store/apps/details?id=com.anydo | `reference-screenshots/google-play-anydo-app-screenshots.png` | Task/calendar/reminder blend and practical onboarding claims. | Useful and efficient. | Settings/reminders should be easy to understand. | Avoid too many reminder prompts. | Reminder design should support gentle planning, not spam. |
| 29 | Google Play Store | Google Calendar | Google Calendar app screenshots | https://play.google.com/store/apps/details?id=com.google.android.calendar | Not captured | Familiar calendar hierarchy and event scanning. | Predictable and practical. | Month planner should respect calendar conventions. | Avoid becoming only a calendar clone. | Month view should connect focus areas and rescued tasks to visible days. |

## Orbit Planner Areas Covered

| Orbit Planner area | Useful sources | Notes |
| --- | --- | --- |
| Today screen | Todoist, Microsoft To Do, Google Tasks, Dribbble dashboards, Material 3 cards | Needs a calm daily plan, progress, rescue entry, and Add Task. |
| Task card | Material 3 cards, Google Tasks, Todoist, Dribbble | Card should show title, status, energy, linked mission, and gentle overdue state. |
| Add task flow | Google Tasks, Todoist, Any.do, Material 3 sheets/dialogs | Keep fast and simple first; advanced fields can be progressive. |
| Progress ring | Pinterest orbit UI, Dribbble goal tracking, dark dashboard searches | Use one primary ring/progress visual, not many competing charts. |
| Bottom navigation | Material 3 navigation bar, Google Play competitors | Use predictable tabs and concise labels. |
| Week planner | TickTick, Structured, calendar references | Use week strip plus mission grouping. |
| Month planner | Google Calendar, TickTick, Any.do | Use calendar familiarity with monthly focus overlay. |
| Year Orbit / circular yearly view | Pinterest orbit UI, Dribbble goal tracking, Material motion/shape | Make this the strongest identity screen. |
| Rescue Mode / recovery flow | Orbit Planner product rules, Microsoft To Do My Day, Google Tasks simplicity | Gentle copy and clear recovery actions matter more than visual drama. |
| Empty states | Pinterest calm aesthetic, Material 3 empty-state thinking, Google apps | Empty states should be emotionally supportive. |
| Settings / reminders screen | Any.do, Google Calendar, Android docs | Keep notification controls clear and non-spammy. |
| Dark premium dashboard | Dribbble, Pinterest, Awwwards, Godly | Use dark background, restrained glow, strong contrast. |
| Calm onboarding | Behance case studies, Landingfolio, Google Play screenshots | Explain the orbit system without marketing overload. |
| Goal tracking | Behance goal tracker, Dribbble goal UI, Habitica identity | Goals should motivate without guilt. |
| Calendar-style planning | Google Calendar, TickTick, Structured search | Use familiar calendar structure with Orbit-specific focus layers. |

## Original UI Concepts

### Concept A - Calm Space Command Center

Visual style:
- Dark premium productivity dashboard with subtle orbit/ring language.
- Feels like a personal command center, not a sci-fi game.
- Uses depth, glow, and circular progress only where they help meaning.

Color direction:
- Background: deep navy or near-black blue.
- Primary accent: cyan.
- Secondary accent: violet.
- Support colors: soft mint for completed, amber for energy, muted rose only for gentle attention.

Card style:
- Rounded Material 3 cards with 8-16 dp radius.
- Subtle border, low opacity surfaces, and very light glow around selected/progress elements.
- Task cards stay readable and practical.

Typography feeling:
- Clean, modern, readable.
- Calm headings, not oversized.
- Strong hierarchy between date, mission, task title, and metadata.

Home screen idea:
- Top: "Today Orbit" with daily progress ring and linked weekly mission.
- Middle: task cards grouped by energy or time.
- Bottom: Rescue Mode entry appears only when unfinished tasks exist.
- Floating Add Task action uses Material 3 behavior.

Year Orbit idea:
- Circular year map with 12 month nodes around a center yearly goal.
- Each month has a progress pulse or small ring.
- Tap a month to reveal monthly focus and connected weekly missions.

Rescue Mode idea:
- A calm recovery screen with copy like "Let's rescue these tasks."
- Each unfinished task gets quick actions: Do today, Move tomorrow, Move weekend, Convert to goal, Delete.
- No red failure language.

Why users may remember it:
- The orbit/ring metaphor makes the app feel original.
- Daily work visibly connects to the year.
- Rescue Mode gives emotional relief instead of guilt.

Risks:
- Too much glow could reduce readability.
- Too much space styling could feel childish or gimmicky.
- Needs strong accessibility contrast checks.

### Concept B - Soft Orbit Garden

Visual style:
- Gentle, calm, and restorative.
- Orbit visuals feel like growth rings and seasonal planning rather than space hardware.
- More human and less technical.

Color direction:
- Background: soft charcoal, muted deep teal, or dusk blue.
- Accents: mint, lavender, warm peach, soft cyan.
- Avoid heavy neon.

Card style:
- Softer cards with warm surfaces and gentle shadows.
- Progress appears as soft rings or layered arcs.
- Rescue cards feel like a supportive checklist.

Typography feeling:
- Friendly and breathable.
- Slightly warmer than strict dashboard typography.
- Copy feels encouraging and beginner-friendly.

Home screen idea:
- Top: "Today's Focus" with a soft ring showing daily momentum.
- Middle: small set of tasks with energy labels.
- Bottom: weekly mission preview and gentle recovery area.

Year Orbit idea:
- Four seasonal arcs or quarters around the yearly goal.
- Monthly focus areas appear as soft orbit markers.
- Progress feels like steady growth, not pressure.

Rescue Mode idea:
- Uses restorative language: "Nothing is lost. Choose what happens next."
- Tasks appear in calm cards with one recommended action.
- Secondary actions are available but quiet.

Why users may remember it:
- Feels emotionally safer than normal productivity apps.
- Distinct from cold corporate task tools.
- Strong fit for users who abandon apps because they feel stressful.

Risks:
- Could feel less premium or less futuristic if too soft.
- Orbit identity may become weaker.
- Needs restraint to avoid wellness-app softness.

### Concept C - Minimal Mission Control

Visual style:
- Clean, restrained, highly usable.
- Less decorative than Concept A.
- Built around clear hierarchy, compact cards, and predictable navigation.

Color direction:
- Background: charcoal/navy.
- Primary accent: cyan or blue.
- Secondary accent: muted violet.
- Minimal semantic colors for status and energy.

Card style:
- Compact Material 3 cards with clear labels and dividers.
- Minimal glow, more emphasis on spacing and typography.
- Best for repeated daily use.

Typography feeling:
- Efficient, crisp, and readable.
- More planner/tool than moodboard.
- Good for users who want speed and clarity.

Home screen idea:
- Top: date, daily mission, and progress summary.
- Middle: task list with sections for Now, Later, and Rescued.
- Bottom navigation is familiar and stable.

Year Orbit idea:
- Simple radial year overview with month dots and progress states.
- Less visual drama, more scanning and quick navigation.

Rescue Mode idea:
- Structured queue of unfinished tasks.
- Each task has a primary recommended action and secondary overflow options.
- Copy remains gentle but the layout is efficient.

Why users may remember it:
- It feels reliable enough to use daily.
- The orbit identity stays present without overwhelming the core task flow.
- It is easiest to implement cleanly in Compose later.

Risks:
- Could feel too similar to existing productivity apps.
- Needs a distinctive Year Orbit and Rescue Mode to avoid generic to-do app energy.
- May need stronger brand moments in onboarding and empty states.

## Recommended Direction

Recommended hybrid: Concept A + Concept C, with Concept B's emotional softness.

Working name:

Calm Orbit Command

Why:
- Concept A gives Orbit Planner its memorable identity: orbit/ring visuals, year connection, command-center feeling.
- Concept C keeps the app practical, Material 3-friendly, and easy to implement later in Jetpack Compose.
- Concept B contributes the emotional tone needed for Rescue Mode: recovery, not guilt.

Design decision:
- Use a dark premium Material 3 base.
- Make Today, Year Orbit, and Rescue Mode the identity screens.
- Keep regular task and planner screens clean and usable.
- Use orbit visuals sparingly: progress ring on Today, radial yearly planner on Year Orbit, small connection indicators on task cards.
- Use gentle copy everywhere unfinished work appears.

## Next Figma Wireframe Priority

1. Today Screen wireframe
2. Rescue Mode wireframe
3. Year Orbit Screen wireframe
4. Add Task flow wireframe
5. Week and Month planner wireframes

Do not implement Android UI until Figma wireframes and visual direction are approved.
