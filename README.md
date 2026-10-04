## I. Project Description

A. Overview
   1. PilotBuddy is a native Android app written in Java using Android Studio.
   2. It consolidates three tools that pilots often use separately: a turn calculator, a wind calculator, and an airport information reference.
   3. All data is stored on the device, so the app works without a signal in the cockpit or on the ramp.

B. Target Users
   1. Student pilots working through training and checkride preparation.
   2. General aviation pilots who want a quick reference on a phone instead of paper charts and separate apps.

C. Goals for the Term
   1. Build a working three-tool Android app with a clean, readable interface.
   2. Implement a normalized SQLite database for airport data.
   3. Apply course concepts including Activities, layouts, Intents, and SQLiteOpenHelper.
   4. Document the project in GitHub with a README and wiki.

## II. Problem Addressed

A. The Problem
   1. Pilots often rely on several different apps, paper tools, or manual math for quick in-flight and pre-flight calculations. **(Proposed problem statement, to be refined with my own pilot-user research)**
   2. Switching between tools costs time and attention, especially for students who are still building workload management habits.
   3. Cockpits and airfields frequently have poor or no connectivity, which makes cloud-based tools unreliable.

B. How PilotBuddy Responds
   1. One app holds the calculators and the airport reference.
   2. A local SQLite database removes the need for an internet connection.
   3. Simple input screens keep the number of taps low.

## III. Platform

A. Operating System
   1. Android (native app).

B. Language and Tools
   1. Java
   2. Android Studio as the development environment
   3. Android SDK and Gradle build system
   4. Git and GitHub for version control and documentation

C. Target Devices
   1. Android phones. **(Proposed, minimum API level to be confirmed in the project settings)**

## IV. Front End and Back End Support

A. Front End
   1. Activities for each major screen.
   2. XML layout files for the user interface.
   3. Input widgets for numeric entry and a search field for airports.
   4. A results list for airport search matches.
   5. Navigation between screens using Intents.

B. Back End
   1. SQLite local database accessed through a SQLiteOpenHelper subclass (DatabaseHelper).
   2. Starter data is inserted with SQL INSERT statements in `onCreate()`.
   3. No external server or web service is required.

C. Normalized Database Design
   1. The schema uses separate airport, runway, and frequency tables linked by keys so data is not duplicated.
   2. **(Proposed)** Table outline:

| Table | Key Fields | Purpose |
|---|---|---|
| airports | airport_id (PK), ident, name, city, state, elevation_ft | One row per airport |
| runways | runway_id (PK), airport_id (FK), designator, length_ft, surface, heading | Many runways per airport |
| frequencies | frequency_id (PK), airport_id (FK), type, frequency_mhz | Many frequencies per airport |

## V. Functionality

A. Turn Calculator **(Proposed inputs and outputs)**
   1. Inputs: true airspeed and bank angle.
   2. Outputs: turn rate and turn radius.
   3. Input validation for empty or unrealistic values.

B. Wind Calculator **(Proposed inputs and outputs)**
   1. Inputs: runway or course heading, wind direction, and wind speed.
   2. Outputs: headwind component and crosswind component, including which side the crosswind comes from.
   3. Input validation for empty values and headings outside 0 to 360 degrees.

C. Airport Info Lookup
   1. Search for an airport by identifier or name.
   2. View airport details, runways, and frequencies pulled from the SQLite tables.
   3. Handle the no-results case with a clear message.

D. General App Behavior
   1. Home screen with one button for each tool.
   2. Offline operation.
   3. Consistent layout and labeling across screens.

E. Development Plan
   1. Implement the SQLite schema and starter data.
   2. Build the Activities and layouts.
   3. Add calculator logic and connect the airport search to the database.
   4. Test, fix defects, and polish the interface.

## VI. Design (Wireframes)

Simple tab or menu-based navigation from a home screen to the three core tools. Planned screen flow, left to right:

| Home | Turn Calculator | Wind Calculator | Airport Info |
| :--: | :-------------: | :-------------: | :----------: |
| <img width="200" alt="Home screen wireframe" src="https://github.com/user-attachments/assets/16662895-6ebb-4719-9ef0-339759f1e430" /> | <img width="200" alt="Turn Calculator wireframe" src="https://github.com/user-attachments/assets/ba03ca60-b6d9-417e-83c5-12ce051b29d5" /> | <img width="200" alt="Wind Calculator wireframe" src="https://github.com/user-attachments/assets/0a9fc412-e133-4585-98bc-a832036f24ef" /> | <img width="200" alt="Airport Info wireframe" src="https://github.com/user-attachments/assets/1dca9a38-fbd1-4cfe-bcb6-d026221a7bf5" /> |
| App title and three navigation buttons, one per tool | Single input field for current course, three result fields for 90/180/270 outcomes | Input fields for heading, wind direction, and wind speed, with output fields for crosswind and headwind/tailwind components | Search field for airport identifier, results area listing runways and frequencies for the matched airport |

### Sreen Flow
```
Home --> Turn Calculator
Home --> Wind Calculator
Home --> Airport Info (search) --> Airport Detail
```

## VII. Application Development

The screenshots below show the working Android build of each screen from the wireframes above. Every screen was captured on an Android emulator in both a light theme and a dark theme. The three tool screens appear in their empty state, before any values are entered.

| Screen | Light Theme | Dark Theme |
| :----: | :---------: | :--------: |
| **Home** | <img width="277" alt="PilotBuddy home screen, light theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/home-light.png" /> | <img width="277" alt="PilotBuddy home screen, dark theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/home-dark.png" /> |
| **Turn Calculator** | <img width="277" alt="Turn Calculator screen, light theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/turn-light.png" /> | <img width="277" alt="Turn Calculator screen, dark theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/turn-dark.png" /> |
| **Crosswind Calculator** | <img width="277" alt="Crosswind Calculator screen, light theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/crosswind-light.png" /> | <img width="277" alt="Crosswind Calculator screen, dark theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/crosswind-dark.png" /> |
| **Airport Information** | <img width="277" alt="Airport Information screen, light theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/airport-light.png" /> | <img width="277" alt="Airport Information screen, dark theme" src="https://github.com/ChrisGonzalez72/PilotBuddy/blob/main/docs/screenshots/airport-dark.png" /> |

## Course

COM 437: Mobile Application Development
