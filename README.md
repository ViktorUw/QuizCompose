# QuizCompose

A quiz app for Android built with **Jetpack Compose**. It asks 10 science questions (physics, chemistry, astronomy) and shows your result at the end.

## Features

- 10 multiple-choice questions with four answers each
- Answer cards that highlight the selected option
- Question counter and progress indicator
- The "Next" button is enabled only after an answer is chosen
- Final screen with the score (e.g. `7/10`)
- Quiz state survives screen rotation (`rememberSaveable`)

## Tech stack

- **Kotlin**
- **Jetpack Compose** with Material 3
- Min SDK 28, target SDK 35

## Project structure

```
app/src/main/java/com/example/quizcompose/
├── MainActivity.kt   # Questions, quiz screen and result screen
└── ui/theme/         # Colors, typography and theme
```

Questions and correct answers are defined at the top of `MainActivity.kt` (`questions` and `correctAnswers`).

## Getting started

1. Clone the repository:
   ```bash
   git clone https://github.com/ViktorUw/QuizCompose.git
   ```
2. Open the project in **Android Studio** and let Gradle sync.
3. Run the app on an emulator or a device with Android 9.0 (API 28) or newer.

> The questions and the interface are in Polish.

See also: [QuizAplication](https://github.com/ViktorUw/QuizAplication), the earlier version of this quiz written in Java with XML layouts.
