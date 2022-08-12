package org.miro.testproject.utils.proxy;

import org.miro.testproject.utils.locators.Locator;
import org.openqa.selenium.WebElement;

public interface Element {
    Locator getLocator();

    void click();

    Element findElement();

    WebElement toWebElement();

    void enterText(String text);

    String getText();

    boolean isVisible();
}
