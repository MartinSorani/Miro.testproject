package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class PrivacyPage extends BasePage {

    //Elements
    public Element lblTitle = getElement("lblTitle");

    public PrivacyPage(WebDriver driver) {
        super(driver);
    }
}
