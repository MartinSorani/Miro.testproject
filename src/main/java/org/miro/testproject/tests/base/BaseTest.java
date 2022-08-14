package org.miro.testproject.tests.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.miro.testproject.pages.MainPage;
import org.miro.testproject.utils.browser.BrowserManager;
import org.miro.testproject.utils.properties.PropertiesReader;
import org.miro.testproject.utils.users.User;
import org.miro.testproject.proxy.driver.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger("[TEST]");
    protected User user;

    private static final PropertiesReader reader = new PropertiesReader("test.config");
    private static final String baseUrl = reader.getProperty("baseUrl");
    private static Driver driver;

    @BeforeEach
    void init() {
        log.info("Start test case");
        driver = new BrowserManager().getDriver();
        user = new User().generateRandomUser();
    }

    @AfterEach
    void tearDown() {
        log.info("Test complete");
        driver.quit();
    }

    protected MainPage test() {
        driver.goToUrl(baseUrl);
        return new MainPage(driver);
    }
}
