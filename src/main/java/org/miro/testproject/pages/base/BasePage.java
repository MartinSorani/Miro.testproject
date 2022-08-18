package org.miro.testproject.pages.base;

import io.qameta.allure.Step;
import org.miro.testproject.proxy.driver.Driver;
import org.miro.testproject.proxy.element.Element;
import org.miro.testproject.proxy.locators.Locator;
import org.miro.testproject.utils.properties.PropertiesReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BasePage {
    protected static final Logger log = LoggerFactory.getLogger("[STEP]");
    private final PropertiesReader reader;
    protected final Driver driver;
    protected static String handleId;


    protected BasePage(Driver driver) {
        this.driver = driver;
        reader = new PropertiesReader(this.getClass().getSimpleName());
        assertUrlIsCorrect();
    }

    protected Element getElement(String elementName) {
        Locator locator = reader.getLocator(elementName);
        return driver.createElement(locator);
    }
    @Step
    private void assertUrlIsCorrect() {
        String expectedUrl = reader.getLocator("url").getSelector();
        driver.waitUrlContains(expectedUrl, 3L);
        assertTrue(driver.getCurrentUrl().contains(reader.getLocator("url").getSelector()));
    }

    protected void switchTabs() {
        log.info("Switching tabs");
        ArrayList<String> currentTabs = new ArrayList<>(driver.getWindowHandles());
        if (currentTabs.size() > 1) {
            handleId = currentTabs.get(0);
            driver.switchTo().window(currentTabs.get(1));
        } else {
            log.warn("Warning: only one tab is active.");
            handleId = currentTabs.get(0);
        }
    }

    public <T extends BasePage> T loadPage(Class<T> tPage) {
        try{
            return tPage.getDeclaredConstructor( Driver.class ).newInstance( driver );
        }catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e){
            log.error("Could not load page!\n");
            e.printStackTrace();
            return null;
        }
    }
}
