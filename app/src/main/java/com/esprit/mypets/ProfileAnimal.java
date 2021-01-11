package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.IServiseVolontaires;
import com.esprit.mypets.entity.Abris;
import com.esprit.mypets.entity.Animal;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.Veterinaires;
import com.esprit.mypets.entity.Volontaires;
import com.esprit.mypets.entyityResponse.AbriResponse;
import com.esprit.mypets.entyityResponse.UserResponse;
import com.esprit.mypets.entyityResponse.VeterinairesResponse;
import com.esprit.mypets.entyityResponse.VolontairesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileAnimal extends AppCompatActivity {

    private Button callphone,info;
    private TextView nameAnimal,owner,Address,type,Rase;
    private ImageView imageView;
    private  String userId;
    ImageButton btnMenu;
    public static String owne;
    public static String phone;
    public static String address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_animal);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){

        }

        callphone = findViewById(R.id.btnAnimalCall);

        nameAnimal = findViewById(R.id.nameAnimalProfileAdop);
        owner = findViewById(R.id.owner);
        Address = findViewById(R.id.addressProfileAdop);
        type = findViewById(R.id.TypeAnimalProfile);
        Rase = findViewById(R.id.RaceAnimalProfile);
        info = findViewById(R.id.btnInfo);
        Intent intent =getIntent();

        Animal a = new Animal();

        a.setId(intent.getStringExtra("id"));
        a.setIdUser(intent.getStringExtra("IUser"));
        a.setName(intent.getStringExtra("Name"));
        a.setRace(intent.getStringExtra("Race"));
        a.setImage(intent.getStringExtra("image"));
        a.setType(intent.getStringExtra("Type"));

        nameAnimal.setText(a.getName());
        Rase.setText(a.getRace());
        type.setText(a.getType());

        owner.setText(owne);
        Address.setText(address);


        info.setOnClickListener(v -> {
            Toast.makeText(ProfileAnimal.this,owne,Toast.LENGTH_SHORT).show();
            owner.setText(owne);
            Address.setText(address);

        });



        btnMenu = findViewById(R.id.btnmenu3);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileAnimal.this,SideMenu.class);
                startActivity(intent);
            }
        });


    }

    public static  void getUserInfo(String idUser, IServieceUser iServieceUser, IServiceAbri iServiceAbri, IServiseVeterinaire iServiseVeterinaire, IServiseVolontaires iServiseVolontaires){

        User user = new User();
        user.setId(idUser);

        Call<UserResponse> call = iServieceUser.getUserbyID(user);

        call.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (!response.isSuccessful()){
                } else {
                    User user1 = response.body().getUser();

                    if(user1.getType().equals("Abris")){
                        Abris a = new Abris();
                        a.setIdUser(user1.getId());
                        Call<AbriResponse> call1 =  iServiceAbri.GetAbrilbyId(a);
                        call1.enqueue(new Callback<AbriResponse>() {
                            @Override
                            public void onResponse(Call<AbriResponse> call, Response<AbriResponse> response) {
                                if (!response.isSuccessful()){

                                }else {
                                    ProfileAnimal.owne = response.body().getAbris().getName();
                                    ProfileAnimal.address = response.body().getAbris().getAdresse();
                                    ProfileAnimal.phone = response.body().getAbris().getTelephon();

                                }

                            }

                            @Override
                            public void onFailure(Call<AbriResponse> call, Throwable t) {

                            }
                        });

                    }else if(user1.getType().equals("veterinarian")){
                        Veterinaires v = new Veterinaires();
                        v.setId(user1.getId());
                        Call<VeterinairesResponse> call2  = iServiseVeterinaire.GetVeterinairesById(v);
                        call2.enqueue(new Callback<VeterinairesResponse>() {
                            @Override
                            public void onResponse(Call<VeterinairesResponse> call, Response<VeterinairesResponse> response) {
                                if (!response.isSuccessful()){

                                }else {
                                    ProfileAnimal.owne = response.body().getVeterinaires().getName();
                                    ProfileAnimal.address = response.body().getVeterinaires().getAdresse();
                                    ProfileAnimal.phone = response.body().getVeterinaires().getTelephon();

                                }
                            }

                            @Override
                            public void onFailure(Call<VeterinairesResponse> call, Throwable t) {

                            }
                        });


                    }else {
                        Volontaires v1 = new Volontaires();
                        v1.setId(user1.getId());
                        Call<VolontairesResponse> call3 =   iServiseVolontaires.GetVeterinairesbyId(v1);
                        call3.enqueue(new Callback<VolontairesResponse>() {
                            @Override
                            public void onResponse(Call<VolontairesResponse> call, Response<VolontairesResponse> response) {
                                if (!response.isSuccessful()){


                                }else {
                                    ProfileAnimal.owne = response.body().getVolontaires().getName();
                                    ProfileAnimal.address = response.body().getVolontaires().getAdresse();
                                    ProfileAnimal.phone = response.body().getVolontaires().getTelephon();

                                }
                            }

                            @Override
                            public void onFailure(Call<VolontairesResponse> call, Throwable t) {

                            }
                        });



                    }


                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }
        });


    }
}