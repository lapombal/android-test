package com.security.br.org.sidi.sgstrial;

import android.icu.text.SimpleDateFormat;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;

/********************************************
 *
 * @author l.pombal
 *
 */

public class ControlData {

    private final String NODE_DB = "transferencia";
    private String statusRet = "";
    AssetOut st = new AssetOut();

    //private List<ControlData> listData = new ArrayList<ControlData>();
    //private ArrayAdapter<ControlData> arrayAdapterData;

    private DatabaseReference referenceDB = FirebaseDatabase.getInstance().getReference();

    public ControlData() {
    }

    public void item (String txtWrote){

    }

    public void searchItem(final String txtWrote) {
       //Query query;

        final DatabaseReference searchref = setDatabaseToRead();

        //query = searchref.orderByKey();

        searchref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("SGS.SIDI", "Modificação de dados detectada!");

                int objC = 1;

                for (DataSnapshot d : dataSnapshot.getChildren()) {
                    AssetOut m = d.getValue(AssetOut.class);

                    Log.i("FIREBASES", dataSnapshot.getValue().toString());

                    Log.i("SGS.SIDI", "Objeto " + objC++);
                    Log.i("SGS.SIDI", "Nome: " + m.getNome());
                    Log.i("SGS.SIDI", "Tipo: " + m.getTipo());
                    Log.i("SGS.SIDI", "Codigo: " + m.getAsset());
                    Log.i("SGS.SIDI", "Destino: " + m.getDestino());
                    Log.i("SGS.SIDI", "Status: " + m.getStatus());

                    if (m.getAsset().equals(txtWrote)) {

                        st = m;
                        Log.i("SGS.SIDI", "Informação encontrada na base: " + st.getAsset());

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

    private DatabaseReference setDatabaseToRead(){

        DatabaseReference readData = referenceDB.child(NODE_DB);

        return readData;
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

    private DatabaseReference setDatabaseDataToWrite(){

        SimpleDateFormat format_date = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        Calendar calendar = Calendar.getInstance();

        Date timestamp = calendar.getTime();

        String date = format_date.format(timestamp);

        DatabaseReference transferReference = referenceDB.child(NODE_DB).child( date );

        return transferReference;
    }

}
