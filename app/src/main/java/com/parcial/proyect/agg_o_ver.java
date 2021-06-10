package com.parcial.proyect;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class agg_o_ver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agg_o_ver);
        loadPreferences();
        mantenerSesion();
    }
    public void verApart(View view){
        Intent ver = new Intent(this, ListUsers.class);
        startActivity(ver);
    }
    public void agg_apart(View view){
        Intent agg = new Intent(this, MainActivity.class);
        startActivity(agg);
    }
    public void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String userState = preferences.getString("state", "error");
        Toast.makeText(this, userState, Toast.LENGTH_SHORT).show();
    }
    public void mantenerSesion(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String userState = "login";
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("state", userState);
        editor.commit();

    }
}