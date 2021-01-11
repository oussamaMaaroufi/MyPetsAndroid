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

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.Veterinaires;

import retrofit2.Retrofit;

public class VeterinaireProfile extends AppCompatActivity {
    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,address,Email,phone,facebook;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseVeterinaire iServiseVeterinaire =retrofitClient.create(IServiseVeterinaire.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaire_profile);
        User user =(User) Vars.getUSER();
        Toast.makeText(VeterinaireProfile.this,  user.toString(), Toast.LENGTH_LONG).show();
        imageProfile = findViewById(R.id.imageProfileVeto);
        nameProfile = findViewById(R.id.nameProfileVeto);




        address = findViewById(R.id.addressProfileVeto);
        Email = findViewById(R.id.emailProfileVeto);
        phone = findViewById(R.id.phoneProfileVeto);
        facebook = findViewById(R.id.facebookProfileVeto);

        nameProfile.setText(user.getName());
        Email.setText(user.getEmail());
        address.setText(Vars.getAddress());
        phone.setText(Vars.getPhone());

        Intent intent =getIntent();

        Veterinaires v = new Veterinaires();

      //  v.setId(intent.getStringExtra("id"));
        v.setIdUser(intent.getStringExtra("IUser"));
        v.setName(intent.getStringExtra("Name"));
        v.setAdresse(intent.getStringExtra("address"));
        v.setImage(intent.getStringExtra("image"));
        v.setTelephon(intent.getStringExtra("phone"));

        nameProfile.setText(v.getName());
        address.setText(v.getAdresse());
        phone.setText(v.getTelephon());
        imageProfile.setImageResource(R.mipmap.ic_logo_foreground);



    }
}