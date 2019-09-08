package com.example.clfp4;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
<<<<<<< HEAD
import android.widget.Checkable;
=======
>>>>>>> CLFPmaster
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.ALARM_SERVICE;
import static android.content.Context.MODE_PRIVATE;

public class daily4 extends Fragment {
<<<<<<< HEAD
    private static final Object JSON = null;
    View view;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh시 mm분 ss초");
    EditText edit_text;
    CustomChoiceListViewAdapter adapter;
    ListView listview;
=======
    View view;
    long mNow;
    Date mDate;

    //    SimpleDateFormat mFormat = new SimpleDateFormat("YYYY년 MM월 dd일");
    //    ArrayList<String> items;
    EditText edit_text;
    SimpleDateFormat mFormat = new SimpleDateFormat("hh시 mm분 ss초");
    //    ArrayAdapter<String> adapter;
    CustomChoiceListViewAdapter adapter;
    ListView listview;
    //    SparseBooleanArray checkedItems;
>>>>>>> CLFPmaster
    String getEdit;
    double count = 0.0;
    double count_all = 0.0;
    TextView textview_goal;
    TextView textview_date;
<<<<<<< HEAD
=======
    String strDate;
>>>>>>> CLFPmaster

    SharedPreferences pref = null;
    int t1;

    Calendar c;
<<<<<<< HEAD
    int nYear,nMon,nDay;
    DatePickerDialog.OnDateSetListener mDateSetListener;

=======
    int nYear, nMon, nDay;
    DatePickerDialog.OnDateSetListener mDateSetListener;
>>>>>>> CLFPmaster

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.daily4, container, false);

        // 오늘 날짜 표현
        textview_date = (TextView) view.findViewById(R.id.textview_date);
        textview_date.setText(getTime());

        ImageButton addButton = (ImageButton) view.findViewById(R.id.button_plus);
        ImageButton modifyButton = (ImageButton) view.findViewById(R.id.button_modify);
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.button_delete);
        ImageButton button_calendar = (ImageButton) view.findViewById(R.id.button_calender);

        edit_text = (EditText) view.findViewById(R.id.edit_text);

<<<<<<< HEAD
        // Adapter 생성성
=======
//        items = new ArrayList<String>();
//         ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
//        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_multiple_choice, items) ;

        //Adapter 생성
>>>>>>> CLFPmaster
        adapter = new CustomChoiceListViewAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) view.findViewById(R.id.listview1);
        listview.setAdapter(adapter);

<<<<<<< HEAD
       // 데이터 표현
       SharedPreferences prefs = getContext().getSharedPreferences("my", MODE_PRIVATE);
=======
        // 데이터 표현
        SharedPreferences prefs = getContext().getSharedPreferences("my", MODE_PRIVATE);
>>>>>>> CLFPmaster
        t1 = prefs.getInt("start", 0);

        for (int i = 0; i < t1+1; i++) {
            String tx;
<<<<<<< HEAD
            //Boolean ty;
            tx = prefs.getString("" + i, null);
            //ty = prefs.getBoolean("check"+i,false);
            if(tx != null){
                adapter.addItem(tx);
            }

        }

        // Calendar

        //DatePicker Listener
        mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String strDate = String.valueOf(year) + "년 ";

                        if(monthOfYear + 1 > 0 && monthOfYear + 1 < 10){
                            strDate = "0" + String.valueOf(monthOfYear+1) +"월";
                        }
                        else
                        {
                            strDate = String.valueOf(monthOfYear+1) +"월";
                        }

                        strDate += String.valueOf(dayOfMonth) + "일";

                        textview_date.setText(strDate);

                        //Toast.makeText(getContext(), strDate, Toast.LENGTH_SHORT).show();
                    }
                };

        c = Calendar.getInstance();
        nYear = c.get(Calendar.YEAR);
        nMon = c.get(Calendar.MONTH);
        nDay = c.get(Calendar.DAY_OF_MONTH);

        ImageButton button_calendar = (ImageButton) view.findViewById(R.id.button_calender);

        button_calendar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

=======
            tx = prefs.getString("" + i, null);
            if(tx != null ){
                adapter.addItem(tx);
            }
        }

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

                        if (dayOfMonth + 1 > 0 && dayOfMonth + 1 < 10)
                            strDate += "0" + String.valueOf(dayOfMonth + 1) + "일";
                        else
                            strDate += String.valueOf(dayOfMonth + 1) + "일";

                        textview_date.setText(strDate);
                        //Toast.makeText(getContext(), strDate, Toast.LENGTH_SHORT).show();
                    }
                };

        c = Calendar.getInstance();
        nYear = c.get(Calendar.YEAR);
        nMon = c.get(Calendar.MONTH);
        nDay = c.get(Calendar.DAY_OF_MONTH);

        button_calendar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

>>>>>>> CLFPmaster
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
<<<<<<< HEAD
                }
                else {
=======
                } else {
//                    items.add(getEdit);
>>>>>>> CLFPmaster
                    adapter.addItem(getEdit);

                    // listview 갱신
                    adapter.notifyDataSetChanged();

                    edit_text.setText("");
<<<<<<< HEAD

                }

                setGoal();

=======
                }
                setGoal();
