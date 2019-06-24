package br.org.sidi.security.ui.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Conection;
import br.org.sidi.security.utils.Tools;

/********************************************
 *
 * @author l.pombal
 *
 */

public class MainActivity extends AppCompatActivity {

    private CardView card_co_asset, card_ci_asset, card_visitor, card_reg_visitor, card_sticker, card_exit;

    private static final int REQUEST_CODE_PERMISSIONS = 358;

    private String[] req_permission = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
    };

    private FirebaseAuth mAuth;

    private Tools mjonir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkPermission(req_permission);

        verifyConnection();

        startItem();

        execCards();
    }

    //Método de Conexão com Firebase Authenticator
    private void verifyConnection() {

        mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            mjonir.DialogMessageLong(MainActivity.this,"Bem vindo de volta " + user.getEmail() + "!");
        }else{
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    //Métodos de Interface
    private void startItem() {

        card_co_asset    = findViewById(R.id.cd_co_asset);
        card_ci_asset    = findViewById(R.id.cd_ci_asset);
        card_visitor     = findViewById(R.id.cd_visitor);
        card_reg_visitor = findViewById(R.id.cd_new_visitor);
        card_sticker     = findViewById(R.id.cd_stk_lap);
        card_exit        = findViewById(R.id.cd_exit);
    }

    private void execCards() {

        card_co_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               try{

                   if (Tools.testClick(1000)){
                       startActivity(new Intent(MainActivity.this, AssetOutActivity.class));
                   }

               }catch (Exception e){

                   mjonir.DialogMessageShort(MainActivity.this, "Não foi possível validar a interface!");
                   Log.e("SGSBeta", "Erro: " + e.getMessage());
               }

            }
        });

        card_ci_asset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if (Tools.testClick(1000)){
                        startActivity(new Intent(MainActivity.this, AssetReturnActivity.class));
                    }

                }catch (Exception e){

                    mjonir.DialogMessageShort(MainActivity.this, "Não foi possível validar a interface!");
                    Log.e("SGSBeta", "Erro: " + e.getMessage());

                }

            }
        });

        card_visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if (Tools.testClick(1000)){
                        startActivity(new Intent(MainActivity.this, VisitorSearchActivity.class));
                    }

                }catch (Exception e){

                    mjonir.DialogMessageShort(MainActivity.this, "Não foi possível validar a interface!");
                    Log.e("SGSBeta", "Erro: " + e.getMessage());

                }

            }
        });

        card_reg_visitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if (Tools.testClick(1000)){
                        startActivity(new Intent(MainActivity.this, RegisterVisitorActivity.class));
                    }

                }catch (Exception e){

                    mjonir.DialogMessageShort(MainActivity.this, "Não foi possível validar a interface!");
                    Log.e("SGSBeta", "Erro: " + e.getMessage());

                }

            }
        });

        card_sticker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{

                    if (Tools.testClick(1000)){
                        startActivity(new Intent(MainActivity.this, LaptopStickerActivity.class));
                    }

                }catch (Exception e){

                    mjonir.DialogMessageShort(MainActivity.this, "Não foi possível validar a interface!");
                    Log.e("SGSBeta", "Erro: " + e.getMessage());

                }

            }
        });

        card_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Conection.logout(mAuth);
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                finish();
            }
        });

    }

    // Métodos de Validação de Permissões

    private void checkPermission (String[] perm){

        try{
            if (Build.VERSION.SDK_INT >= 23){
                List<String> listPerm = new ArrayList<>();

                for (String p: perm){
                    Boolean checkPerm = ContextCompat.checkSelfPermission(this, p) == PackageManager.PERMISSION_GRANTED;
                    if (!checkPerm){
                        listPerm.add(p);
                    }
                }

                if (!listPerm.isEmpty()){
                    String[] newPermissions = new String[listPerm.size()];
                    listPerm.toArray(newPermissions);

                    ActivityCompat.requestPermissions(MainActivity.this, newPermissions, REQUEST_CODE_PERMISSIONS);
                }
            }

        }catch (Exception e){

            mjonir.DialogMessageLong(MainActivity.this, "Não foi possível validar as permissões!");
            Log.e("SGSBeta", "Erro: " + e.getMessage());
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int res: grantResults){
            if(res == PackageManager.PERMISSION_DENIED){
                alertCheckPermission();
            }
        }
    }

    private void alertCheckPermission() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        try{
            builder.setTitle("Permissões Negadas");
            builder.setMessage("Para utilizar o Aplicativo, é necessário aceitar todas as permissões!");

            builder.setPositiveButton("Tentar Novamente", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    checkPermission(req_permission);
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

        }catch (Exception e){

            mjonir.DialogMessageLong(MainActivity.this, "Não foi possível enviar alerta de permissões!");
            Log.e("SGSBeta", "Erro: " + e.getMessage());
        }

    }
}

