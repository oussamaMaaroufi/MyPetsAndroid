package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.esprit.mypets.Retrofit.IServiceLAF;
import com.esprit.mypets.Retrofit.RetrofitClient;

import retrofit2.Retrofit;

public class LostAndFound extends AppCompatActivity {

    Button addLAF,Lost,Found;
    ImageButton btnMenu;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceLAF iServiceLAF =retrofitClient.create(IServiceLAF.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_and_found);
        addLAF = findViewById(R.id.AddPost);
        Lost = findViewById(R.id.btnLost);
        Found = findViewById(R.id.btnfound);

        LostFragment.getAllLost(iServiceLAF);
        FoundFragment.getAllFound(iServiceLAF);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }
        btnMenu = findViewById(R.id.btnmenu4);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LostAndFound.this,SideMenu.class);
                startActivity(intent);
            }
        });
        addLAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentsContainer,new AddLAFFragment())
                        .commit();
            }
        });

        Lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentsContainer,new LostFragment())
                        .commit();

            }
        });

        Found.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentsContainer,new FoundFragment())
                        .commit();

            }
        });


    }


}