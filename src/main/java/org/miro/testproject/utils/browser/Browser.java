package org.miro.testproject.utils.browser;


public class Browser {
    public BrowserName browserName;
    public boolean isHeadless;

    public Browser (BrowserName browserName, boolean isHeadless) {
        this.browserName = browserName;
        this.isHeadless = isHeadless;
    }
}

