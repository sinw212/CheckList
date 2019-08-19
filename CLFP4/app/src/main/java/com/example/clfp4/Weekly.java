package com.example.clfp4;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Weekly extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    EditText edit_text;
    ArrayList<String> items;
    ArrayAdapter adapter;
    TextView tv;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly4);

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        /*bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.daily:
                        startActivity(new Intent(this,MainActivity.class));
                        break;
                    case R.id.weekly:

                        break;
                    case R.id.monthly:

                        break;
                    case R.id.login:

                        break;
                }
                return false;
            }
        });*/

    }



}