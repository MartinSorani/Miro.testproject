package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class ApplePage extends BasePage {

    //Elements
    public Element lblAppleHeader = getElement("lblAppleHeader");

    public ApplePage(Driver driver) {
        super(driver);
    }
}
