package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class GooglePage extends BasePage {

    //Elements
    public Element lblBoxHeader = getElement("lblBoxHeader");

    public GooglePage(WebDriver driver) {
        super(driver);
    }
}
