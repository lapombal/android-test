package com.security.br.org.sidi.sgstrial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

/********************************************
 *
 * @author l.pombal
 *
 */

public class MainActivity extends AppCompatActivity {

    //private ImageButton saidaEquipamento;
    private ImageButton entradaEquipamento;
    private ImageButton contatoEmergencia;
    private ImageButton sairAplicativo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //saidaEquipamento = findViewById(R.id.imgBtnSaidaEquip);

        entradaEquipamento = findViewById(R.id.imgBtnEntdaEquip);
        contatoEmergencia = findViewById(R.id.imgBtnEmergencia);
        sairAplicativo = findViewById(R.id.imgBtnEncerrar);

        /*saidaEquipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, MainActivityOutAsset.class));
            }
        });*/

        entradaEquipamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TestClick.testClick(1000)) {
                    startActivity(new Intent(MainActivity.this, MainActivityReturnAsset.class));
                }
            }
        });

        contatoEmergencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TestClick.testClick(1000)) {
                    startActivity(new Intent(MainActivity.this, MainActivitySOSContact.class));
                }
            }
        });

        sairAplicativo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TestClick.testClick(1000)) {
                    finish();
                }
            }
        });

    }

    public void startOutActivity(View view) {

        if (TestClick.testClick(1000)) {
            Intent outActivity = new Intent(MainActivity.this, MainActivityOutAsset.class);
            startActivity(outActivity);
        }
    }

}
