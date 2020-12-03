package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,information;
    Button posts,aboutMe,photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        
        toolbar = findViewById(R.id.toolbar);
        imageProfile = findViewById(R.id.imageProfile);
        nameProfile = findViewById(R.id.nameProfile);
        information = findViewById(R.id.information);
        aboutMe = findViewById(R.id.aboutMe);
        posts = findViewById(R.id.posts);
        photos = findViewById(R.id.photos);



        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
    }
}