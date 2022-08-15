
<br />
<p align="center">
  <a href="https://gitlab.com/MartinSorani/miro.testproject">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

  <h3 align="center">Miro Test Project</h3>

  <p align="center">
    Short assignment for Miro sign up flow
  </p>
</p>



## Table of Contents

* [Requirements](#about-the-project)
  * [Built With](#built-with)
* [Getting Started](#getting-started)
  * [Prerequisites](#prerequisites)
* [Configuration](#configuration)
* [Test suite documentation](#test-suite-documentation)
* [Contact](#contact)



## Requirements for this assignment

● Feel free to choose a coding language among Javascript/Typescript/Java. We
would suggest to pick Typescript since our team is using TS as main E2E UI test
language
● You could choose any test framework such as selenium, playwright, cypress or
testcafe etc.
● Please avoid using some BDD Framework such as Cucumber.
● Please add a Readme within the project
● We can consider a successful registration when the "Check your email" screen is
visible.
● The way to manage test structure is also as important as designing the test
cases from all possible perspectives.
● We suggest the code of the implemented assignment should not be shared
publicly. Please host your code within Gitlab and share with qa.review@miro.com.


### Built With

* [https://maven.apache.org](Apache Maven)
* [https://junit.org/junit5/](JUnit 5)
* [https://www.java.com/en/](Java)
* [https://www.selenium.dev/](Selenium)


## Getting Started

To get a local copy up and running follow these steps:

1. Clone the repo
```sh
git clone git@gitlab.com:MartinSorani/miro.testproject.git
```
2. Run the tests
```sh
mvn clean test
```


### Prerequisites

This project requires Java 8+ and Apache Maven to run.
Download and install java for your OS from [https://www.java.com/en/](this link).
Verify your Java installation by running the command

`java -version`
~~~
λ java -version
java version "1.8.0_181"
Java(TM) SE Runtime Environment (build 1.8.0_181-b13)
Java HotSpot(TM) 64-Bit Server VM (build 25.181-b13, mixed mode)
~~~

Download Maven from [https://maven.apache.org](this link) and follow the instructions for installation.
Verify your Maven installation by running

`mvn -v`
~~~
λ mvn -v
Apache Maven 3.8.5 (3599d3414f046de2324203b78ddcf9b5e4388aa0)
Maven home: E:\Program Files\apache-maven-3.8.5
Java version: 1.8.0_181, vendor: Oracle Corporation, runtime: E:\Program Files\Java\jdk1.8.0_181\jre
Default locale: en_US, platform encoding: Cp1252
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
~~~

## Configuration

This project uses Selenium Webdriver to emulate and automate interaction with different web browsers.
The supported browsers for this project are:
- Firefox
- Edge
- Chrome

In order to specify the browser to emulate we can:

1. Add the parameter `browser` to the Maven command:

```sh
mvn clean test -Dbrowser=Firefox
```
or

2. Configure the desired option in the file `src/main/resources/test.config.properties`

~~~
browser = Edge
~~~

In the same manner, we can specify if our browser instance is to run headless:

1. Add the parameter `headless` to the Maven command:

```sh
mvn clean test -Dheadless=true
```
or

2. Configure the desired option in the file `src/main/resources/test.config.properties`

~~~
headless = true
~~~

## Test suite documentation

Access this test suite documentation in [https://app.qase.io/project/MTP?view=1&suite=4](Qase) or access the reader friendly version at [TestCases.html](./documents/TestCases.html)


## Contact

Martin Sorani - [martin.sorani@gmail.com](mailto:martin.sorani@gmail.com)


