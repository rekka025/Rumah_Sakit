package com.reka.rumahsakit.SharePreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.reka.rumahsakit.MainActivity;
import com.reka.rumahsakit.LoginActivity;

import java.util.HashMap;

/**
 * Created by reka on 4/3/18.
 */

public class SessionManager {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context context;
    int mode = 0;

    private static final String pref_name = "logreg";
    private static final String login = "ilogin";
    public static final String kunci_username = "kusername";

    public SessionManager(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(pref_name,mode);
        editor = pref.edit();
    }

    //ini adalah apa yang akan kita simpan sebagai pengenalnya masuk ke hal1
    public void createSession(String username){
        editor.putBoolean(login,true);
        editor.putString(kunci_username,username);
        editor.commit();

    }
    private boolean login() {
        return pref.getBoolean(login, false);
    }

    //jika awalan dia belum login maka dia akan di larikan ke menu login
    //jika sudah ada akun yang telah login maka dia akan di larikan ke hal1 terlebih dahulu
    public void checkLogin(){
        if (!this.login()){
            //ke login activty dlu
            Intent i = new Intent(context, LoginActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else {
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }

    //ini adalah method logout yang dia akan menghapus data2 yang telah kita inputkan pada login sebelumnya
    //dan akan di larikan ke menu login untuk login baru
    public void logOut(){
        editor.clear();
        editor.commit();
        Intent  i = new Intent(context,LoginActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }

    //ini untuk mengambil data yang telah kita inputkan yaitu username saja
    public HashMap<String,String> getUser(){
    HashMap<String, String> user = new HashMap<String, String>();
    user.put(pref_name,pref.getString(pref_name,null));
    user.put(kunci_username,pref.getString(kunci_username,null));
    return user;

    }
}
