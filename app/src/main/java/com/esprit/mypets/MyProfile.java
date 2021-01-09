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

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.User;

import retrofit2.Retrofit;

public class MyProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,address,Email,phone,facebook;
    Button btnMyAnimals,aboutMe,photos;
    ImageButton btnMenu;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }
        User user =(User) Vars.getUSER();
        Toast.makeText(MyProfile.this,  user.toString(), Toast.LENGTH_LONG).show();
        imageProfile = findViewById(R.id.imageProfile);
        nameProfile = findViewById(R.id.nameProfile);
        btnMyAnimals = findViewById(R.id.btnMyAnimals);
        address = findViewById(R.id.addressProfile);
        Email = findViewById(R.id.emailProfile);
        phone = findViewById(R.id.phoneProfile);
        facebook = findViewById(R.id.facebookProfile);
      //  photos = findViewById(R.id.photos);

        nameProfile.setText(user.getName());
        Email.setText(user.getEmail());
        address.setText(Vars.getAddress());
        phone.setText(Vars.getPhone());
        MyAnimals.getAllAnimal(iServiseAnimal);

            if (user.getType().equals("Volontaires")) {


            } else if (user.getType().equals("Abris")) {

            } else {


            }




        btnMenu = findViewById(R.id.btnmenu2);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyProfile.this,SideMenu.class);
                startActivity(intent);
            }
        });

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