package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class FacebookPage extends BasePage {

    Element btnAllowCookies = getElement("btnAllowCookies");
    public Element lblFacebookHeader = getElement("lblFacebookHeader");

    public FacebookPage(Driver driver) {
        super(driver);
    }

    public FacebookPage allowEssentialCookies() {
        log.info("Click allow only essential cookies button");
        btnAllowCookies.click();
        return this;
    }
}
