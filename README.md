# 강의

> https://www.inflearn.com/course/%EC%8A%A4%ED%94%84%EB%A7%81-%EC%9E%85%EB%AC%B8-%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8

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
  - 보통 그룹의 기업명 / 사이트
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

> Grandle은 의존관계가 있는 라이브러리 모두 다운로드

### 스프링 부트 라이브러리

- spring-boot-starter-web
  - spring-boot-starter-tomcat: 톰캣(웹서버)
  - spring-webmvc: 스프링 웹 MVC
- spring-boot-starter-thymeleaf: 타임리프 템플릿 엔진(View)
- spring-boot-starter(공통): 스프링 부트 + 스프링 코어 + 로깅
  - spring-boot
    - spring-core
  - spring-boot-starter-logging
    - logback, slf4j

### 테스트 라이브러리

- spring-boot-starter-test
  - junit: 테스트 프레임워크
  - mockito: 목 라이브러리
  - assertj: 테스트 코드 편히 작성할 수 있도록
  - spring-test: 스프링 통합 테스트 지원

## View 환경설정

- @GetMapping("whatUwant")
  - url 매칭, get방식으로
  - http://localhost:8080/whatUwant로 접근하게 해 줌
- model.addAttribute("data", "hello!!");
  - context라고 생각하면 됨
  - key: data
  - value: hello!!
- return "hello";
  - 리턴값이 문자라면 viewResolver가 화면을 찾아서 처리
  - 스프링 부트 템플릿엔진 기본 ViewName(리턴 String) 매핑
  - resourses:templates/{ViewName}.html

### [Spring-boot-devtools](https://velog.io/@bread_dd/Spring-Boot-Devtools)

## 빌드하고 실행

### 서버 배포 시

- teminal 이동
- `.\gradlew build`로 파일 생성
- `cd build/libs`로 폴더 이동(실제 배포 시에는 생성한 파일을 옮긴 경로)
- `java -jar {tab으로 자동완성}`로 생성한 파일 기반 서버 run
- `.\gradlew clean`빌드 폴더 삭제
- `.\gradlew clean build`빌드 폴더 삭제 후 재생성

## API

- `@ResponseBody`
  - http의 Body부분에 데이터를 직접 넣어주겠다
  - 데이터 자체를 그대로 띄워 줌
  - API 응답용으로 사용
  - return을 String이 아닌 객체로 한다면 JSON 방식으로 줄 수 있다!
    - HttpMessageConverter가 객체 데이터를 JsonConverter로 돌려준다.

## 백엔드

### 일반적인 웹 애플리케이션 계층 구조

- 컨트롤러: 웹 MVC의 컨트롤러 역할, 외부 요청을 받음
  - `유저의 요청응 듣곤 어디로 향할지 컨트롤한다!`
- 서비스: 핵심 비즈니스 로직 구현
  - 회원가입시 ID는 중복될 수 없다 등
  - 비즈니스 도메인 객체를 가지고 핵심 비즈니스 로직이 동작하도록 구현한 계층
  - `도착한 곳에서 규칙에 맞게 서비스를 제공한다!`
- 리포지토리: 데이터베이스에 접근, 도메인 객체를 DB에 저장하고 관리
  - `어떠한 서비스를 제공했는지 기록한다!`
- 도메인: 회원, 주문, 쿠폰 등등 데이터베이스에 저장하고 관리되는 비즈니스 도메인 객체
  - `서비스 제공 및 기록은 규율에 맞게끔!`

### 클래스 의존관계

- 아직 데이터 저장소가 선정되지 않았다는 가정, 인터페이스로 구현 클래스를 변경할 수 있도록 설계

### 테스트케이스

- src/test에 동일하게 패키지 경로 만든 후 클래스 생성(뒤에 Test 붙이는 걸 추천)
  - Ctrl + shift + T로 인텔리제이에서 간단하게 구현 가능
- 테스트용 함수에 `@Test`선언
- 런 누르면 일반 main처럼 동작함
- 테스트 코드들을 한 번에 돌릴 경우, 코드를 작성한 순서대로 돌아간다는 보장이 없다. 즉, 각 테스트가 끝나면 해당 테스트에서 세팅한 것들을 초기화해 줄 필요가 있다.
  - `@AfterEach`를 선언 후 함수를 입력하면, 각 테스트가 끝날 때마다 해당 함수를 돌려준다.

## 스프링 빈을 등록하는 두가지 방법

### 컴포넌트 스캔과 자동 의존관계 설정

#### Controller->Service->Repository 연결

- Annotation으로 `@Controller`를 주게 되면, 스프링이 컨테이너에 멤버 컨트롤러 객체를 생성하여 넣어두고 관리한다.
- 마찬가지로 `@Service`, `@Repository`를 각자에 맞게 선언한다.
-  `@Autowired`를 사용하여 Controller와 Service를 연결시킨다(Controller의 Service 선언은 DI형태).
- `@Autowired`를 사용하여 Service와 Repository를 연결시킨다(Service의 Repository선언은 DI형태).
- 생성자가 딱 하나일때는 `@Autowired` 생략 가능

#### 왜 컴포넌트 스캔인가?

`@Controller`,  `@Service`, `@Repository` 모두 컴포넌트 등록되어 있다(Annotation으로 `@Component` 존재).

그러므로 스프링이 시작될 때 객체를 생성시켜 스프링 컨테이너에 담아둔다(스프링 빈으로 자동 등록).

참고: 기본적으로 빈을 등록할때는 싱글톤으로 등록. 따라서 같은 스프링 빈이면 모두 같은 인스턴스. 설정으로 싱글톤이 아니게 설정할 수 있지만, 아~주 특수한 케이스를 제외하면 싱글톤을 지키자!

