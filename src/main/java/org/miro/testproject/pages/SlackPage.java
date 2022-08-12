package org.miro.testproject.pages;

import org.miro.testproject.pages.base.BasePage;
import org.miro.testproject.utils.proxy.Element;
import org.openqa.selenium.WebDriver;

public class SlackPage extends BasePage {

    //Elements
    public Element lblHeader = getElement("lblHeader");

    public SlackPage(WebDriver driver) {
        super(driver);
    }
}
