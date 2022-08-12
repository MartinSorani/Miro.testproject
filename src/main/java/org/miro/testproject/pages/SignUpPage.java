package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {

    //Elements
    Element txtUsername = getElement("txtUsername");
    Element txtEmail = getElement("txtEmail");
    Element txtPassword = getElement("txtPassword");
    Element chkSignupTerms = getElement("chkSignupTerms");
    Element chkSignupSubscribe = getElement("chkSignupSubscribe");
    Element btnSubmit = getElement("btnSubmit");
    Element lnkTerms = getElement("lnkTerms");
    Element lnkPrivacy = getElement("lnkPrivacy");

    public Element lblEmailWarning = getElement("lblEmailWarning");
    public Element lblPasswordWarning = getElement("lblPasswordWarning");
    public Element lblTermsWarning = getElement("lblTermsWarning");

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage enterUsername(String username) {
        log.info("Enter username " + username);
        txtUsername.enterText(username);
        return this;
    }

    public SignUpPage enterEmail(String email) {
        log.info("Enter email " + email);
        txtEmail.enterText(email);
        return this;
    }

    public SignUpPage enterPassword(String password) {
        log.info("Enter password " + password);
        txtPassword.enterText(password);
        return this;
    }

    public SignUpPage checkTermsBox() {
        log.info("Check terms checkbox");
        chkSignupTerms.click();
        return this;
    }

    public SignUpPage checkNewsBox() {
        log.info("Check subscribe to news checkbox");
        chkSignupSubscribe.click();
        return this;
    }

    public CodeVerificationPage clickSignupButton() {
        log.info("Click sign up button");
        btnSubmit.click();
        return new CodeVerificationPage(this.driver);
    }

    public SignUpPage clickSignupButtonExpectError() {
        log.info("Click sign up button and expect an error message");
        btnSubmit.click();
        return this;
    }

    public TermsPage clickTermsLink() {
        handleId = driver.getWindowHandle();
        lnkTerms.click();
        return new TermsPage(driver);
    }

    public PrivacyPage clickPrivacyLink() {
        handleId = driver.getWindowHandle();
        lnkPrivacy.click();
        return new PrivacyPage(driver);
    }
}
