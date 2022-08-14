package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class GooglePage extends BasePage {

    //Elements
    public Element lblBoxHeader = getElement("lblBoxHeader");

    public GooglePage(Driver driver) {
        super(driver);
    }
}
