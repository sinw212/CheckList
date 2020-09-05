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

    private Daily_CheckList_Fragment daily_checklist = new Daily_CheckList_Fragment();
    private Daily_Statistics_Fragment daily_statistics = new Daily_Statistics_Fragment();
    private Monthly_Statistics_Fragment monthly_statistics = new Monthly_Statistics_Fragment();
    private Help_Fragment help = new Help_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.frame_layout, daily_checklist).commitAllowingStateLoss();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction transaction = fragmentManager.beginTransaction();

                switch(item.getItemId()) {
                    case R.id.daily_checklist: {
                        transaction.replace(R.id.frame_layout, daily_checklist).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.daily_statistics: {
                        transaction.replace(R.id.frame_layout, daily_statistics).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.monthly_statistics: {
                        transaction.replace(R.id.frame_layout, monthly_statistics).commitAllowingStateLoss();
                        break;
                    }
                    case R.id.help: {
                        transaction.replace(R.id.frame_layout, help).commitAllowingStateLoss();
                        break;
                    }
                }
                return true;
            }
        });
    }
}