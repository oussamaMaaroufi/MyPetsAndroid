package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.Veterinaires;

import retrofit2.Retrofit;

public class AbriProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,address,Email,phone,facebook;
    Button aboutMe,btnAnimals;
    ImageButton btnMenu;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abri_profile);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        User user =(User) Vars.getUSER();
        Toast.makeText(AbriProfile.this,  user.toString(), Toast.LENGTH_LONG).show();
        imageProfile = findViewById(R.id.imageProfileAbri);
        nameProfile = findViewById(R.id.nameProfileAbri);
        address = findViewById(R.id.addressProfileAbri);
        Email = findViewById(R.id.emailProfileAbri);
        phone = findViewById(R.id.phoneProfileAbri);
        facebook = findViewById(R.id.facebookProfileAbri);
        btnAnimals = findViewById(R.id.btnMyAnimalsAbri);

        nameProfile.setText(user.getName());
        Email.setText(user.getEmail());
        address.setText(Vars.getAddress());
        phone.setText(Vars.getPhone());
        //MyAnimals.getAllAbriAnimals(iServiseAnimal);

        Intent intent =getIntent();

        Abris v = new Abris();

        //  v.setId(intent.getStringExtra("id"));
        v.setIdUser(intent.getStringExtra("IUser"));
        v.setName(intent.getStringExtra("Name"));
        v.setAdresse(intent.getStringExtra("Adresse"));
        v.setImage(intent.getStringExtra("image"));
        v.setTelephon(intent.getStringExtra("phone"));

        nameProfile.setText(v.getName());
        address.setText(v.getAdresse());
        phone.setText(v.getTelephon());
        imageProfile.setImageResource(R.mipmap.ic_logo_foreground);

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
        btnMenu = findViewById(R.id.btnmenu8);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbriProfile.this,SideMenu.class);
                startActivity(intent);
            }
        });

    }
}