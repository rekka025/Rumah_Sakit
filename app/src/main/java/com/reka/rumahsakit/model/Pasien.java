package com.reka.rumahsakit.model;

/**
 * Created by reka on 3/19/18.
 */

public class Pasien {
    String username;
    String password;
    String nama;
    String alamat;
    String nohp;
    String noktp;
    String email;

    public Pasien(String username, String password, String nama, String alamat, String nohp, String noktp, String email) {
        this.username = username;
        this.password = password;
        this.nama = nama;
        this.alamat = alamat;
        this.nohp = nohp;
        this.noktp = noktp;
        this.email = email;
    }




    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getNohp() {
        return nohp;
    }

    public void setNohp(String nohp) {
        this.nohp = nohp;
    }

    public String getNoktp() {
        return noktp;
    }

    public void setNoktp(String noktp) {
        this.noktp = noktp;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    //respon server
    String value;
    String message;

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }


}
