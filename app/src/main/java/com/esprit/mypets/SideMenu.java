package com.esprit.mypets;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.google.android.material.navigation.NavigationView;

import retrofit2.Retrofit;

public class SideMenu extends AppCompatActivity {

    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    Button home,profile,lost,found,veterinaire,abri,logout;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseVeterinaire iServiseVeterinaire =retrofitClient.create(IServiseVeterinaire.class);
    IServiceAbri iServiceAbri = retrofitClient.create(IServiceAbri.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        getActionBar().setDisplayHomeAsUpEnabled( false );
    //    getActionBar().setDisplayShowTitleEnabled( false );

        

        setContentView(R.layout.activity_side_menu);
        home = findViewById(R.id.MenuHome);
        profile = findViewById(R.id.MenuProfile);
        lost = findViewById(R.id.MenuLost);
        veterinaire = findViewById(R.id.MenuVeterinaire);
        abri = findViewById(R.id.MenuAbri);
        logout = findViewById(R.id.MenuLogout);
        VeterinaireList.getListVeterinaires(iServiseVeterinaire);
        AbriList.getListAbris(iServiceAbri);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,MyProfile.class);
                startActivity(intent);
            }
        });
        lost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,LostAndFound.class);
                startActivity(intent);
            }
        });

        veterinaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,VeterinaireList.class);
                startActivity(intent);
            }
        });
        abri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,AbriList.class);
                startActivity(intent);
            }
        });

    }
}