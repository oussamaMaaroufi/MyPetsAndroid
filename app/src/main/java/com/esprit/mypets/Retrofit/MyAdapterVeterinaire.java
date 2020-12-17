package com.esprit.mypets.Retrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.mypets.R;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.Veterinaires;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MyAdapterVeterinaire extends RecyclerView.Adapter<MyAdapterVeterinaire.MyViewHolder>{
    private Context myContext;
    private ArrayList<Veterinaires> veterinaires;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseVeterinaire iServiseVeterinaire =retrofitClient.create(IServiseVeterinaire.class);

    public MyAdapterVeterinaire(Context myContext, ArrayList<Veterinaires> veterinaires) {
        this.myContext = myContext;
        this.veterinaires = veterinaires;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.single_row_veterinaires,parent,false);
        return new MyAdapterVeterinaire.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Veterinaires veto =veterinaires.get(position);
        //holder.imageView.setImageResource(country.getImage());
        holder.nomAnimal.setText(veto.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(myContext,veto.getAdresse(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return veterinaires.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nomAnimal;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageVeterianireHome);
            nomAnimal = itemView.findViewById(R.id.nameVeterinaireHome);


        }

    }
}
