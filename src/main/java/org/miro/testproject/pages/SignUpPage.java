package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.locales.Language;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;
import org.miro.testproject.proxy.locators.Locator;
import org.openqa.selenium.TimeoutException;

public class SignUpPage extends BasePage {

    //region Elements
    Element txtUsername = getElement("txtUsername");
    Element txtEmail = getElement("txtEmail");
    Element txtPassword = getElement("txtPassword");

    Element chkSignupTerms = getElement("chkSignupTerms");
    Element chkSignupSubscribe = getElement("chkSignupSubscribe");
    Element chkReviewTerms = getElement("chkReviewTerms");
    Element chkReviewSubscribe = getElement("chkReviewSubscribe");

    Element btnSubmit = getElement("btnSubmit");
    Element btnContinueSignup = getElement("btnContinueSignup");
    Element btnSelectLanguage = getElement("btnSelectLanguage");
    Element btnGoogleSignup = getElement("btnGoogleSignup");
    Element btnSlackSignup = getElement("btnSlackSignup");
    Element btnMicrosoftSignup = getElement("btnMicrosoftSignup");
    Element btnAppleSignup = getElement("btnAppleSignup");
    Element btnFacebookSignup = getElement("btnFacebookSignup");
    Element btnSignIn = getElement("btnSignIn");

    Element lnkTerms = getElement("lnkTerms");
    Element lnkPrivacy = getElement("lnkPrivacy");

    Element divReviewTerms = getElement("divReviewTerms");
    Element divLanguageBox = getElement("divLanguageBox");
    //endregion

    //region Validation
    public Element lblEmailWarning = getElement("lblEmailWarning");
    public Element lblPasswordWarning = getElement("lblPasswordWarning");
    public Element lblTermsWarning = getElement("lblTermsWarning");
    public Element lblTermsReviewWarning = getElement("lblTermsReviewWarning");
    public Element lblHeaderTitle = getElement("lblHeaderTitle");
    //endregion

    public SignUpPage(Driver driver) {
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
        log.info("Click terms link");
        lnkTerms.click();
        switchTabs();
        return new TermsPage(driver);
    }

    public PrivacyPage clickPrivacyLink() {
        log.info("Click privacy link");
        lnkPrivacy.click();
        switchTabs();
        return new PrivacyPage(driver);
    }

    public SignUpPage clickGoogleSignup() {
        log.info("Click sign up with Google button");
        btnGoogleSignup.click();
        divReviewTerms.waitUntilVisible();
        return this;
    }

    public SignUpPage clickSlackSignup() {
        log.info("Click sign up with Slack button");
        btnSlackSignup.click();
        divReviewTerms.waitUntilVisible();
        return this;
    }

    public SignUpPage clickMicrosoftSignup() {
        log.info("Click sign up with Microsoft button");
        btnMicrosoftSignup.click();
        divReviewTerms.waitUntilVisible();
        return this;
    }

    public SignUpPage clickAppleSignup() {
        log.info("Click sign up with Apple button");
        btnAppleSignup.click();
        divReviewTerms.waitUntilVisible();
        return this;
    }

    public SignUpPage clickFacebookSignup() {
        log.info("Click sign up with Facebook button");
        btnFacebookSignup.click();
        divReviewTerms.waitUntilVisible();
        return this;
    }

    public SignUpPage checkReviewTerms() {
        log.info("Check terms of service checkbox");
        chkReviewTerms.click();
        return this;
    }

    public SignUpPage checkReviewSubscribe() {
        log.info("Check receive news checkbox");
        chkReviewSubscribe.click();
        return this;
    }

    public GooglePage clickContinueToGoogle() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return new GooglePage(driver);
    }

    public SlackPage clickContinueToSlack() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return new SlackPage(driver);
    }

    public MicrosoftPage clickContinueToMicrosoft() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return new MicrosoftPage(driver);
    }

    public ApplePage clickContinueToApple() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return new ApplePage(driver);
    }

    public FacebookPage clickContinueToFacebook() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return new FacebookPage(driver);
    }

    public SignUpPage clickContinueToSignupAndExpectError() {
        log.info("Click continue to sign up button");
        btnContinueSignup.click();
        return this;
    }

    public SignUpPage selectLanguage(Language language) {
        log.info("Selecting language " + language.label);
        btnSelectLanguage.click();
        log.info("Wait for language menu to be visible");
        divLanguageBox.waitUntilVisible();
        Element targetOption = driver.createElement(new Locator()
                .setSelector("a[data-locale=" + language.code + "] div div")
                .setBy("css"));
        log.info(language.label + " option found. Clicking option");
        targetOption.click();
        if (!driver.getBrowser().equals("Firefox")) {
            try {
                log.info("Wait for language box to disappear");
                divLanguageBox.waitUntilInvisible();
            } catch (TimeoutException e) {
                log.warn("Language box didn't go away! Clicking again.");
                targetOption.click();
            }
        }
        if (!language.equals(Language.ENGLISH)) {
            log.info("Wait for url to contain " + language.code);
            driver.waitUrlContains(language.code, 2L);
        }
        log.info("Wait for the submit button to be visible");
        btnSubmit.waitUntilVisible();
        return this;
    }

    public SignInPage clickSignInButton() {
        log.info("Click Sign in button");
        btnSignIn.click();
        return new SignInPage(driver);
    }
}
