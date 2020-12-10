package com.esprit.mypets.Retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.mypets.R;
import com.esprit.mypets.entity.Animal;

import java.util.ArrayList;
import java.util.List;

public class MyAdapterAnimal extends RecyclerView.Adapter<MyAdapterAnimal.MyViewHolder> {
    private Context myContext;
    private ArrayList<Animal> animals;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nomAnimal,raceAnimal ;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageAniamlHome);
            nomAnimal = itemView.findViewById(R.id.nameAniamlHome);
            raceAnimal = itemView.findViewById(R.id.raceAnimalHome);

        }

    }
    public void updateData(ArrayList<Animal> viewModels) {
        animals.clear();
        animals.addAll(viewModels);
        notifyDataSetChanged();
    }

    public MyAdapterAnimal(Context myContext, ArrayList<Animal> animals) {
        this.myContext = myContext;
        this.animals = animals;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.singel_row_animal,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Animal animal =animals.get(position);
        //holder.imageView.setImageResource(country.getImage());
        holder.nomAnimal.setText(animal.getName());
        holder.raceAnimal.setText(animal.getRace());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,animal.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return animals.size();
    }



}
