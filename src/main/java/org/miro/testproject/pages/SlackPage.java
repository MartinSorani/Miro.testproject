package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;

public class SlackPage extends BasePage {

    //Elements
    public Element lblHeader = getElement("lblHeader");

    public SlackPage(Driver driver) {
        super(driver);
    }
}
