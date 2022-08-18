package org.miro.testproject.utils.common;

import io.qameta.allure.Attachment;
import org.miro.testproject.utils.properties.PropertiesReader;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HelperUtil {

    protected static final Logger log = LoggerFactory.getLogger("[PROCESS]");

    /**
     * Helper method to find out if an object is null or empty
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

    /**
     * Fetch the value of a property from the specified file
     * @param property Key for the target property
     * @param file Filename without the extension: .properties will be added to the file name
     * @return String value for target property
     */
    public static String retrievePropertyFromFile(String property, String file) {
        String result = new PropertiesReader(file).getProperty(property);
        if (isNullOrEmpty(result)) {
            log.debug("Could not fetch property " + property);
            throw new NullPointerException(property + " not found in " + file + "!");
        }
        return result;
    }

    /**
     * Take a screenshot of current driver state
     * @param webDriver Native webdriver in its current state
     * @param fileName Screenshot will be saved in {root}/screenshots/
     */
    @Attachment
    public static byte[] snapshot(WebDriver webDriver, String fileName) {
        TakesScreenshot ts = (TakesScreenshot) webDriver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        byte[] fileContent;
        try {
            log.debug("Taking screenshot");
            fileContent = Files.readAllBytes(source.toPath());
            FileHandler.copy(source,
                    new File(
                            "./screenshots/" +
                                    LocalDateTime.now().format(
                                            DateTimeFormatter.ofPattern("HH.mm.ss_MM-dd-yyyy")) +
                                    fileName + "_" +
                                    ".png"));
        } catch (IOException i) {
            log.debug("Error processing screenshot!");
            return null;
        }
        return fileContent;
    }

    /**
     * Forcefully kill all driver processes if user is running Windows
     * The implementation of this method is not necessary but a precaution when running on debug mode
     * @param name of the target process to be terminated
     */
    public static void killAllProcesses(String name) {
        if (!System.getProperty("os.name").startsWith("Windows")) {
            log.debug("Method not implemented for this OS");
            return;
        }
        try {
            log.debug("Force terminating driver process");
            Runtime.getRuntime().exec("taskkill /F /IM " + name + ".exe /T");
        } catch (IOException e) {
            log.debug("No process found by the name " + name);
        }
    }
}
