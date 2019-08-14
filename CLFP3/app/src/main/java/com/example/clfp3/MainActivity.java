package com.example.clfp3;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.daily3);

        Intent loading = new Intent(this, LoadingActivity.class);
        startActivity(loading);

        ImageButton button_plus = (ImageButton) findViewById(R.id.button_plus) ;
        edit_text = (EditText) findViewById(R.id.edit_text);

        items = new ArrayList<>() ;
        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items) ;

        // listview 생성 및 adapter 지정.
        ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        button_plus.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이템 추가.
                items.add(edit_text.getText().toString());

                // listview 갱신
                adapter.notifyDataSetChanged();

                edit_text.setText("");
            }
        });

        Text_daily = (TextView) findViewById(R.id.Text_daily);
        Text_weekly = (TextView) findViewById(R.id.Text_weekly);
        Text_monthly = (TextView) findViewById(R.id.Text_monthly);
        Text_login = (TextView) findViewById(R.id.Text_login);

        Text_weekly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Weekly.class);

                startActivity(intent);
            }
        });

        Text_monthly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Monthly.class);

                startActivity(intent);
            }
        });

        Text_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Login.class);

                startActivity(intent);
            }
        });


        TextView textview_date = (TextView) findViewById(R.id.textview_date);

        textview_date.setText(getTime());

    }

    private String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }


}
