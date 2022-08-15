package org.miro.testproject.tests.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.miro.testproject.pages.MainPage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.tests.listener.CustomListener;
import org.miro.testproject.utils.browser.BrowserManager;
import org.miro.testproject.utils.common.HelperUtil;
import org.miro.testproject.utils.properties.PropertiesReader;
import org.miro.testproject.utils.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(CustomListener.class)
public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger("[TEST]");
    private static final PropertiesReader reader = new PropertiesReader("test.config");
    private static final String baseUrl = reader.getProperty("baseUrl");
    private static Driver driver;
    protected User user;

    @BeforeEach
    void init(TestInfo testInfo) {
        log.info("Starting test case " +
                testInfo.getDisplayName());
        driver = new BrowserManager().getDriver();
        user = new User().generateRandomUser();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        log.info("Completed test " +
                testInfo.getDisplayName());
        driver.quit();
    }

    protected MainPage test() {
        driver.goToUrl(baseUrl);
        return new MainPage(driver);
    }

    protected boolean disableCondition() {
        return (driver.getBrowser().equals("Chrome") &&
                System.getProperty("headless").equals("true"));
    }

    public static void snapshot(String fileName) {
        HelperUtil.takeScreenshot(driver.getWebDriver(), fileName);
    }
}
