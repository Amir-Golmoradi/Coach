````markdown
# 🚀 Contributing to Coach

Thank you for considering contributing to Coach!  
We welcome all contributions, whether it's **bug fixes, feature improvements, documentation updates, or suggestions**.

Your contributions will help shape **Coach** into a cutting-edge **AI-powered fitness platform**!

---

## 📌 How to Contribute

### 1️⃣ Fork the Repository

Click the **Fork** button on the top right corner of this repository to create a copy under your GitHub account.

### 2️⃣ Clone Your Fork

Run the following command in your terminal:

```sh
git clone https://github.com/YOUR_USERNAME/Coach.git
cd Coach
````

### 3️⃣ Create a New Branch

Branch naming convention:

- **Features:** `feature/your-feature-name`
- **Bug Fixes:** `fix/your-bug-name`
- **Documentation:** `docs/update-readme`

```sh
git checkout -b feature/your-feature-name
```

### 4️⃣ Make Your Changes

- Follow **Clean Architecture** and **Domain-Driven Design (DDD)** principles.
- Ensure **code readability and modular design**.
- Write **meaningful commit messages** (see below 👇).

### 5️⃣ Write Tests

All new features **must** include tests. Run the test suite before submitting:

```sh
mvn test
```

### 6️⃣ Commit Changes

Follow this commit message format:

```
[Type] Short Description (#Issue Number)
```

**Examples:**  
✅ `[Feature] Added AI-based workout suggestions (#32)`  
✅ `[Fix] Resolved API timeout issue (#45)`

### 7️⃣ Push Your Changes

```sh
git push origin feature/your-feature-name
```

### 8️⃣ Open a Pull Request (PR)

- Go to **Pull Requests** in the main repository.
- Click **"New Pull Request"**.
- Select your branch and **describe your changes**.

---

## 📜 Contribution Guidelines

✅ **Follow the project structure.**  
✅ **Write clean, modular, and well-documented code.**  
✅ **Use meaningful variable and function names.**  
✅ **Ensure all tests pass before submitting a PR.**  
✅ **Be respectful and constructive in code reviews.**

🚨 **We won't accept PRs with:** ❌ Unclear commit messages.  
❌ Failing tests.  
❌ Unrelated changes in the same PR.

