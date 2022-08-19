package org.miro.testproject.proxy.driver;

import org.miro.testproject.proxy.element.Element;
import org.miro.testproject.proxy.element.ElementImpl;
import org.miro.testproject.proxy.locators.Locator;
import org.miro.testproject.utils.browser.Browser;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

import static org.miro.testproject.utils.common.HelperUtil.killAllProcesses;

public class DriverImpl implements Driver {

    private final WebDriver webDriver;
    private final Browser browser;

    public DriverImpl(WebDriver driver, Browser browser) {
        this.browser = browser;
        this.webDriver = driver;
    }

    @Override
    public Browser getBrowser() {
        return this.browser;
    }

    @Override
    public Element createElement(Locator locator) {
        return new ElementImpl(this, locator);
    }

    @Override
    public void goToUrl(String url) {
        this.webDriver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        return this.webDriver.getCurrentUrl();
    }

    @Override
    public void quit() {
        this.webDriver.manage().deleteAllCookies();
        this.webDriver.quit();
        killAllProcesses(this.browser.getProcessName());
    }

    @Override
    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    @Override
    public Set<String> getWindowHandles() {
        return this.webDriver.getWindowHandles();
    }

    @Override
    public WebDriver.TargetLocator switchTo() {
        return this.webDriver.switchTo();
    }

    @Override
    public void close() {
        this.webDriver.close();
    }

    @Override
    public void waitUrlContains(String expected, long duration) {
        WebDriverWait wait = new WebDriverWait(this.webDriver, Duration.ofSeconds(duration));
        wait.until(ExpectedConditions.urlContains(expected));
    }

    @Override
    public void setUserLocale(String locale) {
        this.webDriver.manage().deleteCookie(getUserLocale());
        Cookie userLocale = new Cookie("userLocale", locale, "miro.com", "/", null);
        this.webDriver.manage().addCookie(userLocale);
    }

    @Override
    public Cookie getUserLocale() {
        return this.webDriver.manage().getCookieNamed("userLocale");
    }
}
