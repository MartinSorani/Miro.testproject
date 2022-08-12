package org.miro.testproject.utils.proxy;

import org.miro.testproject.utils.locators.Locator;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementImpl implements Element {

    private final WebDriver driver;
    private final WebElement webElement;
    private final Locator locator;

    public ElementImpl(WebDriver driver, Locator locator) {
        this.locator = locator;
        this.driver = driver;
        this.webElement = driver.findElement(locator.getBy());
    }

    private void waitToBeClickable() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.elementToBeClickable(locator.getBy()));
    }

    public WebElement toWebElement() {
        return this.webElement;
    }

    public void enterText(String text) {
        this.webElement.clear();
        this.webElement.sendKeys(text);
    }

    @Override
    public Locator getLocator() {
        return this.locator;
    }

    @Override
    public void click() {
        waitToBeClickable();
        try {
            webElement.click();
        } catch (ElementClickInterceptedException e) {
            removeCookieBanner();
            webElement.click();
        }
    }

    @Override
    public Element findElement() {
        return new ElementImpl(this.driver, locator);
    }

    @Override
    public String getText() {
        return this.webElement.getText();
    }

    @Override
    public boolean isVisible() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10L));
        wait.until(ExpectedConditions.visibilityOf(this.webElement));
        return this.webElement.isDisplayed();
    }

    private void removeCookieBanner() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("return document.querySelectorAll('#onetrust-consent-sdk').forEach(function(element) {element.remove();});");
    }
}
