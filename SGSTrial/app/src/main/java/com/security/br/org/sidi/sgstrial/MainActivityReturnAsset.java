package com.security.br.org.sidi.sgstrial;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/********************************************
 *
 * @author l.pombal
 *
 */

public class MainActivityReturnAsset extends AppCompatActivity {

    private EditText codeEquip;
    private Button btnQRBarCode;
    private Button btnSubmit;

    ControlData dbAcessObj = new ControlData();
    AssetOut assetObj = new AssetOut();

    static final int ACTIVITY_2_REQUEST = 1;

    private List<AssetOut> listAssetOut = new ArrayList<AssetOut>();
    private String uuid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_return_asset);

        startUIComponents();

        verifyTextChange();

        btnQRBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readQRBarCode();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!listAssetOut.isEmpty()){

                    dbAcessObj.updateDataStatus(uuid, "Concluído");
                    Toast.makeText(MainActivityReturnAsset.this, "Processo Concluído com Sucesso!", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(MainActivityReturnAsset.this, "Objeto Retornado ou Não Transferido!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void verifyTextChange() {
        codeEquip.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String codWrote = codeEquip.getText().toString().trim();

                if((!codWrote.isEmpty())&&(codWrote.length()>6)){
                    btnSubmit.setVisibility(View.VISIBLE);
                    searchItem(codWrote);

                }else{
                    btnSubmit.setVisibility(View.INVISIBLE);
                }

            }
        });

    }

    private void searchItem(final String codWrote) {
        DatabaseReference refDb = FirebaseDatabase.getInstance().getReference();
        DatabaseReference itemRef = refDb.child("transferencia");

        Log.i("SGS.SIDI", "Iniciando Busca de Dados!");

        listAssetOut.clear();

        itemRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.i("SGS.SIDI", "Dados localizados!");
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    AssetOut m = objSnapshot.getValue(AssetOut.class);

                    if ((m.getAsset().equals(codWrote)) && (!m.getStatus().equals("Concluído"))) {

                        Log.i("SGS.OBJ.SIDI", "CHAVE DO OBJETO: " + objSnapshot.getKey());
                        Log.i("SGS.SIDI", "Informação encontrada na base: " + dataSnapshot.getValue().toString());
                        listAssetOut.add(assetObj);
                        assetObj = objSnapshot.getValue(AssetOut.class);
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

    private void startUIComponents() {
        codeEquip = findViewById(R.id.txtCodigoAsset);
        btnQRBarCode = findViewById(R.id.btnLeitorCodigo);
        btnSubmit = findViewById(R.id.btnTransferir);
    }

    /* Métodos de preenchimento do Campo Código do Equipamento */

    private void readQRBarCode(){
        if (TestClick.testClick(1000)) {
            Intent outActivity = new Intent(this, ActivityQRBarReader.class);
            startActivityForResult(outActivity, ACTIVITY_2_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTIVITY_2_REQUEST) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("resultado");
                codeEquip.setText(result);
            }
        }
    }


}
