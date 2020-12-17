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

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.User;

import retrofit2.Retrofit;

public class AbriProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,address,Email,phone,facebook;
    Button aboutMe,btnAnimals;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abri_profile);
        User user =(User) Vars.getUSER();
        Toast.makeText(AbriProfile.this,  user.toString(), Toast.LENGTH_LONG).show();
        imageProfile = findViewById(R.id.imageProfileAbri);
        nameProfile = findViewById(R.id.nameProfileAbri);
        aboutMe = findViewById(R.id.aboutMeAbri);
        address = findViewById(R.id.addressProfileAbri);
        Email = findViewById(R.id.emailProfileAbri);
        phone = findViewById(R.id.phoneProfileAbri);
        facebook = findViewById(R.id.facebookProfileAbri);
        btnAnimals = findViewById(R.id.btnMyAnimalsAbri);

        nameProfile.setText(user.getName());
        Email.setText(user.getEmail());
        address.setText(Vars.getAddress());
        phone.setText(Vars.getPhone());
        //MyAnimals.getAllAnimals(iServiseAnimal);

        aboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbriProfile.this, AbriProfile.class);
                startActivity(intent);
            }
        });

        btnAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =new Intent(AbriProfile.this,MyAnimals.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(AbriProfile.this,  e.getMessage(), Toast.LENGTH_LONG).show();
                }


            }
        });

    }
}