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

import com.esprit.mypets.Adoption;
import com.esprit.mypets.R;
import com.esprit.mypets.VeterinaireProfile;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.Veterinaires;

import java.util.ArrayList;
import java.util.Collections;

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

        holder.nomAnimal.setText(veto.getName());
        holder.imageView.setImageResource(R.mipmap.ic_logo_foreground);


        holder.itemView.setOnClickListener(v -> {
            Intent intent =new Intent(myContext, VeterinaireProfile.class);
            intent.putExtra("Name",veto.getName());
            intent.putExtra("address",veto.getAdresse());
            intent.putExtra("phone",veto.getTelephon());
            intent.putExtra("image",veto.getImage());
            intent.putExtra("IdUser",veto.getIdUser());
            myContext.startActivity(intent);
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
