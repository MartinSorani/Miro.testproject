package org.miro.testproject.pages.base;

import org.miro.testproject.utils.PropertiesReader;
import org.miro.testproject.utils.locators.Locator;
import org.miro.testproject.utils.proxy.Element;
import org.miro.testproject.utils.proxy.ElementImpl;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger("Console");
    private final PropertiesReader reader;
    protected final WebDriverWait wait;
    protected final WebDriver driver;
    protected static String handleId;


    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        reader = new PropertiesReader(this.getClass().getSimpleName());
        assertUrlIsCorrect();
    }

    protected Element getElement(String elementName) {
        Locator locator = reader.getProperty(elementName);
        return new ElementImpl(this.driver, locator);
    }

    protected void waitForElementVisible(String elementName) {
        wait.until(ExpectedConditions.visibilityOf(getElement(elementName).toWebElement()));
    }

    protected void waitForElementVisible(Element element) {
        wait.until(ExpectedConditions.visibilityOf(element.toWebElement()));
    }

    private void assertUrlIsCorrect() {
        assertTrue(driver.getCurrentUrl().contains(reader.getProperty("url").getSelector()));
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
