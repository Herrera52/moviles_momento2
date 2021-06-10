package com.parcial.proyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {

    EditText  edtName,edtEmail,edtPassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         loadPreferences();
        setContentView(R.layout.activity_login);


        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void login(View view){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(login.this, "Inicio de sesion exitoso", Toast.LENGTH_SHORT).show();
                            Intent list = new Intent(login.this, agg_o_ver.class);
                            mantenerSesion();

                            startActivity(list);

                        } else {
                            Toast.makeText(login.this, "Error al iniciar sesion", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
    public void registt(View view){
        Intent regg = new Intent(this, registro.class);
        startActivity(regg);
    }

    public void mantenerSesion(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String userState = "login";
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("state", userState);
        editor.commit();

    }
    public void loadPreferences(){
        SharedPreferences preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String userState = preferences.getString("state", "error");
        if(userState.equals("login")){
            Intent intent = new Intent(login.this, agg_o_ver.class);
            startActivity(intent);
            finish();
        }
    }

}