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

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger("[Test]");
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

    private void assertUrlIsCorrect() {
        assertTrue(driver.getCurrentUrl().contains(reader.getProperty("url").getSelector()));
    }
}
