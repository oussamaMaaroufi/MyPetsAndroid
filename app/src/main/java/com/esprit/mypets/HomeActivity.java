package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
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
    RecyclerView recyclerView;

    MyAdapterAnimal myAdapterAnimal;

    ArrayList<Animal> animals = new ArrayList<>();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);

   // final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", 0); // 0 - for private mode
    //final SharedPreferences.Editor editor = pref.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        recyclerView = findViewById(R.id.recyclerViewHome);

        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,true));
        animals.add(new Animal("oussama","3arbi","hhhh"));
        myAdapterAnimal = new MyAdapterAnimal(this, animals);

        recyclerView.setAdapter(myAdapterAnimal);

        getAllAnimals();


/*
        Name = findViewById(R.id.homeName);
        email = findViewById(R.id.homeEmail);
        Intent intent = getIntent();
        Name.setText(Vars.getUSER().getName());
        email.setText(Vars.getUSER().getEmail());
*/

    }

    public ArrayList<Animal> getAllAnimals(){
         ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();

        Call<ArrayList<Animal>> call = iServiseAnimal.GetAllAnimal();

        call.enqueue(new Callback<ArrayList<Animal>>() {
            @Override
            public void onResponse(Call<ArrayList<Animal>> call, Response<ArrayList<Animal>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(HomeActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                } else {
                 //   ArrayList<Animal> animalResponse = response.body();
                    Toast.makeText(HomeActivity.this,  response.body().toString(), Toast.LENGTH_SHORT).show();
                //for (Animal a : animalResponse){

                     //   Toast.makeText(HomeActivity.this,  a.toString(), Toast.LENGTH_SHORT).show();
                //    }



                   //   AnimalResponse animalResponse1 =new AnimalResponse();
                   // animalResponse1.setAnimal(animalResponse.getAnimal().get(0));
                     // Toast.makeText(HomeActivity.this,  animalResponse.ge, Toast.LENGTH_SHORT).show();
                     //Toast.makeText(HomeActivity.this,  " "+animalResponse1.getAnimal().getName(), Toast.LENGTH_SHORT).show();



                }

            }

            @Override
            public void onFailure(Call<ArrayList<Animal>> call, Throwable t) {

            }
        });
        return list;
    }



}