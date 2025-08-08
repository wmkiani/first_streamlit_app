# Arabic Learner (English/Urdu to Arabic)

A simple Android app (Kotlin + Jetpack Compose) to help English and Urdu speakers learn Arabic. It includes:

- Learning paths with lessons
- Progress tracking (% completed)
- Daily word (Word of the Day)
- English and Urdu localized UI

## Structure

- `app/src/main/java/com/example/arabiclearner/` — Kotlin sources
- `app/src/main/res/values/` — English strings
- `app/src/main/res/values-ur/` — Urdu strings

## Running

1. Open this folder (`arabic-learner`) in Android Studio (Giraffe+).
2. Ensure `compileSdk = 34` SDK is installed in the SDK Manager.
3. Sync Gradle and Run on an emulator or device.

## Notes

- Data is in-memory via `InMemoryContentRepository` for quick prototyping. Replace with Room/remote API as needed.
- Progress is kept in-memory for now. Hook up DataStore/Room to persist across app restarts.
- Navigation has 3 tabs: Home, Learn, Progress. Tap a lesson to see details and mark complete.