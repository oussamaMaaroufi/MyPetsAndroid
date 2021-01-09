package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServiceAbri;
import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.IServiseAnimal;
import com.esprit.mypets.Retrofit.IServiseVeterinaire;
import com.esprit.mypets.Retrofit.IServiseVolontaires;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.Abris;
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
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private Button btntSignInGoogle;
    private Button btnRegister;
   // private AppDatabase database;

    IServieceUser iServieceUser;
    private IServiceAbri iServeceAbri;
    private IServiseVeterinaire iServiseVeterinaire;
    private IServiseVolontaires iServiseVolontaires;
    private Retrofit retrofit = RetrofitClient.getInstance();
  //  final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", 0); // 0 - for private mode
   // final SharedPreferences.Editor editor = pref.edit();
  IServiseAnimal iServiseAnimal =retrofit.create(IServiseAnimal.class);


    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btnRegister =findViewById(R.id.btnRegister);
        btnSignIn = findViewById(R.id.btnSignIn);
        btntSignInGoogle = findViewById(R.id.btnSignInGoogle);
         email = findViewById(R.id.TxtEmail);
        password =findViewById(R.id.TxtPassword);


        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
     ///   database = AppDatabase.getInstance(this);

        //Init service

        iServieceUser =retrofit.create(IServieceUser.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginUser(email.getText().toString(), password.getText().toString());


            }
        });


    }
    private  void loginUser(String email,String password){
        if(email.isEmpty()){
            Toast.makeText(this,"Email cannot be null or empyt",Toast.LENGTH_SHORT).show();
        }else
        if(password.isEmpty()){
            Toast.makeText(this,"password cannot be null or empyt",Toast.LENGTH_SHORT).show();
        }else if(!email.isEmpty() && !password.isEmpty() ){
            User user = new User(email, password);

            try {
               // Toast.makeText(LoginActivity.this, email + " " + password, Toast.LENGTH_SHORT).show();

                Call<UserResponse> call = iServieceUser.loginUser(user);
                call.enqueue(new Callback<UserResponse>() {
                    @Override
                    public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                        if (!response.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                           // Toast.makeText(LoginActivity.this,response.errorBody().toString()+"Password or Email is not correct", Toast.LENGTH_SHORT).show();

                        }else {
                             UserResponse userResponse = response.body();
                            User user = userResponse.getUser();
                            Vars.setUSER(user);
                            HomeActivity.getAllAnimals(iServiseAnimal);
                            //Toast.makeText(LoginActivity.this, Vars.getUSER().toString(), Toast.LENGTH_SHORT).show();
                            if (user.getType().equals("Volontaires")) {
                                iServiseVolontaires = retrofit.create(IServiseVolontaires.class);
                                Volontaires volontaires = new Volontaires();
                                volontaires.setIdUser(user.getId());
                                GetProfilVolo(volontaires);
                            } else if (user.getType().equals("Abris")) {
                                iServeceAbri = retrofit.create(IServiceAbri.class);
                                Abris abris = new Abris();
                                abris.setIdUser(user.getId());
                                GetProfilAbri(abris);
                            } else {
                                iServiseVeterinaire = retrofit.create(IServiseVeterinaire.class);
                                Veterinaires veterinaires = new Veterinaires();
                                veterinaires.setIdUser(user.getId());
                                GetProfilVeto(veterinaires);

                            }



                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, call.request() + t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

            } catch (Exception e) {
                Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }

        }

    }

    private  void GetProfilVolo(Volontaires volontaires){

        try {
            Call<VolontairesResponse> call = iServiseVolontaires.GetVeterinairesbyId(volontaires);
            call.enqueue(new Callback<VolontairesResponse>() {
                @Override
                public void onResponse(Call<VolontairesResponse> call, Response<VolontairesResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    VolontairesResponse volontairesResponse =response.body();
                    if (volontairesResponse.getSuccess().equals("true")){
                        Vars.setAddress(volontairesResponse.getVolontaires().getAdresse());
                        Vars.setPhone(volontairesResponse.getVolontaires().getTelephon());

                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }else {

                     //   Toast.makeText(LoginActivity.this, volontairesResponse.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<VolontairesResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private  void GetProfilAbri(Abris abris){

        try {
            Call<AbriResponse> call = iServeceAbri.GetAbrilbyId(abris);
            call.enqueue(new Callback<AbriResponse>() {
                @Override
                public void onResponse(Call<AbriResponse> call, Response<AbriResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    AbriResponse abriResponse =response.body();
                    if (abriResponse.getSuccess().equals("true")){
                        Vars.setAddress(abriResponse.getAbris().getAdresse());
                        Vars.setPhone(abriResponse.getAbris().getTelephon());
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();

                    }else {
                      //  Toast.makeText(LoginActivity.this, abriResponse.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<AbriResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
    private  void GetProfilVeto(Veterinaires veterinaires){

        try {
            Call<VeterinairesResponse> call = iServiseVeterinaire.GetVeterinairesById(veterinaires);
            call.enqueue(new Callback<VeterinairesResponse>() {
                @Override
                public void onResponse(Call<VeterinairesResponse> call, Response<VeterinairesResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Error ", Toast.LENGTH_SHORT).show();
                        //       ErrorTxt.setText("Code : "+response.code());
                    }
                    VeterinairesResponse veterinairesResponse =response.body();
                    if (veterinairesResponse.getSuccess().equals("true")){
                        Vars.setAddress(veterinairesResponse.getVeterinaires().getAdresse());
                        Vars.setPhone(veterinairesResponse.getVeterinaires().getTelephon());
                        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finishAffinity();
                    }else {
                //        Toast.makeText(LoginActivity.this, veterinairesResponse.toString(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    }
                }
                @Override
                public void onFailure(Call<VeterinairesResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }


}