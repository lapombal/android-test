package com.security.br.org.sidi.sgstrial;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/********************************************
 *
 * @author l.pombal
 *
 */

public class ControlData {

    private final String NODE_DB = "transferencia";
    private String uuid;

    private List<AssetOut> listAssetOut = new ArrayList<AssetOut>();

    private DatabaseReference referenceDB = FirebaseDatabase.getInstance().getReference();

    public ControlData() {
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public List<AssetOut> getListAssetOut() {
        return listAssetOut;
    }

    public void setListAssetOut(List<AssetOut> listAssetOut) {
        this.listAssetOut = listAssetOut;
    }

    public void searchItem(final String txtWrote) {
        DatabaseReference itemRef = setDatabaseToRead();

        Log.i("SGS.SIDI", "Iniciando Busca de Dados!");

        listAssetOut.clear();

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("SGS.SIDI", "Dados localizados!");
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    AssetOut m = objSnapshot.getValue(AssetOut.class);

                    if ((m.getAsset().equals(txtWrote)) && (!m.getStatus().equals("Concluído"))) {

                        //Log.i("SGS.OBJ.SIDI", "CHAVE DO OBJETO: " + objSnapshot.getKey());
                        //Log.i("SGS.SIDI", "Informação encontrada na base: " + dataSnapshot.getValue().toString());
                        listAssetOut.add(m);
                        uuid = objSnapshot.getKey();

                    } else {
                        Log.i("SGS.SIDI", "Valor não encontrado!");
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

        AssetOut asset = new AssetOut(nome, destino, codasset, tipo, status);

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

}
