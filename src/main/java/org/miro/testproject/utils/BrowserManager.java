package org.miro.testproject.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class BrowserManager {
    private WebDriver driver;
    private final boolean isHeadless;

    public BrowserManager() {
        PropertiesReader reader = new PropertiesReader("test.config");
        this.isHeadless = Boolean.parseBoolean(reader.getProperty("headless").getSelector());
        createDriver(reader.getProperty("browser").getSelector());
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    private void setDriver(WebDriver driver) {
        this.driver = driver;
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
                setDriver(new ChromeDriver(chromeOptions));
                break;
            case "Firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.setHeadless(isHeadless);
                setDriver(new FirefoxDriver(firefoxOptions));
                break;
            case "Edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.setHeadless(isHeadless);
                setDriver(new EdgeDriver(edgeOptions));
                break;
            default:
                throw new IllegalStateException("Browser not selected! Add target browser in test.config.properties");
        }
    }
}
