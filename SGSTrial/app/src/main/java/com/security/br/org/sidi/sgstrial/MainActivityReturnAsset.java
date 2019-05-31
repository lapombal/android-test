package com.security.br.org.sidi.sgstrial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

    private static final int ACTIVITY_2_REQUEST = 1;

    private List<AssetOut> listAssetOut = new ArrayList<AssetOut>();

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
                listAssetOut = dbAcessObj.getListAssetOut();

                if (!listAssetOut.isEmpty()){

                    dbAcessObj.updateDataStatus(dbAcessObj.getUuid(), "Concluído");
                    Toast.makeText(MainActivityReturnAsset.this, "Processo Concluído com Sucesso!", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    Toast.makeText(MainActivityReturnAsset.this, "Objeto Retornado ou Não Transferido!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void startUIComponents() {
        codeEquip = findViewById(R.id.txtCodigoAsset);
        btnQRBarCode = findViewById(R.id.btnLeitorCodigo);
        btnSubmit = findViewById(R.id.btnTransferir);
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
                    dbAcessObj.searchItem(codWrote);

                }else{
                    btnSubmit.setVisibility(View.INVISIBLE);
                }

            }
        });
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
