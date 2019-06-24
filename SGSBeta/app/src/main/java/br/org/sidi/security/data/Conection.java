package br.org.sidi.security.data;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Conection {

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    public Conection() {
    }

    public static FirebaseAuth getFirebaseAuth(){
        try{

            if (firebaseAuth == null){
                initFirebaseAuth();
            }

        }catch (Exception e){

            Log.e("SGSBeta", "Erro: " + e.getMessage());
        }

        return firebaseAuth;
    }

    private static void initFirebaseAuth() {
        firebaseAuth = FirebaseAuth.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null){
                    firebaseUser = user;
                }
            }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    public static FirebaseUser getFirebaseUser(){
        return firebaseUser;
    }

    public static void logout(FirebaseAuth m){
        m.signOut();
    }


}
