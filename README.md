# 내일배움캠프 한달인턴 온보딩 과제 (JAVA)

- **API docs** : [Swagger](http://43.203.198.62:8080/swagger-ui/index.html)

## Features
- Spring Security + JWT 를 활용한 인증, 인가 기능
  -  Token 리프레시 기능
- Jwt 관련 단위 테스트 작성
- 로그인, 회원가입 컨트롤러 API 단위 테스트 작성
- EC2 배포

### 회원가입 ( POST /signup )
- Request Message
```json
{
  "username": "test",
  "password": "test1234",
  "nickname": "test"
}
```

- Response Message
```json
{
  "username": "test",
  "nickname": "test",
  "authorities": [
    {
      "authorityName": "ROLE_USER"
    }
  ]		
}
```
### 로그인 ( POST /sign )
- Request Message
```json
{
	"username": "test",
	"password": "1234"
}
```

- Response Message
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzdHJpbmciLCJhdXRoIjoiUk9MRV9VU0VSIiwiZXhwIjoxNzMxMjQ3MTE2LCJpYXQiOjE3MzEyNDM1MTZ9.e8DinZq4gP-SyuFo0xJsSNn5DjNphhPYBu3OvsXFxbA"
}
```

