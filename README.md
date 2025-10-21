# Android Study - 구구단 앱

최신 Android 개발 기술을 학습하기 위한 구구단 앱 프로젝트입니다.

## 기술 스택

### 언어 및 프레임워크
- **Kotlin 2.0.0** - 최신 Kotlin 문법 적용
- **Android SDK 35** (compileSdk & targetSdk)
- **Minimum SDK 21** (Android 5.0 Lollipop)

### 아키텍처
- **MVVM (Model-View-ViewModel)** 패턴
- **Repository 패턴**
- **Clean Architecture** 원칙

### 핵심 라이브러리
- **AndroidX** - 최신 지원 라이브러리
- **Kotlin Coroutines** - 비동기 프로그래밍
- **StateFlow** - 반응형 상태 관리
- **ViewBinding** - 타입 안전한 뷰 참조
- **Lifecycle Components** - 생명주기 관리

### 테스트
- **JUnit 4** - 단위 테스트 프레임워크
- **MockK** - Kotlin 모킹 라이브러리
- **Kotest** - Kotlin 테스트 프레임워크
- **Truth** - Google의 Assertion 라이브러리
- **Espresso** - UI 테스트
- **UI Automator** - E2E 테스트
- **Robolectric** - Android 유닛 테스트
- **Coroutines Test** - 코루틴 테스트

## 프로젝트 구조

```
app/src/
├── main/
│   └── java/kr/jell/android_study/
│       ├── domain/
│       │   ├── model/              # 도메인 모델 (Data class, Sealed class)
│       │   └── repository/         # Repository 인터페이스 및 구현
│       ├── presentation/
│       │   └── viewmodel/          # ViewModel
│       ├── utils/                  # Extension Functions 및 유틸리티
│       ├── MainActivity.kt         # 메인 화면
│       └── Gugudan/
│           └── Gugudan.kt          # 구구단 화면
├── test/                           # Unit Tests
│   └── java/kr/jell/android_study/
│       ├── domain/
│       │   ├── model/
│       │   └── repository/
│       └── presentation/
│           └── viewmodel/
└── androidTest/                    # Instrumented Tests (E2E)
    └── java/kr/jell/android_study/
        ├── MainActivityE2ETest.kt
        ├── GugudanActivityE2ETest.kt
        └── NavigationE2ETest.kt
```

## 주요 기능

### 1. 구구단 계산 및 표시
- 2단부터 9단까지 구구단 생성
- MVVM 패턴을 사용한 데이터 관리
- StateFlow를 통한 반응형 UI 업데이트

### 2. 최신 Kotlin 문법
- **Data Class** - 불변 데이터 모델
- **Sealed Class** - 타입 안전한 상태 관리
- **Extension Functions** - 코드 재사용성 향상
- **Inline Reified Functions** - 타입 안전한 Activity 시작
- **Coroutines & Flow** - 비동기 작업 처리

### 3. 테스트 주도 개발 (TDD)
- 단위 테스트 커버리지
- 통합 테스트 (E2E)
- UI 테스트 자동화

## 빌드 및 실행

### 요구사항
- Android Studio Hedgehog | 2023.1.1 이상
- JDK 17
- Android SDK 35

### 빌드
```bash
./gradlew assembleDebug
```

### 테스트 실행

#### 단위 테스트
```bash
./gradlew test
```

#### UI 테스트 (Android 기기 또는 에뮬레이터 필요)
```bash
./gradlew connectedAndroidTest
```

## 코드 스타일

- **Kotlin Official Style Guide** 준수
- **클린 코드** 원칙 적용
- **KDoc** 주석으로 문서화

## 학습 포인트

### 1. Kotlin 2.0 최신 기능
- Context Receivers
- Opt-in Requirements
- 개선된 타입 추론

### 2. 현대적인 Android 개발
- MVVM 아키텍처 패턴
- Repository 패턴
- StateFlow를 사용한 상태 관리
- Coroutines를 사용한 비동기 처리

### 3. 테스트 전략
- 단위 테스트 (Unit Test)
- 통합 테스트 (Integration Test)
- UI 테스트 (Espresso)
- E2E 테스트 (UI Automator)

## 버전 히스토리

### v2.0.0 (2025)
- Kotlin 2.0.0 업그레이드
- MVVM 아키텍처 적용
- 완전한 테스트 커버리지 구현
- 최신 AndroidX 라이브러리 적용

### v1.0.0 (2017)
- 초기 프로젝트 생성

## 개발자

jellive (jellpd)
