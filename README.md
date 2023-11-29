# Webservice-project

**AuthAndAut**

## Project Description

The AuthAndAut project is a Spring Boot application that provides a secure authentication and authorization mechanism. It uses Spring Security for handling user authentication and authorization, and it employs JWT (JSON Web Token) for secure communication between the client and the server.

## Features

### 1. User Registration

- **Endpoint:** `/auth/register`
- **Description:** Allows users to register by providing a username, password, and an option to assign admin privileges.

### 2. User Login

- **Endpoint:** `/auth/login`
- **Description:** Allows users to log in and generates a JWT token for secure access to protected resources.

### 3. User Access Level

- **Endpoint:** `/user/`
- **Description:** Provides a simple endpoint for demonstrating user access levels.

### 4. Post Management

#### Add Post

- **Endpoint:** `/user/addPost`
- **Description:** Allows authenticated users to add posts. Posts are associated with the user who created them.

#### Get All Posts

- **Endpoint:** `/user/getPosts`
- **Description:** Allows authenticated users to retrieve all posts.

#### Get Post by ID

- **Endpoint:** `/user/getPostById/{id}`
- **Description:** Allows authenticated users to retrieve a specific post by providing its ID.

#### Update Post

- **Endpoint:** `/user/updatePost/{postId}`
- **Description:** Allows administrators to update a post. Only users with the 'ADMIN' role can perform this action.

#### Delete Post

- **Endpoint:** `/user/deletePost/{postId}`
- **Description:** Allows administrators to delete a post. Only users with the 'ADMIN' role can perform this action.

## Configuration

### Security Configuration

The project uses Spring Security for authentication and authorization. The configuration can be found in the `SecurityConfig` class.

### JWT Configuration

JWT decoding and encoding are configured in the `JwtDecoder` and `JwtEncoder` beans in the `SecurityConfig` class.

## Installation
*Make sure you these downloaded before you start*
+ An IDE of your choice preferably intelliJ [Here](https://www.jetbrains.com/idea/download/#section=windows) and the latest [JDK 21](https://www.oracle.com/se/java/technologies/downloads/)
  
### Usage
+ Clone the repository.
+  Configure the application.properties file with your database and other settings.
+  *before you start the program*
+ start the mysql workbench and create a new database called jwtsecurit like this
```
CREATE DATABASE jwtsecurity;
```
+  Run the application using your preferred IDE or by using the command: ./mvnw spring-boot:run

### dependancies
+ [Spring starter web](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-web)
+ [Spring starter Test](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test)
+ [Spring starter data jpa](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa)
+ [MySQL Connector/j](https://mvnrepository.com/artifact/com.mysql/mysql-connector-j)
+ [spring boot starter security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
+ [spring security test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test)
+ [spring boot starter-oauth2 resource server](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-oauth2-resource-server)
+ [Lombok](https://mvnrepository.com/artifact/org.projectlombok/lombok)
  
