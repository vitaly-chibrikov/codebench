package com.goeuro.testsolution.loader.http;

import com.goeuro.testsolution.base.JsonStringLoader;
import com.goeuro.testsolution.exceptions.TestException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author v.chibrikov
 */
public class HttpJsonStringLoaderImpl implements JsonStringLoader {
    private final String apiUrl;

    public HttpJsonStringLoaderImpl(String apiUrl, String cityName) {
        try {
            this.apiUrl = apiUrl + URLEncoder.encode(cityName, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new TestException(e);
        }
    }

    @Override
    public String load() {
        try {
            HttpAnswer answer = HttpHelper.sendGet(apiUrl);
            if (answer.getCode() == 200) {
                return answer.getPage();
            } else {
                throw new TestException("Server answer: " + answer.getCode() + " for URL: " + apiUrl);
            }
        } catch (IOException e) {
            throw new TestException(e);
        }
    }
}
