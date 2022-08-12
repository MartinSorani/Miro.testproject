package org.miro.testproject.suites.base;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.miro.testproject.pages.MainPage;
import org.miro.testproject.utils.BrowserManager;
import org.miro.testproject.utils.PropertiesReader;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger("[Test]");
    private static PropertiesReader reader = new PropertiesReader("test.config");
    private static final String baseUrl = reader.getProperty("baseUrl").getSelector();
    private static WebDriver driver;

    @BeforeAll
    static void initAll() {
        driver = new BrowserManager().getDriver();
    }

    @AfterAll
    static void tearDownAll() {
        log.info("Test complete");
        driver.quit();
    }

    protected MainPage test() {
        log.info("Begin test case");
        driver.get(baseUrl);
        return new MainPage(driver);
    }
}
