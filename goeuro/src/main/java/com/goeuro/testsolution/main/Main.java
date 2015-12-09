package com.goeuro.testsolution.main;

import com.goeuro.testsolution.base.*;
import com.goeuro.testsolution.exceptions.TestException;
import com.goeuro.testsolution.loader.CityDataLoaderImpl;
import com.goeuro.testsolution.loader.http.HttpJsonStringLoaderImpl;
import com.goeuro.testsolution.saver.CsvFileCityDataSaver;
import com.goeuro.testsolution.saver.file.FileCityLinesWriter;
import com.goeuro.testsolution.tools.SimpleLogger;

import java.util.Date;

/**
 * @author v.chibrikov
 */
public class Main {
    private final static String propertiesFile = "testsolution.properties";
    private final static SimpleLogger logger = new SimpleLogger(Main.class);

    public static void main(String[] args) {
        try {
            final Config cfg = new Config(propertiesFile);
            final String cityName = ArgumentValidator.getCityName(args, cfg.getCityNameRegex());
            if (cityName == null) {
                return;
            }
            final String fileName = cityName.replaceAll(" ", "_") + ".csv";
            logger.info("Data will be written to: " + fileName);
            final long startTime = (new Date().getTime());
            final JsonStringLoader jsonStringLoader = new HttpJsonStringLoaderImpl(cfg.getApiUrl(), cityName);
            final CityDataLoader cityDataLoader = new CityDataLoaderImpl(jsonStringLoader);
            final CityData[] cityData = cityDataLoader.call();
            final long serverAnswerTime = (new Date().getTime());
            logger.info("Server answer received after: " + (serverAnswerTime - startTime) + " ms.");

            final CityLinesWriter cityLinesWriter = new FileCityLinesWriter(fileName);
            final CityDataSaver saver = new CsvFileCityDataSaver(cityData, cfg.getCsvFields(), cityLinesWriter);
            saver.run();

        } catch (TestException e) {
            logger.error(e.getMessage());
        }
    }
}
