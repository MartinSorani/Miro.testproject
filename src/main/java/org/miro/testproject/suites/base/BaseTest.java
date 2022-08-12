package org.miro.testproject.suites.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.miro.testproject.pages.MainPage;
import org.miro.testproject.utils.BrowserManager;
import org.miro.testproject.utils.PropertiesReader;
import org.miro.testproject.utils.users.User;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger("[Test]");
    protected User user;

    private static final PropertiesReader reader = new PropertiesReader("test.config");
    private static final String baseUrl = reader.getProperty("baseUrl").getSelector();
    private static WebDriver driver;

    @BeforeEach
    void init() {
        log.info("Start test case");
        driver = new BrowserManager().getDriver();
        user = new User().generateRandomUser();
    }

    @AfterEach
    void tearDown() {
        log.info("Test complete");
        driver.close();
        driver.quit();
    }

    protected MainPage test() {
        driver.get(baseUrl);
        return new MainPage(driver);
    }
}
