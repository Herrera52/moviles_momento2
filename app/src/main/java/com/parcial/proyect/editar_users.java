package com.parcial.proyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class editar_users extends AppCompatActivity {

    EditText etApart, etPais, etCity, etDescrip, etPrice, etDireccion;
    Button btEditar;

    FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_users);

        etApart = findViewById(R.id.etApart);
        etPais = findViewById(R.id.etPais);
        etCity = findViewById(R.id.etCity);
        etDireccion = findViewById(R.id.etDireccion);
        etDescrip = findViewById(R.id.etDescrip);
        etPrice = findViewById(R.id.etPrice);
        btEditar = findViewById(R.id.btEditar);


        String apto =getIntent().getStringExtra("id");

        DocumentReference docRef = db.collection("apartments").document(apto);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d("Mensaje1", "DocumentSnapshot data: " + document.getData());
                        String apartament =document.getString("apart");
                        String pais =document.getString("pais");
                        String city =document.getString("city");
                        String direccion =document.getString("direccion");
                        String description =document.getString("descrip");
                        String price =document.getString("price");


                        etApart.setText(apartament);
                        etPais.setText(pais);
                        etCity.setText(city);
                        etDireccion.setText(direccion);
                        etDescrip.setText(description);
                        etPrice.setText(price);




                    } else {
                        Log.d("Ese id no existe", "No such document");
                    }
                } else {
                    Log.d("No conecta a la Bd", "get failed with ", task.getException());
                }
            }
        });


    }
    public void actualizar (View view){
        btEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editarApart();
                Toast.makeText(editar_users.this, "APARTAMENTO ACTUALIZADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void editarApart (){
        String apto1 =getIntent().getStringExtra("id");

        String apartm = etApart.getText().toString();
        String city = etCity.getText().toString();
        String pais = etPais.getText().toString();
        String direccion = etDireccion.getText().toString();
        String descripcion = etDescrip.getText().toString();
        String price = etPrice.getText().toString();


        Map<String, Object> apartament = new HashMap<>();
        apartament.put("apart", apartm);
        apartament.put("city", city);
        apartament.put("pais", pais);
        apartament.put("direccion", direccion);
        apartament.put("descrip", descripcion);
        apartament.put("price", price);

        db.collection("apartments").document(apto1)
                .update(apartament)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "APARTAMENTO ACTUALIZADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                       card();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "ERROR AL ACTUALIZAR EL APARTAMENTO", Toast.LENGTH_SHORT).show();
                    }
                });






    }
    public void card(){
        Intent intent = new Intent(getApplicationContext(), ListUsers.class);
        startActivity(intent);
    }

    public void volverEdit (View view){
        Intent volverEdit = new Intent(this, ListUsers.class);
        startActivity(volverEdit);
    }



}