package com.example.clfp4;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class monthly4 extends Fragment {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("YYYY년");
    ArrayList<String> items;
    ListView listview;
    DatePickerDialog.OnDateSetListener myDatePicker;
    TextView textview_date;

    Calendar c;
    int nYear,nMon,nDay;
    DatePickerDialog.OnDateSetListener mDateSetListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.monthly4, container, false);

        // 오늘 날짜 표현
        textview_date = (TextView) view.findViewById(R.id.textview_date);
        textview_date.setText(getTime());

        ImageButton button_calendar = (ImageButton) view.findViewById(R.id.button_calender);

        // Calendar
        //DatePicker Listener
        mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {
                        String strDate = String.valueOf(year) + "년";

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

                DatePickerDialog oDialog = new DatePickerDialog(getContext(),
                        mDateSetListener, nYear, nMon, nDay);

                oDialog.show();
            }
        });

        return view;
    }
    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
}