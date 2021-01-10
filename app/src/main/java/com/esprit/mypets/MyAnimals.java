package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.MyAdapterMyAnimal;
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
    MyAdapterMyAnimal myAdapterAnimal;
    Button btnAddAnimal,btnAfficheMyAnimals;
    ImageButton btnMenu;
    public static ArrayList<Animal> animal = new ArrayList<>();
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_animals);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }
      //  getAllAnimals(iServiseAnimal);
        btnAfficheMyAnimals = findViewById(R.id.btnAfficheMyAnimals);
        btnAddAnimal = findViewById(R.id.AddAnimal);
        recyclerView = findViewById(R.id.recyclerviewMyAnimals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        myAdapterAnimal = new MyAdapterMyAnimal(this, animal);
        recyclerView.setAdapter(myAdapterAnimal);

        btnAfficheMyAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            //    Toast.makeText(MyAnimals.this, animals.get(0).toString(), Toast.LENGTH_SHORT).show();
                if(!MyAnimals.animal.isEmpty()) {
                    myAdapterAnimal = new MyAdapterMyAnimal(getApplicationContext(), MyAnimals.animal);

                    recyclerView.setAdapter(myAdapterAnimal);
                }else {
                    Toast.makeText(MyAnimals.this, "you don't have any animals (add your animal)", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnAddAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(MyAnimals.this,AddAnimal.class);
                startActivity(intent);
            }
        });

        btnMenu = findViewById(R.id.btnmenu7);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyAnimals.this,SideMenu.class);
                startActivity(intent);
            }
        });

    }

    public static void getAllAnimal(IServiseAnimal iServiseAnimal){
        //    ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();
        User u = (User) Vars.getUSER();

        Call<AnimalResponseList> call = iServiseAnimal.GetAnimalbyIdUser(u);

        call.enqueue(new Callback<AnimalResponseList>() {
            @Override
            public void onResponse(Call<AnimalResponseList> call, Response<AnimalResponseList> response) {
                if (!response.isSuccessful()){
                   // Toast.makeText(MyAnimals.this, "Error "+response.errorBody(), Toast.LENGTH_SHORT).show();
                } else {
                    MyAnimals.animal = response.body().getAnimal();
                }

            }

            @Override
            public void onFailure(Call<AnimalResponseList> call, Throwable t) {

            }
        });
    }


}