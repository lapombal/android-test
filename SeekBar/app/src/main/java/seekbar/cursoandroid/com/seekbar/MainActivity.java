package seekbar.cursoandroid.com.seekbar;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private SeekBar seekbar;
    private TextView textoExibicao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBarId);
        textoExibicao = findViewById(R.id.textoExibicaoId);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //textoExibicao.setText("Progresso..." + progress);

                //Código para acompanhar o status do andamento da Seekbar.
                textoExibicao.setText("Progresso..." + progress + " / " + seekbar.getMax() );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seekbar pressionado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(MainActivity.this, "Seekbar não está pressionado", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
