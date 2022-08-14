package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class SignInPage extends BasePage {

    public Element lblSignInHeader = getElement("lblSignInHeader");
    public Element btnSignIn = getElement("btnSignIn");

    public SignInPage(Driver driver) {
        super(driver);
    }
}
