package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entyityResponse.AnimalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Adoption extends AppCompatActivity {

    private Context myContext = this;
    private TextView race,type,name;
    private Button AddAdoption,RemoveAdoption,Delete;
    private Animal animal = new Animal();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    IServieceUser iServieceUser = retrofitClient.create(IServieceUser.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adoption);
        Intent intent =getIntent();
        animal.setId(intent.getStringExtra("id"));
        animal.setIdUser(intent.getStringExtra("IUser"));
        animal.setName(intent.getStringExtra("Name"));
        animal.setRace(intent.getStringExtra("Rece"));
        animal.setImage(intent.getStringExtra("image"));
        animal.setType(intent.getStringExtra("Type"));

        race= findViewById(R.id.adoptionRace);
        race.setText(animal.getRace());
        type = findViewById(R.id.AdoptionType);
        type.setText(animal.getType());
        name = findViewById(R.id.nameAnimalProfile);
        name.setText(animal.getName());

        AddAdoption = findViewById(R.id.addtoAdoption);
        RemoveAdoption = findViewById(R.id.removefromAdoption);
        Delete = findViewById(R.id.DeleleAnimalProfil);

        Delete.setOnClickListener( v -> {

                deleteAnimal(animal);

        });

        AddAdoption.setOnClickListener(v -> {

        });



    }
    public void deleteAnimal(Animal animal){
        Call<AnimalResponse> call  = iServiseAnimal.DeleteAnimal(animal);
        call.enqueue(new Callback<AnimalResponse>() {
            @Override
            public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                if (!response.isSuccessful()){

                    Toast.makeText(myContext, "Error ", Toast.LENGTH_SHORT).show();
                } else {
                    MyAnimals.getAllAnimals(iServiseAnimal);
                    Toast.makeText(myContext,  "Delete is Successful"  , Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<AnimalResponse> call, Throwable t) {

            }
        });
    }
}