package br.org.sidi.security.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Asset;
import br.org.sidi.security.data.Connection;
import br.org.sidi.security.ui.task.QRBarCodeActivity;
import br.org.sidi.security.utils.Tools;

public class AssetReturnActivity extends AppCompatActivity {

    private EditText codeEquip;
    private Button btnQRBarCode, btnSubmit;

    Connection dbAcessObj = new Connection();

    private static final int ACTIVITY_2_REQUEST = 1;

    private List<Asset> listAsset = new ArrayList<Asset>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_return);

        startUIComponents();

        verifyTextChange();

        startClickEvents();

    }

    private void startUIComponents() {
        codeEquip = findViewById(R.id.edt_CodeAsset_Return);
        btnQRBarCode = findViewById(R.id.btn_Barcode_Return);
        btnSubmit    = findViewById(R.id.btn_Return);
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
                    dbAcessObj.searchAssetItem(codWrote);

                }else{
                    btnSubmit.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    private void startClickEvents() {

        btnQRBarCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readQRBarCode();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listAsset = dbAcessObj.getListAssetOut();

                if (!listAsset.isEmpty()){
                    try{
                        dbAcessObj.updateDataStatus(dbAcessObj.getUuid(), "Concluído");
                        Tools.alertShort(AssetReturnActivity.this, "Processo Concluído com Sucesso!");
                        finish();
                    }catch (Exception e){
                        Tools.alertLong(AssetReturnActivity.this, "Não foi possível realizar conexão com a base de dados!");
                        Log.e("SGSBeta", "Erro: " + e.getMessage());
                    }
                }else{
                    Tools.alertShort(AssetReturnActivity.this, "Objeto Retornado ou Não Transferido!");
                }
            }
        });
    }

    private void readQRBarCode() {
        if (Tools.testClick(1000)) {
            Intent outActivity = new Intent(this, QRBarCodeActivity.class);
            startActivityForResult(outActivity, ACTIVITY_2_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ACTIVITY_2_REQUEST) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("resultado");
                codeEquip.setText(result);
            }
        }

    }
}
