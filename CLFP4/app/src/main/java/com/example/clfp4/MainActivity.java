package com.example.clfp4;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    private FragmentManager fragmentManager = getSupportFragmentManager();

    private daily4 daily4 = new daily4();
    private weekly4 weekly4 = new weekly4();
    private monthly4 monthly4 = new monthly4();
    private help4 help4 = new help4();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, daily4).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch(item.getItemId()) {
                    case R.id.daily: {
                        transaction.replace(R.id.frame_layout, daily4).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.weekly: {
                        transaction.replace(R.id.frame_layout, weekly4).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.monthly: {
                        transaction.replace(R.id.frame_layout, monthly4).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.login: {
                        transaction.replace(R.id.frame_layout, help4).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
}