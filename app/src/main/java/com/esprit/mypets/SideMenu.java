package com.esprit.mypets;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.Toast;
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

    Button home,profile,lost,found,veterinaire,abri,logout,myanimals;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseVeterinaire iServiseVeterinaire =retrofitClient.create(IServiseVeterinaire.class);
    IServiceAbri iServiceAbri = retrofitClient.create(IServiceAbri.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //getActionBar().setDisplayHomeAsUpEnabled( false );
    //    getActionBar().setDisplayShowTitleEnabled( false );
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPetsUser", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }

        setContentView(R.layout.activity_side_menu);
        home = findViewById(R.id.MenuHome);
        profile = findViewById(R.id.MenuProfile);
        lost = findViewById(R.id.MenuLost);
        veterinaire = findViewById(R.id.MenuVeterinaire);
        abri = findViewById(R.id.MenuAbri);
        logout = findViewById(R.id.MenuLogout);
        myanimals = findViewById(R.id.MenuMyAnimal);
        VeterinaireList.getListVeterinaires(iServiseVeterinaire);
        AbriList.getListAbris(iServiceAbri);



        logout.setOnClickListener(v -> {
            editor.clear();
            editor.commit();
            Intent intent = new Intent(SideMenu.this, MainActivity.class);
            startActivity(intent);
        });

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

        logout.setOnClickListener(v -> {
            editor.clear(); //editor.remove("name");
            editor.commit();
            Toast.makeText(SideMenu.this, "Logout", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SideMenu.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
        myanimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SideMenu.this,MyAnimals.class);
                startActivity(intent);
            }
        });

    }
}