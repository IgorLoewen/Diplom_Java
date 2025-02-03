# Project Description

This project tests the resource: stellarburgers.nomoreparties.site/

## General Information

This project is a diploma work consisting of three parts:

1. **Unit Tests**
2. **API Testing**
3. **UI Testing**

The goal of the project is to test the functionality of the Stellar Burgers cosmic fast food, including its API and web
application. All three parts were merged into a single branch for better usability and integration.

## Technologies

The following technologies and tools were used in the project:

- **Java 11**
- **JUnit 4**
- **Mockito**
- **Selenium**
- **REST-Assured**
- **Allure**
- **Jacoco**

## Implementation

### 1. Unit Tests

In the first part of the project, unit tests were created for the following classes:

- `Bun`
- `Burger`
- `Ingredient`
- `IngredientType`

Mocks, stubs, and parameterization were used to ensure a high level of code coverage (at least 70%).
Jacoco report was generated and uploaded to the repository: igorloewen.github.io/Diplom_Java/

### 2. API Testing

For the second part, API tests were implemented, covering the main interaction scenarios with the Stellar Burgers
service:

- User creation
- User login
- User data modification
- Order creation
- Fetching user orders

Project structure:

- The `data` folder contains `OrderData` and `UserData` classes used for generating test data.
- Tests are located in the `test/api` folder. Each test is isolated and verifies a specific API scenario.

### 3. UI Testing

UI tests were developed using Selenium and the Page Object model.

The tests cover scenarios such as:

- User registration
- User login
- Navigation between pages
- Logging out
- Verifying constructor sections ("Buns," "Sauces," "Fillings")

Features:

- Classes like `LoginPage`, `MainPage`, `ProfilePage`, and `RegisterPage` are used to describe page elements.
- UI tests run in Google Chrome by default but are also configured to work with Yandex Browser. Tests can be triggered
  from the command line.
- After the tests are completed, the test environment is cleaned up, including the deletion of any temporary users
  created during the tests.

## Test Execution

To run the tests with the desired browser:

For Chrome:

```bash
mvn test -Dbrowser=chrome
```

For Yandex:

```bash
mvn test -Dbrowser=yandex
```

For both browsers:

```bash
mvn clean test -Dbrowser=chrome && mvn test -Dbrowser=yandex
```

## Allure Report: Generation and Viewing

This project uses **Allure** for generating test reports. The results are stored in the `target/allure-results` folder,
and the generated reports are stored in `target/allure-report`.

---

### 1. Preparation

Ensure that Allure is installed on your computer. Follow the instructions for your OS:

#### For Windows:

1. Download [Allure Commandline](https://github.com/allure-framework/allure2).
2. Extract the archive and add the `bin` folder to the `PATH` environment variable.

#### For macOS:

```bash
brew install allure
```

#### For Linux:

```bash
sudo apt install allure
```

---

### 2. Generating Test Results

1. Run the tests:
   ```bash
   mvn clean test
   ```
   After running the tests, the results will be stored in the folder:
   ```
   target/allure-results
   ```

---

### 3. Generating Allure Report

To create an HTML report from the saved results:

```bash
allure generate target/allure-results -o target/allure-report --clean
```

The `--clean` flag clears the `target/allure-report` folder before generating a new report.

---

### 4. Viewing Allure Report

To start a local web server and view the report in your browser:

```bash
allure serve target/allure-results
```

This method automatically creates a temporary HTML report and opens it in your browser.

If you have already generated the report (see step 3) and want to open it from the folder:

```bash
allure open target/allure-report
```

---

### 5. Git and Allure

#### Updating `.gitignore`

Add the following to `.gitignore`:

```plaintext
# Exclude temporary files and Allure cache
**/allure-results/*
**/allure-report/history/*
!**/allure-report/
!**/allure-results/
```

These commands:

1. **Exclude temporary files** from `allure-results` and `allure-report` that are not needed for analysis.
2. **Preserve necessary files**, such as report structure and results, for uploading to GitHub.

---

### 6. Uploading Results and Reports to GitHub

After modifying `.gitignore` and generating reports, run the following commands to upload the data to GitHub:

```bash
git add target/allure-results
git add target/allure-report
git commit -m "Added Allure Results and Allure Report"
git push origin main
```

Now Allure Results and Reports will be added to your repository and preserved between runs.

## Links

- Jacoco Report: igorloewen.github.io/Diplom_Java/

