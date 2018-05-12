package com.reka.rumahsakit.model;

import java.util.List;

/**
 * Created by reka on 3/26/18.
 */

public class ValueDokter {
    String value;
    String message;
    List<Dokter> result;


    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public List<Dokter> getResult() {
        return result;
    }
}
