package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class MainPage extends BasePage {

    //Elements
    Element btnSignup = getElement("btnSignup");

    public MainPage(Driver driver) {
        super(driver);
    }

    public SignUpPage clickSignUpButton() {
        log.info("Click sign up button");
        btnSignup.click();
        return new SignUpPage(this.driver);
    }
}
