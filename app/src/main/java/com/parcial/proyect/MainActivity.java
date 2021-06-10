package com.parcial.proyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edtApart, edtPais, edtCity, edtDescrip, edtDireccion, etUserr, etPass, edtPrice;
    private Button info;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtApart = findViewById(R.id.edtApart);
        edtPais = findViewById(R.id.edtPais);
        edtCity= findViewById(R.id.edtCity);
        edtDireccion = findViewById(R.id.edtDireccion);
        edtDescrip = findViewById(R.id.edtDescrip);
        edtPrice = findViewById(R.id.edtPrice);




    }

    /*public void registrarUser(View view) {

        String correo = etUserr.getText().toString();
        String contra = etPass.getText().toString();
        mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "APARTAMENTO AGREGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "ERROR AL AGREGAR EL APARTAMENTO", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }*/

    public void saveuser(View view) {



        if (validar()) {

        }

        Map<String, Object> user = new HashMap<>();
        String apart = edtApart.getText().toString();
        String pais = edtPais.getText().toString();
        String city = edtCity.getText().toString();
        String direccion =edtDireccion.getText().toString();
        String descrip = edtDescrip.getText().toString();
        String  price = edtPrice.getText().toString();
        user.put("apart", apart);
        user.put("pais", pais);
        user.put("city", city);
        user.put("direccion", direccion);
        user.put("descrip", descrip);
        user.put("price", price);

        // Add a new document with a generated ID
        db.collection("apartments")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(MainActivity.this, "APARTAMENTO AGREGADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        Log.d("firebase", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "ERROR AL AGREGAR EL APARTAMENTO", Toast.LENGTH_SHORT).show();
                        Log.w("firebase", "Error adding document", e);
                    }
                });
    }


    public boolean validar() {
        boolean retorno = true;

        String c1 = edtApart.getText().toString();
        String c2 = edtPais.getText().toString();
        String c3 = edtCity.getText().toString();
        String c4 = edtDescrip.getText().toString();
        String c5 =  edtPrice.getText().toString();
        String c6 = edtDireccion.getText().toString();

        if (c1.isEmpty()) {

            edtApart.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c2.isEmpty()) {

            edtPais.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c3.isEmpty()) {

            edtCity.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c4.isEmpty()) {

            edtDescrip.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c5.isEmpty()) {

            edtPrice.setError("Este campo no puede quedar vacio");
            retorno = false;
        }
        if (c6.isEmpty()) {

            edtDireccion.setError("Este campo no puede quedar vacio");
            retorno = false;
        }


        return retorno;
    }


    public void Regresar(View view) {
        //Intent regresar = new Intent(this, login.class);
        Intent regresar = new Intent(this, agg_o_ver.class);
        startActivity(regresar);

    }

    /*public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean  onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        if(id == R.id.info){
           Intent intent= new Intent(getApplicationContext(), info_app.class);
           startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }*/



}