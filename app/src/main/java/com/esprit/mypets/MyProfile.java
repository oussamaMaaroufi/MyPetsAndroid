package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,information;
    Button btnMyAnimals,aboutMe,photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        
        toolbar = findViewById(R.id.toolbar);
        imageProfile = findViewById(R.id.imageProfile);
        nameProfile = findViewById(R.id.nameProfile);
        information = findViewById(R.id.information);
        aboutMe = findViewById(R.id.aboutMe);
        btnMyAnimals = findViewById(R.id.btnMyAnimals);
        photos = findViewById(R.id.photos);

        nameProfile.setText(Vars.getUSER().getName());
        btnMyAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent =new Intent(MyProfile.this,RegisterActivity.class);
                    startActivity(intent);
            }
        });

        information.setText(Vars.getUSER().getEmail()+"\n");



        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
    }
}