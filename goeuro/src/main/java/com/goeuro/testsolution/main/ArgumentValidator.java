package com.goeuro.testsolution.main;

/**
 * @author v.chibrikov
 */
public class ArgumentValidator {
    private static final String REQUIRED = "City name as parameter required: CityName or \"City Name\"";
    private static final String MULTIWORDS = "Use quotes for multiwords name: \"City Name\"";
    private static final String DOES_NOT_MATCH = "Bad city name: %s does not match to: %s";


    public static String getCityName(String[] args, String regex) {
        if (args.length == 0) {
            System.out.println(REQUIRED);
            return null;
        }
        if (args.length > 1) {
            System.out.println(MULTIWORDS);
            return null;
        }
        final String cityName = args[0];

        final String trimmedName = cityName.trim();
        final boolean nameMatches = trimmedName.matches(regex);
        if (nameMatches) {
            return trimmedName;
        } else {
            System.out.println(String.format(DOES_NOT_MATCH, cityName, regex));
            return null;
        }
    }
}
