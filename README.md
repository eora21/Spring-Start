# 프로젝트 생성

> https://start.spring.io/

## Maven vs Gradle

> 필요한 라이브러리를 가져오고, 빌드 라이프사이클까지 관리해주는 툴

### Maven

- xml로 구성되어있음
- Build라는 동적인 요소를 xml로 정의하기에는 어려운 부분이 많음

### Gradle

- 빌드 배포 도구
- 빌드스크립트로 구성되어있음
  - 로직 간결하게 구현 가능
  - 동적인 Build는 groovy 스크립트로 플러그인을 호출하거나 직접 코드를 작성할 수 있음
- Maven보다 최대 100배 더 빠름

## Spring Boot Version

- Snapshot
  - 만들고 있는 버전
- M1
  - 정식 릴리즈되지 않은 버전

## Project Metadata

- Group
  - 보통 그룹의 기업명
  - 역순으로 작성
- Artifact
  - 빌드되어 나온 결과물, like 프로젝트명

## Dependencies

> 어떤 라이브러리를 가져다 쓸 것인가

- Spring Web
- Thymeleaf
  - html을 만들어 줄 템플릿 엔진

# 생성 후

- SpringBootApplication
  - Tomcat 서버 내장
- Gradle을 통해 코드가 실행되는 경우
  - 설정
  - gradle 검색
  - build and run using, run tests using을 인텔리제이로 변경

## 라이브러리 살펴보기
