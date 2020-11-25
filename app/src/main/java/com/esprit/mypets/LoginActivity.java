package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IMyServiece;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.esprit.mypets.entity.User;
import com.esprit.mypets.entyityResponse.UserResponse;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.gson.JsonObject;

import java.io.Console;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
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
  //  private static Object Activity = LoginActivity.this;

    //CompositeDisposable  compositeDisposable = new CompositeDisposable();
    IMyServiece iMyServiece;

    @Override
    protected void onStop() {
        //compositeDisposable.clear();
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

        //Init service

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyServiece =retrofitClient.create(IMyServiece.class);

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
            Toast.makeText(this,"Email cannot be null or empty",Toast.LENGTH_SHORT).show();
        }
        if(password.isEmpty()){
            Toast.makeText(this,"password cannot be null or empty",Toast.LENGTH_SHORT).show();
        }
        User user =new User(email,password);

        try {


            Call<UserResponse> call = iMyServiece.loginUser(user);
            call.enqueue(new Callback<UserResponse>() {
                @Override
                public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                    if(!response.isSuccessful()){
                        Toast.makeText(LoginActivity.this,"Error ", Toast.LENGTH_SHORT).show();

                    }
                    UserResponse userResponse =response.body();
                    if (userResponse.getSuccess().equals("true")){
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        User user1 = userResponse.getUser();
                        intent.putExtra("Name",user1.getName());
                        intent.putExtra("Email",user1.getEmail());
                        intent.putExtra("Type",user1.getType());
                        intent.putExtra("Token",userResponse.getToken());
                        startActivityForResult(intent,1);

                    }
                  //  Toast.makeText(LoginActivity.this,userResponse.toString(), Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<UserResponse> call, Throwable t) {
                    Toast.makeText(LoginActivity.this,call.request() +t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }catch (Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }


}