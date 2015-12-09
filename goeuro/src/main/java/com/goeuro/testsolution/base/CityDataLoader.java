package com.goeuro.testsolution.base;

/**
 * Loader of city data. Can extends Callable.
 *
 * @author v.chibrikov
 */
public interface CityDataLoader {
    CityData[] call();
}
