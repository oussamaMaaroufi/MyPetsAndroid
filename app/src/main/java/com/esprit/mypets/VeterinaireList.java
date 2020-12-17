package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.MyAdapterLAF;
import com.esprit.mypets.Retrofit.MyAdapterVeterinaire;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entyityResponse.VeterinaireResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class VeterinaireList extends AppCompatActivity {
    public static ArrayList<Veterinaires> veterinaires = new ArrayList<Veterinaires>();
    private RecyclerView recyclerView ;
    private MyAdapterVeterinaire adapterVeterinaire;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiseVeterinaire iServiseVeterinaire =retrofitClient.create(IServiseVeterinaire.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veterinaire_list);
        recyclerView = findViewById(R.id.veterinaireListRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterVeterinaire = new MyAdapterVeterinaire(this,veterinaires);

        recyclerView.setAdapter(adapterVeterinaire);

    }


    public static void getListVeterinaires (IServiseVeterinaire iServiseVeterinaire){

        Call<VeterinaireResponseList> call = iServiseVeterinaire.GetVeterinaires();
        call.enqueue(new Callback<VeterinaireResponseList>() {
            @Override
            public void onResponse(Call<VeterinaireResponseList> call, Response<VeterinaireResponseList> response) {
                if (!response.isSuccessful()){

                }else {
                    VeterinaireList.veterinaires = response.body().getVeterinaires();
                }
            }

            @Override
            public void onFailure(Call<VeterinaireResponseList> call, Throwable t) {

            }
        });
    }
}