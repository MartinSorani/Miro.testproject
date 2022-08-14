package org.miro.testproject.proxy.element;

import org.miro.testproject.proxy.locators.Locator;
import org.openqa.selenium.WebElement;

public interface Element {
    Locator getLocator();

    void click();

    WebElement toWebElement();

    void enterText(String text);

    String getText();

    boolean isVisible();

    void waitUntilVisible();

    void waitUntilInvisible();

    String getAttribute(String attribute);
}
