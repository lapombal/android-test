package com.security.br.org.sidi.sgstrial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

/********************************************
 *
 * @author l.pombal
 *
 */

public class MainActivityOutAsset extends AppCompatActivity {

    private EditText idPerson;
    private EditText codeEquip;
    private RadioGroup rdoDestiny;
    private RadioGroup rdoType;
    private RadioButton rdoBtChosen;
    private Button btnQRBarReader;
    private Button btnSubmit;

    static final int ACTIVITY_2_REQUEST = 1;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_out_asset);

        startUIComponents();

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
                    ControlData asset = new ControlData();

                    asset.writeData(idPerson.getText().toString(), codeEquip.getText().toString(), getRdoValue(rdoDestiny), getRdoValue(rdoType), "Em Andamento");
                    Toast.makeText(MainActivityOutAsset.this, "Cadastro realizado", Toast.LENGTH_SHORT).show();
                    finish();

                }else{
                    String error = checkInformation();
                    Toast.makeText(MainActivityOutAsset.this, "Campos Obrigatórios não Preenchidos: \n" + error, Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void startUIComponents() {
        idPerson = findViewById(R.id.txtIdColaborador);
        codeEquip = findViewById(R.id.txtCodigoAsset);
        rdoDestiny = findViewById(R.id.rdoGroupDestino);
        rdoType = findViewById(R.id.rdoGroupTipoEquipamento);
        btnQRBarReader = findViewById(R.id.btnLeitorCodigo);
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

    /* Métodos responsáveis pela verificação de dados e recuperação das informações do Radio Button (Destino ou Tipo de equipamento) */

    private String getRdoValue(RadioGroup rdoGroupSelection) {

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