## Spring Security JWT Authentication API

## Description
This project implements authentication and authorization using **Spring Security** and **JWT (JSON Web Token)**. It includes user authentication, password encoding, and secure token-based access control.

## Features
- **JWT-based Authentication**: Secure login and session handling.
- **Spring Security Integration**: Protects endpoints using JWT.
- **User Registration**: Saves new users with encrypted passwords.
- **Session Handling**: Generates unique session IDs.

## Technologies Used
- **Spring Boot**
- **Spring Security**
- **JWT (JSON Web Token)**
- **Spring Data JPA**
- **MySQL Database**
- **Maven**

## Endpoints
### Public Endpoints
- `GET /hello` - Returns a greeting message with session ID.
- `GET /xtoken` - Returns token-related session info.
- `POST /login` - Authenticates user and generates JWT.
- `POST /post` - Registers a new user with encrypted password.

## Prerequisites
- JDK 17
- MySQL installed and running
- Postman (for testing API requests)

3. Configure **application.properties**:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/your_database
   spring.datasource.username=root
   spring.datasource.password=your_password
   jwt.secret=your_secret_key
   ```

## Notes
- Ensure that the **database is created** before running the application.
- Use **JWT tokens** for authentication in secured endpoints.
- Passwords are securely stored using **BCrypt password encoding**.

## Author
This project is built for secure user authentication using **Spring Security and JWT**.

