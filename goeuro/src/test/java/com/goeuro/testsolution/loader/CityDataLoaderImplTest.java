package com.goeuro.testsolution.loader;

import com.goeuro.testsolution.base.CityData;
import com.goeuro.testsolution.base.JsonStringLoader;
import com.goeuro.testsolution.exceptions.TestException;
import com.google.gson.JsonSyntaxException;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * @author v.chibrikov
 */
public class CityDataLoaderImplTest {
    @Test
    public void successLoadTest() {
        JsonStringLoader jsonStringLoader = new JsonStringLoader() {
            @Override
            public String load() {
                return "[\n" +
                        "\n" +
                        " {\n" +
                        "\n" +
                        " _id: 377078,\n" +
                        " key: null,\n" +
                        " name: \"Potsdam\",\n" +
                        " fullName: \"Potsdam, Germany\",\n" +
                        " iata_airport_code: null,\n" +
                        " type: \"location\",\n" +
                        " country: \"Germany\",\n" +
                        "\n" +
                        " geo_position: {\n" +
                        " latitude: 52.39886,\n" +
                        " longitude: 13.06566\n" +
                        " },\n" +
                        " location_id: 377078,\n" +
                        " inEurope: true,\n" +
                        " countryCode: \"DE\",\n" +
                        " coreCountry: true,\n" +
                        " distance: null\n" +
                        " },\n" +
                        "\n" +
                        " {\n" +
                        " _id: 410978,\n" +
                        " key: null,\n" +
                        " name: \"Potsdam\",\n" +
                        " fullName: \"Potsdam, USA\",\n" +
                        " iata_airport_code: null,\n" +
                        " type: \"location\",\n" +
                        " country: \"USA\",\n" +
                        "\n" +
                        " geo_position: {\n" +
                        " latitude: 44.66978,\n" +
                        " longitude: -74.98131\n" +
                        " },\n" +
                        "\n" +
                        " location_id: 410978,\n" +
                        " inEurope: false,\n" +
                        " countryCode: \"US\",\n" +
                        " coreCountry: false,\n" +
                        " distance: null\n" +
                        " }\n" +
                        " ]";
            }
        };

        CityDataLoaderImpl cityDataLoader = new CityDataLoaderImpl(jsonStringLoader);
        CityData[] cityDatum = cityDataLoader.call();

        assertEquals(2, cityDatum.length);
        assertEquals("CityData{_id='377078', key='null', name='Potsdam', fullName='Potsdam, Germany', iata_airport_code='null', type='location', country='Germany', geo_position=GeoPosition{latitude='52.39886', longitude='13.06566'}, location_id='377078', inEurope='true', countryCode='DE', coreCountry='true', distance='null'}", cityDatum[0].toString());
    }

    @Test
    public void wrongJsonTest() {
        JsonStringLoader jsonStringLoader = new JsonStringLoader() {
            @Override
            public String load() {
                return "[...]";
            }
        };

        CityDataLoaderImpl cityDataLoader = new CityDataLoaderImpl(jsonStringLoader);
        try {
            cityDataLoader.call();
        } catch (TestException e) {
            assertEquals(JsonSyntaxException.class, e.getCause().getClass());
            return;
        }
        //unreachable
        throw new RuntimeException("Unreachable");
    }

    @Test
    public void emptyJsonTest() {
        JsonStringLoader jsonStringLoader = new JsonStringLoader() {
            @Override
            public String load() {
                return "[]";
            }
        };

        CityDataLoaderImpl cityDataLoader = new CityDataLoaderImpl(jsonStringLoader);
        CityData[] cityDatum = cityDataLoader.call();

        assertEquals(0, cityDatum.length);
    }

}
