package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class app_bar_main extends AppCompatActivity {

    private Button menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_main);
        try {
            this.getSupportActionBar().hide();
        }catch (Exception e){
        }
        Toast.makeText(this,"test Menu",Toast.LENGTH_SHORT).show();

      //  menu = findViewById(R.id.btnmenu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(app_bar_main.this,"test Menu",Toast.LENGTH_SHORT).show();
                Intent intent  =  new Intent(app_bar_main.this,SideMenu.class);
                startActivity(intent);
            }
        });







    }
}