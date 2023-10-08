# wanted-pre-onboarding-backend

## 주요 개발환경

- IntelliJ IDEA (Community)
- Java 11
- Gradle 8.2.1
- Spring Boot 2.7.16
- QueryDSL
- (ORM) JPA
- (RDB) MySQL
- Git

## Git commit 메시지 규칙

### 1) 작성 규칙
* 작은 단위로 커밋하기
* 제목은 최대 50글자 넘지 않기
* 본문은 한 줄당 72자 내로 작성
* 작성 형식
    * 태그: 커밋 메시지
    * ex) `feat: 채용공고 삭제 기능 구현`

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

## ERD
![ERD](https://github.com/Dam0123/wanted-pre-onboarding-backend/assets/91379555/f73e7830-0b91-4a55-9cd2-98061c72de51)

## API 명세


## 프로젝트 진행 순서
1) 요구사항 분석
2) DB 설계
3) API 명세 작성
4) 프로젝트 개발환경 세팅
5) 개발 일정 세우기
6) API 구현
7) Unit Test
8) 문서 정리
9) 보완할 부분 수정 작업