# Welcome to Coach!

<div align="center"> 
  <img height="100" width="100" src="assets/coach-logo.png" />
  <br> 
  <strong>Coach</strong> 
</div> 
<br>

## Table of Contents

- [Project Overview](#project-overview)
- [Built With](#built-with)
    - [Backend](#backend)
    - [Mobile](#mobile)
- [Contributing](#contributing)
- [Code of Conduct](#code-of-conduct)
- [License](#license)

## Project Overview

Coach is a comprehensive fitness application designed to provide personalized workout and diet plans using AI-driven
algorithms. It is built with a modular, monolithic architecture, providing flexibility for potential scalability in the
future. The platform ensures robust security, real-time data synchronization, and seamless integration across mobile and
backend systems.

## Built With

### Backend

The backend of Coach is developed using the following technologies:

| Technology                 | Description                                                                               |
|----------------------------|-------------------------------------------------------------------------------------------|
| Spring Boot                | A powerful framework for building Java-based applications with enterprise-level features. |
| MSSQL Server               | A relational database used for storing structured data efficiently.                       |
| MongoDB                    | A NoSQL database used for managing unstructured and semi-structured data.                 |
| Redis                      | Used for caching and session management to improve performance.                           |
| JWT & OAuth2               | For securing APIs and user authentication with industry-standard protocols.               |
| Spring Security            | A security framework for configuring and managing authentication and authorization.       |
| RESTful API                | For exposing endpoints and interacting with the mobile and web clients.                   |
| JUnit & Mockito            | For writing unit tests and ensuring the application's stability.                          |
| Domain-Driven Design (DDD) | Aligning the software model with the core business domain.                                |
| Hexagonal Architecture     | Promoting separation of concerns for easier maintainability.                              |

### Mobile

For mobile app development, we are using:

| Technology | Description                                                       |
|------------|-------------------------------------------------------------------|
| Flutter    | For building cross-platform mobile apps for both iOS and Android. |
| Bloc       | State management solution to handle the app's state.              |
| Dart       | The programming language used to build the mobile app.            |

## Contributing

We welcome contributions from the open-source community!
Please follow the steps from [CONTRIBUTE](./CONTRIBUTE.md) guidelines:

### Code Quality and Style

- Follow consistent code formatting rules.
- Write clear, descriptive commit messages.
- Use proper naming conventions for variables, methods, and classes.

**Bad Example:**

```java
public void a() { /* Code here */ }
```

**Good Example:**

```java
public void processOrder() { /* Code here */ }
```

### Issue Reporting and Resolution

- Always open an issue for bugs or new feature requests before starting any work.
- Provide a detailed description of the issue or feature request, including steps to reproduce if it's a bug.

**Bad Example:**
"App crashes when I open the orders page."

**Good Example:**
"App crashes on the Orders page after clicking on a specific order ID. Steps to reproduce:

1. Open the app.
2. Navigate to the Orders tab.
3. Click on the order ID '12345'."

### Pull Requests

- Ensure your branch is up to date with the latest main branch.
- Make sure your code passes all the tests before creating a pull request.
- Include a description of what your pull request does and why.

## Code of Conduct

We are committed to creating an open, inclusive, and respectful environment for all contributors to the project. Please
follow this [CODE OF CONDUCT](./CODE_OF_CONDUCT.md) during your involvement in the project.

## License

This project is licensed under the MIT License - see the [LICENSE](./LICENSE.md) file for details.

