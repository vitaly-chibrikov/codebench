package com.goeuro.testsolution.tools;

import java.util.List;

/**
 * @author v.chibrikov
 */
public class CSVHelper {
    public static StringBuilder build(List<String[]> cityLines) {
        StringBuilder lineBuilder = new StringBuilder();
        for (String[] city : cityLines) {
            String prefix = "";
            for (String field : city) {
                lineBuilder.append(prefix).append(field);
                prefix = ", ";
            }
            lineBuilder.append(System.lineSeparator());
        }
        return lineBuilder;
    }
}
