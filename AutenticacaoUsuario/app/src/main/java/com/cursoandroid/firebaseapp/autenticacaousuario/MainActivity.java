package com.cursoandroid.firebaseapp.autenticacaousuario;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();





        //Login do usuario
        /*firebaseAuth.signInWithEmailAndPassword("lapombal@gmail.com", "leleo123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    //Sucesso ao cadastrar usuario
                    Log.i("signIn", "Sucesso ao fazer login usuario!!!");

                }else{
                    // erro ao cadastrar o usuario
                    Log.i("signIn", "Erro ao fazer login usuario!!!");
                }

            }
           });*/

        firebaseAuth.signOut();

        if (firebaseAuth.getCurrentUser() != null) {

            Log.i("verificaUsuario", "Usuario está logado!!");
        } else {

            Log.i("verificaUsuario", "Usuario não está logado!!");
        }

        /*firebaseAuth.createUserWithEmailAndPassword("lapombal@gmail.com", "leleo123")
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if ( task.isSuccessful() ){
                    //Sucesso ao cadastrar usuario
                    Log.i("createUser", "Sucesso ao cadastrar usuario!!!");

                }else{
                    // erro ao cadastrar o usuario
                    Log.i("createUser", "Erro ao cadastrar usuario!!!");
                }
            }
        });*/


    }
}
