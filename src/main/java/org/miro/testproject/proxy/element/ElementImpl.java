package org.miro.testproject.proxy.element;

import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.locators.Locator;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementImpl implements Element {

    private final WebDriver webDriver;
    private final Locator locator;
    private final WebDriverWait wait;

    public ElementImpl(Driver driver, Locator locator) {
        this.locator = locator;
        this.webDriver = driver.getWebDriver();
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(2L));
    }

    private WebElement webElement() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
    }

    private void waitToBeClickable() {
        wait.until(ExpectedConditions.elementToBeClickable(webElement()));
    }

    public WebElement toWebElement() {
        return webElement();
    }

    public void enterText(String text) {
        webElement().clear();
        webElement().sendKeys(text);
    }

    @Override
    public void waitUntilVisible() {
        wait.until(ExpectedConditions.visibilityOf(webElement()));
    }

    @Override
    public void waitUntilInvisible() {
        wait.until(ExpectedConditions.invisibilityOf(webElement()));
    }

    @Override
    public Locator getLocator() {
        return this.locator;
    }

    @Override
    public void click() {
        waitToBeClickable();
        try {
            webElement().click();
        } catch (ElementClickInterceptedException e) {
            removeCookieBanner();
            webElement().click();
        }
    }

    @Override
    public String getText() {
        return webElement().getText();
    }

    @Override
    public boolean isVisible() {
        return webElement().isDisplayed();
    }

    private void removeCookieBanner() {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("return document.querySelectorAll('#onetrust-consent-sdk').forEach(function(element) {element.remove();});");
    }
}
