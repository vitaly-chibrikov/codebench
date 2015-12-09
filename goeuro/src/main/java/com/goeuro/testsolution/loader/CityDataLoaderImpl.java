package com.goeuro.testsolution.loader;

import com.goeuro.testsolution.base.CityData;
import com.goeuro.testsolution.base.CityDataLoader;
import com.goeuro.testsolution.base.JsonStringLoader;
import com.goeuro.testsolution.exceptions.TestException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

/**
 * @author v.chibrikov
 */
public class CityDataLoaderImpl implements CityDataLoader {
    private final JsonStringLoader stringLoader;

    public CityDataLoaderImpl(JsonStringLoader jsonStringLoader) {
        this.stringLoader = jsonStringLoader;
    }

    @Override
    public CityData[] call() {
        final String answer = stringLoader.load();
        final Gson gson = new GsonBuilder().create();
        try {
            CityData[] cityData = gson.fromJson(answer, CityData[].class);
            return cityData;
        } catch (JsonSyntaxException e) {
            throw new TestException("Can't parse server answer. Wrong json:\n" + answer, e);
        }
    }

}
