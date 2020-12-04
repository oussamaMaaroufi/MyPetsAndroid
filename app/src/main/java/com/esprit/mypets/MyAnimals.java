package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyAnimals extends AppCompatActivity {
    RecyclerView recyclerView;
    MyAdapterAnimal myAdapterAnimal;
    Button btnAddAnimal,btnAfficheMyAnimals;

    ArrayList<Animal> animals = new ArrayList<>();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animals);

        getAllAnimals();
        btnAfficheMyAnimals = findViewById(R.id.btnAfficheMyAnimals);
        btnAddAnimal = findViewById(R.id.AddAnimal);
        recyclerView = findViewById(R.id.recyclerviewMyAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        btnAfficheMyAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterAnimal = new MyAdapterAnimal(getApplicationContext(),  animals);

                recyclerView.setAdapter(myAdapterAnimal);
            }
        });
        btnAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyAnimals.this,AddAnimal.class);
                startActivity(intent);
            }
        });



    }

    public void getAllAnimals(){
        //    ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();
        User u = Vars.getUSER();

        Call<AnimalResponseList> call = iServiseAnimal.GetAnimalbyIdUser(u);

        call.enqueue(new Callback<AnimalResponseList>() {
            @Override
            public void onResponse(Call<AnimalResponseList> call, Response<AnimalResponseList> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MyAnimals.this, "Error "+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    animals = response.body().getAnimal();
                }

            }

            @Override
            public void onFailure(Call<AnimalResponseList> call, Throwable t) {

            }
        });
    }
}