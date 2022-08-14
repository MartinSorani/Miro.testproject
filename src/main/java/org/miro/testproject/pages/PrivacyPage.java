package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class PrivacyPage extends BasePage {

    //Elements
    public Element lblTitle = getElement("lblTitle");

    public PrivacyPage(Driver driver) {
        super(driver);
    }
}
