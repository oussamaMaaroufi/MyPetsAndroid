package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileAnimal extends AppCompatActivity {

    private Button callphone,Photo;
    private TextView nameAnimal,owner,Address,type,Rase;
    private ImageView imageView;
    private  String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);
        /*
        callphone = findViewById(R.id.btnAnimalCall);

        nameAnimal = findViewById(R.id.nameAnimalProfileAdop);
        owner = findViewById(R.id.owner);
        Address = findViewById(R.id.addressProfileAdop);
        type = findViewById(R.id.TypeAnimalProfile);
        Rase = findViewById(R.id.RaceAnimalProfile);
        Photo = findViewById(R.id.btnphotosAnimal);

*/


    }
}