package br.org.sidi.security.data;

import android.icu.text.SimpleDateFormat;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Connection {

    private final String NODE_DB = "transferencia";
    private String uuid;

    private List<Asset> listAsset = new ArrayList<Asset>();

    private DatabaseReference referenceDB = FirebaseDatabase.getInstance().getReference();

    private static FirebaseAuth firebaseAuth;
    private static FirebaseAuth.AuthStateListener authStateListener;
    private static FirebaseUser firebaseUser;

    public Connection() {
    }

    // Métodos Firebase Realtime Database
    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<Asset> getListAssetOut() {
        return listAsset;
    }

    public void setListAssetOut(List<Asset> listAssetOut) {
        this.listAsset = listAssetOut;
    }

    public void searchAssetItem(final String txtWrote) {
        DatabaseReference itemRef = setDatabaseToRead();

        Log.i("SGSBeta", "Iniciando Busca de Dados!");

        listAsset.clear();

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("SGSBeta", "Dados localizados!");
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Asset m = objSnapshot.getValue(Asset.class);

                    try{
                        if ((m.getAsset().equals(txtWrote)) && (!m.getStatus().equals("Concluído"))) {

                            //Log.i("SGS.OBJ.SIDI", "CHAVE DO OBJETO: " + objSnapshot.getKey());
                            //Log.i("SGS.SIDI", "Informação encontrada na base: " + dataSnapshot.getValue().toString());
                            listAsset.add(m);
                            uuid = objSnapshot.getKey();

                        } else {
                            Log.i("SGSBeta", "Valor não encontrado!");
                        }

                    }catch (Exception e){
                        Log.e("SGSBeta", "Erro: " + e.getMessage());
                    }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void writeData (String nome, String destino, String codasset, String tipo, String status){

        DatabaseReference carryoutSgsReference = setDatabaseDataToWrite();

        Asset asset = new Asset(nome, destino, codasset, tipo, status);

        carryoutSgsReference.setValue( asset );
    }

    public void updateDataStatus (String uuid, String status){

        DatabaseReference carryoutSgsReference = setDatabaseToRead();

        carryoutSgsReference.child(uuid).child("status").setValue(status);
    }


    private DatabaseReference setDatabaseToRead(){

        DatabaseReference readData = referenceDB.child(NODE_DB);

        return readData;
    }

    private DatabaseReference setDatabaseDataToWrite(){

        SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        Calendar calendar = Calendar.getInstance();

        Date timestamp = calendar.getTime();

        String date = format_date.format(timestamp);

        DatabaseReference transferReference = referenceDB.child(NODE_DB).child( date );

        return transferReference;
    }

    // Métodos Firebase Authenticator
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
