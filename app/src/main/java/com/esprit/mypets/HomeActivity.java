package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    private TextView Name,email;

   // final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref1", 0); // 0 - for private mode
    //final SharedPreferences.Editor editor = pref.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Name = findViewById(R.id.homeName);
        email = findViewById(R.id.homeEmail);
        Intent intent = getIntent();
        Name.setText(intent.getStringExtra("Name"));
        email.setText(intent.getStringExtra("Email"));

          //  Name.setText(pref.getString("name","test0").toString());
          //  email.setText(pref.getString("email","test0").toString());








    }
}