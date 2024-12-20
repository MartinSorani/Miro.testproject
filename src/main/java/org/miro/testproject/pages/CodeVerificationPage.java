package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class CodeVerificationPage extends BasePage {

    //Elements
    public Element txtCode = getElement("txtCode");
    public Element lblConfirmation = getElement("lblConfirmation");

    public CodeVerificationPage(Driver driver) {
        super(driver);
    }
}
