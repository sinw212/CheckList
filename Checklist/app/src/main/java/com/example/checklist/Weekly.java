package com.example.checklist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Weekly extends AppCompatActivity {

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    EditText edit_text;
    ArrayList<String> items;
    ArrayAdapter adapter;

    TextView Text_daily,Text_weekly,Text_monthly,Text_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weekly3);

        Text_daily = (TextView) findViewById(R.id.Text_daily);
        Text_weekly = (TextView) findViewById(R.id.Text_weekly);
        Text_monthly = (TextView) findViewById(R.id.Text_monthly);
        Text_login = (TextView) findViewById(R.id.Text_login);


        Text_daily.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weekly.this, MainActivity.class);

                startActivity(intent);
            }
        });

        Text_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weekly.this, Monthly.class);

                startActivity(intent);
            }
        });

        Text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Weekly.this, Login.class);

                startActivity(intent);
            }
        });

    }
}



