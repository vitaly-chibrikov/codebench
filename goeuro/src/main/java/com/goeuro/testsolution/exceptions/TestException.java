package com.goeuro.testsolution.exceptions;

/**
 * @author v.chibrikov
 */
public class TestException extends RuntimeException {
    public TestException(String s) {
        super(s);
    }

    public TestException(Throwable throwable) {
        super(throwable);
    }

    public TestException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
