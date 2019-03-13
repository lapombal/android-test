package sqlite.cursoandroid.com.sqlite;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            SQLiteDatabase bancodeDados = openOrCreateDatabase("app", MODE_PRIVATE, null);

            //tabela
            bancodeDados.execSQL("CREATE TABLE IF NOT EXISTS pessoas(nome VARCHAR, idade INT(3) ) ");

            //Inserir dados na tabela
            bancodeDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Marcos', 30)");
            bancodeDados.execSQL("INSERT INTO pessoas (nome, idade) VALUES ('Ana', 20)");

            Cursor cursor = bancodeDados.rawQuery("SELECT * FROM pessoas", null);

            int indiceColunaNome = cursor.getColumnIndex("nome");
            int indiceColunaIdade = cursor.getColumnIndex("idade");

            cursor.moveToFirst();

            while (cursor != null){
                Log.i ("RESULTADO - nome: ", cursor.getString( indiceColunaNome ));
                Log.i ("RESULTADO - idade: ", cursor.getString( indiceColunaIdade ));
                cursor.moveToNext();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
