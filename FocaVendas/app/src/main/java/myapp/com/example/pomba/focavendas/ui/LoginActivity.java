package myapp.com.example.pomba.focavendas.ui;

import android.Manifest;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

import myapp.com.example.pomba.focavendas.R;
import myapp.com.example.pomba.focavendas.data.Conexao;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;
    private Button btnEntrar;
    private TextView txtResetSenha;

    private FirebaseAuth auth;

    private static final int REQUEST_CODE_PERMISSOES = 358;

    private String[] permissoes = new String[]{
            Manifest.permission.INTERNET
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        inicializaComponentes();

        eventoClicks();
    }

    private void eventoClicks() {
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                if ((email.isEmpty())||(senha.isEmpty())){
                    alert("E-mail ou senha invalidos!");
                }else{
                    login(email, senha);
                }
            }
        });

        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (LoginActivity.this, ResetSenhaActivity.class);
                startActivity(i);
            }
        });
    }

    private void login(String email, String senha) {
        auth.signInWithEmailAndPassword(email, senha)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Intent i = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(i);
                        }else{
                            alert("E-mail ou Senha incorretos!");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        editEmail = findViewById(R.id.edt_email);
        editSenha = findViewById(R.id.edt_password);
        btnEntrar = findViewById(R.id.bt_entrar);
        txtResetSenha = findViewById(R.id.txt_resetPassword);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
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
                ActivityCompat.requestPermissions(LoginActivity.this, novasPermissoes, REQUEST_CODE_PERMISSOES);
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
