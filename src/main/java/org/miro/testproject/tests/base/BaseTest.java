package org.miro.testproject.tests.base;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.miro.testproject.pages.MainPage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.tests.listener.CustomListener;
import org.miro.testproject.utils.browser.BrowserManager;
import org.miro.testproject.utils.properties.PropertiesReader;
import org.miro.testproject.utils.users.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.qameta.allure.Allure.step;
import static org.miro.testproject.utils.browser.BrowserName.*;
import static org.miro.testproject.utils.common.HelperUtil.snapshot;

@ExtendWith(CustomListener.class)
public class BaseTest {

    protected static final Logger log = LoggerFactory.getLogger("[TEST]");
    private static final PropertiesReader PROPERTIES_READER = new PropertiesReader("test.config");
    private static final String BASE_URL = PROPERTIES_READER.getProperty("baseUrl");
    private static final BrowserManager BROWSER_MANAGER = new BrowserManager();

    private static Driver driver;
    protected User user;

    @BeforeEach
    void init(TestInfo testInfo) {
        step("Starting test case " +
                testInfo.getDisplayName());
        log.info("Starting test case " +
                testInfo.getDisplayName());
        driver = BROWSER_MANAGER.newDriver();
        user = new User().generateRandomUser();
    }

    @AfterEach
    void tearDown(TestInfo testInfo) {
        step("Completed test " +
                testInfo.getDisplayName());
        log.info("Completed test " +
                testInfo.getDisplayName());
        driver.quit();
    }

    protected MainPage test() {
        driver.goToUrl(BASE_URL);
        return new MainPage(driver);
    }

    protected boolean isHeadlessChrome() {
        return (BROWSER_MANAGER.getBrowser().browserName.equals(Chrome) &&
                BROWSER_MANAGER.getBrowser().isHeadless);
    }

    protected boolean isHeadlessChromeOrEdge() {
        return ((BROWSER_MANAGER.getBrowser().browserName.equals(Chrome) ||
                BROWSER_MANAGER.getBrowser().browserName.equals(Edge)) &&
                BROWSER_MANAGER.getBrowser().isHeadless);
    }

    public static void takeScreenshot(String fileName) {
        snapshot(driver.getWebDriver(), fileName);
    }
}
