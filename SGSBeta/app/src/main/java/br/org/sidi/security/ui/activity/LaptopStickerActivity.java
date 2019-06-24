package br.org.sidi.security.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import br.org.sidi.security.R;

public class LaptopStickerActivity extends AppCompatActivity {

    private ImageView imgEmployee;
    private EditText stickerNum;
    private Button btnSearchNote;
    private TextView txtEmployeeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laptop_sticker);

        startUIComponents();

        startEventClicks();
    }

    private void startUIComponents() {
        imgEmployee     = findViewById(R.id.img_emp_picture);
        stickerNum      = findViewById(R.id.edt_sticker_code);
        txtEmployeeName = findViewById(R.id.lbl_emp_name);
        btnSearchNote   = findViewById(R.id.btn_Search_sticker);
    }

    /// PAREI AQUIIIIIII!!!!!!!!
    private void startEventClicks() {
        btnSearchNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String txtStkNum = stickerNum.getText().toString().trim();
                if(!txtStkNum.isEmpty()){

                }else{

                }
            }
        });
    }


}
