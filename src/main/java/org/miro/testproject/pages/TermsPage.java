package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class TermsPage extends BasePage {

    //Elements
    public Element lblTitle = getElement("lblTitle");

    public TermsPage(WebDriver driver) {
        super(driver);
    }

    public SignUpPage closeTabAndReturn() {
        driver.close();
        driver.switchTo().window(handleId);
        return new SignUpPage(driver);
    }
}
