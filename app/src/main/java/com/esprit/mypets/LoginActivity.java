package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.esprit.mypets.Retrofit.IMyServiece;
import com.esprit.mypets.Retrofit.RetrofitClient;
import com.google.android.material.datepicker.CompositeDateValidator;
import com.google.gson.JsonObject;

import java.io.Console;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText email;
    private EditText password;
    private Button btnSignIn;
    private Button btntSignInGoogle;
    private Button btnRegister;
  //  private static Object Activity = LoginActivity.this;

    CompositeDisposable  compositeDisposable = new CompositeDisposable();
    IMyServiece iMyServiece;

    @Override
    protected void onStop() {
        compositeDisposable.clear();
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

                liginUser(email.getText().toString(), password.getText().toString());


            }
        });


    }
    private  void liginUser(String email,String password){
        if(email.isEmpty()){
            Toast.makeText(this,"Email cannot be null or empyt",Toast.LENGTH_SHORT).show();
        }
        if(password.isEmpty()){
            Toast.makeText(this,"password cannot be null or empyt",Toast.LENGTH_SHORT).show();
        }

        try {
            Toast.makeText(LoginActivity.this,email+" "+password, Toast.LENGTH_SHORT).show();

            compositeDisposable.add(iMyServiece.loginUser(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Consumer<String>() {
                        @Override
                        public void accept(String s) throws Exception {
                            Toast.makeText(LoginActivity.this, "" + s, Toast.LENGTH_SHORT).show();
                        }
                    }));
        }catch (Exception e){
            Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }




    }


}