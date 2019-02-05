package dialog.cursoandroid.com.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button botao;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botao = findViewById(R.id.botaoId);

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Criar alert dialog

                dialog = new AlertDialog.Builder(MainActivity.this);

                //configurar o título
                dialog.setTitle("Título do dialog");

                //configurar a mensagem
                dialog.setMessage("Mensagem de dialog");

                dialog.setCancelable(false);

                //Botão negativo, dialog
                dialog.setNegativeButton("Não",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                               Toast.makeText(MainActivity.this, "Pressionado botão Não", Toast.LENGTH_SHORT).show();
                            }
                        });

                //Botão positivo
                dialog.setPositiveButton("Sim",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MainActivity.this, "Pressionado botão Sim", Toast.LENGTH_SHORT).show();
                            }
                        });

                dialog.create();
                dialog.show();

            }
        });

    }
}
