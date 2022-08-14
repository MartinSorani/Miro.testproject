package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class MicrosoftPage extends BasePage {

    //Elements
    public Element imgMicrosoftLogo = getElement("imgMicrosoftLogo");

    public MicrosoftPage(Driver driver) {
        super(driver);
    }
}
