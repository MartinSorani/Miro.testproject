package org.miro.testproject.utils.browser;


public class Browser {
    public BrowserName browserName;
    public boolean isHeadless;

    private String processName;

    public Browser (BrowserName browserName, boolean isHeadless) {
        this.browserName = browserName;
        this.isHeadless = isHeadless;
        setProcessName();
    }

    private void setProcessName() {
        switch (browserName) {
            case Chrome:
                processName = "chromedriver.exe";
                break;
            case Firefox:
                processName = "geckodriver.exe";
                break;
            case Edge:
                processName = "edgedriver.exe";
        }
    }

    public String getProcessName() {
        return this.processName;
    }
}

