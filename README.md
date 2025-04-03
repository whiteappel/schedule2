# schedule2
# 회원 API 명세서

| 기능       | 메소드  | URL               | Request                                      | Response                                        | 상태코드                   |
|------------|--------|-------------------|----------------------------------------------|------------------------------------------------|----------------------------|
| 회원가입   | POST   | `/members/signup` | `{ "username": "user1", "password": "1234", "email": "user1@example.com" }` | `{ "id": 1, "username": "user1" }`             | `201 Created`              |
| 회원 조회  | GET    | `/members/{id}`   | `id: Long (Path Variable)`                   | `{ "id": 1, "username": "user1", "email": "user1@example.com" }` | `200 OK`                   |
| 비밀번호 변경 | PATCH  | `/members/{id}`   | `{ "oldPassword": "1234", "newPassword": "5678" }` | `{ "newPassword": "5678" }`                     | `200 OK` / `401 Unauthorized` |
| 회원 삭제  | DELETE | `/members/{id}`   | `id: Long (Path Variable)`                   | 없음                                           | `200 OK` / `404 Not Found` |

## 설명

### 회원가입
- 새로운 회원을 생성하는 API
- 요청 데이터(JSON): `username`, `password`, `email`
- 상태코드 `201 Created` 반환

### 회원 조회
- 특정 회원의 정보를 조회하는 API
- `id`를 Path Variable로 전달
- 회원이 존재하지 않으면 `404 Not Found`

### 비밀번호 변경
- 기존 비밀번호와 새 비밀번호를 입력받아 변경
- 기존 비밀번호가 일치하지 않으면 `401 Unauthorized`

### 회원 삭제
- 특정 회원을 삭제하는 API
- 회원이 존재하지 않으면 `404 Not Found`

---

# 일정(Board) API 명세서

| 기능           | 메소드 | URL            | Request                          | Response                                                                                     | 상태코드                   |
|---------------|--------|----------------|----------------------------------|----------------------------------------------------------------------------------------------|----------------------------|
| 일정 생성     | POST   | `/boards`       | `requestdto`                     | `{ "id": 1, "title": "Meeting", "contents": "Project discussion", "username": "user1" }`    | `201 Created`              |
| 전체 일정 조회 | GET    | `/boards`       | 없음                             | `[ { "id": 1, "title": "Meeting", "contents": "Project discussion", "username": "user1" } ]` | `200 OK`                   |
| 특정 일정 조회 | GET    | `/boards/{id}`  | `id: Long (Path Variable)`       | `{ "id": 1, "title": "Meeting", "contents": "Project discussion", "username": "user1", "email": "user1@example.com" }` | `200 OK` / `404 Not Found` |
| 일정 삭제     | DELETE | `/boards/{id}`  | `id: Long (Path Variable)`       | `{ "message": "Board deleted successfully" }`                                               | `200 OK` / `404 Not Found` |

## 설명

### 일정 생성
- 새 일정을 등록하는 API
- 요청 데이터(JSON): `title`, `contents`, `username`
- 응답으로 생성된 일정 데이터 반환
- 상태코드 `201 Created` 반환

### 전체 일정 조회
- 저장된 모든 일정을 조회하는 API
- 요청 데이터 없음
- 응답으로 일정 리스트 반환

### 특정 일정 조회
- 특정 `id`의 일정을 조회하는 API
- 요청 데이터 없음 (Path Variable로 `id` 전달)
- 응답으로 `title`, `contents`, `username`, `email` 포함된 데이터 반환
- 일정이 없을 경우 `404 Not Found`

### 일정 삭제
- 특정 `id`의 일정을 삭제하는 API
- 요청 데이터 없음 (Path Variable로 `id` 전달)
- 삭제 성공 시 `200 OK`, 일정이 없을 경우 `404 Not Found`

---

# 유저(User) 로그인/로그아웃 API 명세서

| 기능   | 메소드 | URL     | Request                                    | Response                                                         | 상태코드                  |
|--------|--------|---------|-------------------------------------------|------------------------------------------------------------------|---------------------------|
| 로그인 | POST   | `/login` | `{ "userName": "user1", "password": "1234" }` | 쿠키(`Set-Cookie: userId=1`), `redirect:/home` | `302 Found` / `401 Unauthorized` |
| 로그아웃 | POST   | `/logout` | 없음                                    | 쿠키 삭제 (`Set-Cookie: userId=; Max-Age=0`), `redirect:/home` | `302 Found` |

## 설명

### 로그인 (`/login`)
- `POST` 요청으로 `userName`, `password`를 전달받아 로그인 수행
- 성공 시:
  - 로그인한 유저의 ID를 쿠키(`userId=1`)에 저장
  - `/home`으로 리다이렉트 (`302 Found`)
- 실패 시:
  - 로그인 페이지로 리다이렉트 (`302 Found`)
  - 또는 `401 Unauthorized` 응답 가능

### 로그아웃 (`/logout`)
- `POST` 요청으로 쿠키 삭제 후 로그아웃
- `userId` 쿠키의 `Max-Age=0`으로 설정해 브라우저에서 제거
- `/home`으로 리다이렉트 (`302 Found`)

---

# 세션(Session) 기반 홈 페이지 API 명세서

| 기능         | 메소드 | URL            | Request                     | Response                                                                           | 상태코드                  |
|-------------|--------|----------------|-----------------------------|------------------------------------------------------------------------------------|---------------------------|
| 세션 홈 조회 | GET    | `/session-home` | 세션 쿠키 (`JSESSIONID`)   | - **세션 있음**: `session-home.html` 페이지 반환  <br> - **세션 없음**: `redirect:/session-login` | `200 OK` / `302 Found` |

## 설명

### 세션 기반 홈 페이지 (`/session-home`)
- 브라우저가 `JSESSIONID` 쿠키를 포함해 요청하면, 해당 세션을 조회
- 세션이 없으면:
  - 로그인되지 않은 것으로 간주하고 `"session-login"` 페이지로 리다이렉트 (`302 Found`)
- 세션이 있으면:
  - 세션에서 로그인한 사용자 정보를 조회
  - 로그인 정보가 존재하면 `"session-home"` 페이지로 이동 (`200 OK`)

---

# 세션(Session) 기반 로그인/로그아웃 API 명세서

| 기능          | 메소드  | URL                | Request Body                              | Response                                                                 | 상태코드         |
|--------------|--------|------------------|----------------------------------|------------------------------------------------------------------------|---------------|
| **세션 로그인** | `POST` | `/session-login`  | `{ "userName": "string", "password": "string" }` | - **성공**: `302 Found`, `Location: /session-home` <br> - **실패**: `session-login.html` 페이지 반환 | `302 Found` / `200 OK` |
| **세션 로그아웃** | `POST` | `/session-logout` | `Session Cookie (JSESSIONID)` | - 세션 삭제 후 `session-home.html` 페이지로 리다이렉트 (`302 Found`) | `302 Found` |

## 설명

### `/session-login` (POST)
- 사용자가 로그인 정보를 입력하여 요청하면, `userService.login()`을 통해 검증
- 로그인 성공 시, 세션을 생성하고 로그인 정보를 `session.setAttribute()`에 저장
- 로그인 성공 후 `/session-home`으로 리다이렉트 (`302 Found`)
- 로그인 실패 시 `session-login.html` 페이지 반환 (`200 OK`)

### `/session-logout` (POST)
- 현재 사용자의 세션을 조회 (`request.getSession(false)`)
- 세션이 있으면 `session.invalidate()`로 세션 삭제
- 이후 `/session-home`으로 리다이렉트 (`302 Found`)
