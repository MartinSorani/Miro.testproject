package org.miro.testproject.proxy.driver;

import org.miro.testproject.proxy.element.Element;
import org.miro.testproject.proxy.locators.Locator;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public interface Driver {
    Element createElement(Locator locator);

    void goToUrl(String url);

    String getCurrentUrl();

    void quit();

    WebDriver getWebDriver();

    Set<String> getWindowHandles();

    WebDriver.TargetLocator switchTo();

    void close();

    void waitUrlContains(String expected, long duration);
}
