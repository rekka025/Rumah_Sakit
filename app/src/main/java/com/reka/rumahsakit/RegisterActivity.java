package com.reka.rumahsakit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.reka.rumahsakit.model.Pasien;
import com.reka.rumahsakit.retrofit.ApiService;
import com.reka.rumahsakit.retrofit.RetrofitCilent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    private EditText edtNama;
    private EditText edtNomerktp;
    private EditText edtAlamat;
    private EditText edtNohp;
    private EditText edtPass;
    private TextView txshow;
    private EditText edtEmail;
    private Button btndaftar;
    private ProgressDialog pd;
    private EditText edtUser;
    String username, password, nama, alamat, nohp, noktp, email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        this.setTitle("Form Pendaftaran");
        initView();
        // untuk menghilangkan show
        txshow.setVisibility(View.GONE);

        password();

        pd = new ProgressDialog(this);

        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //semua yang di tuliskan di String adalah dari database
                // edtuser adalah inputannya yang inputannya nanti akan kita kirim ke database sesuai Stringnya
                username = edtUser.getText().toString();
                password = edtPass.getText().toString();
                nama = edtNama.getText().toString();
                alamat = edtAlamat.getText().toString();
                nohp = edtNohp.getText().toString();
                noktp = edtNomerktp.getText().toString();
                email = edtEmail.getText().toString();

                if (edtNama.getText().toString().trim().isEmpty()) {
                    edtNama.setError("Tidak Boleh Kosong");
                } else if (edtNomerktp.getText().toString().trim().isEmpty()) {
                    edtNomerktp.setError("Tidak Boleh Kosong");
                } else if (edtAlamat.getText().toString().trim().isEmpty()) {
                    edtAlamat.setError("Tidak Boleh Kosong");
                } else if (edtNohp.getText().toString().trim().isEmpty()) {
                    edtNohp.setError("Tidak Boleh Kosong");
                } else if (edtUser.getText().toString().trim().isEmpty()) {
                    edtUser.setError("Tidak Boleh Kosong");
                } else if (edtPass.getText().toString().trim().isEmpty()) {
                    edtPass.setError("Tidak Boleh Kosong");
                } else if (edtEmail.getText().toString().trim().isEmpty()) {
                    edtEmail.setError("Tidak Boleh Kosong");
                } else {

                    registerRequest();

                }

            }

            private void registerRequest() {
                ApiService api = RetrofitCilent.getClient().create(ApiService.class);
                Call<Pasien> call = api.register(username, password, nama, alamat, nohp, noktp, email);
                call.enqueue(new Callback<Pasien>() {
                    @Override
                    public void onResponse(Call<Pasien> call, Response<Pasien> response) {
                        if (response.isSuccessful()) {
                            pd.setCancelable(false);
                            pd.setMessage("Loading ...");
                            pd.show();

                            String success = response.body().getValue();
                            String message = response.body().getMessage();
                            pd.dismiss();
                            if (success.equals("1")) {
                                edtNama.setText("");
                                edtNomerktp.setText("");
                                edtAlamat.setText("");
                                edtNohp.setText("");
                                edtUser.setText("");
                                edtPass.setText("");
                                edtEmail.setText("");
                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                            } else {

                                Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Pasien> call, Throwable t) {

                    }
                });

            }
        });
    }



    private void password() {
        edtPass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (edtPass.getText().length() > 0) {
                    txshow.setVisibility(View.VISIBLE);
                } else {
                    txshow.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        // untuk bisa mengklik show dan menggantinya dengan hide
        txshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txshow.getText() == "Show") {
                    txshow.setText("Hide");
                    edtPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    edtPass.setSelection(edtPass.length());
                } else {
                    txshow.setText("Show");
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    edtPass.setSelection(edtPass.length());
                }
            }
        });
    }

    private void initView() {
        edtNama = findViewById(R.id.edt_nama);
        edtNomerktp = findViewById(R.id.edt_nomerktp);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNohp = findViewById(R.id.edt_nohp);
        edtPass = findViewById(R.id.edt_pass);
        edtEmail = findViewById(R.id.edt_email);
        txshow = findViewById(R.id.txshow);
        btndaftar = findViewById(R.id.btndaftar);
        edtUser = findViewById(R.id.edt_user);

    }
}
