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

# C# ASP.NET with AWS Cloud Technology Stack

| Technology                           | Description                                                                                                |
|--------------------------------------|------------------------------------------------------------------------------------------------------------|
| **ASP.NET Core**                     | A modern, cross-platform framework for building C# applications with enterprise-level features.            |
| **AWS RDS (SQL Server)**             | A managed relational database service for efficiently storing structured data in the cloud.                |
| **AWS DocumentDB (MongoDB)**         | A managed NoSQL database service compatible with MongoDB, ideal for unstructured and semi-structured data. |
| **AWS ElastiCache (Redis)**          | A fully managed caching service for improving performance through session and data caching.                |
| **JWT & OAuth2**                     | For securing APIs and handling user authentication with industry-standard protocols in ASP.NET.            |
| **ASP.NET Identity**                 | A robust framework for authentication and authorization in .NET applications.                              |
| **RESTful API with ASP.NET Web API** | For creating API endpoints to serve mobile and web clients effectively.                                    |
| **xUnit & Moq**                      | For writing unit tests and ensuring application stability in the .NET ecosystem.                           |
| **Domain-Driven Design (DDD)**       | Structuring the software model to align with the business domain.                                          |
| **Hexagonal Architecture**           | Promoting separation of concerns and ensuring long-term maintainability.                                   |
| **AWS Elastic Beanstalk**            | Simplifying deployment and management of ASP.NET Core applications in the cloud.                           |
| **AWS Lambda**                       | Leveraging serverless computing for event-driven tasks and microservices.                                  |
                         |

### Mobile
The mobile application is entirely developed and maintained by the **Persian Flutter** Community:

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

```csharp
public void a() { /* Code here */ }
```

**Good Example:**

```csharp
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

