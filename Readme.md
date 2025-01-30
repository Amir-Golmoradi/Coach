# Welcome to Coach!
<div align="center">  
  <image height= "100" width ="100" src="assets/logo.png"></image>
  <br>  
  <strong>Coach</strong>  
</div>
<br>

![Static Badge](https://img.shields.io/badge/MIT-License-brightgreen) [![Contributions Highly Welcome](https://img.shields.io/badge/contributions-welcome-brightgreen.svg?style=flat)](https://github.com/Amir-Golmoradi/Coach/blob/master/CONTRIBUTING.md) ![GitHub Repo stars](https://img.shields.io/github/stars/Amir-Golmoradi/Coach) ![GitHub forks](https://img.shields.io/github/forks/Amir-Golmoradi/Coach?label=Amir%20Golmoradi) ![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)

## 📜 Description

Leading a healthy life poses Herculean tasks in the fast-paced world of today. Coach, an artificial intelligence-powered
mobile app, seeks to empower individuals towards fitness and health by giving them personalized guidance, useful
information, and constant motivation.

COACH is a smart, personalized virtual coach that helps users achieve their fitness goals by providing tailored training
and diet plans. They can monitor their exercise routines, track their diet, and get personalized advice based on their
goals, performance, and preferences. Coach leverages the strength of AI to anticipate future obstacles, discover new
avenues for improvement, and guide users toward achieving their fitness goals in a healthy, sustainable way.

## 🛠 Technology Stack

| Category                     | Technology                                                                                                                                                                                                      | Description                                                      |
|------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| **Programming Language**     | ![Java](https://img.shields.io/badge/Java-21-blue?logo=openjdk)                                                                                                                                                 | Core backend language                                            |
| **Frontend**                 | ![React](https://img.shields.io/badge/React-18-blue?logo=react) ![TypeScript](https://img.shields.io/badge/TypeScript-5-blue?logo=typescript)                                                                   | React-based UI for Admin Panel, using TypeScript for type safety |
| **Architecture**             | ![DDD](https://img.shields.io/badge/Architecture-DDD-blueviolet) ![Clean Architecture](https://img.shields.io/badge/Architecture-Clean_Architecture-blueviolet)                                                 | Modular, scalable, and maintainable design                       |
| **Framework**                | ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.4.2-brightgreen?logo=springboot)                                                                                                                    | Rapid development with embedded servers                          |
| **Security System**          | ![OAuth 2.0](https://img.shields.io/badge/Auth-OAuth_2.0-orange?logo=oauth) ![Magic Links](https://img.shields.io/badge/Auth-Magic_Links-blue) ![Email OTP](https://img.shields.io/badge/Auth-Email_OTP-yellow) | Secure authentication via Google, Apple, Facebook                |
| **Database**                 | ![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-blue?logo=postgresql)                                                                                                                            | Scalable and reliable relational database                        |
| **Database Migration**       | ![Flyway](https://img.shields.io/badge/Migration-Flyway-red?logo=flyway)                                                                                                                                        | Database version control                                         |
| **Dependency Injection**     | ![Spring DI](https://img.shields.io/badge/DI-Spring_DI-brightgreen?logo=spring)                                                                                                                                 | Manages dependencies and beans lifecycle                         |
| **Configuration Management** | ![Spring Cloud Vault](https://img.shields.io/badge/Config-Spring_Cloud_Vault-purple?logo=vault)                                                                                                                 | Secure secrets and configuration management                      |
| **Asynchronous Work**        | ![Spring WebFlux](https://img.shields.io/badge/Reactive-Spring_WebFlux-green?logo=spring)                                                                                                                       | Non-blocking reactive programming                                |
| **Testing & TDD**            | ![TDD](https://img.shields.io/badge/Testing-TDD-yellow) ![JUnit 5](https://img.shields.io/badge/Testing-JUnit_5-blue?logo=junit5) ![TestContainers](https://img.shields.io/badge/Testing-TestContainers-orange) | Automated tests and integration testing with containers          |
| **Build Tool**               | ![Maven](https://img.shields.io/badge/Build-Maven-red?logo=apachemaven)                                                                                                                                         | Dependency management and project build                          |
| **Logging**                  | ![SLF4J](https://img.shields.io/badge/Logging-SLF4J-lightgrey?logo=log4j)                                                                                                                                       | Unified logging for backend services                             |
| **Development Tools**        | ![Spring Boot DevTools](https://img.shields.io/badge/DevTools-Spring_Boot_DevTools-brightgreen)                                                                                                                 | Enhances development experience with auto-restart                |
| **CI/CD**                    | ![GitHub Actions](https://img.shields.io/badge/CI/CD-GitHub_Actions-blue?logo=githubactions)                                                                                                                    | Automate testing and deployment                                  |
| **Containerization**         | ![Docker](https://img.shields.io/badge/Containerization-Docker-blue?logo=docker)                                                                                                                                | Isolated application environment                                 |

## 🤝 Contribution

Contributions are welcome! If you have any feedback or suggestions, please don't hesitate to let us know. We appreciate
your contributions and support. If you find a bug or want to add a new feature, please submit a pull request. Please
read [CONTRIBUTING.md](https://github.com/Amir-Golmoradi/Coach/blob/master/Contributing.md) for details on
our [Code of Conduct](https://github.com/Amir-Golmoradi/Coach/blob/master/Code_Of_Conduct.md) and the process for
submitting pull requests.

## 📱 Application Overview

This project is designed for a **fitness app** that helps users track their workouts, diet, and get AI-powered
recommendations. The app can sync with other fitness platforms for a seamless experience. The backend API is built using
**Java with Spring Boot**, allowing you to scale the app while providing a secure and smooth experience.

## Installation Steps

#### Java & Spring Boot Installation

1. **Clone the project repository:**

    ```bash
    git clone https://github.com/Amir-Golmoradi/Coach.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd Coach
    ```

3. **Maven Setup:**

    ```bash
    mvn clean install
    ```

4. **Create a copy of the `.env` file (add environment variables if needed):**

    ```bash
    cp .env.example .env
    ```

5. **Generate an application key (if required):**

    ```bash
    mvn generate-key
    ```

6. **Run migrations and seed the database (if needed):**

    ```bash
    mvn spring-boot:run
    ```

7. **Start the server:**

    ```bash
    mvn spring-boot:run
    ```

## 📑 Features

- **Admin Dashboard**: Manage users, exercises, plans, and nutrition.
- **AI Integration**: Recommendations based on user activity and preferences.
- **Full API**: Allows integration with mobile apps, external devices, and other platforms.

## Table of Features

| Features           | Admin Panel    | API App        | AI Integration |
|--------------------|----------------|----------------|----------------|
| Dashboard          | 🔜 Coming Soon | ✔️ Yes         | 🔜 Coming Soon |
| User Management    | ✔️ Done        | ✔️ Yes         | 🔜 Coming Soon |
| Workout Tracking   | ✔️ Done        | ✔️ Yes         | 🔜 Coming Soon |
| Diet Tracking      | ✔️ Done        | ✔️ Yes         | 🔜 Coming Soon |
| AI Recommendations | 🔜 Coming Soon | 🔜 Coming Soon | 🔜 Coming Soon |

## Run Tests

For running automated tests:

```bash
mvn test
```

## Postman Collection
🔜 Coming Soon

Feel free to contribute and improve this project!

Created by Amir Golmoradi
