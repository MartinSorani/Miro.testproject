package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class CodeVerificationPage extends BasePage {

    //Elements
    public Element txtCode = getElement("txtCode");
    public Element lblConfirmation = getElement("lblConfirmation");

    public CodeVerificationPage(WebDriver driver) {
        super(driver);
    }
}
