package org.miro.testproject.suites.signup;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.miro.testproject.pages.CodeVerificationPage;
import org.miro.testproject.pages.SignUpPage;
import org.miro.testproject.suites.base.BaseTest;
import org.miro.testproject.utils.users.User;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SignUpTests extends BaseTest {

    User user;

    @BeforeEach
    void init() {
        user = new User().generateRandomUser();
    }

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

    static Stream<String> invalidPasswords() {
        return Stream.of(
                " ",
                "4",
                "abc",
                "sjh456r"
        );
    }
}
