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

## Services

### TokenService

```java

