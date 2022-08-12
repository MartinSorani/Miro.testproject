package org.miro.suites;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.miro.testproject.pages.*;
import org.miro.testproject.suites.base.BaseTest;
import org.miro.testproject.utils.users.User;

import java.util.stream.Stream;

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
    @MethodSource("invalidPasswords")
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

    /**
     * Provider for shouldTriggerPasswordWarning
     * @return Stream of invalid passwords
     */
    static Stream<String> invalidPasswords() {
        return Stream.of(
                " ",
                "4",
                "abc",
                "sjh456r"
        );
    }
}
