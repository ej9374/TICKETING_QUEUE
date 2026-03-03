# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build and Run Commands

```bash
# Build the project
./gradlew build

# Run the application
./gradlew bootRun

# Run all tests
./gradlew test

# Run a single test class
./gradlew test --tests "eunji.ticketing.TicketingApplicationTests"

# Run a single test method
./gradlew test --tests "eunji.ticketing.TicketingApplicationTests.contextLoads"

# Clean build
./gradlew clean build

# Build executable jar
./gradlew bootJar
```

# 프로젝트 가이드라인 (Ticketing Queue System)

이 프로젝트는 대규모 트래픽을 처리하는 콘서트 대기열 및 티켓팅 시스템이다.
Claude는 아래의 규칙을 절대적으로(Strictly) 준수하여 코드를 생성해야 한다.

## 1. 🛑 [절대 규칙] AI와 개발자의 역할 분담 (Workflow)
- **비즈니스 로직 작성 금지:** AI는 `Service` 계층의 핵심 비즈니스 로직(동시성 제어, 분산 락, 대기열 순서 보장 등)을 절대 직접 구현하지 않는다. 내부 로직은 `// TODO: 개발자가 직접 작성함`으로 비워둔다.
- **AI의 역할:** API 명세를 기반으로 한 DTO 생성, Validation 어노테이션 부착, Controller/Service 뼈대(Skeleton) 생성, 에러/예외 케이스 도출, 그리고 완성된 로직에 대한 엣지 케이스 단위 테스트 작성만 수행한다.

## 2. 📨 공통 응답 및 예외 포맷 (Response & Exception)
모든 API 응답은 아래 규칙을 무조건 따른다. Entity를 직접 반환하는 것은 엄격히 금지한다.
- **성공 응답:** `ApiResponse<T>` 포맷 사용. (success, status, message, code, data, timestamp 필드 포함)
- **실패 응답:** `ErrorResponse` 포맷 사용. (data 필드 없음)
- **예외 처리:** - 비즈니스 에러는 `BizException`을 throw 한다.
    - Validation(@Valid) 실패는 `@RestControllerAdvice`에서 캐치하여 400 Bad Request 에러로 통일한다.

## 3. 💾 JPA 및 데이터베이스 규칙 (Database Rules)
- **트랜잭션 분리:** - 상태를 변경하는 로직(CUD): `@Transactional`
    - 상태를 변경하지 않는 단순 조회 로직(R): `@Transactional(readOnly = true)` 강제 적용.
- **JPA 최적화:** 모든 연관관계 매핑은 `FetchType.LAZY`를 기본으로 한다. N+1 문제가 예상되는 경우 `Fetch Join`을 적용한다.

## 4. 📁 패키지 구조 제한
- `controller` / `service` / `repository` / `entity` / `dto` / `exception` / `response` 계층을 엄격하게 분리하여 파일을 생성한다.
