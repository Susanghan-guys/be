# Contributing Guide

협업 규칙과 컨벤션을 정리한 문서입니다.

1. [브랜치 전략](#브랜치-전략)
2. [커밋 규칙](#커밋-규칙)
3. [PR 규칙](#pr-규칙)
4. [이슈 작성](#이슈-작성)
5. [코드 스타일](#코드-스타일)
6. [작업 흐름](#작업-흐름)
7. [참고](#참고)

<br>

## 브랜치 전략

- **`main`**: 운영 브랜치
- **`develop`**: 개발 브랜치
  
- 작업 브랜치는 기본적으로 `feat/#이슈번호-작업명`, `fix/#이슈번호-작업명`, `refactor/#이슈번호-작업명` 형식을 사용합니다.
- 작업 유형에 따라 다른 prefix도 상황에 맞게 사용할 수 있습니다.

**예시**
```text
feat/#119-full-report-creation
fix/#131-mail-duplication
refactor/#138-strength-weakness-balance`
```
<br>

## 커밋 규칙

- 커밋은 `#이슈번호 prefix: 내용` 형태를 기본으로 사용합니다.
- 내용은 어떤 변경이 있었는지 바로 보이도록 적어주세요.
  
- 필요하면 `body`와 `footer`를 함께 작성합니다.
  - `body`: 변경 이유나 핵심 작업 내용을 간단히 적습니다.
  - `footer`: 관련 이슈, breaking change 등 추가 정보를 적습니다.

### 기능
> - `feat`: 새로운 기능 추가
> - `fix`: 버그 수정
> - `hotfix`: 운영 중 긴급 수정

### 개선
> - `style`: 포맷팅, 세미콜론 누락, 코드 변경 없는 정리
> - `refactor`: 로직 재구성, 구조 개선
> - `comment`: 필요한 주석 추가 또는 변경

### 기타
> - `docs`: 문서 수정
> - `test`: 테스트 코드 추가 또는 수정
> - `chore`: 빌드, 설정, 패키지 관리 등
> - `rename`: 파일 또는 폴더 이름 변경
> - `remove`: 사용하지 않는 파일 또는 폴더 삭제
  
**예시**
```text
#131 fix: 메일 중복 발송 로직 수정

동일 요청에서 메일이 여러 번 전송되는 문제를 수정

Refs: #131
```

<br>

## PR 규칙

- 저장소의 PR 템플릿을 사용합니다.
- PR에는 아래 항목을 꼭 작성합니다.
  - `🔗 연관된 이슈`: 관련 이슈를 연결
  - `📝 작업 내용`: 변경한 내용을 간단히 정리
  - `📸 스크린샷`: 필요한 경우 첨부
  - `💬 리뷰 요구사항`: 리뷰어가 확인해야 할 부분 강조
    
- 다른 팀원이 코드 리뷰를 남기고, 리뷰 반영이 끝나면 승인 후 머지합니다.
- 변경 범위가 큰 경우에는 작업 내용을 상세히 남겨주세요.

<br>

## 이슈 작성

<img width="643" height="193" alt="image" src="https://github.com/user-attachments/assets/1f7b2e63-f88d-4a5f-9428-b509b662a619" />

- 저장소의 이슈 템플릿을 사용합니다.
  - 버그 리포트: `bug-report-template.md`
  - 기능 추가: `feature-template.md`
  - 리팩토링: `refactor-template.md`
- 제목은 커밋메세지와 동일하게 prefix 규칙에 맞춰 작성합니다.

<br>

## 코드 스타일

- 기능별 패키지를 분리합니다.
- `Controller`, `Service`, `Domain`, `Repository`의 책임을 나눠서 작성합니다.
- 공통 응답, 예외, 보안 설정은 `global` 패키지에서 관리합니다.
- Swagger 문서와 실제 API 변경 사항은 함께 반영합니다.

**패키지 구조 예시**
```text
src/main/java/com/susanghan_guys/server
└── user
    ├── application      # service, validator
    ├── domain           # entity, enum
    ├── dto              # request, response  
    ├── exception        # error code, custom exception
    ├── infrastructure   # repository, mapper
    └── presentation     # controller, success code, swagger
```
<br>

## 작업 흐름

1. 이슈 생성 또는 확인
2. 작업 브랜치 생성
3. 작업 후 커밋
4. PR 생성
5. 리뷰 반영
6. 승인 후 머지

<br>

## 참고

- 
