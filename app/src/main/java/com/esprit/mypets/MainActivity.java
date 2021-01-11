package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceAdoption;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private Button joinUs;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    private IServiceAdoption iServiceAdoption;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPetsUser", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        iServiceAdoption = retrofitClient.create(IServiceAdoption.class);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        HomeActivity.getAllAnimals(iServiseAnimal,iServiceAdoption);

        

        joinUs = findViewById(R.id.joinUs);
        joinUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(pref.contains("id")){
                    User user = new User();
                    user.setType(pref.getString("type","Type"));
                    user.setId(pref.getString("id","id"));
                    user.setEmail(pref.getString("email","email"));
                    user.setName(pref.getString("name","name"));
                    Vars.setUSER(user);
                    Vars.setPhone(pref.getString("phone","phone"));
                    Vars.setAddress(pref.getString("Adresse","Adresse"));
                    Vars.setImage(pref.getString("Image","image"));
                    Toast.makeText(MainActivity.this,  pref.getString("id","tt"), Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();

                }else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });



    }

}