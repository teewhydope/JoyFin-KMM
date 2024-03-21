# JoyFin-KMM

A Kotlin Multiplatform Mobile Project that demonstrates code sharing between iOS and Android apps.

### Features

- Clean Architecture
- MVVM
- [Shared Business Logic](https://github.com/teewhydope/JoyFin-KMM/tree/main/common)
- Native
  UI ([Android XML](https://github.com/teewhydope/JoyFin-KMM/tree/main/app-ui/src/main/java/com/teewhydope/app)
  and [UIKIT](https://github.com/teewhydope/JoyFin-KMM/tree/main/iosApp/iosApp/ViewController))
- [Androidx](https://github.com/teewhydope/JoyFin-KMM/tree/main/iosApp/iosApp/ViewController) & [Swift](https://github.com/teewhydope/JoyFin-KMM/tree/main/iosApp/iosApp/Presentation/Login)
  Viewmodels
- Hilt DI (Android)/ Manual DI (iOS)

## Building iOS Data

1. run build from the terminal to generate ios framework

```
> ./gradlew common:ios-data:build 
```

- Username: teewhy@mobiledev.com
- Password: teewhydope

<img src="https://github.com/teewhydope/JoyFin-KMM/blob/main/assets/Screenshot_20240319_225826.png" width="200">  <img src="https://github.com/teewhydope/JoyFin-KMM/blob/main/assets/Screenshot_20240319_211512.png" width="200">  <img src="https://github.com/teewhydope/JoyFin-KMM/blob/main/assets/Screenshot_20240319_225827.png" width="200">  <img src="https://github.com/teewhydope/JoyFin-KMM/blob/main/assets/Screenshot_20240319_225828.png" width="200">
