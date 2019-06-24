package br.org.sidi.security.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Connection;
import br.org.sidi.security.ui.task.QRBarCodeActivity;
import br.org.sidi.security.utils.Tools;

public class AssetOutActivity extends AppCompatActivity {

    private EditText idPerson;
    private EditText codeEquip;
    private RadioGroup rdoDestiny;
    private RadioGroup rdoType;
    private Button btnQRBarReader;
    private Button btnSubmit;

    private static final int ACTIVITY_2_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_out);

        startUIComponents();

        startEventClicks();
    }

    private void startUIComponents() {
        idPerson = findViewById(R.id.edt_IdEmployee);
        codeEquip = findViewById(R.id.edt_CodeAsset);
        rdoDestiny = findViewById(R.id.rdo_GroupDestiny);
        rdoType = findViewById(R.id.rdo_GroupTAsset);
        btnQRBarReader = findViewById(R.id.btn_Barcode);
        btnSubmit = findViewById(R.id.btn_Transfer);
    }

    private void startEventClicks() {
        btnQRBarReader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readQRBarCode();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (checkInformation().equals("")){
                    Connection asset = new Connection();
                    try{
                        asset.writeData(idPerson.getText().toString(), codeEquip.getText().toString(), getRdoValue(rdoDestiny), getRdoValue(rdoType), "Em Andamento");
                        Tools.alertLong(AssetOutActivity.this, "Cadastro realizado");
                        finish();
                    }catch (Exception e){
                        Tools.alertLong(AssetOutActivity.this, "Não foi possível realizar conexão com a base de dados!");
                        Log.e("SGSBeta", "Erro: " + e.getMessage());
                    }
                }else{
                    String error = checkInformation();
                    Tools.alertLong(AssetOutActivity.this, "Campos Obrigatórios não Preenchidos: \n" + error);
                }
            }
        });

    }

    private void readQRBarCode(){
        if (Tools.testClick(1000)) {
            Intent outActivity = new Intent(this, QRBarCodeActivity.class);
            startActivityForResult(outActivity, ACTIVITY_2_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == ACTIVITY_2_REQUEST) {
            if(resultCode == RESULT_OK){
                String result = data.getStringExtra("resultado");
                codeEquip.setText(result);
            }
        }
    }

    /* Métodos responsáveis pela verificação de dados e recuperação das informações do Radio Button (Destino ou Tipo de equipamento) */

    private String getRdoValue(RadioGroup rdoGroupSelection) {
        RadioButton rdoBtChosen;

        int idRdoBTSelected = rdoGroupSelection.getCheckedRadioButtonId();
        String txtRdoButton = "";
        rdoBtChosen = findViewById(idRdoBTSelected);

        if (idRdoBTSelected > 0){

            txtRdoButton = (String) rdoBtChosen.getText();

        }

        return txtRdoButton;
    }

    private String checkInformation (){
        String error_message = "";

        if(idPerson.getText().length() == 0){

            idPerson.setError("Digite o ID do Colaborador!");
            error_message = "ID do Colaborador\n";

        }else if(idPerson.length() < 4){

            idPerson.setError("Valor inserido não é valido");
            error_message = "ID do Colaborador - Valor muito curto!\n";

        }

        if(codeEquip.getText().length() == 0){

            codeEquip.setError("Digite ou Leia o Codigo do Equipamento");
            error_message += "Codigo do Equipamento\n";

        }else if(codeEquip.length() < 7){

            codeEquip.setError("Valor inserido não é valido");
            error_message += "Codigo do Equipamento - Valor muito curto!\n";

        }

        if(getRdoValue(rdoDestiny).equals(""))
        {
            error_message += "Destino do Equipamento\n";
        }

        if(getRdoValue(rdoType).equals("")){
            error_message += "Tipo do Equipamento\n";

        }

        return error_message;
    }
}
