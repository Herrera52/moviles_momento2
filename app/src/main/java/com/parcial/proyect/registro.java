package com.parcial.proyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class registro extends AppCompatActivity {

    EditText edtUserr, edtName,edtEmail,edtPassword;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        edtUserr = findViewById(R.id.edtUserr);
        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
    }

    public void registerUser(View view){
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(registro.this, "Usuario Registrado Correctamente", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(registro.this, "Ocurrio un error al registrar usuario", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public  void vollv(View view){
        Intent voll = new Intent(this, login.class);
        startActivity(voll);
    }
}