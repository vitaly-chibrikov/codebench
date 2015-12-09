package com.goeuro.testsolution.base;

import com.goeuro.testsolution.exceptions.TestException;
import com.goeuro.testsolution.tools.SimpleLogger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author v.chibrikov
 */
public class Config {
    private final static SimpleLogger logger = new SimpleLogger(Config.class);
    private final static String API_URL = "http://api.goeuro.com/api/v2/position/suggest/en/";
    private final static String CITY_NAME_REGEX = "^[a-zA-Z_ ]*$";
    private final static String CSV_FIELDS = "_id, name, type, geo_position.latitude, geo_position.longitude";

    private final String apiUrl;
    private final String cityNameRegex;
    private final String csvFields;

    public Config(String cfgName) {
        Properties properties = getProperties(cfgName);
        if (properties != null) {
            this.apiUrl = getNotNull(properties, "API_URL", cfgName);
            this.cityNameRegex = getNotNull(properties, "CITY_NAME_VALIDATOR", cfgName);
            this.csvFields = getNotNull(properties, "CSV_FIELDS", cfgName);
        } else {
            this.apiUrl = API_URL;
            this.cityNameRegex = CITY_NAME_REGEX;
            this.csvFields = CSV_FIELDS;
            logger.warn("Default values used: " + this.toString());
        }
    }

    private String getNotNull(Properties properties, String name, String cfgName) {
        String value = properties.getProperty(name);
        if (value == null)
            throw new TestException("Can't get " + name + " from config " + cfgName);
        return value;
    }

    public String[] getCsvFields() {
        return csvFields.trim().replace(" ", "").split(",");
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public String getCityNameRegex() {
        return cityNameRegex;
    }

    private static Properties getProperties(String cfgName) {
        Properties properties = new Properties();
        try (InputStream input = new FileInputStream(cfgName)) {
            properties.load(input);
            return properties;
        } catch (IOException e) {
            logger.error("Can't find config file." + e.toString());
            return null;
        }
    }

    @Override
    public String toString() {
        return "Config{" +
                "apiUrl='" + apiUrl + '\'' +
                ", cityNameRegex='" + cityNameRegex + '\'' +
                '}';
    }
}
