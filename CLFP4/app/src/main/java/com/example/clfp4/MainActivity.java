package com.example.clfp4;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    TextView tv;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        tv = (TextView) findViewById(R.id.textView);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.daily:
                        tv.setText("Daily");
                        break;
                    case R.id.weekly:
                        tv.setText("Weekly");
                        break;
                    case R.id.monthly:
                        tv.setText("Monthly");
                        break;
                    case R.id.login:
                        tv.setText("Login");
                        break;
                }
                return false;
            }
        });
    }
}