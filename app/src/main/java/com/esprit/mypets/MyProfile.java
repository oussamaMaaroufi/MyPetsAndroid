package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,information;
    Button btnMyAnimals,aboutMe,photos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        imageProfile = findViewById(R.id.imageProfile);
        nameProfile = findViewById(R.id.nameProfile);
     //   information = findViewById(R.id.information);
     //   aboutMe = findViewById(R.id.aboutMe);
        btnMyAnimals = findViewById(R.id.btnMyAnimals);
      //  photos = findViewById(R.id.photos);

        nameProfile.setText(Vars.getUSER().getName());

        btnMyAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =new Intent(MyProfile.this,MyAnimals.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(MyProfile.this,  e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });




   //     information.setText(Vars.getUSER().getEmail()+"\n");




    }
}