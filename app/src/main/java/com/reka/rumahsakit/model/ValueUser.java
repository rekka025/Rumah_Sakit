package com.reka.rumahsakit.model;

import java.util.List;

/**
 * Created by reka on 4/6/18.
 */

public class ValueUser {
    String value;
    String message;
    List<Pasien> result;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<Pasien> getResult() {
        return result;
    }
}
