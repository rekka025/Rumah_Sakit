package com.reka.rumahsakit.model;

/**
 * Created by reka on 3/26/18.
 */

public class Dokter {
    //ini mendeklarasikan field di dalam database
    String nama;
    String jeniskelamin;
    String jadwaldinas;
    String spesialis;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getJadwaldinas() {
        return jadwaldinas;
    }

    public void setJadwaldinas(String jadwaldinas) {
        this.jadwaldinas = jadwaldinas;
    }

    public String getSpesialis() {
        return spesialis;
    }

    public void setSpesialis(String spesialis) {
        this.spesialis = spesialis;
    }

}
