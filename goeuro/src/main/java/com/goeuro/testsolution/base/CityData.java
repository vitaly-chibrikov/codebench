package com.goeuro.testsolution.base;

import java.lang.reflect.Field;

/**
 * @author v.chibrikov
 */
public class CityData {
    private String _id;
    private String key;
    private String name;
    private String fullName;
    private String iata_airport_code;
    private String type;
    private String country;
    private GeoPosition geo_position;

    private String location_id;
    private String inEurope;
    private String countryCode;
    private String coreCountry;
    private String distance;

    public CityData() {
    }

    public CityData(String _id,
                    String key,
                    String name,
                    String fullName,
                    String iata_airport_code,
                    String type,
                    String country,
                    GeoPosition geo_position,
                    String location_id,
                    String inEurope,
                    String countryCode,
                    String coreCountry,
                    String distance) {
        this._id = _id;
        this.key = key;
        this.name = name;
        this.fullName = fullName;
        this.iata_airport_code = iata_airport_code;
        this.type = type;
        this.country = country;
        this.geo_position = geo_position;
        this.location_id = location_id;
        this.inEurope = inEurope;
        this.countryCode = countryCode;
        this.coreCountry = coreCountry;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "CityData{" +
                "_id='" + _id + '\'' +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", fullName='" + fullName + '\'' +
                ", iata_airport_code='" + iata_airport_code + '\'' +
                ", type='" + type + '\'' +
                ", country='" + country + '\'' +
                ", geo_position=" + geo_position +
                ", location_id='" + location_id + '\'' +
                ", inEurope='" + inEurope + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", coreCountry='" + coreCountry + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
