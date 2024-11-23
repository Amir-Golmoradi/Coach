# Contributing Guidelines 

## English Version

We appreciate your interest in contributing to this project! Please ensure you carefully read and follow the guidelines outlined below. Non-compliance with these rules may result in your contributions being rejected or delayed. To maintain high-quality standards, it is essential that contributors follow the best practices outlined here.

### 1. Code of Conduct
All contributors must follow the [Code of Conduct](link_to_code_of_conduct) to foster a welcoming and respectful community. Any violation of this code will result in immediate removal from the project.

### 2. Fork and Pull Request Workflow 
- **Fork** the repository and create a **new branch** from the `main` branch. Never work directly on the `main` branch.
- **Do not** create a pull request from your `main` branch.
- Always ensure your branch is **up to date** with the `main` branch to prevent merge conflicts.

  **Incorrect**:
  ```
  # Working directly on the main branch
  git checkout main
  git pull origin main
  git push origin main
  ```
  **Correct**:
  ```
  # Create a new feature branch
  git checkout -b feature/my-new-feature
  git pull origin main
  git push origin feature/my-new-feature
  ```

- Create a **pull request (PR)** with a clear and concise title and description. Be sure to include the following:
    - What the change does.
    - Why the change is necessary.
    - Any relevant background or context.

  **Incorrect**:
  ```
  "Fixed things"
  ```
  **Correct**:
  ```
  "Fixed bug in user login flow to prevent incorrect password error."
  ```

### 3. Code Quality 
Maintaining clean, maintainable, and efficient code is crucial to the project's success.

- **Follow the project's coding standards** (refer to the project's style guide).
- Use **descriptive naming** for variables, functions, and classes to ensure clarity.
- Your code should be **efficient** and **easily understandable**. Avoid overly complex logic.

  **Incorrect**:
  ```java
  var x = 5;
  var y = 10;
  var z = x + y;
  ```
  **Correct**:
  ```java
  var baseSalary = 5000;
  var bonus = 1000;
  var totalSalary = baseSalary + bonus;
  ```

- **Write tests** for your code, including unit tests and integration tests. Make sure all tests pass before submitting your pull request.

  **Incorrect**:
  You add a new feature without any tests and submit it.

  **Correct**:
  Add unit tests that test the core logic of the new feature.

### 4. Commit Messages 
Writing clear and concise commit messages is crucial for project history and collaboration.

- **Use the imperative mood** (e.g., "Fix bug" rather than "Fixed bug").
- A good commit message should answer the **why** and **what** of the change, not just the **how**.
- A commit should represent a **single logical unit of work**. Avoid large commits that contain multiple changes unrelated to each other.

  **Incorrect**:
  ```
  "Fixed bug in login flow and added new payment gateway"
  ```
  **Correct**:
  ```
  "Fixed bug in login flow"
  ```
  ```
  "Added support for new payment gateway"
  ```

- **Do not include irrelevant information** in your commit messages (e.g., "Fixes issue #123" should only be included if it's relevant to the issue being addressed).

### 5. Testing 
Testing is an essential part of ensuring your changes work as expected and don't break existing functionality.

- Write **unit tests** for every function and method you create.
- Include **integration tests** for features that interact with other parts of the system.
- Ensure that all tests pass before submitting a pull request.

  **Incorrect**:
  You submit a new feature without any tests, causing potential failures in other parts of the system.

  **Correct**:
  Add tests that cover various edge cases for the new feature.

- You must use the project's **approved testing framework**. In this case, we use **XUnit** for C# testing.

### 6. Documentation 
Documentation is crucial for understanding how the project works, especially for future contributors.

- Ensure your code is **well-documented**. Each function or method must have **docstrings** describing its behavior.
- Update the relevant **documentation files** (`README.md`, `CONTRIBUTING.md`, etc.) whenever new features are added or changed.
- **Be clear and concise** in your explanations.

  **Incorrect**:
  ```java
  // This function does something
  public void DoSomething() { ... }
  ```
  **Correct**:
  ```java
  // This function processes the user data and returns the result after validation.
  public void ProcessUserData() { ... }
  ```

### 7. Issue Tracking 
Before starting any work, ensure there is a corresponding **issue** in the project’s issue tracker.

- If there is no issue for the change you intend to make, **create one** with the necessary details (describe the bug, feature, or enhancement).
- Make sure the issue is linked to your pull request.

  **Incorrect**:
  You work on a feature without opening an issue first, leading to confusion about the purpose of the changes.

  **Correct**:
  Open an issue with a clear description, and reference the issue in your pull request.

### 8. Security 
- **Do not expose sensitive information** such as API keys, passwords, or any credentials in your code.
- **Report any security vulnerabilities** directly to the project maintainers. Do not disclose security issues publicly until they are fixed.

  **Incorrect**:
  Hard-coding API keys or passwords in the code.

  **Correct**:
  Store sensitive information in environment variables or use secure vaults.

### 9. License 
By contributing, you agree to license your contributions under the **MIT License**. All contributions should comply with the licensing terms.

