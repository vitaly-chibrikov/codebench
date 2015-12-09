package com.goeuro.testsolution.saver;

import com.goeuro.testsolution.base.CityData;
import com.goeuro.testsolution.base.CityDataSaver;
import com.goeuro.testsolution.base.CityLinesWriter;
import com.goeuro.testsolution.base.GeoPosition;
import com.goeuro.testsolution.exceptions.TestException;
import com.goeuro.testsolution.tools.CSVHelper;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * @author v.chibrikov
 */
public class CsvFileCityDataSaverTest {
    @Test
    public void getParametersTest() {
        final StringBuilder builder = new StringBuilder();
        CityLinesWriter cityLinesWriter = new CityLinesWriter() {

            @Override
            public void write(List<String[]> cityLines) {
                final StringBuilder lineBuilder = CSVHelper.build(cityLines);
                builder.append(lineBuilder.toString());
            }
        };

        CityData[] cityData = {new CityData("1", "2", "3", "4", "5", "6", "7", new GeoPosition("14", "15"), "9", "10", "11", "12", "13")};

        final CityDataSaver saver = new CsvFileCityDataSaver(cityData, new String[]{"_id", "name", "type", "geo_position.latitude", "geo_position.longitude"}, cityLinesWriter);
        saver.run();

        assertEquals("1, 3, 6, 14, 15" + System.lineSeparator(), builder.toString());
    }

    @Test
    public void wrongParameterNameTest() {
        CityLinesWriter cityLinesWriter = new CityLinesWriter() {
            @Override
            public void write(List<String[]> cityLines) {
                //do nothing
            }
        };

        CityData[] cityData = {new CityData("1", "2", "3", "4", "5", "6", "7", new GeoPosition("14", "15"), "9", "10", "11", "12", "13")};

        final CityDataSaver saver = new CsvFileCityDataSaver(cityData, new String[]{"id"}, cityLinesWriter);
        try {
            saver.run();
        } catch (TestException e) {
            assertTrue(e.getMessage().contains("Can't get field: 'id'"));
            return;
        }

        //unreachable
        throw new RuntimeException("Unreachable");
    }
}
