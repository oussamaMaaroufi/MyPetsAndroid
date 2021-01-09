package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileAnimal extends AppCompatActivity {

    private Button callphone,Photo;
    private TextView nameAnimal,owner,Address,type,Rase;
    private ImageView imageView;
    private  String userId;
    ImageButton btnMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }

        callphone = findViewById(R.id.btnAnimalCall);

        nameAnimal = findViewById(R.id.nameAnimalProfileAdop);
        owner = findViewById(R.id.owner);
        Address = findViewById(R.id.addressProfileAdop);
        type = findViewById(R.id.TypeAnimalProfile);
        Rase = findViewById(R.id.RaceAnimalProfile);
        Photo = findViewById(R.id.btnphotosAnimal);

        btnMenu = findViewById(R.id.btnmenu3);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAnimal.this,SideMenu.class);
                startActivity(intent);
            }
        });


    }
}