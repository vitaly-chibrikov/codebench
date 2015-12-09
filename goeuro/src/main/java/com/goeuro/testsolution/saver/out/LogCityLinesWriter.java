package com.goeuro.testsolution.saver.out;

import com.goeuro.testsolution.base.CityDataSaver;
import com.goeuro.testsolution.base.CityLinesWriter;
import com.goeuro.testsolution.tools.CSVHelper;
import com.goeuro.testsolution.tools.SimpleLogger;

import java.util.List;

/**
 * Prints city data to out. Good for debug.
 *
 * @author v.chibrikov
 */
public class LogCityLinesWriter implements CityLinesWriter {
    private final static SimpleLogger logger = new SimpleLogger(CityDataSaver.class);

    @Override
    public void write(List<String[]> cityLines) {
        logger.info("City lines found (" + cityLines.size() + "):");
        final StringBuilder lineBuilder = CSVHelper.build(cityLines);
        logger.info(lineBuilder.toString());
    }
}
