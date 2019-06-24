package com.security.br.org.sidi.sgstrial;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

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

    private static final int REQUEST_CODE_PERMISSOES = 358;

    private String[] permissoes = new String[]{
            Manifest.permission.CAMERA,
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        validaPermissoes(permissoes);
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

    // Métodos de Permissões
    private void validaPermissoes (String[] permissoes){

        if (Build.VERSION.SDK_INT >= 23){
            List<String> listaPermissoes = new ArrayList<>();

            for (String permissao: permissoes){
                Boolean validaPermissao = ContextCompat.checkSelfPermission(this, permissao) == PackageManager.PERMISSION_GRANTED;
                if (!validaPermissao){
                    listaPermissoes.add(permissao);
                }
            }

            if (!listaPermissoes.isEmpty()){
                String[] novasPermissoes = new String[listaPermissoes.size()];
                listaPermissoes.toArray(novasPermissoes);
                ActivityCompat.requestPermissions(MainActivity.this, novasPermissoes, REQUEST_CODE_PERMISSOES);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int resultado: grantResults){
            if(resultado == PackageManager.PERMISSION_DENIED){
                alertaValidaPermissao();
            }
        }
    }

    private void alertaValidaPermissao() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar esse APP, é necessário aceitar todas as permissões");

        builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                validaPermissoes(permissoes);
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
