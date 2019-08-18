package com.example.clfp4;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {


    BottomNavigationView bottomNavigationView;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    EditText edit_text;
    ArrayList<String> items;
    ArrayAdapter adapter;
    TextView text_succes;
    double count = 0.0;
    double all_count;


    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton button_plus = (ImageButton) findViewById(R.id.button_plus);
        edit_text = (EditText) findViewById(R.id.edit_text);

        items = new ArrayList<>();
        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, items) ;

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        text_succes = (TextView) findViewById(R.id.text_succes);

        button_plus.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 아이템 추가.
                items.add(edit_text.getText().toString());

                // listview 갱신
                adapter.notifyDataSetChanged();

                edit_text.setText("");

                all_count = adapter.getCount();



                if(count == 0){
                    text_succes.setText("달성률 : 0%");
                }
                else{
                    double s_rate = Double.parseDouble(String.format("%.2f",count / all_count));
                    int r_rate = (int) (s_rate*100);
                    text_succes.setText("달성률 : "+  r_rate+"%");
                }


            }
        });


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count = listview.getCheckedItemCount();
                all_count = adapter.getCount();

                if(count == 0){
                    text_succes.setText("달성률 : 0%");
                }
                else{
                    double s_rate = Double.parseDouble(String.format("%.2f",count / all_count));
                    int r_rate = (int) (s_rate*100);
                    text_succes.setText("달성률 : "+  r_rate+"%");
                }

            }
        });


        // 오늘 날짜 지정
        TextView textview_date = (TextView) findViewById(R.id.textview_date);
        textview_date.setText(getTime());

        // 하단 바
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.daily:

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
        });

        resetAlarm(this);


    }

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public static void resetAlarm(Context context){
        Intent resetIntent = new Intent(context,AlarmReceiver.class);
        PendingIntent resetSender =
                PendingIntent.getBroadcast(
                        context,
                        0,
                        resetIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

        // 자정 시간
        Calendar resetCal = Calendar.getInstance();
        resetCal.setTimeInMillis(System.currentTimeMillis());
        resetCal.set(Calendar.HOUR_OF_DAY, 0);
        resetCal.set(Calendar.MINUTE,0);
        resetCal.set(Calendar.SECOND, 0);

        AlarmManager resetAlarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        resetAlarmManager.set(AlarmManager.RTC,
                resetCal.getTimeInMillis(),
                resetSender);

        //다음날 0시에 맞추기 위해 24시간을 뜻하는 상수인 AlarmManager.INTERVAL_DAY를 더해줌.
        resetAlarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, resetCal.getTimeInMillis() +AlarmManager.INTERVAL_DAY, AlarmManager.INTERVAL_DAY, resetSender); SimpleDateFormat format = new SimpleDateFormat("MM/dd kk:mm:ss");
        String setResetTime = format.format(new Date(resetCal.getTimeInMillis()+AlarmManager.INTERVAL_DAY));


    }

    public  void listview_reset(){
        // 일정목록 달력에 넘겨주기 (아직 안함)

        // 리스트뷰 항목 전체 삭제
        all_count = adapter.getCount();
        for(int i = 0; i< all_count;i++){
            adapter.remove(adapter.getItem(i));
        }
    }

    public void data_pass(){
        // 달성률을 Weekly.class에 전달
    }


}