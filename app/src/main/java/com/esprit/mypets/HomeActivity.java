package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    private TextView Name,email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Name = findViewById(R.id.homeName);
        email = findViewById(R.id.homeEmail);
        Intent intent = getIntent();


            Name.setText(intent.getStringExtra("Name"));
            email.setText(intent.getStringExtra("Email"));





    }
}