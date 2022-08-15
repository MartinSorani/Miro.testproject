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
    private final boolean isHeadless;
    String configFile = "test.config";
    private Driver driver;

    public BrowserManager() {
        this.isHeadless = getHeadlessParam();
        System.setProperty("headless", String.valueOf(isHeadless));
        createDriver(getBrowserParam());
    }

    public Driver getDriver() {
        return this.driver;
    }

    private void setDriver(Driver driver) {
        this.driver = driver;
    }

    private String getBrowserParam() {
        String targetBrowser = System.getProperty("browser");
        if (isNullOrEmpty(targetBrowser))
            return retrievePropertyFromFile("browser", configFile);
        return targetBrowser;
    }

    private boolean getHeadlessParam() {
        String headless = System.getProperty("headless");
        if (isNullOrEmpty(headless))
            return Boolean.parseBoolean(retrievePropertyFromFile("headless", configFile));
        return Boolean.parseBoolean(headless);
    }

    private void createDriver(String browser) {
        switch (browser) {
            case "Chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) {
                    chromeOptions.addArguments("--headless");
                    chromeOptions.addArguments("--disable-gpu");
                }
                setDriver(new DriverImpl(new ChromeDriver(chromeOptions)));
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(isHeadless);
                setDriver(new DriverImpl(new FirefoxDriver(firefoxOptions)));
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setHeadless(isHeadless);
                setDriver(new DriverImpl(new EdgeDriver(edgeOptions)));
                break;
            default:
                throw new IllegalStateException("Browser not selected! Add target browser in test.config.properties");
        }
    }
}
