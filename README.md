# calendar

ERD 주소:
https://www.erdcloud.com/d/nmwykTouifcvc5kft

### Lv 1. 일정 생성 및 조회  `필수`✅

### Lv 2. 일정 수정 및 삭제  `필수`✅


## 4️⃣ 도전 기능 가이드

### Lv 3. 연관 관계 설정  `도전`🔺

👉 user_id를 email를 통해서 찾아내고 확인해주는 것이 목적이였으나 실패

### Lv 4. 페이지네이션  `도전` 🔺

👉 Paging 객체를 활용하지 못하였으니 실패

### Lv 5. 예외 발생 처리  `도전`🔺

👉 블로그를 너무 많이 봤습니다. 완벽한 이해가 아니라서 실패

### Lv 6. null 체크 및 특정 패턴에 대한 검증 수행  `도전`🔺

👉 블로그를 너무 많이 봤습니다. 완벽한 이해가 아니라서 실패

도전 기능 가이드는 전체적으로 정리 필요하다. 페이지네이션에서 급격하게 바뀐 생각들이 부족한 시간을 초래하였다. 처음부터 천천히 진행해야 할것같다.

손으로 작성해

# Calendar API 명세서 (postMan export 했습니다)

## 개요
Calendar API는 사용자 관리 및 일정 관리를 위한 RESTful API입니다. 아래는 각 API 엔드포인트의 상세 명세입니다.

## 사용자(User) 관련 API

| 번호 | 메서드 | 엔드포인트 | 설명 | 요청 예시 |
|------|--------|------------|------|----------|
| 0 | POST|`/users`  | 새로운 사용자 등록|{"name": "name2", "email": "email@naver.com","password":"123" }
| 1 | GET | `/users` | 모든 사용자 정보를 조회 | - |
| 2 | PUT | `/users/{userId}` | 특정 사용자의 정보를 수정 | `{ "name": "name2", "email": "email@naver.com" }` |
| 3 | DELETE | `/users/{userId}` | 특정 사용자를 삭제 | - |

## 캘린더(Calendar) 관련 API

| 번호 | 메서드 | 엔드포인트 | 설명 | 요청 예시 |
|------|--------|------------|------|----------|
| 4 | POST | `/calendars` | 새로운 일정 생성 | `{ "name": "name2", "password": "123", "task": "할일", "userId": "1" }` |
| 5 | GET | `/calendars` | 모든 일정 조회 | - |
| 6 | GET | `/calendars/{userId}` | 특정 사용자의 일정 조회 | - |
| 7 | GET | `/calendars/{userId}/{calendarId}` | 특정 일정 조회 | - |
| 8 | PUT | `/calendars/{userId}/{calendarId}` | 특정 일정 수정 | `{ "name": "name2", "task": "task", "password": "123" }` (Query: `email=email@naver.com`) |
| 9 | DELETE | `/calendars/{userId}/{calendarId}` | 특정 일정 삭제 | (Query: `email=email@naver.com`) |

## 페이지네이션(Page) 관련 API

| 번호 | 메서드 | 엔드포인트 | 설명 | 요청 예시 |
|------|--------|------------|------|----------|
| 10 | GET | `/pages` | 페이지별 데이터 조회 | (Query: `pageNum=1`, `pageSize=5`) |

