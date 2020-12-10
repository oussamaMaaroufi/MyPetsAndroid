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
import com.esprit.mypets.entity.LostAndFound;

import java.util.ArrayList;

public class MyAdapterLAF  extends RecyclerView.Adapter<MyAdapterLAF.MyViewHolder>{
    private Context myContext;
    private ArrayList<LostAndFound> lostAndFounds;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView userName,desc ;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.LAFimageAniaml);
            userName = itemView.findViewById(R.id.LAFUserName);
            desc = itemView.findViewById(R.id.LAFDesc);

        }

    }
    public void updateData(ArrayList<LostAndFound> viewModels) {
        lostAndFounds.clear();
        lostAndFounds.addAll(viewModels);
        notifyDataSetChanged();
    }

    public MyAdapterLAF(Context myContext, ArrayList<LostAndFound> lostAndFounds) {
        this.myContext = myContext;
        this.lostAndFounds = lostAndFounds;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(myContext).inflate(R.layout.singel_row_lost_found,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final LostAndFound lostAndFound =lostAndFounds.get(position);
       // holder.imageView.setImageResource(lostAndFound.getImage());
        holder.userName.setText(lostAndFound.getUserName());
        holder.desc.setText(lostAndFound.getDesc());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(myContext,"Help me", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return lostAndFounds.size();
    }

}
