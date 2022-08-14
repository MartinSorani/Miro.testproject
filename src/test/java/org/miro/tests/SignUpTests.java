package org.miro.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.miro.testproject.pages.*;
import org.miro.testproject.tests.base.BaseTest;
import org.miro.testproject.utils.common.HelperUtil;
import org.miro.testproject.utils.locales.Language;
import org.miro.testproject.utils.users.User;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpTests extends BaseTest {

    @Test
    @DisplayName("Should land in the code verification page")
    void shouldReachCodeVerification() {

        CodeVerificationPage codeVerificationPage =
                test()
                        .clickSignUpButton()
                        .enterUsername(user.getUsername())
                        .enterEmail(user.getEmail())
                        .enterPassword(user.getPassword())
                        .checkTermsBox()
                        .clickSignupButton();

        assertTrue(codeVerificationPage.txtCode.isVisible());
        assertTrue(codeVerificationPage.lblConfirmation.getText().contains(user.getEmail()));
    }

    @ParameterizedTest
    @ValueSource(strings = {"asdfjh", "2345342", "hotmail", "_-_-"})
    @DisplayName("Should trigger an email validation error")
    void shouldTriggerEmailWarning(String domain) {
        user = new User()
                .setRandomUsername(10)
                .setRandomEmail(domain)
                .setRandomPassword();

        SignUpPage signUpPage =
                test()
                        .clickSignUpButton()
                        .enterUsername(user.getUsername())
                        .enterEmail(user.getEmail());

        assertTrue(signUpPage.lblEmailWarning.isVisible());
        assertEquals("Enter a valid email address.", signUpPage.lblEmailWarning.getText());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "4", "abc", "sjh456r"})
    @DisplayName("Should trigger a password validation error")
    void shouldTriggerPasswordWarning(String password) {
        user = new User()
                .setRandomUsername(10)
                .setRandomEmail("testemail.com")
                .setPassword(password);

        SignUpPage signUpPage =
                test()
                        .clickSignUpButton()
                        .enterUsername(user.getUsername())
                        .enterEmail(user.getEmail())
                        .enterPassword(user.getPassword());

        assertTrue(signUpPage.lblPasswordWarning.isVisible());
        assertEquals("Please use 8+ characters for secure password.", signUpPage.lblPasswordWarning.getText());
    }

    @Test()
    @DisplayName("Should trigger a terms checkbox warning")
    void shouldTriggerTermsWarning() {
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton()
                        .enterUsername(user.getUsername())
                        .enterEmail(user.getEmail())
                        .enterPassword(user.getPassword())
                        .checkNewsBox()
                        .clickSignupButtonExpectError();

        assertTrue(signUpPage.lblTermsWarning.isVisible());
        assertEquals("Please agree with the Terms to sign up.", signUpPage.lblTermsWarning.getText());
    }

    @Test
    @DisplayName("Should bring up the terms of service page")
    void shouldRedirectToTermsPage() {
        TermsPage termsPage =
                test()
                        .clickSignUpButton()
                        .clickTermsLink();

        assertEquals("Terms of Service", termsPage.lblTitle.getText());
    }

    @Test
    @DisplayName("Should bring up the privacy policy page")
    void shouldRedirectToPrivacyPage() {
        PrivacyPage privacyPage =
                test()
                        .clickSignUpButton()
                        .clickPrivacyLink();

        assertEquals("Privacy Policy", privacyPage.lblTitle.getText());
    }

    @Test
    @DisplayName("Should sign up with Google account")
    void shouldLandInGoogleAccount() {
        GooglePage googlePage =
                test()
                        .clickSignUpButton()
                        .clickGoogleSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToGoogle();

        assertTrue(googlePage.lblBoxHeader.isVisible());
    }

    @Test
    @DisplayName("Should trigger terms review warning")
    void shouldTriggerTermsReviewWarning() {
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton()
                        .clickGoogleSignup()
                        .checkReviewSubscribe()
                        .clickContinueToSignupAndExpectError();

        assertTrue(signUpPage.lblTermsReviewWarning.isVisible());
        assertEquals("Please agree with the Terms of Service and Privacy Policy", signUpPage.lblTermsReviewWarning.getText());
    }

    @Test
    @DisplayName("Should sign up with Slack account")
    void shouldLandInSlackAccount() {
        SlackPage slackPage =
                test()
                        .clickSignUpButton()
                        .clickSlackSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToSlack();

        assertTrue(slackPage.lblHeader.isVisible());
        assertEquals("Sign in to your workspace", slackPage.lblHeader.getText());
    }

    @Test
    @DisplayName("Should display the title in several languages")
    void shouldChangeLocale() {

        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        for (Language lang : Language.values()) {
            signUpPage.selectLanguage(lang);

            log.info("Asserting header text matches expected language: " + lang.label);
            assertEquals(new String(getExpectedTitle(lang).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                    signUpPage.lblHeaderTitle.getText());
        }
    }

    /**
     * Reader for expected title in shouldChangeLocale
     *
     * @return Expected header title in specified language
     */
    static String getExpectedTitle(Language language) {
        return HelperUtil.retrievePropertyFromFile(language.code, "ExpectedTitle");
    }
}
