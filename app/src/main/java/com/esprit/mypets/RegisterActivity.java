package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.esprit.mypets.Retrofit.IMyServiece;

import io.reactivex.disposables.CompositeDisposable;

public class RegisterActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable ; // = new CompositeDisposable();
    IMyServiece iMyServiece;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }
}