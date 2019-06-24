package myapp.com.example.pomba.focavendas.ui;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import myapp.com.example.pomba.focavendas.R;
import myapp.com.example.pomba.focavendas.data.Conexao;

public class ResetSenhaActivity extends AppCompatActivity {

    private EditText editEmail;
    private Button btnResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);

        inicializaComponentes();
        eventoClick();

    }

    private void eventoClick() {
        btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                if (email.isEmpty()){
                    alert("E-mail inválido!");
                }else{
                    resetSenha(email);
                }
            }
        });

    }

    private void resetSenha (String email){
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(ResetSenhaActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            alert("Um e-mail foi enviado para alterar sua senha!");
                            finish();
                        }else{
                            alert("E-mail fornecido não cadastrado!");
                        }
                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(ResetSenhaActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    private void inicializaComponentes() {
        editEmail = findViewById(R.id.edt_emailReset);
        btnResetSenha = findViewById(R.id.bt_resetSenha);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
