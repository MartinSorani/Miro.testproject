package org.miro.testproject.utils.common;

import org.miro.testproject.utils.properties.PropertiesReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelperUtil {

    /**
     * Helper method to find out if an object is null or empty
     *
     * @param object target object
     * @return true if the object is null, empty or a NullPointerException is thrown
     */
    public static boolean isNullOrEmpty(Object object) {
        try {
            return object == null || object.toString().isEmpty();
        } catch (NullPointerException e) {
            return true;
        }
    }

    public static String retrievePropertyFromFile(String property, String file) {
        return new PropertiesReader(file).getProperty(property);
    }

    public static void takeScreenshot(WebDriver webDriver, String fileName) {
        TakesScreenshot ts = (TakesScreenshot) webDriver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        try {
            FileHandler.copy(source,
                    new File(
                            "./screenshots/" +
                                    fileName +
                                    LocalDateTime.now().format(
                                            DateTimeFormatter.ofPattern("MM-dd-yyyy_HH.mm.ss")) +
                                    ".png"));
        } catch (IOException i) {
            System.out.println("Failed to take screenshot");
        }
    }
}