>>>>>>> CLFPmaster
            }
        });
        // 수정 버튼 리스너
        modifyButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for(int i=count-1; i>=0; i--) {
                    if (checkedItems.get(i)) {
                        adapter.setItem(i,edit_text.getText().toString());
=======
//                checkedItems = listview.getCheckedItemPositions();
//                int count = adapter.getCount() ;
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
//                        items.set(i,"") ;
//                        items.set(i,edit_text.getText().toString()) ;
                        adapter.setItem(i, edit_text.getText().toString());
>>>>>>> CLFPmaster
                    }
                }

                adapter.notifyDataSetChanged();
<<<<<<< HEAD
=======
//                edit_text.setText("");
>>>>>>> CLFPmaster
                listview.clearChoices();

                setGoal();
            }
<<<<<<< HEAD
        }) ;
=======
        });
>>>>>>> CLFPmaster
        // 삭제버튼 리스너
        deleteButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
<<<<<<< HEAD
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for(int i=count-1; i>=0; i--) {
                    if (checkedItems.get(i)) {
                        adapter.removeItem(i);
                    }
                }

                adapter.notifyDataSetChanged();
                listview.clearChoices();

                setGoal();

=======
//                checkedItems = listview.getCheckedItemPositions();
                SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for (int i = count - 1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
//                        items.remove(i);
                        adapter.removeItem(i);
                    }
                }
                adapter.notifyDataSetChanged();

                listview.clearChoices();

                setGoal();
>>>>>>> CLFPmaster
            }
        });

        // 리스트뷰 리스너
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
               setGoal();
            }
        });

        // 알람 매니저
        Intent mAlarmIntent = new Intent(this.getActivity(),AlarmReceiver.class);

        PendingIntent mPendingIntent =
                PendingIntent.getBroadcast(
                        this.getActivity(),
                        0,
                        mAlarmIntent,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );

       // 자정 시간
        Calendar resetCal = Calendar.getInstance();
        resetCal.setTimeInMillis(System.currentTimeMillis());
        resetCal.set(Calendar.HOUR_OF_DAY, 22);
        resetCal.set(Calendar.MINUTE,25);
        resetCal.set(Calendar.SECOND, 0);

        AlarmManager mAlarmManager = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        //다음날 0시에 맞추기 위해 24시간을 뜻하는 상수인 AlarmManager.INTERVAL_DAY를 더해줌.
        mAlarmManager.setRepeating(AlarmManager.RTC, resetCal.getTimeInMillis(), AlarmManager.INTERVAL_DAY, mPendingIntent);

       /* SimpleDateFormat format = new SimpleDateFormat("MM/dd kk:mm:ss");
        String setResetTime = format.format(new Date(resetCal.getTimeInMillis()+AlarmManager.INTERVAL_DAY));*/

        //알람 삭제
       /* AlarmManager am = (AlarmManager) getActivity().getSystemService(ALARM_SERVICE);

        if (mPendingIntent != null)
        { am.cancel(mPendingIntent);
        mPendingIntent.cancel(); }*/
=======
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
>>>>>>> CLFPmaster

        return view;
    }

<<<<<<< HEAD
     public String getTime() {
=======
    public String getTime() {
>>>>>>> CLFPmaster
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    public void setGoal() {
        textview_goal = (TextView) view.findViewById(R.id.textview_goal);
        count = listview.getCheckedItemCount();
        count_all = adapter.getCount();

<<<<<<< HEAD
        if(count == 0 ) {
            textview_goal.setText("0 %");
        }
        else {
            double rate = Double.parseDouble(String.format("%.2f", count/count_all));
=======
        if (count == 0) {
            textview_goal.setText("0 %");
        } else {
            double rate = Double.parseDouble(String.format("%.2f", count / count_all));
>>>>>>> CLFPmaster
            int goal_rate = (int) (rate * 100);
            textview_goal.setText(goal_rate + " %");
        }
    }

    @Override
<<<<<<< HEAD
        public void  onPause(){
            super.onPause();

            pref = getContext().getSharedPreferences("my", 0);

            //저장을위해 Edit 객체 호출
            SharedPreferences.Editor edit = pref.edit();

            //저장하기전에 다지우기
            edit.clear();
            edit.commit();

            //CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1) ;


        //지금까지 생성된 리스트뷰 텍스트 저장
            for(int i =0; i<adapter.getCount(); i++){
                //edit.putString(""+i, listview.g);
               /* if (cb.isChecked()) {
                    edit.putBoolean("check"+i,true);
                }
                else
                {
                    edit.putBoolean("check"+i,false);
                }*/
                edit.putInt("start", i);

                //변경된 값 저장
                edit.commit();
            }

=======
    public void  onPause(){
        super.onPause();

        pref = getContext().getSharedPreferences("my", 0);

        //저장을위해 Edit 객체 호출
        SharedPreferences.Editor edit = pref.edit();

        //저장하기전에 다지우기
        edit.clear();
        edit.commit();

        //지금까지 생성된 리스트뷰 텍스트 저장
        for(int i =0; i< adapter.getCount(); i++){

            edit.putString(""+i, adapter.getItem(i).toString());

            CheckBox cb = (CheckBox) view.findViewById(R.id.checkBox1) ;

            edit.putBoolean("check"+i, cb.isChecked());

            edit.putInt("start", i);

            //변경된 값 저장
            edit.commit();
        }
>>>>>>> CLFPmaster
    }
}