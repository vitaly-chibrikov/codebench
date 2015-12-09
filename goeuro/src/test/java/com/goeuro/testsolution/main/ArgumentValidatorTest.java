package com.goeuro.testsolution.main;

import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;

/**
 * I am not really strong in regex. So, let's test it.
 *
 * @author v.chibrikov
 */
public class ArgumentValidatorTest {
    private static final String CITY_NAME_VALIDATOR = "^[a-zA-Z_ ]+$";

    @SuppressWarnings("ZeroLengthArrayAllocation")
    @Test
    public void badNameTest() {
        assertNull(ArgumentValidator.getCityName(new String[0], CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[2], CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[]{""}, CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[]{" "}, CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[]{"1"}, CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[]{"-"}, CITY_NAME_VALIDATOR));
        assertNull(ArgumentValidator.getCityName(new String[]{",."}, CITY_NAME_VALIDATOR));
    }

    @Test
    public void goodNameTest() {
        assertNotNull(ArgumentValidator.getCityName(new String[]{"Frankfurt am Main"}, CITY_NAME_VALIDATOR));
        assertNotNull(ArgumentValidator.getCityName(new String[]{"Moscow"}, CITY_NAME_VALIDATOR));
        assertNotNull(ArgumentValidator.getCityName(new String[]{"Berlin"}, CITY_NAME_VALIDATOR));
    }
}
