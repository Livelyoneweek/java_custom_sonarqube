# My Custom SonarQube Plugin

Java 프로젝트의 코드 품질을 높이기 위한 **SonarQube 커스텀 룰 플러그인**입니다.  
예시 룰: _"모든 메서드는 주석을 달아야 한다."_ 등.

## 🔧 프로젝트 구성

- Java 17 기반
- Gradle 빌드 시스템
- SonarQube Plugin API 사용 (`sonar-plugin-api-impl`)
- Java 언어용 룰 (`Plugin-RequiredForLanguages: java`)

## 📁 주요 클래스

| 클래스명 | 설명 |
|----------|------|
| `CustomRulesPlugin` | SonarQube 플러그인의 진입점 |
| `MyCustomRulesDefinition` | 커스텀 룰 정의 등록 |
| `MyCustomRule` | 메서드 주석 유무를 검사하는 룰 구현체 |

## 📦 빌드 방법

```bash
./gradlew clean build