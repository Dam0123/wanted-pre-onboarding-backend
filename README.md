# wanted-pre-onboarding-backend

## 주요 개발환경

---
- IntelliJ IDEA (Community)
- Java 11
- Gradle 8.2.1
- Spring Boot 2.7.16
- QueryDSL
- (ORM) JPA
- (RDB) MySQL
- Git
- (Documentation) Swagger 3.0

## Git commit 메시지 규칙 & Merge 전략

---
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

### 3) Merge 전략
- Create a Merge Commit
 <details>
	<summary>사용 이유</summary>
  	<div markdown="1">
      어떤 브랜치에서 어떤 커밋이 진행되어 어떻게 머지가 되었구나 라는 자세한 정보를 얻을 수 있도록 하기 위해서이기도 하고, 너무 많은 빈번한 commit이 이뤄질만한 프로젝트가 아니기 때문에 선택하게 됨.
  	</div>
</details>

## GitHub Branch 별 용도

---
<details>
	<summary>main</summary>
  	<div markdown="1">
      각 "feat/도메인" 의 코드 변경 사항을 main으로 merge 함. 어플리케이션 최종 실행 버전.
  	</div>
</details>

<details>
	<summary>feat/application</summary>
  	<div markdown="1">
      지원내역 domain에 해당하는 기능들을 구현하고 해당 브랜치로 commit,push 함. test 코드도 포함.
  	</div>
</details>


<details>
	<summary>feat/company</summary>
  	<div markdown="1">
      회사 domain에 해당하는 기능들을 구현하고 해당 브랜치로 commit,push 함. test 코드도 포함.
  	</div>
</details>

<details>
	<summary>feat/post</summary>
  	<div markdown="1">
      채용공고 domain에 해당하는 기능들을 구현하고 해당 브랜치로 commit,push 함. test 코드도 포함.
  	</div>
</details>

<details>
	<summary>feat/user</summary>
  	<div markdown="1">
      사용자 domain에 해당하는 기능들을 구현하고 해당 브랜치로 commit,push 함. test 코드도 포함.
  	</div>
</details>

## 기능 요구사항 분석

---
[요구사항 분석 확인하기](https://www.notion.so/61f7835acfaf4c0f930dbba1622f2441?v=699c1e37fc024a22a2ab65619d0e9bf2&pvs=4)

## ERD

---
![ERD](https://github.com/Dam0123/wanted-pre-onboarding-backend/assets/91379555/c50677c3-afdb-4d61-bd80-09ebf6acfba9)


## API 문서

---
### API 명세서 (Notion)
[API 명세서 확인하기](https://www.notion.so/API-4e96824b5603493c9df80d18f7266ea1?pvs=4)

### Swagger API 명세서 확인 방법

---
1. src -> main -> java -> com -> wanted -> 'WantedApplication' 을 실행해주세요.
2. 웹 브라우저에 'http://localhost:8080/swagger-ui/index.html' 를 입력해주세요.
3. API 문서 화면이 잘 보인다면, '채용공고 등록 요청' API부터 실행해주세요.
4. 채용공고가 등록된 상태로 진행해야 다른 API 요청들도 정상적으로 성공할 수 있습니다.
풀리퀘스트를 보내주세요.


## 프로젝트 진행 순서

---
1) 요구사항 분석
2) DB 설계
3) API 명세 작성
4) 프로젝트 개발환경 세팅
5) 개발 일정 세우기
6) API 구현
7) Unit Test
8) 문서 정리
9) 보완할 부분 수정 작업
