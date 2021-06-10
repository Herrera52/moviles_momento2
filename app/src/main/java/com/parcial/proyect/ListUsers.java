package com.parcial.proyect;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import Modelos.usersModel;

public class ListUsers extends AppCompatActivity {

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    RecyclerView rvFirestoreUsersList;
    FirestoreRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        rvFirestoreUsersList = findViewById(R.id.CardView);

        Query query = db.collection("apartments");

        final FirestoreRecyclerOptions<usersModel> options = new FirestoreRecyclerOptions.Builder<usersModel>().setQuery(query, usersModel.class).build();

        adapter = new FirestoreRecyclerAdapter<usersModel, UsersViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final UsersViewHolder holder, final int position, @NonNull usersModel model) {
                holder.tvApart.setText(model.getApart());
                holder.tvPais.setText(model.getPais());
                holder.tvCity.setText(model.getCity());
                holder.tvDescrip.setText(model.getDescrip());
                holder.tvPrice.setText(model.getPrice());
                holder.tvDireccion.setText(model.getDireccion());
                final String id = getSnapshots().getSnapshot(position).getId();
                holder.btnDeletApart.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDatos(id);
                    }
                });

                holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent edit  = new Intent(ListUsers.this, editar_users.class);
                        edit.putExtra("id",id);
                        startActivity(edit);

                    }
                });



            }


            @NonNull
            @Override
            public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_single, parent, false);
                return new UsersViewHolder(view);
            }
        };
        rvFirestoreUsersList.setHasFixedSize(true);
        rvFirestoreUsersList.setLayoutManager(new LinearLayoutManager(this));
        rvFirestoreUsersList.setAdapter(adapter);
    }

    public void deleteDatos(String id) {
        db.collection("apartments").document(id)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ListUsers.this, "Apartamento Eliminado Correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ListUsers.this, "A Ocurrido Un Error", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    static class UsersViewHolder extends RecyclerView.ViewHolder{
        TextView tvApart, tvPais, tvCity, tvDescrip, tvPrice, tvDireccion;
        Button btnDeletApart, btnVolver, btnEdit;
        public UsersViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPais = itemView.findViewById(R.id.tvPais);
            tvCity = itemView.findViewById(R.id.tvCity);
            tvDescrip = itemView.findViewById(R.id.tvDescrip);
            tvApart = itemView.findViewById(R.id.tvApart);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
            btnDeletApart = itemView.findViewById(R.id.btnDeletApart);
            btnVolver = itemView.findViewById(R.id.btnVolver);
            btnEdit = (Button) itemView.findViewById(R.id.btnEdit);


        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
     public  void volver_agg_ver(View view){
        Intent vol = new Intent(ListUsers.this, agg_o_ver.class);
        startActivity(vol);
     }

}