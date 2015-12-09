package com.goeuro.testsolution.saver;

import com.goeuro.testsolution.base.CityData;
import com.goeuro.testsolution.base.CityDataSaver;
import com.goeuro.testsolution.base.CityLinesWriter;
import com.goeuro.testsolution.tools.ReflectionHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author v.chibrikov
 */
public class CsvFileCityDataSaver implements CityDataSaver {
    private final CityLinesWriter cityLinesWriter;
    private final CityData[] cities;
    private final String[] fields;

    public CsvFileCityDataSaver(CityData[] cities, String[] fields, CityLinesWriter cityLinesWriter) {
        this.cities = cities;
        this.fields = fields;
        this.cityLinesWriter = cityLinesWriter;
    }

    @Override
    public void run() {
        final List<String[]> cityLines = getCityLines(cities);
        cityLinesWriter.write(cityLines);
    }

    private List<String[]> getCityLines(CityData[] cities) {
        final List<String[]> cityLines = new ArrayList<>();
        for (CityData city : cities) {
            final List<String> values = new ArrayList<>();
            for (String field : fields) {
                final String value = (String) ReflectionHelper.getValue(city, field);
                values.add(value);
            }
            cityLines.add(values.toArray(new String[values.size()]));
        }
        return cityLines;
    }
}
