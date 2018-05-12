package com.reka.rumahsakit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.reka.rumahsakit.adapter.AdapterDokter;
import com.reka.rumahsakit.model.Dokter;
import com.reka.rumahsakit.model.ValueDokter;
import com.reka.rumahsakit.retrofit.ApiService;
import com.reka.rumahsakit.retrofit.RetrofitCilent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalActivity extends AppCompatActivity {
    private List<Dokter> dokters = new ArrayList<>();
    private AdapterDokter adapterDokter;

    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal);
        this.setTitle("Jadwal Dokter");
        progressBar = (ProgressBar) findViewById(R.id.pg);
        recyclerView = (RecyclerView) findViewById(R.id.rc);
        adapterDokter = new AdapterDokter(this, dokters);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapterDokter);

        loadData();
    }

    private void loadData() {
        ApiService apiService = RetrofitCilent.getClient().create(ApiService.class);
        Call<ValueDokter> call = apiService.view();
        call.enqueue(new Callback<ValueDokter>() {
            @Override
            public void onResponse(Call<ValueDokter> call, Response<ValueDokter> response) {
             String value = response.body().getValue();
                progressBar.setVisibility(View.GONE);
                if (value.equals("1")){
                    dokters = response.body().getResult();
                    adapterDokter = new AdapterDokter(JadwalActivity.this, dokters);
                    recyclerView.setAdapter(adapterDokter);
                }
            }

            @Override
            public void onFailure(Call<ValueDokter> call, Throwable t) {
                Toast.makeText(JadwalActivity.this, "Anda Sedang Tidak Terhubung Dengan Internet", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }
}
