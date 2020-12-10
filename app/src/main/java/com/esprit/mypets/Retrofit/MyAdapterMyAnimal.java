package com.esprit.mypets.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.mypets.AddAnimal;
import com.esprit.mypets.HomeActivity;
import com.esprit.mypets.MyAnimals;
import com.esprit.mypets.R;
import com.esprit.mypets.Vars;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AnimalResponse;
import com.esprit.mypets.entyityResponse.AnimalResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MyAdapterMyAnimal extends RecyclerView.Adapter<MyAdapterMyAnimal.MyViewHolder>{
    private Context myContext;
    private  ArrayList<Animal> animals;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseAnimal iServiseAnimal =retrofitClient.create(IServiseAnimal.class);
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nomAnimal,raceAnimal ;
        Button delete,adoption;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageAniamlHome);
            nomAnimal = itemView.findViewById(R.id.nameAniamlHome);
            raceAnimal = itemView.findViewById(R.id.raceAnimalHome);
            delete = itemView.findViewById(R.id.btnDeleteAnimal);
            adoption = itemView.findViewById(R.id.btnAddAdoption);

        }

    }

    public MyAdapterMyAnimal(Context myContext, ArrayList<Animal> animals) {
        this.myContext = myContext;
        this.animals = animals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.singel_row_my_animal,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Animal animal =animals.get(position);
        //holder.imageView.setImageResource(country.getImage());
        holder.nomAnimal.setText(animal.getName());
        holder.raceAnimal.setText(animal.getRace());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext, animal.toString(), Toast.LENGTH_SHORT).show();
                deleteAnimal(animal);
                updateData(animals);

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,animal.getType(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.adoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    public void updateData(ArrayList<Animal> viewModels) {
        animals.clear();
        animals.addAll(viewModels);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return animals.size();
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

