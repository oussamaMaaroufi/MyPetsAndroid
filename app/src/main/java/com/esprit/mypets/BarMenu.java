package com.esprit.mypets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import com.google.android.material.appbar.MaterialToolbar;

public class BarMenu extends AppCompatActivity {
MaterialToolbar toolbar;
Button menubar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_menu);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

}