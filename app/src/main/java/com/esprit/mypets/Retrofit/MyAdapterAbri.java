package com.esprit.mypets.Retrofit;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.mypets.AbriProfile;
import com.esprit.mypets.R;
import com.esprit.mypets.entity.Abris;

import java.util.ArrayList;

import retrofit2.Retrofit;

public class MyAdapterAbri extends RecyclerView.Adapter<MyAdapterAbri.MyViewHolder>{
    private Context myContext;
    private ArrayList<Abris> abrises;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceAbri iServiceAbri =retrofitClient.create(IServiceAbri.class);

    public MyAdapterAbri(Context myContext, ArrayList<Abris> abrises) {
        this.myContext = myContext;
        this.abrises = abrises;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.single_row_abri,parent,false);
        return new MyAdapterAbri.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Abris abris =abrises.get(position);
        holder.nomAbris.setText(abris.getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(myContext,abris.getAdresse(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(myContext, AbriProfile.class);

                myContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return abrises.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nomAbris;

        private Context context;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageAbriHome);
            nomAbris = itemView.findViewById(R.id.nameAbriHome);
            context = itemView.getContext();

        }

    }
}
