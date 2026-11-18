# JWT Microservices (Java 25)

Four independent Spring Boot WAR applications:

| Service | Port | Purpose |
|---|---:|---|
| Eureka Server | 8761 | Service registry |
| Auth Service | 8081 | Checks demo credentials and creates JWTs |
| Book Service | 8082 | Secures the in-memory book API with JWT |
| API Gateway | 8080 | Routes `/auth/**` and `/books/**` through Eureka |

All use Java 25 and Spring Boot `4.2.0-RC2`; Eureka and Gateway use Spring Cloud `2026.0.0-RC2`. The Cloud reference lists Boot 4.2.0-RC2 as the supported Boot version. RC versions are previews.

## Run in Eclipse

1. Install a Java 25 JDK and Maven 3.9+. Set Eclipse's installed JRE to Java 25.
2. Choose **File > Import > Maven > Existing Maven Projects** and select this `jwt-microservices` folder. Eclipse discovers all four Maven projects.
3. Start the `*Application.java` classes with **Run As > Java Application** in this order: Eureka (8761), Auth (8081), Book (8082), Gateway (8080).
4. Visit http://localhost:8761 and check that `AUTH-SERVICE`, `BOOK-SERVICE`, and `API-GATEWAY` register.

Each project also runs with `mvn spring-boot:run` from its own folder. Auth and Book can be called directly without Eureka; Eureka is needed for Gateway discovery and routing.

## Postman

**Login** — `POST http://localhost:8080/auth/login` (direct service: port 8081). Set `Content-Type: application/json`; raw JSON body:

```json
{"username":"student","password":"password123"}
```

Copy the response's `token` field. Invalid credentials return 401.

**List books** — `GET http://localhost:8080/books` (direct: port 8082), with header `Authorization: Bearer <paste-token-here>`. A valid token returns the in-memory books.

**Add a book** — `POST http://localhost:8080/books`, with `Content-Type: application/json` and the same Bearer header. Body:

```json
{"bookId":3,"bookName":"Clean Code","author":"Robert C. Martin"}
```

**Without a token** — `GET http://localhost:8080/books` with no Authorization header returns 401. Malformed, expired, and incorrectly signed tokens also return 401.

## Class guide

- `User`: receives the login username and password from JSON.
- `AuthController`: handles `POST /auth/login` and returns a token or 401.
- `AuthService`: compares with the example account `student` / `password123`.
- Auth `JwtUtil`: signs a one-hour JWT containing the username.
- `Book`: contains `bookId`, `bookName`, and `author`.
- `BookController`: lists and adds books in memory; changes reset on restart.
- Book `JwtUtil`: verifies the shared signature and token expiry and reads the username.
- `JwtAuthenticationFilter`: extracts `Bearer` from Authorization, validates it, and marks the request authenticated.
- `SecurityConfig`: leaves auth login open; requires authentication for book API and uses stateless sessions.
- `EurekaServerApplication`: starts the registry and dashboard.
- `GatewayApplication`: starts the servlet gateway. Its properties route service paths to names registered in Eureka.

The signing secret is repeated in auth and book to keep the lesson simple. Replace it with a securely stored secret in a real application. Credentials and books are intentionally in memory.
