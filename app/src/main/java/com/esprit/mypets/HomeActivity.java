package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;
import com.esprit.mypets.entyityResponse.UserResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeActivity extends AppCompatActivity {
    private TextView Name,email;
    private Context myContext = this;
    RecyclerView recyclerView;
    Button btnAffich ,btnMyProfil,btnLAF,btnMenu;
    MyAdapterAnimal myAdapterAnimal;

    public static ArrayList<Animal> animals = new ArrayList<Animal>();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLAF= findViewById(R.id.btnLostAndfound);


        try {
            User u =(User) Vars.getUSER();
            Toast.makeText(HomeActivity.this, u.toString(), Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(HomeActivity.this,  e.getMessage(), Toast.LENGTH_LONG).show();
        }

        Call<AnimalResponseList> call = iServiseAnimal.GetAllAnimal();

        call.enqueue(new Callback<AnimalResponseList>() {
            @Override
            public void onResponse(Call<AnimalResponseList> call, Response<AnimalResponseList> response) {
                if (!response.isSuccessful()){
                    //  Toast.makeText(HomeActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                } else {
                       Toast.makeText(HomeActivity.this,  response.body().toString()    , Toast.LENGTH_SHORT).show();
                    animals = response.body().getAnimal();

                    myAdapterAnimal = new MyAdapterAnimal(myContext, HomeActivity.animals);
                    recyclerView.setAdapter(myAdapterAnimal);
                }

            }

            @Override
            public void onFailure(Call<AnimalResponseList> call, Throwable t) {

            }
        });

        btnAffich = findViewById(R.id.btnAffiche);
        btnMyProfil = findViewById(R.id.Myprofil);
        recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
//        if(!HomeActivity.animals.isEmpty()) {
      //  myAdapterAnimal = new MyAdapterAnimal(getApplicationContext(), HomeActivity.animals);
 //        }
     //   recyclerView.setAdapter(myAdapterAnimal);

        btnMyProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent =new Intent(HomeActivity.this,MyProfile.class);
                    startActivity(intent);
                }catch (Exception e){
                    Toast.makeText(HomeActivity.this,  e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });




        btnAffich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterAnimal = new MyAdapterAnimal(getApplicationContext(), HomeActivity.animals);
           //     Toast.makeText(HomeActivity.this, Vars.USER.getName(), Toast.LENGTH_LONG).show();

                recyclerView.setAdapter(myAdapterAnimal);
            }
        });


        btnLAF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(HomeActivity.this,LostAndFound.class);
                startActivity(intent);

            }
        });

        btnMenu= findViewById(R.id.buttonMenu);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,SideMenu.class);
                startActivity(intent);
            }
        });



/*
        Name = findViewById(R.id.homeName);
        email = findViewById(R.id.homeEmail);
        Intent intent = getIntent();
        Name.setText(Vars.getUSER().getName());
        email.setText(Vars.getUSER().getEmail());
*/

    }

    public static void getAllAnimals(IServiseAnimal iServiseAnimal){
        //    ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();

        Call<AnimalResponseList> call = iServiseAnimal.GetAllAnimal();

        call.enqueue(new Callback<AnimalResponseList>() {
            @Override
            public void onResponse(Call<AnimalResponseList> call, Response<AnimalResponseList> response) {
                if (!response.isSuccessful()){
                  //  Toast.makeText(HomeActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                } else {
                 //   Toast.makeText(HomeActivity.this,  response.body().getAnimal().get(0).toString()     , Toast.LENGTH_SHORT).show();
                    animals = response.body().getAnimal();


                }

            }

            @Override
            public void onFailure(Call<AnimalResponseList> call, Throwable t) {

            }
        });
    }


/*
    public void addElement() {
        System.out.println("Opening...");
        synchronized (list) {

            // add an element and notify all that an element exists
            getAllAnimals();
            System.out.println("add");

            getAllAnimals().notifyAll();
            System.out.println("notifyAll called!");
        }
        System.out.println("Closing...");
    }
*/


}