package br.org.sidi.androidfirebasestorage;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_CODE = 1000; // Any number you want
    private Button btnUpload;
    private ImageView imageView;
    AlertDialog dialog;

    StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //init
        dialog = new SpotsDialog.Builder().setContext(this).build();
        btnUpload = findViewById(R.id.btn_upload);
        imageView = findViewById(R.id.image_view);

        storageReference = FirebaseStorage.getInstance().getReference().child("visitor").child("imagem_upload"); // Create name for file

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //We will use INTENT to pick image

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select picture"), PICK_IMAGE_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_CODE){
            //Show dialog
            dialog.show();

            UploadTask uploadTask = storageReference.putFile(data.getData());

            //create task

            Task<Uri> task = uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                @Override
                public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                    if (!task.isSuccessful()){

                        Toast.makeText(MainActivity.this,"Failed", Toast.LENGTH_SHORT).show();
                    }

                    return storageReference.getDownloadUrl();
                }
            }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    if (task.isSuccessful()){

                        //String url = task.getResult().toString().substring(0,task.getResult().toString().indexOf("&token"));
                        String url = task.getResult().toString();

                        Log.d("DIRECTLINK", url);
                        Picasso.get().load(url).into(imageView);

                        dialog.dismiss();
                    }
                }
            });
        }

    }
}
