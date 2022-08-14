package org.miro.testproject.pages.utils.properties;

import org.miro.testproject.proxy.locators.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger logger = LoggerFactory.getLogger("[Debug]");
    String resource;
    InputStream inputStream;
    Properties properties;

    public PropertiesReader(String resource) {
        this.resource = resource + ".properties";
        properties = new Properties();
    }

    public Locator getLocator(String key) {
        inputStream = createInputStream("/maps/", resource);
        try {
            properties.load(inputStream);
            String[] property = properties.getProperty(key).split(";");
            if (property.length <= 1) {
                return new Locator()
                        .setSelector(property[0])
                        .setBy("css");
            } else {
                return new Locator()
                        .setSelector(property[0])
                        .setBy(property[1]);
            }
        } catch (IOException e) {
            logger.debug("Properties file not found!");
            return new Locator()
                    .setSelector("")
                    .setBy("");
        }
    }

    public String getProperty(String key) {
        inputStream = createInputStream("/", resource);
        try {
            properties.load(inputStream);
            return properties.getProperty(key);
        }
        catch (IOException e) {
            logger.error("Unable to retrieve property " + key);
            return "";
        }
    }

    InputStream createInputStream(String resourcePath, String resource){
        return this.getClass().getResourceAsStream(resourcePath + resource);
    }
}
