package org.miro.testproject.pages.base;

import org.miro.testproject.utils.properties.PropertiesReader;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.locators.Locator;
import org.miro.testproject.proxy.element.Element;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger("[ACTION]");
    private final PropertiesReader reader;
    protected final WebDriverWait wait;
    protected final Driver driver;
    protected static String handleId;


    protected BasePage(Driver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver.getWebDriver(), Duration.ofSeconds(10L));
        reader = new PropertiesReader(this.getClass().getSimpleName());
        assertUrlIsCorrect();
    }

    protected Element getElement(String elementName) {
        Locator locator = reader.getLocator(elementName);
        return driver.createElement(locator);
    }

    private void assertUrlIsCorrect() {
        String expectedUrl = reader.getLocator("url").getSelector();
        wait.until(ExpectedConditions.urlContains(expectedUrl));
        assertTrue(driver.getCurrentUrl().contains(reader.getLocator("url").getSelector()));
    }

    protected void switchTabs() {
        ArrayList<String> currentTabs = new ArrayList<>(driver.getWindowHandles());
        if (currentTabs.size() > 1) {
            handleId = currentTabs.get(0);
            driver.switchTo().window(currentTabs.get(1));
        } else {
            log.warn("Warning: only one tab is active.");
            handleId = currentTabs.get(0);
        }
    }
}
