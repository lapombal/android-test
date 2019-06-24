package br.org.sidi.security.ui.activity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import br.org.sidi.security.R;
import br.org.sidi.security.data.Connection;

public class PasswordResetActivity extends AppCompatActivity {

    private EditText editEmail;
    private Button btnPasswordReset;
    private android.app.AlertDialog.Builder dialog;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset);

        startItem();

        clickEvent();
    }

    private void startItem() {

        editEmail        = findViewById(R.id.edt_emailReset);
        btnPasswordReset = findViewById(R.id.btn_pwdReset);
    }

    private void clickEvent() {
        btnPasswordReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();

                if (email.isEmpty()){
                    alert("E-mail inválido!");
                }else {
                    passwordReset(email);
                }
            }
        });

    }

    private void passwordReset(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(PasswordResetActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            dialogReturn("Alteração de Senha","Você receberá um e-mail com o link para mudança da senha!");
                        }else{
                            dialog("Alteração de Senha", "E-mail fornecido não cadastrado!");
                        }
                    }
                });

    }

    private void dialogReturn(String t, String m){
        dialog = new android.app.AlertDialog.Builder(PasswordResetActivity.this);

        dialog.setTitle(t);
        dialog.setMessage(m);

        dialog.setCancelable(false);

        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        dialog.create();
        dialog.show();
    }


    private void dialog(String t, String m){
        dialog = new android.app.AlertDialog.Builder(PasswordResetActivity.this);

        dialog.setTitle(t);
        dialog.setMessage(m);

        dialog.setCancelable(false);

        dialog.setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialog.create();
        dialog.show();
    }

    private void alert(String s) {
        Toast.makeText(PasswordResetActivity.this,s,Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        auth = Connection.getFirebaseAuth();
    }
}
