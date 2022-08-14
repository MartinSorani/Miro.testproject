package org.miro.testproject.utils.common;

import org.miro.testproject.utils.properties.PropertiesReader;

public class HelperUtil {

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

    public static String retrievePropertyFromFile(String property, String file) {
        return new PropertiesReader(file).getProperty(property);
    }
}
