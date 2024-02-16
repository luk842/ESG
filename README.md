# test

1. Java recommend version is 19
2. Maven recommend version is 3.9.1
3. IDE (e.g. Intellij [Home page](https://www.jetbrains.com/idea/download/))

## Intellij config

1. Local admin to install Java and Intellij on your local machine. It is required in case you want to run tests locally

### How to open project locally

1. Clone this repository to your local machine
2. Import it in IntelliJ
3. Click right button on a _pom.xml_ file and select _Maven_ -> _Reimport_
4. Wait until all dependencies are resolved

#### Running tests in IDE

Before running to avoid downloading browser by Playwright you need to open Run Configuration and set Environment
Variable: `PLAYWRIGHT_SKIP_BROWSER_DOWNLOAD=1`.
Next You have to choose env, use Maven profiles in Intellij on the right site(DEV, TEST, UAT).
Tests You can run from Test.xml or directly from Test class

##### Repository structure

I implement typical page object pattern structure.
listeners - listeners configuration
pages -  pages structure
properties - env configuration properties
setup - driver, test setup
resources - rest of properties
report - for example: target>surefire-reports?index.html

###### Contact

* Łukasz Pater - _lpater842@gmail.com_