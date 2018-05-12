package com.reka.rumahsakit;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.reka.rumahsakit.SharePreference.SessionManager;

public class MainActivity extends AppCompatActivity {
    SessionManager sessionManager;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setTitle("Home");

        //di cetak lagi biar bisa di gunakan
        sessionManager = new SessionManager(getApplicationContext());

    }

    public void jdDokter(View view) {
        Intent jadwalDokter = new Intent(this, JadwalActivity.class);
        startActivity(jadwalDokter);
    }

    public void edAkun(View view) {

    Intent edit = new Intent(this, EditActivity.class);
    startActivity(edit);


    }

    public void logout(View view) {
        sessionManager.logOut();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;

        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }

        }, 2000);
    }
}
