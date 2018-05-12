package com.reka.rumahsakit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reka.rumahsakit.SharePreference.SessionManager;
import com.reka.rumahsakit.model.Pasien;
import com.reka.rumahsakit.model.ResponServer;
import com.reka.rumahsakit.model.ValueUser;
import com.reka.rumahsakit.retrofit.ApiService;
import com.reka.rumahsakit.retrofit.RetrofitCilent;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditActivity extends AppCompatActivity {
    //    SessionManager sessionManager;
    String username, password, nama, alamat, nohp, noktp, email;
    private EditText edtNama;
    private EditText edtNomerktp;
    private EditText edtAlamat;
    private EditText edtNohp;
    private EditText edtPass;
    private TextView txshow;
    private EditText edtEmail;

    SessionManager sessionManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        this.setTitle("Edit Data");
        //untuk mengambil inputan username dari edit text login yang di simpan di sharepreference
        sessionManager = new SessionManager(getApplicationContext());
        HashMap<String, String> user = sessionManager.getUser();
        username = user.get(SessionManager.kunci_username);

        initView();
        ambilData();

    }

    //untuk mengambil data dengan menggunakan username yang di kirim ke server untuk mengambil data2 yg lain
    private void ambilData() {
        ApiService api = RetrofitCilent.getClient().create(ApiService.class);
        Call<ValueUser> call = api.ambil(username);
        call.enqueue(new Callback<ValueUser>() {
            @Override
            public void onResponse(Call<ValueUser> call, Response<ValueUser> response) {

                if (response.isSuccessful()) {
                    edtNama.setText(response.body().getResult().get(0).getNama());
                    edtNomerktp.setText(response.body().getResult().get(0).getNoktp());
                    edtAlamat.setText(response.body().getResult().get(0).getAlamat());
                    edtNohp.setText(response.body().getResult().get(0).getNohp());
                    edtPass.setText(response.body().getResult().get(0).getPassword());
                    edtEmail.setText(response.body().getResult().get(0).getEmail());
                }


            }

            @Override
            public void onFailure(Call<ValueUser> call, Throwable t) {
                Toast.makeText(EditActivity.this, "Silahkan Periksa Jaringan Anda", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void initView() {
        edtNama = findViewById(R.id.edt_nama);
        edtNomerktp = findViewById(R.id.edt_nomerktp);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNohp = findViewById(R.id.edt_nohp);
        edtPass = findViewById(R.id.edt_pass);
        txshow = findViewById(R.id.txshow);
        edtEmail = findViewById(R.id.edt_email);


    }

    public void btnAkun(View view) {

        editAkun();
    }

    public void editAkun() {
        nama = edtNama.getText().toString();
        noktp = edtNomerktp.getText().toString();
        alamat = edtAlamat.getText().toString();
        nohp = edtNohp.getText().toString();
        password = edtPass.getText().toString();
        email = edtEmail.getText().toString();

        if (edtNama.getText().toString().trim().isEmpty()) {
            edtNama.setError("Tidak Boleh Kosong");
        } else if (edtNomerktp.getText().toString().trim().isEmpty()) {
            edtNomerktp.setError("Tidak Boleh Kosong");
        } else if (edtAlamat.getText().toString().trim().isEmpty()) {
            edtAlamat.setError("Tidak Boleh Kosong");
        } else if (edtNohp.getText().toString().trim().isEmpty()) {
            edtNohp.setError("Tidak Boleh Kosong");
        } else if (edtPass.getText().toString().trim().isEmpty()) {
            edtPass.setError("Tidak Boleh Kosong");
        } else if (edtEmail.getText().toString().trim().isEmpty()) {
            edtEmail.setError("Tidak Boleh Kosong");
        } else {

//            pertama kita panggil dulu retrofitnya
//            1. ApiService api = RetrofitCilent.getClient().create(ApiService.class);
//            2. kita panggil model pasiennya yaitu Call<Pasien> trus itu kita panggil lagi yang berada di apiservicenya
//            yaitu editData dan kita masukkan sesuai data yang ingin kita ganti
//            kita masukkan antriannya (enqueue)


            ApiService api = RetrofitCilent.getClient().create(ApiService.class);
                                                    //harus urut sesuai inputan karena berpengaruh pada pengiriman data
            Call<ResponServer> call = api.editData(username,nama, noktp,   alamat, nohp,password, email);
            call.enqueue(new Callback<ResponServer>() {
                @Override
                public void onResponse(Call<ResponServer> call, Response<ResponServer> response) {
                    if (response.isSuccessful()) {
                        String value = response.body().getValue();
                        String message = response.body().getMessage();
                        if (value.equals("1")) {
                            Toast.makeText(EditActivity.this, message, Toast.LENGTH_SHORT).show();
                        } else {

                            Toast.makeText(EditActivity.this, message, Toast.LENGTH_SHORT).show();
                        }

                    }
                }

                @Override
                public void onFailure(Call<ResponServer> call, Throwable t) {
                    Toast.makeText(EditActivity.this, " Periksa Jaringan Anda", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }



}
