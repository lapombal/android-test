package com.cursoandroid.firebaseapp.firebaseapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuarioReference = databaseReference.child("usuarios");
    private DatabaseReference produtoReference = databaseReference.child("produtos").child("001");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //incluirProduto();

        incluirUsuario();

        usuarioReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Log.i("FIREBASES", dataSnapshot.getValue().toString() );
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void incluirProduto(){

        //Incluindo produtos
        Produto produto = new Produto();

        produto.setDescricao("Bola");
        produto.setCor("Marrom");
        produto.setFabricante("Umbro");

        produtoReference.child("003").setValue( produto );
    }

    private void incluirUsuario(){

        //Incluindo usuário
        Usuario usuario = new Usuario();

        usuario.setNome("Tereza");
        usuario.setSobrenome("Cristina");
        usuario.setIdade(23);
        usuario.setSexo("Feminino");

        usuarioReference.child("005").setValue( usuario );

    }
}
