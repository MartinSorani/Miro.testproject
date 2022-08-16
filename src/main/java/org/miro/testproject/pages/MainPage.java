package org.miro.testproject.pages;

import io.qameta.allure.Step;
import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class MainPage extends BasePage {

    //Elements
    Element btnSignup = getElement("btnSignup");

    public MainPage(Driver driver) {
        super(driver);
    }

    @Step
    public SignUpPage clickSignUpButton() {
        log.info("Click sign up button");
        btnSignup.click();
        return new SignUpPage(this.driver);
    }
}
