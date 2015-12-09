package com.goeuro.testsolution.base;

/**
 * @author v.chibrikov
 */
public class GeoPosition {
    private String latitude;
    private String longitude;

    public GeoPosition() {
    }

    public GeoPosition(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "GeoPosition{" +
                "latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
