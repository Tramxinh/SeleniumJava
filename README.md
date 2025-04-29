# 🚗 Project Auto Testing with Selenium + Java

This project uses ** Selenium WebDriver **, ** Java **, and ** TestNG ** to automate the user interface test on
the browser. The test case is designed to run ** parallel (parallel) ** to optimize the execution time.
---

## 🔧 Technical used

- ☕ Java (JDK 17+)
- 🌐 Selenium WebDriver
- 🧪 TestNG
- 📦 Maven
- 🧩 ChromeDriver
- 💡 Log4j / Allure Reports

---

## 📁 Folder structure

```bash
Du-An-Auto-Selenium/
├── .idea/                # IntelliJ IDEA structure
├── allure-report/        # Allure report
├── export/               # Export file folder
├── logs/                 # Files log
├── src/
│   ├── main/             # Source code main
│   └── test/
│       ├── java/
│       │   └── sam/
│       │       └── com/
│       │           ├── common/         # Common utility class
│       │           └── pageObjectModal # Function Page
│       └── resources/   # File resource (test data, config, v.v.)
├── suites/               # Folder test suite (testng.xml, v.v.) 
├── .gitignore            
├── pom.xml               # File Maven to management dependency & structure
├── README.md             # Introduction Document, project tutorial 
           



🚀 Tutorial run the project
1. Clone to local
git clone https://github.com/samdao39/DU_AN_AUTO_SELENIUM.git

cd cd Du-An-Auto-Selenium

2. Install dependency
mvn clean install

3. Run test parallel with TestNG
mvn test -DsuiteXmlFile=suites/testng.xml

4. Run file Allure report
 allure generate --single-file target/allure-results --clean        
 then open file html in  folder allure report

5. Test Cases
List test case run auto test:

🟢 Login successfully

🟢 Login with Email invalid

🟢 Login with Password invalid

🟢 Search products and check info import to Excel file

🟢 Add products to cart and check information in excal file

🟢 Checkout in cart

List test case is writed follow to the Page Object Model (POM) and improvement to run parallel.

🛠 System is required:
✅ Java JDK 8 or more

✅ Maven 3.6+

✅ Browser Chrome (flexible)

✅ WebDriver (ChromeDriver..)

🤝 Comment & Contact:
You can write an issue if you have a bug or contribute the idea. Thankyou!

Contact:

📧 Email: daonguyenanhtram@gmail.com

🧑‍💻 GitHub: @samdao39

