package com.security.br.org.sidi.sgstrial;

import android.provider.ContactsContract;
import android.renderscript.Script;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewDebug;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DatabaseReference transferenciaSgsReference = iniciaEstruturaBase();

        transferenciaSgsReference.setValue("Teste");

    }

    private DatabaseReference iniciaEstruturaBase(){

        DatabaseReference sgsfireReference = FirebaseDatabase.getInstance().getReference();

        Calendar calendar = Calendar.getInstance();

        Integer dia = calendar.get(Calendar.DAY_OF_MONTH);
        Integer ano = calendar.get(Calendar.YEAR);
        Integer mes = calendar.get(Calendar.MONTH);

        DatabaseReference transferenciaReference = sgsfireReference.child("transferencia").child( ano.toString() ).child( mes.toString() ).child( dia.toString() );

        return transferenciaReference;
    }
}
