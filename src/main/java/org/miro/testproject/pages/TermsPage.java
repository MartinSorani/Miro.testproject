package org.miro.testproject.pages;

import io.qameta.allure.Step;
import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class TermsPage extends BasePage {

    //Elements
    public Element lblTitle = getElement("lblTitle");

    public TermsPage(Driver driver) {
        super(driver);
    }

    @Step
    public SignUpPage closeTabAndReturn() {
        driver.close();
        driver.switchTo().window(handleId);
        return new SignUpPage(driver);
    }
}
