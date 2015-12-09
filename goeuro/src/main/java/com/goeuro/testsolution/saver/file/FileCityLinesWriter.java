package com.goeuro.testsolution.saver.file;

import com.goeuro.testsolution.base.CityLinesWriter;
import com.goeuro.testsolution.exceptions.TestException;
import com.goeuro.testsolution.tools.CSVHelper;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

/**
 * @author v.chibrikov
 */
public class FileCityLinesWriter implements CityLinesWriter {
    private final String fileName;

    public FileCityLinesWriter(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void write(List<String[]> cityLines) {
        final StringBuilder lineBuilder = CSVHelper.build(cityLines);
        try {
            java.nio.file.Files.write(Paths.get(fileName),
                    lineBuilder.toString().getBytes("utf-8"),
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new TestException(e);
        }
    }


}