#### 아무 곳에서나 다 가능한가?

HelloSpringApplication이 존재하는 패키지 이하부터 가능

설정을 따로 해주면 되긴 하겠지만..

### 자바 코드로 직접 스프링 빈 등록하기

- HelloSpringApplication과 동일한 위치에 SpringConfig 생성
- `@Configration` 선언
- 특정 함수에 `@Bean` 선언
  - 로직대로 호출하여 Spring Bean에 등록
- Controller는 그냥 컴포넌트 스캔처럼 세팅, Service랑 Repository만 설정

#### 왜 생성자 주입으로 하는가?

- 필드 주입은 불안정하다. 직접 선언해주는 것이 더 좋다. 코드 수정도 명시적으로 되어 있어야 가능하니까..
- setter 주입은 항상 열려있다(public). 한 번 만들어지면 교체할 이유가 없다. 헌데 잘못 접근해서 교체된다면..?

## 개방-폐쇄 원칙

> 확장은 열려있고 수정,변경은 닫혀있다

DI를 활용하여 기존에 쓰던 DB 대신 다른 DB를 연결할 수 있었다!

인터페이스 상속, 확장으로 이루어졌다!

## 스프링 부트 테스트

- `@SpringBootTest`와 `@Transactional` 선언
  - `@SpringBootTest`로 스프링 컨테이너와 테스트를 같이 실행한다.
  - `@Transactional`의 역할은 commit을 거치지 않게끔 해 준다!
  - DB는 Insert 후에 commit을 통해 상태를 반영한다. 헌데 반영하지 않게끔만 해 주면, 테스트를 계속해서 돌릴 수 있게 된다!
  - 대부분의 DB는 Autocommit이지만, 해당 Annotation으로 commit이 일어나지 않게끔 해 주면서, 테스트를 반복적으로 사용할 수 있도록 한다!
- `@Autowired` 필드 주입 써도 상관없다
  - 가져다 쓰고 끝이기 때문. 테스트에서 코드 수정할 필요가..? 테스트하고 끝인데..?
- 단위 테스트를 잘 만드는 게 훨씬 더 좋은 테스트이다. 상황마다 다르지만..

## JDBC

### save

```java
@Override
public Member save(Member member) {  // 공식문서 참조
    SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);  // 데이터소스로 DB연결
    jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");  // 해당 테이블에 삽입, key로 반환할 컬럼은 id

    Map<String, Object> parameters = new HashMap<>();
    parameters.put("name", member.getName());  // 파라미터 name 컬럼에 값 넣기

    Number key = jdbcInsert.executeAndReturnKey(new  // 자동생성된 키 값 반환
            MapSqlParameterSource(parameters));
    member.setId(key.longValue());
    return member;
}
```

## JPA

> sql과 데이터 중심의 설계에서 객체 중심의 설계로!

### Domain

`@Entity`: JPA에게 관리를 맡김

`@Id`: 기본 키 필드

`@GeneratedValue(strategy = GenerationType.IDENTITY)`: DB에서 key값을 자동으로 생성해주는것과 같음

`@Column(name = "username")`: DB에서의 컬럼명

### EntityManager

모든 동작을 수행하게끔 해줌, 스프링 부트가 자동 생성, 프로퍼티 세팅 및 DB 커넥션 정보 등을 다 합쳐서 생성됨.

### save

```java
@Override
public Member save(Member member) {
    em.persist(member);  // 영속화하다, 영구저장하다
    return member;
}
```

압도적..!

### JPQL

```java
@Override
public Optional<Member> findByName(String name) {
    List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
            .setParameter("name", name)
            .getResultList();

    return result.stream().findAny();
}
```

SQL과 비슷하지만 객체 중심으로 접근 가능. 오히려 파이썬에 가까운 느낌..?

### @Transactional

데이터를 저장하거나 변경할 때 꼭 필요함..!

## Spring Data JPA

```java
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
```

- **구현 끝**
  - Spring Data JPA가 `JpaRepository`를 지니고 있으면 구현체를 자동으로 만들어 Bean에 등록까지 해준다!
  - save, find 등의 CRUD를 내부적으로 가지고 있기 때문에 걱정하지 않아도 된다!
  - 단, 무언가를 기준으로 값을 찾는것은 회사마다, 환경마다 다르기 때문에 해당하는 부분만 Override하면 된다.
    - findByXXXX를 선언하면 JPQL에 자동적으로 해당 구문에 맞게끔 내부 구현이 된다
    - = 인터페이스 이름만으로 개발이 끝나버린다..!
- 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다
  - 쿼리도 자바 코드로 안전하게 작성 가능, 동적 쿼리도 편리
- 해당 조합으로 어려운 쿼리는 네이티브 쿼리 혹은 스프링 JdbcTemplate를 사용하면 된다

## AOP

- 모든 쿼리가 얼마나 걸리는지를 알아보려면..?
  - 핵심 관심사항이 아닌 곁다리다
  - 공통 관심 사항(모든 쿼리에 공통적으로 들어가게 되는 관심사)이다
  - 시간 측정 로직 + 핵심 비즈니스 로직이 섞여 유지보수가 어렵다
  - 별도의 공통 로직으로 만들자니.. 그것도 매우 어렵다
  - 시간 측정 로직을 변경한다면 하나하나 수정해야 한다(밤샘..)
- AOP(Aspect Oriented Programming)
- 공통 관심 사항과 핵심 관심 사항을 분리, 원하는 곳에 공통 관심 사항 적용
- 프록시라는 가짜 spring been을 거치며 AOP 타겟을 확인하고 그 후에 실제 been이 동작됨

