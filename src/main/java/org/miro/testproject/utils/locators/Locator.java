package org.miro.testproject.utils.locators;

import org.openqa.selenium.By;

public class Locator {
    private String selector;
    private By by;

    public String getSelector(){
        return this.selector;
    }

    public By getBy(){
        return this.by;
    }

    public Locator setSelector(String selector) {
        if(selector == null)
            selector = "";
        this.selector = selector;
        return this;
    }

    public Locator setBy(String by) {
        switch (by) {
            case "id":
                this.by = By.id(getSelector());
                break;
            case "css":
                this.by = By.cssSelector(getSelector());
                break;
            case "xpath":
                this.by = By.xpath(getSelector());
                break;
            default:
                this.by = By.name(getSelector());
                break;
        }
        return this;
    }
}
