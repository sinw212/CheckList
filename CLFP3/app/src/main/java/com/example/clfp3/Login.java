package com.example.clfp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Login extends AppCompatActivity {

    TextView Text_daily,Text_weekly,Text_monthly,Text_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login3);

        Text_daily = (TextView) findViewById(R.id.Text_daily);
        Text_weekly = (TextView) findViewById(R.id.Text_weekly);
        Text_monthly = (TextView) findViewById(R.id.Text_monthly);
        Text_login = (TextView) findViewById(R.id.Text_login);

        Text_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,MainActivity.class);

                startActivity(intent);
            }
        });

        Text_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Weekly.class);

                startActivity(intent);
            }
        });

        Text_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Monthly.class);

                startActivity(intent);
            }
        });
    }

}



