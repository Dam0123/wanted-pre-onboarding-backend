# wanted-pre-onboarding-backend
### 요구사항 정리

## ✨  개발환경

### 1) 주요 환경

- IntelliJ IDEA (Community)
- Java 11
- Gradle 7.6.1
- Spring Boot 2.7.16

### 2) 활용 기술

- (ORM) JPA

### 3) 데이터베이스

- (RDB) MySQL

### 4) VCS(Version Controller System) & etc

- Git
- Notion

## 🅲 커밋 메시지 규칙

### 1) 작성 규칙
* 작은 단위로 커밋하기
* 제목은 최대 50글자 넘지 않기
* 본문은 한 줄당 72자 내로 작성
* README 파일 수정 작업은 이슈번호 생략 가능
* 작성 형식
    * 태그: 커밋 메시지 (이슈번호)
    * ex) `feat: 채용공고 삭제 기능 구현 (#1)`

### 2) 태그 설명
| 태그이름     | 설명                         |
|----------|----------------------------|
| setting  | 프로젝트 개발 환경 구축 및 설정         |
| feat     | 새로운 기능 추가 및 코드 구현          |
| update   | 이미 구현된 코드에서 기능 추가 및 변경     |
| bug      | 버그 수정                      |
| docs     | 문서 작성 및 수정                 |
| refactor | 코드 리팩토링 (성공적으로 배포한 코드를 변경) |
| test     | 테스트 코드, 리펙토링 테스트 코드 추가     |
| comment  | 필요한 주석 추가 및 변경             |
| rename   | 파일 혹은 폴더명 수정하거나 옮기는 경우     |
| remove   | 파일을 삭제하는 작업만 수행하는 경우       |