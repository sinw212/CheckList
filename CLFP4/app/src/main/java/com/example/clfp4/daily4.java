package com.example.clfp4;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class daily4 extends Fragment {
    View view;
    long mNow;
    Date mDate;

    SimpleDateFormat mFormat = new SimpleDateFormat("YYYY년 MM월 dd일");
    SimpleDateFormat Format_today = new SimpleDateFormat("YYYYMMdd");
    EditText edit_text;
    //    SimpleDateFormat mFormat = new SimpleDateFormat("hh시 mm분 ss초");
    CustomChoiceListViewAdapter adapter;
    ListView listview;
    String getEdit;
    double count = 0.0;
    double count_all = 0.0;
    TextView textview_goal;
    TextView textview_date;
    String strDate;

    String date_for_data = "";

    Calendar c;
    int nYear, nMon, nDay;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daily4, container, false);

        // 오늘 날짜 표현
        textview_date = (TextView) view.findViewById(R.id.textview_date);
        textview_date.setText(getTime(mFormat));

        ImageButton addButton = (ImageButton) view.findViewById(R.id.button_plus);
        ImageButton modifyButton = (ImageButton) view.findViewById(R.id.button_modify);
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.button_delete);
        ImageButton button_calendar = (ImageButton) view.findViewById(R.id.button_calender);

        edit_text = (EditText) view.findViewById(R.id.edit_text);

        //Adapter 생성
        adapter = new CustomChoiceListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

//        getData(getTime(Format_today));

        // Calendar
        //DatePicker Listener
        mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        strDate = String.valueOf(year) + "년 ";

                        if (monthOfYear + 1 > 0 && monthOfYear + 1 < 10)
                            strDate += "0" + String.valueOf(monthOfYear + 1) + "월 ";
                        else
                            strDate += String.valueOf(monthOfYear + 1) + "월 ";

                        if (dayOfMonth > 0 && dayOfMonth < 10)
                            strDate += "0" + String.valueOf(dayOfMonth) + "일";
                        else
                            strDate += String.valueOf(dayOfMonth) + "일";

                        textview_date.setText(strDate);

                        date_for_data = "";
                        date_for_data += String.valueOf(year);

                        if(monthOfYear+1 > 0 && monthOfYear+1 < 10)
                            date_for_data += "0"+String.valueOf(monthOfYear+1);
                        else
                            date_for_data += String.valueOf(monthOfYear+1);

                        if(dayOfMonth > 0 && dayOfMonth < 10)
                            date_for_data += "0"+String.valueOf(dayOfMonth);
                        else
                            date_for_data += String.valueOf(dayOfMonth);

//                        //다른날짜 선택해도 남아있는 리스트
//                        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
//                        int count = adapter.getCount();
//
//                        for(int i=count-1; i>=0; i--) {
//                            adapter.removeItem(i);
//                        }
//                        adapter.notifyDataSetChanged();
//
//                        listview.clearChoices();
//
//                        //리스트 불러오기
//                        getData(date_for_data);

                    }
                };

        c = Calendar.getInstance();
        nYear = c.get(Calendar.YEAR);
        nMon = c.get(Calendar.MONTH);
        nDay = c.get(Calendar.DAY_OF_MONTH);

        button_calendar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog oDialog = new DatePickerDialog(getContext(),
                        mDateSetListener, nYear, nMon, nDay);

                oDialog.show();
            }
        });

        // 추가 버튼 리스너
        addButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit = edit_text.getText().toString();

                if (getEdit.getBytes().length <= 0) {
                    Toast.makeText(getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    adapter.addItem(getEdit);

                    // listview 갱신
                    adapter.notifyDataSetChanged();
                    edit_text.setText("");
                }
                setGoal();
            }
        });
        // 수정 버튼 리스너
        modifyButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        adapter.setItem(i, edit_text.getText().toString());
                    }
                }
                adapter.notifyDataSetChanged();
                edit_text.setText("");
                listview.clearChoices();

                setGoal();
            }
        });
        // 삭제버튼 리스너
        deleteButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        adapter.removeItem(i);
                    }
                }
                adapter.notifyDataSetChanged();

                listview.clearChoices();

                setGoal();
            }
        });

        // 리스트뷰 리스너
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setGoal();
            }
        });

//        // 알람 매니저
//        Intent mAlarmIntent = new Intent(this.getActivity(),AlarmReceiver.class);
//
//        PendingIntent mPendingIntent =
//                PendingIntent.getBroadcast(
//                        this.getActivity(),
//                        0,
//                        mAlarmIntent,
//                        PendingIntent.FLAG_UPDATE_CURRENT
//                );
//
//        // 자정 시간
//        Calendar resetCal = Calendar.getInstance();
//        resetCal.setTimeInMillis(System.currentTimeMillis());
//        resetCal.set(Calendar.HOUR_OF_DAY, 22);
//        resetCal.set(Calendar.MINUTE,25);
//        resetCal.set(Calendar.SECOND, 0);
//
//        AlarmManager mAlarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
//
//        //다음날 0시에 맞추기 위해 24시간을 뜻하는 상수인 AlarmManager.INTERVAL_DAY를 더해줌.
//        mAlarmManager.setRepeating(AlarmManager.RTC, resetCal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingIntent);
//
//       /* SimpleDateFormat format = new SimpleDateFormat("MM/dd kk:mm:ss");
//        String setResetTime = format.format(new Date(resetCal.getTimeInMillis()+AlarmManager.INTERVAL_DAY));*/
//
//        //알람 삭제
//       /* AlarmManager am = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);
//
//        if (mPendingIntent != null)
//        { am.cancel(mPendingIntent);
//        mPendingIntent.cancel(); }*/

        return view;
    }

//    public void getData(String date) {
//        SharedPreferences prefs = getContext().getSharedPreferences("my"+date, MODE_PRIVATE);
//        t1 = prefs.getInt("start", 0);
//        for(int i=0; i<t1+1; i++) {
//            String tx;
//            tx = prefs.getString("" + i, null);
//            if(tx != null) {
//                adapter.addItem(tx);
//            }
//        }
//    }

    public String getTime(SimpleDateFormat Format) {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return Format.format(mDate);
    }

    public void setGoal() {
        textview_goal = (TextView) view.findViewById(R.id.textview_goal);
        count = listview.getCheckedItemCount();
        count_all = adapter.getCount();

        if (count == 0) {
            textview_goal.setText("0 %");
        } else {
            double rate = Double.parseDouble(String.format("%.2f", count / count_all));
            int goal_rate = (int) (rate * 100);
            textview_goal.setText(goal_rate + " %");
        }
    }

//    @Override
//    public void  onPause(){
//        super.onPause();
//
//        pref = getContext().getSharedPreferences("my", 0);
//
//        //저장을위해 Edit 객체 호출
//        SharedPreferences.Editor edit = pref.edit();
//
//        //저장하기전에 다지우기
//        edit.clear();
//        edit.commit();
//
//        //지금까지 생성된 리스트뷰 텍스트 저장
//        for(int i =0; i< adapter.getCount(); i++){
//
//            edit.putString(""+i, adapter.getItem(i).toString());
//
//            CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1) ;
//
//            edit.putBoolean("check"+i, cb.isChecked());
//
//            edit.putInt("start", i);
//
//            //변경된 값 저장
//            edit.commit();
//        }
//    }
}