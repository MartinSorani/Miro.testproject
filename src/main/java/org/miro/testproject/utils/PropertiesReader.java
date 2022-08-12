package org.miro.testproject.utils;

import org.miro.testproject.utils.locators.Locator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final Logger logger = LoggerFactory.getLogger("[Debug]");
    private final Properties properties;
    private final InputStream inputStream;

    public PropertiesReader(String resource) {
        properties = new Properties();
        inputStream = this.getClass().getResourceAsStream("/" + resource + ".properties");
    }

    public Locator getProperty(String key) {
        try {
            properties.load(inputStream);
            String[] property = properties.getProperty(key).split(";");
            if (property.length <= 1) {
                return new Locator()
                        .setSelector(property[0])
                        .setBy("");
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
}
