package org.miro.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledIf;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
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

    /**
     * Reader for expected title in shouldChangeLocale
     *
     * @return Expected header title in specified language
     */
    static String getExpectedTitle(Language language) {
        return HelperUtil.retrievePropertyFromFile(language.code, "ExpectedTitle");
    }

    @Test
    @DisplayName("Should land in the code verification page")
    void shouldReachCodeVerification() {

        CodeVerificationPage codeVerificationPage;
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            codeVerificationPage =
                    signUpPage
                            .enterEmail(user.getEmail())
                            .clickContinue(0)
                            .enterUsername(user.getUsername())
                            .clickContinue(1)
                            .enterPassword(user.getPassword())
                            .checkTermsBox()
                            .clickContinue(2)
                            .loadPage(CodeVerificationPage.class);
        } else {
            codeVerificationPage =
                    signUpPage
                            .enterUsername(user.getUsername())
                            .enterEmail(user.getEmail())
                            .enterPassword(user.getPassword())
                            .checkTermsBox()
                            .clickSignupButton();
        }

        log.info("Asserting text code label is visible and user email is in the message");
        assertTrue(codeVerificationPage.txtCode.isVisible());
        assertTrue(codeVerificationPage.lblConfirmation.getText().contains(user.getEmail()));
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "435", "abc", "#&%$("})
    @DisplayName("Should not trigger name validation warning")
    void shouldValidateName(String username) {
        user = new User()
                .setUsername(username)
                .setRandomEmail("testemail.com")
                .setRandomPassword();

        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            signUpPage
                    .enterEmail(user.getEmail())
                    .clickContinue(0)
                    .enterUsername(user.getUsername())
                    .clickContinue(1);
        } else {
            signUpPage
                    .enterUsername(user.getUsername())
                    .enterEmail(user.getEmail())
                    .enterPassword(user.getPassword());
        }

        log.info("Asserting name warning is not displayed");
        assertEquals("", signUpPage.lblNameWarning.getText());
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
                        .enterEmail(user.getEmail());

        log.info("Asserting email warning message is displayed");
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
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            signUpPage
                    .enterEmail(user.getEmail())
                    .clickContinue(0)
                    .enterUsername(user.getUsername())
                    .clickContinue(1)
                    .enterPassword(user.getPassword());
        } else {
            signUpPage
                    .enterUsername(user.getUsername())
                    .enterEmail(user.getEmail())
                    .enterPassword(user.getPassword());
        }

        log.info("Asserting password warning message is displayed");
        assertTrue(signUpPage.lblPasswordWarning.isVisible());
        assertEquals("Please use 8+ characters for secure password.", signUpPage.lblPasswordWarning.getText());
    }

    @Test
    @DisplayName("Should trigger a terms checkbox warning")
    void shouldTriggerTermsWarning() {

        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            signUpPage
                    .enterEmail(user.getEmail())
                    .clickContinue(0)
                    .enterUsername(user.getUsername())
                    .clickContinue(1)
                    .enterPassword(user.getPassword())
                    .checkSubscribeBox()
                    .clickContinue(2);
        } else {
            signUpPage
                    .enterUsername(user.getUsername())
                    .enterEmail(user.getEmail())
                    .enterPassword(user.getPassword())
                    .checkSubscribeBox()
                    .clickSignupButtonExpectError();
        }

        log.info("Asserting terms warning is displayed");
        assertTrue(signUpPage.lblTermsWarning.isVisible());
        assertEquals("Please agree with the Terms to sign up.", signUpPage.lblTermsWarning.getText());
    }

    @Test
    @DisplayName("Should bring up the terms of service page")
    void shouldRedirectToTermsPage() {

        TermsPage termsPage;
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            termsPage =
                    signUpPage
                            .enterEmail(user.getEmail())
                            .clickContinue(0)
                            .enterUsername(user.getUsername())
                            .clickContinue(1)
                            .clickTermsLink();
        } else {
            termsPage =
                    signUpPage
                            .clickTermsLink();
        }

        log.info("Asserting user is in Terms or service page");
        assertEquals("Terms of Service", termsPage.lblTitle.getText());
    }

    @Test
    @DisplayName("Should bring up the privacy policy page")
    void shouldRedirectToPrivacyPage() {

        PrivacyPage privacyPage;
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton();
        if (signUpPage.isSimplifiedOverlay()) {
            privacyPage =
                    signUpPage
                            .enterEmail(user.getEmail())
                            .clickContinue(0)
                            .enterUsername(user.getUsername())
                            .clickContinue(1)
                            .clickPrivacyLink();
        } else {
            privacyPage =
                    signUpPage
                            .clickPrivacyLink();
        }

        log.info("Asserting user is in Privacy Policy page");
        assertEquals("Privacy Policy", privacyPage.lblTitle.getText());
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

        log.info("Asserting terms review warning is displayed");
        assertTrue(signUpPage.lblTermsReviewWarning.isVisible());
        assertEquals("Please agree with the Terms of Service and Privacy Policy", signUpPage.lblTermsReviewWarning.getText());
    }

    @Test
    @DisabledIf(value = "isHeadlessChrome", disabledReason = "Google account redirects to service login on headless Chrome")
    @DisplayName("Should land in Google account page")
    void shouldLandInGoogleAccount() {

        GooglePage googlePage =
                test()
                        .clickSignUpButton()
                        .clickGoogleSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToGoogle();

        log.info("Asserting Google header is present");
        assertTrue(googlePage.lblBoxHeader.isVisible());
    }

    @Test
    @DisplayName("Should land in Slack account page")
    void shouldLandInSlackAccount() {

        SlackPage slackPage =
                test()
                        .clickSignUpButton()
                        .clickSlackSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToSlack();

        log.info("Asserting Slack header is present");
        assertTrue(slackPage.lblHeader.isVisible());
        assertEquals("Sign in to your workspace", slackPage.lblHeader.getText());
    }

    @Test
    @DisplayName("Should land in Microsoft account page")
    void shouldLandInMicrosoftAccount() {

        MicrosoftPage microsoftPage =
                test()
                        .clickSignUpButton()
                        .clickMicrosoftSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToMicrosoft();

        log.info("Asserting Microsoft logo is present");
        assertTrue(microsoftPage.imgMicrosoftLogo.isVisible());
        assertEquals("Microsoft", microsoftPage.imgMicrosoftLogo.getAttribute("alt"));
    }

    @Test
    @DisabledIf(value = "isHeadlessChromeOrEdge", disabledReason = "Apple ID page returns 404 on a headless Chrome or Edge")
    @DisplayName("Should land in Apple ID page")
    void shouldLandInAppleAccount() {

        ApplePage applePage =
                test()
                        .clickSignUpButton()
                        .clickAppleSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToApple();

        log.info("Asserting Apple ID header is present");
        assertTrue(applePage.lblAppleHeader.isVisible());
        assertEquals("Apple ID", applePage.lblAppleHeader.getText());
    }

    @Test
    @DisplayName("Should land in Facebook page")
    void shouldLandInFacebookAccount() {

        FacebookPage facebookPage =
                test()
                        .clickSignUpButton()
                        .clickFacebookSignup()
                        .checkReviewTerms()
                        .checkReviewSubscribe()
                        .clickContinueToFacebook()
                        .allowEssentialCookies();

        log.info("Asserting Facebook header is present");
        assertTrue(facebookPage.lblFacebookHeader.isVisible());
        assertTrue(facebookPage.lblFacebookHeader.getText().contains("Facebook"));
    }

    @Test
    @DisplayName("Should redirect to sign in page")
    void shouldLandInSignInPage() {

        SignInPage signInPage =
                test()
                        .clickSignUpButton()
                        .clickSignInButton();

        log.info("Asserting user lands in sign in page");
        assertTrue(signInPage.btnSignIn.isVisible());
        assertEquals("Sign in", signInPage.lblSignInHeader.getText());
    }

    @ParameterizedTest
    @EnumSource(Language.class)
    @DisplayName("Should display header text in several languages")
    void shouldChangeLocale(Language language) {

        log.info("Should display header text in " + language);
        SignUpPage signUpPage =
                test()
                        .clickSignUpButton()
                        .selectLanguage(language);

        log.info("Asserting header text matches expected language: " + language);
        assertEquals(new String(getExpectedTitle(language).getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8),
                signUpPage.lblHeaderTitle.getText());
    }
}
