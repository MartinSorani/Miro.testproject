package org.miro.testproject.tests.listener;

import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

import static org.miro.testproject.tests.base.BaseTest.takeScreenshot;

public class CustomListener implements BeforeTestExecutionCallback, AfterTestExecutionCallback {

    protected static final Logger log = LoggerFactory.getLogger("[TEST]");

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        Method testMethod = extensionContext.getRequiredTestMethod();
        boolean testFailed = extensionContext.getExecutionException().isPresent();
        if (testFailed) {
            log.info("Test case failure. Taking screenshot");
            takeScreenshot(testMethod.getName());
        }
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
    }
}
