package com.reka.rumahsakit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.reka.rumahsakit.SharePreference.SessionManager;
import com.reka.rumahsakit.model.Pasien;
import com.reka.rumahsakit.retrofit.ApiService;
import com.reka.rumahsakit.retrofit.RetrofitCilent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtLoginUsername;
    private EditText edtLoginPassword;
    String username,password;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    sessionManager = new SessionManager(getApplicationContext());


    }

    public void register(View view) {
        Intent reg = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(reg);

    }

    public void login(View view) {
        //apa yang kita tulis di dalam edt username dan edt pass ini akan di kirim ke method requestLogin
        // lalu requestLogin mengirimkan ke server
       username = edtLoginUsername.getText().toString();
       password = edtLoginPassword.getText().toString();
       requestLogin();
       sessionManager.createSession(edtLoginUsername.getText().toString());
//       validasiLogin(username,password);
    }

    private void requestLogin() {
    ApiService apiService = RetrofitCilent.getClient().create(ApiService.class);
    //memanggil model pasien// ini untuk memanggil apiServicenya yaitu login
    Call<Pasien> call =       apiService.login(username,password);
    call.enqueue(new Callback<Pasien>() {
        @Override
        public void onResponse(Call<Pasien> call, Response<Pasien> response) {
            if (response.isSuccessful()){
                //memanggil status yang ada di model Pasien success dan message yaitu suatu pesan dari server
                //yang kita panggil lalu kita buat Toastnya agar pesan itu muncul
                String success = response.body().getValue();
                String message = response.body().getMessage();
                if (success.equals("1")){
                    //jika sukses dengan isian 1 dari server  maka akan kita inten(pindah activty)
                    //lalu kita munculkan ke toast pesan dari server
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }else {
                    //else 0 adalah salah , bisa jadi salah password dan belum terdaftar
                    Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                }
            }


        }

        @Override
        public void onFailure(Call<Pasien> call, Throwable t) {
             t.printStackTrace();
            Toast.makeText(LoginActivity.this, "Jaringan Eror", Toast.LENGTH_SHORT).show();
        }
    });




    }

    private boolean validasiLogin(String username, String password){
        if (username == null || username.trim().length() == 0){
            Toast.makeText(this, "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password == null || password.trim().length() == 0){
            Toast.makeText(this, "password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void initView() {
        edtLoginUsername = findViewById(R.id.edt_login_username);
        edtLoginPassword = findViewById(R.id.edt_login_password);

    }
}
