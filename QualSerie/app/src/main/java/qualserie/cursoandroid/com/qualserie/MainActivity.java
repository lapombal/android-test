package qualserie.cursoandroid.com.qualserie;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

public class MainActivity extends Activity {

    private SeekBar seekbar;
    private ImageView imagemExibicao;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = findViewById(R.id.seekBarId);
        imagemExibicao = findViewById(R.id.imagemExibicaoId);

        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int valorProgresso = progress;

                if (valorProgresso == 1){
                    imagemExibicao.setImageResource( R.drawable.pouco );
                }else if (valorProgresso == 2){
                    imagemExibicao.setImageResource( R.drawable.medio );
                }else if (valorProgresso == 3){
                    imagemExibicao.setImageResource( R.drawable.muito );
                }else if (valorProgresso == 4){
                    imagemExibicao.setImageResource( R.drawable.susto );
                    mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.grito_de_monstro);
                    tocarSom();
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void tocarSom(){
        if( mediaPlayer != null){
            mediaPlayer.start();
        }

    }

    @Override
    protected void onDestroy() {
        if( mediaPlayer != null){
            mediaPlayer.release();
            mediaPlayer = null;
        }

        super.onDestroy();
    }
}
