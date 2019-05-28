package com.security.br.org.sidi.sgstrial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivityOutAsset extends Activity {

    private EditText idColaborador;
    private EditText codEquipamento;
    private RadioGroup rdoDestino;
    private RadioButton rdobtDestinoEscolhido;
    private RadioGroup rdoTipo;
    private RadioButton rdobtTipoEscolhido;
    private RadioButton rdobtEscolhido;
    private Button btnLeitor;
    private Button btnConfirmar;

    static final int ACTIVITY_2_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_out_asset);

        idColaborador = findViewById(R.id.txtIdColaborador);
        codEquipamento = findViewById(R.id.txtCodigoAsset);
        rdoDestino = findViewById(R.id.rdoGroupDestino);
        rdoTipo = findViewById(R.id.rdoGroupTipoEquipamento);
        btnLeitor = findViewById(R.id.btnLeitorCodigo);
        btnConfirmar = findViewById(R.id.btnTransferir);

        btnLeitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readQRBarCode();
            }
        });

        btnConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });
    }

    /* Chamadas de código para preenchimento do Campo Código do Equipamento */

    private void readQRBarCode(){
        if (CliqueTeste.testClique(1000)) {
            Intent outActivity = new Intent(this, ActivityQRBarReader.class);
            startActivityForResult(outActivity, ACTIVITY_2_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == ACTIVITY_2_REQUEST) {
            if(resultCode == RESULT_OK){
                String resultado = data.getStringExtra("resultado");
                codEquipamento.setText(resultado);
            }
        }
    }

    /* Métodos responsáveis pela seleção do Botão */

    private String verifyRdoButton(RadioGroup rdoGroupSelection){

        int idRdoBTSelected = rdoGroupSelection.getCheckedRadioButtonId();
        String txtRdoButton = "";

        if (idRdoBTSelected > 0){
            rdobtEscolhido = findViewById(idRdoBTSelected);
            txtRdoButton = (String) rdobtEscolhido.getText();
        }

        return txtRdoButton;

    }

    /*DatabaseReference transferenciaSgsReference = iniciaEstruturaBase();

        Transferencia asset = new Transferencia();

        asset.setAsset("S12052258");
        asset.setDestino("Jacarandá");
        asset.setNome("Luis de Camoes");
        asset.setData("13:17");
        asset.setStatus("Em Andamento");

        //asset.iniciaTransferencia("Luis de Camoes", "S170544596", "Jacarandá", "Em Andamento", "27/05/2019");

        transferenciaSgsReference.setValue( asset );*/

    private DatabaseReference iniciaEstruturaBase(){

        DatabaseReference sgsfireReference = FirebaseDatabase.getInstance().getReference();

        Calendar calendar = Calendar.getInstance();

        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        Integer ano = calendar.get(Calendar.YEAR);
        Integer mes = calendar.get(Calendar.MONTH);


        DatabaseReference transferenciaReference = sgsfireReference.child("transferencia").child( ano.toString() ).child( mes.toString() ).child( dia.toString() ).child("002");

        return transferenciaReference;
    }

}
