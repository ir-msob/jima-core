package ir.msob.jima.core.test.testcontainer;

import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.GenericContainer;

/**
 * This abstract class, BaseContainerConfiguration, serves as a foundation for defining and managing TestContainers within JUnit 5 test suites.
 * It implements JUnit extensions for setup and teardown of containers.
 */
public abstract class BaseContainerConfiguration implements AfterAllCallback, BeforeAllCallback {

    /**
     * Subclasses must provide an implementation of this method to return the specific TestContainer instance to be managed.
     *
     * @return The TestContainer to be managed by this configuration.
     */
    protected abstract <SELF extends GenericContainer<SELF>> GenericContainer<SELF> getContainer();

    /**
     * Callback method to start the TestContainer before all tests within the test suite.
     *
     * @param extensionContext The JUnit ExtensionContext providing access to the test context.
     */
    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!getContainer().isRunning()) {
            getContainer().start();
        }
    }

    /**
     * Callback method to stop the TestContainer after all tests within the test suite.
     *
     * @param extensionContext The JUnit ExtensionContext providing access to the test context.
     */
    @Override
    public void afterAll(ExtensionContext extensionContext) {
        if (getContainer().isRunning()) {
            getContainer().stop();
        }
    }
}
