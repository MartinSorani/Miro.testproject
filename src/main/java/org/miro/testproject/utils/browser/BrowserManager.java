package org.miro.testproject.utils.browser;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.driver.DriverImpl;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.miro.testproject.utils.common.HelperUtil.isNullOrEmpty;
import static org.miro.testproject.utils.common.HelperUtil.retrievePropertyFromFile;

public class BrowserManager {
    private final String configFile = "test.config";
    private final Browser browser;
    private Driver driver;

    public BrowserManager() {
        this.browser = fetchBrowserSettings();
    }

    public Driver getDriver() {
        if (isNullOrEmpty(this.driver))
            return newDriver();
        return this.driver;
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public Driver newDriver() {
        this.driver = createDriver(this.browser);
        return this.driver;
    }

    private Browser fetchBrowserSettings() {
        boolean isHeadless = getHeadlessParam();
        String targetBrowser = System.getProperty("browser");
        if (isNullOrEmpty(targetBrowser))
            targetBrowser = retrievePropertyFromFile("browser", configFile);
        switch (targetBrowser) {
            case "Chrome":
                return new Browser(BrowserName.Chrome, isHeadless);
            case "Firefox":
                return new Browser(BrowserName.Firefox, isHeadless);
            case "Edge":
                return new Browser(BrowserName.Edge, isHeadless);
            default:
                throw new IllegalStateException("Browser not selected! Add target browser in test.config.properties");
        }
    }

    private boolean getHeadlessParam() {
        String headless = System.getProperty("headless");
        if (isNullOrEmpty(headless))
            return Boolean.parseBoolean(retrievePropertyFromFile("headless", configFile));
        return Boolean.parseBoolean(headless);
    }

    private Driver createDriver(Browser browser) {
        switch (browser.browserName) {
            case Chrome:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (browser.isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                return new DriverImpl(new ChromeDriver(chromeOptions), browser);
            case Firefox:
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(browser.isHeadless);
                return new DriverImpl(new FirefoxDriver(firefoxOptions), browser);
            case Edge:
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setHeadless(browser.isHeadless);
                return new DriverImpl(new EdgeDriver(edgeOptions), browser);
            default:
                throw new IllegalStateException("Error creating driver. Verify settings in test.config.properties are correct");
        }
    }
}
