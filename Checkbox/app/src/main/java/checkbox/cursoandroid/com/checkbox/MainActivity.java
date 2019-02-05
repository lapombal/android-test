package checkbox.cursoandroid.com.checkbox;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

    private CheckBox checkBoxCao;
    private CheckBox checkBoxGato;
    private CheckBox checkBoxPapagaio;

    private Button botaoMostrar;
    private TextView textoExibicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkBoxCao = findViewById(R.id.caoId);
        checkBoxGato = findViewById(R.id.gatoId);
        checkBoxPapagaio = findViewById(R.id.papagaioId);

        textoExibicao = findViewById(R.id.mostrarId);
        botaoMostrar = findViewById(R.id.botaoMostrarId);

        botaoMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String itensSelecionados = "";

                itensSelecionados += "a: Item: " + checkBoxCao.getText() + " - Status: " + checkBoxCao.isChecked() + "\n";
                itensSelecionados += "b: Item: " + checkBoxGato.getText() + " - Status: " + checkBoxGato.isChecked() + "\n";
                itensSelecionados += "c: Item: " + checkBoxPapagaio.getText() + " - Status: " + checkBoxPapagaio.isChecked() + "\n";

                textoExibicao.setText(itensSelecionados);
            }
        });

    }
}
