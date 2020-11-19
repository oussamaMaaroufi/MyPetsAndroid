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

import java.io.Console;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText email,password;
    private Button btnSignIn;
    private Button btntSignInFb;
    private Button btntSignInGoogle;
    private Button btntSignInTwt;
    private Button btnRegister;
    private static String ss;

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
        btntSignInFb = findViewById(R.id.btnSignInFb);
        btntSignInGoogle = findViewById(R.id.btnSignInGoogle);
        btntSignInTwt = findViewById(R.id.btnSignInTwitter);
        email = findViewById(R.id.TxtEmail);
        password =findViewById(R.id.TxtPassword);

        //Init service

        Retrofit retrofitClient = RetrofitClient.getInstance();
        iMyServiece =retrofitClient.create(IMyServiece.class);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    liginUser(email.getText().toString(), password.getText().toString());



                Intent intent =new Intent(LoginActivity.this,RegisterActivity.class);

                startActivity(intent);
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

          /*  compositeDisposable(iMyServiece.loginUser(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(s -> {

                    }));*/


    }



    private void toastMake(String s){
        Toast.makeText(LoginActivity.this,""+s,Toast.LENGTH_SHORT).show();

    }
}