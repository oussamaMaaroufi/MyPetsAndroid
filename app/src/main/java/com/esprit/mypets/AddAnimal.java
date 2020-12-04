package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AddAnimal extends AppCompatActivity {
    private Spinner spinner;
    private EditText name,race;
    private Button save;

    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_animal);

        name = findViewById(R.id.NameAddAnimal);
        race = findViewById(R.id.race);
        save = findViewById(R.id.SaveAddAnimal);
        Animal animal =new Animal();


        spinner = findViewById(R.id.spinner);
        String[] items = new String[]{"Cat", "Dog", "bird"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().isEmpty()){
                    Toast.makeText(AddAnimal.this, "name required ", Toast.LENGTH_SHORT).show();
                }else if(race.getText().toString().isEmpty()){
                    Toast.makeText(AddAnimal.this, "Race required ", Toast.LENGTH_SHORT).show();
                }if(spinner.isSelected()){
                    Toast.makeText(AddAnimal.this, "Type required ", Toast.LENGTH_SHORT).show();
                }else{
                    String type = spinner.getSelectedItem().toString();
                    animal.setType(type);
                    animal.setRace(race.getText().toString());
                    animal.setName(name.getText().toString());
                    animal.setIdUser(Vars.getUSER().getId());

                    Call<AnimalResponse> call  = iServiseAnimal.AddAnimal(animal);
                    call.enqueue(new Callback<AnimalResponse>() {
                        @Override
                        public void onResponse(Call<AnimalResponse> call, Response<AnimalResponse> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(AddAnimal.this, "Error ", Toast.LENGTH_SHORT).show();
                            } else {
                                  Toast.makeText(AddAnimal.this,  response.body().getAnimal().toString()  , Toast.LENGTH_SHORT).show();
                                  Intent intent =new Intent(AddAnimal.this,HomeActivity.class);
                                  startActivity(intent);
                            }

                        }

                        @Override
                        public void onFailure(Call<AnimalResponse> call, Throwable t) {

                        }
                    });



                }




            }
        });


    }

}