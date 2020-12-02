package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
    Button btnAffich ;
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
        getAllAnimals();
        btnAffich = findViewById(R.id.btnAffiche);
        recyclerView = findViewById(R.id.recyclerViewHome);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,true));


        recyclerView.setAdapter(myAdapterAnimal);


        btnAffich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAdapterAnimal = new MyAdapterAnimal(getApplicationContext(), animals);
                Toast.makeText(HomeActivity.this,  animals.get(1).toString(), Toast.LENGTH_LONG).show();

                recyclerView.setAdapter(myAdapterAnimal);
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

    public void getAllAnimals(){
        //    ArrayList<Animal> list = new ArrayList<Animal>();
        AnimalResponse animalResponse =new AnimalResponse();

        Call<AnimalResponseList> call = iServiseAnimal.GetAllAnimal();

        call.enqueue(new Callback<AnimalResponseList>() {
            @Override
            public void onResponse(Call<AnimalResponseList> call, Response<AnimalResponseList> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(HomeActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                } else {
                    AnimalResponseList animalResponse = response.body();
                    animals = animalResponse.getAnimal();

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