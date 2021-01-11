package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.esprit.mypets.Retrofit.MyAdapterAbri;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AbriResponse;
import com.esprit.mypets.entyityResponse.AbriResponseList;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AbriProfile extends AppCompatActivity {

    Toolbar toolbar;
    ImageView imageProfile;
    TextView nameProfile,address,Email,phone,facebook;
    Button btnAnimals;
    ImageButton btnMenu;
    RecyclerView recyclerView;
    MyAdapterAbri adapterAbri;
    public static ArrayList<Abris> abrises = new ArrayList<Abris>();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceAbri iServiceAbri =retrofitClient.create(IServiceAbri.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_abri_profile);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }


        imageProfile = findViewById(R.id.imageProfileAbri);
        nameProfile = findViewById(R.id.nameProfileAbri);

        address = findViewById(R.id.addressProfileAbri);
        Email = findViewById(R.id.emailProfileAbri);
        phone = findViewById(R.id.phoneProfileAbri);
        facebook = findViewById(R.id.facebookProfileAbri);
        btnAnimals = findViewById(R.id.btnMyAnimalsAbri);

   /*     nameProfile.setText(user.getName());
        Email.setText(user.getEmail());
        address.setText(Vars.getAddress());
        phone.setText(Vars.getPhone());*/
        //MyAnimals.getAllAbriAnimals(iServiseAnimal);



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