


          
# 🚗 Project Auto Testing with Selenium + Java

This project uses **Selenium WebDriver**, **Java**, and **TestNG** to automate user interface testing on browsers. The test cases are designed to run in **parallel** to optimize execution time.

---

## 🔧 Technologies Used

- ☕ Java (JDK 17+)
- 🌐 Selenium WebDriver 4.31.0
- 🧪 TestNG 7.4.0
- 📦 Maven
- 🧩 WebDriver (ChromeDriver)
- 💡 Log4j / Allure Reports
- 📊 Apache POI (Excel Processing)
- 🎥 Monte Screen Recorder

---

## 📁 Project Structure

```
Project-Auto-Selenium/
├── src/
│   ├── main/             # Main source code
│   │   ├── java/         # Contains main Java classes
│   │   └── resources/    # Resources for main source code
│   └── test/
│       ├── java/         # Test source code
│       │   └── com/
│       │       └── sam/
│       │           ├── cms/
│       │           │   └── tests/    # Test cases
│       │           ├── common/       # Common utility classes
│       │           └── pageObjects/  # Page Object Model classes
│       └── resources/    # Resources for tests (test data, configuration, etc.)
├── suites/               # Directory containing test suites (suiteCms.xml, etc.)
├── logs/                 # Directory for log files
├── export/               # Directory for exported files
├── allure-report/        # Allure reports
├── pom.xml               # Maven file for dependency & structure management
└── README.md             # Project introduction and guide
```

---

## 🚀 How to Run the Project

### 1. System Requirements

- ✅ Java JDK 17 or higher
- ✅ Maven 3.6+
- ✅ Chrome browser (or other browsers depending on configuration)
- ✅ Corresponding WebDriver (ChromeDriver, EdgeDriver, etc.)

### 2. Clone the Project

```bash
git clone https://github.com/Tramxinh/SeleniumJava.git
cd Project-Auto-Selenium
```

### 3. Install Dependencies

```bash
mvn clean install
```

### 4. Run Tests with Maven

```bash
mvn test -DsuiteXmlFile=suites/suiteCms.xml
```

### 5. Generate Allure Report

```bash
allure generate --single-file target/allure-results --clean
```

Then open the HTML file in the allure-report directory to view the report.

---

## 🖥️ How to Run the Project with IDEs

### IntelliJ IDEA

1. **Open the Project**
   - Open IntelliJ IDEA
   - Select File > Open... > Select the project directory > OK

2. **Install Dependencies**
   - Wait for IntelliJ to automatically import the Maven project
   - Or right-click on the `pom.xml` file > Maven > Reload project

3. **Run the Test Suite**
   - Open the `suites/suiteCms.xml` file
   - Right-click on the file > Run 'suiteCms.xml'
   - Or click on the run icon next to the `<suite>` tag in the file

4. **Create a TestNG Run Configuration**
   - Select Run > Edit Configurations...
   - Click the + button > TestNG
   - Select Suite in Test kind
   - Select the path to the `suites/suiteCms.xml` file in Suite
   - Name the configuration and click Apply > OK
   - Run the configuration from the toolbar

### Visual Studio Code

1. **Install Extensions**
   - Open VS Code
   - Install extensions: Java Extension Pack, Maven for Java, Test Runner for Java

2. **Open the Project**
   - File > Open Folder... > Select the project directory

3. **Install Dependencies**
   - Open Terminal in VS Code
   - Run the command: `mvn clean install`

4. **Run Tests with Maven**
   - In Terminal, run the command: `mvn test -DsuiteXmlFile=suites/suiteCms.xml`

5. **Run Tests with TestNG Explorer**
   - Open the `suites/suiteCms.xml` file
   - Click on the TestNG Explorer icon in the sidebar
   - Find and run the test suite from the explorer

---

## 📋 Test Case List

- 🟢 **Login Test**
  - Successful login
  - Login with invalid Email
  - Login with invalid Password

- 🟢 **Manage Profile Test**
  - Manage personal information

- 🟢 **Product Detail Test**
  - Search for products and check information exported to Excel file

- 🟢 **Cart Detail Test**
  - Add products to cart and check information in Excel file

- 🟢 **Checkout Detail Test**
  - Checkout from cart

All test cases are written following the Page Object Model (POM) pattern and are optimized to run in parallel.

---

## 🤝 Contributions & Contact

You can create an issue if you find a bug or want to contribute ideas. Thank you!

**Contact:**

- 📧 Email: daonguyenanhtram@gmail.com
- 🧑‍💻 GitHub: @Tramxinh