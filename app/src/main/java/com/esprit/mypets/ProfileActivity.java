package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private EditText phone,addres;
    private Button save,btnuplode;
    private TextView messageText;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        btnuplode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                btnuplode = (Button)findViewById(R.id.uplodeimage);
                messageText  = (TextView)findViewById(R.id.messageText);
                dialog = ProgressDialog.show(ProfileActivity.this, "", "Uploading file...", true);

                new Thread(new Runnable() {
                    public void run() {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                messageText.setText("uploading started.....");
                            }
                        });

                   //     uploadFile(uploadFilePath + "" + uploadFileName);

                    }
                }).start();
            }
        });
            }


}