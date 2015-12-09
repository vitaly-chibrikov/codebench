package com.goeuro.testsolution.tools;

/**
 * Simple logger for test. Can be changed to log4j logger.
 *
 * @author v.chibrikov
 */
public class SimpleLogger {
    private final String loggerName;

    public SimpleLogger(String loggerName) {
        this.loggerName = loggerName;
    }

    public SimpleLogger(Class<?> clazz) {
        this.loggerName = clazz.getSimpleName();
    }

    public void info(String message) {
        System.out.println("INFO " + loggerName + ": " + message);
    }

    public void warn(String message) {
        System.out.println("WARN " + loggerName + ": " + message);
    }

    public void error(String message) {
        System.out.println("ERROR " + loggerName + ": " + message);
    }
}
