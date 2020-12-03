package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IServieceUser;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.database.AppDatabase;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entity.UserSharedpref;
import com.esprit.mypets.entyityResponse.UserResponse;

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
  //  final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", 0); // 0 - for private mode
   // final SharedPreferences.Editor editor = pref.edit();


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

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iServieceUser =retrofitClient.create(IServieceUser.class);

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

                liginUser(email.getText().toString(), password.getText().toString());


            }
        });


    }
    private  void liginUser(String email,String password){
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

                        }
                        UserResponse userResponse = response.body();
                        if (userResponse.getSuccess().equals("true")) {
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                            Vars.setUSER(userResponse.getUser());
                            startActivityForResult(intent,1);
                            finish();

                        }
                          Toast.makeText(LoginActivity.this,response.errorBody().toString()+"Password or Email is not correct", Toast.LENGTH_SHORT).show();
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


}