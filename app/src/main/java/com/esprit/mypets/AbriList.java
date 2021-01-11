package com.esprit.mypets;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.MyAdapterAbri;
import com.esprit.mypets.Retrofit.MyAdapterAnimal;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.AbriResponseList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AbriList extends AppCompatActivity {
    public static ArrayList<Abris> abrises = new ArrayList<Abris>();
    private RecyclerView recyclerView ;
    private MyAdapterAbri adapterAbri;
    ImageButton btnMenu;
    Retrofit retrofitClient = RetrofitClient.getInstance();
    IServiceAbri iServiceAbri =retrofitClient.create(IServiceAbri.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        setContentView(R.layout.activity_abri_list);
        recyclerView = findViewById(R.id.abriListRecycler);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPetsUser", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        Abris user = new Abris();
        editor.putString("id", user.getId());
        editor.putString("name", user.getName());
        editor.putString("Adresse", user.getAdresse());
        editor.putString("Image", user.getImage());
        editor.putString("Telephon", user.getTelephon());
        editor.commit();


        recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        adapterAbri = new MyAdapterAbri(this,abrises);
        recyclerView.setAdapter(adapterAbri);

        if(!AbriList.abrises.isEmpty()) {
            adapterAbri = new MyAdapterAbri(getApplicationContext(), AbriList.abrises);
            recyclerView.setAdapter(adapterAbri);
        }


        btnMenu= findViewById(R.id.btnmenu5);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AbriList.this,SideMenu.class);
                startActivity(intent);
            }
        });



    }
    public static void getListAbris (IServiceAbri iServiceAbri){

        Call<AbriResponseList> call = iServiceAbri.GetAbris();
        call.enqueue(new Callback<AbriResponseList>() {
            @Override
            public void onResponse(Call<AbriResponseList> call, Response<AbriResponseList> response) {
                if (!response.isSuccessful()){

                }else {
                    AbriList.abrises = response.body().getAbrises();

                    String a = abrises.get(0).getAdresse();
                }
            }

            @Override
            public void onFailure(Call<AbriResponseList> call, Throwable t) {

            }
        });
    }

}
