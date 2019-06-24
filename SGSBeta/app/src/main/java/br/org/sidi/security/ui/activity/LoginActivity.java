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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Connection;
import br.org.sidi.security.utils.Tools;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPasswd;
    private Button btnLogin;
    private TextView txtPasswordReset;

    private FirebaseAuth auth;

    private static final int REQUEST_CODE_PERMISSIONS = 358;

    private String[] req_permission = new String[]{
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        checkPermission(req_permission);

        startUIComponents();

        startClickEvents();
    }

    private void startUIComponents() {
        editEmail        = findViewById(R.id.edt_email);
        editPasswd       = findViewById(R.id.edt_password);
        btnLogin         = findViewById(R.id.btn_Login);
        txtPasswordReset = findViewById(R.id.txt_resetPassword);
    }

    private void startClickEvents() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String passwd = editPasswd.getText().toString().trim();

                if ((email.isEmpty())||(passwd.isEmpty())){
                    //alert("E-mail ou senha invalidos!");
                    Tools.alertShort(LoginActivity.this, "E-mail ou senha invalidos!");
                }else {
                    loginInit(email, passwd);
                }
            }
        });

        txtPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetActivity.class));
            }
        });
    }

    private void loginInit(String email, String passwd) {
        auth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }else {
                            //alert("E-mail ou Senha incorretos!");
                            Tools.alertShort(LoginActivity.this, "E-mail ou Senha incorretos!");
                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Connection.getFirebaseAuth();
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

                    ActivityCompat.requestPermissions(LoginActivity.this, newPermissions, REQUEST_CODE_PERMISSIONS);
                }
            }

        }catch (Exception e){

            //alert("Não foi possível validar as permissões!");
            Tools.alertLong(LoginActivity.this, "Não foi possível validar as permissões!");
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
            builder.setMessage("Para a utilização do aplicativo, é necessário aceitar todas as permissões solicitadas!");

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

            //alert("Não foi possível enviar alerta de permissões!");
            Tools.alertLong(LoginActivity.this, "Não foi possível enviar alerta de permissões!");
            Log.e("SGSBeta", "Erro: " + e.getMessage());
        }

    }
}
