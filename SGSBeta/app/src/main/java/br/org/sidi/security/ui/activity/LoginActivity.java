package br.org.sidi.security.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Conection;

public class LoginActivity extends AppCompatActivity {

    private EditText editEmail, editPasswd;
    private Button btnLogin;
    private TextView txtPasswordReset;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        startItem();

        clickEvent();
    }

    private void startItem() {
        editEmail        = findViewById(R.id.edt_email);
        editPasswd       = findViewById(R.id.edt_password);
        btnLogin         = findViewById(R.id.btn_Login);
        txtPasswordReset = findViewById(R.id.txt_resetPassword);
    }

    private void clickEvent() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String passwd = editPasswd.getText().toString().trim();

                if ((email.isEmpty())||(passwd.isEmpty())){
                    alert("E-mail ou senha invalidos!");
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

    private void alert(String s) {
        Toast.makeText(LoginActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    private void loginInit(String email, String passwd) {
        auth.signInWithEmailAndPassword(email, passwd)
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));

                        }else {
                            alert("E-mail ou Senha incorretos!");
                        }

                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conection.getFirebaseAuth();
    }
}
