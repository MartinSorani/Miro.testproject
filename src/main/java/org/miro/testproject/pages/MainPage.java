package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class MainPage extends BasePage {

    //Elements
    Element btnSignup = getElement("btnSignup");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage clickSignUpButton() {
        log.info("Click sign up button");
        btnSignup.click();
        return new SignUpPage(this.driver);
    }
}
