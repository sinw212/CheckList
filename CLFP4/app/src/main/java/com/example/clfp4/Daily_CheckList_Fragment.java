package com.example.clfp4;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.clfp4.ListView.CheckListAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Daily_CheckList_Fragment extends Fragment {
    private long mNow;
    private Date mDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("YYYY년 MM월 dd일");

    private Calendar c;
    private int nYear, nMon, nDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView tv_date, tv_goal;
    private ImageButton btn_calendar;
    private Button btn_add;
    private EditText et_today_todo;
    private SwipeMenuListView listview_todolist;
    private CheckListAdapter checklistAdapter;

    private String Number;
    private int number = 1;
    private String getEdit;
    private double count = 0.0;
    private double count_all = 0.0;

    int ck_arr[] = new int[100];


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_daily_checklist, container, false);

        return v;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tv_date = getView().findViewById(R.id.tv_date);
        tv_goal = getView().findViewById(R.id.tv_goal);
        btn_calendar = getView().findViewById(R.id.btn_calender);
        btn_add = getView().findViewById(R.id.btn_add);
        et_today_todo = getView().findViewById(R.id.et_today_todo);
        listview_todolist = getView().findViewById(R.id.listview_todolist);
        Number = "";
        getEdit = "";

        // 오늘 날짜 표현
        tv_date.setText(getTime(mFormat));

        //Adapter 생성
        checklistAdapter = new CheckListAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview_todolist = getView().findViewById(R.id.listview_todolist);
        listview_todolist.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);

        listview_todolist.setAdapter(checklistAdapter);

        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {
                // Create different menus depending on the view type
                SwipeMenuItem modifyItem = new SwipeMenuItem(getActivity());
                // set item background
                modifyItem.setBackground(new ColorDrawable(Color.rgb(187, 187, 187)));
                // set item width
                modifyItem.setWidth(dp2px(90));
                // add to menu
                menu.addMenuItem(modifyItem);
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(getActivity());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(198, 67, 40)));
                // set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }

        };
        // set creator
        listview_todolist.setMenuCreator(creator);

        listview_todolist.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
//                        checklistAdapter.setItem(position, et_today_todo.getText().toString());

                        checklistAdapter.notifyDataSetChanged();
                        et_today_todo.setText("");

                        setGoal();
                        break;
                    case 1:
//                        checklistAdapter.removeItem(position);

                        checklistAdapter.notifyDataSetChanged();

                        setGoal();
                        break;
                }
                return true;
            }

        });

        // 리스트뷰 리스너
        listview_todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setGoal();
            }
        });

        // Calendar
        //DatePicker Listener
        mDateSetListener =
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year, int monthOfYear,
                                          int dayOfMonth) {

                        String strDate = String.valueOf(year) + "년 ";

                        if (monthOfYear + 1 > 0 && monthOfYear + 1 < 10)
                            strDate += "0" + String.valueOf(monthOfYear + 1) + "월 ";
                        else
                            strDate += String.valueOf(monthOfYear + 1) + "월 ";

                        if (dayOfMonth > 0 && dayOfMonth < 10)
                            strDate += "0" + String.valueOf(dayOfMonth) + "일";
                        else
                            strDate += String.valueOf(dayOfMonth) + "일";

                        tv_date.setText(strDate);
                    }
                };

        c = Calendar.getInstance();
        nYear = c.get(Calendar.YEAR);
        nMon = c.get(Calendar.MONTH);
        nDay = c.get(Calendar.DAY_OF_MONTH);

        // 달력 아이콘 리스너
        btn_calendar.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog oDialog = new DatePickerDialog(getContext(),
                        mDateSetListener, nYear, nMon, nDay);

                oDialog.show();
            }
        });

        // 추가 버튼 리스너
        btn_add.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit = et_today_todo.getText().toString();
                //임시 세팅
                Number = String.valueOf(number);
                number++;

                if (getEdit.getBytes().length <= 0) {
                    Toast.makeText(getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    checklistAdapter.addItem(Number, getEdit);

                    // listview 갱신
                    checklistAdapter.notifyDataSetChanged();
                    et_today_todo.setText("");
                }
                setGoal();
            }
        });
    }

    public String getTime(SimpleDateFormat Format) {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return Format.format(mDate);
    }

    public void setGoal() {
        tv_goal = getView().findViewById(R.id.tv_goal);
        count = listview_todolist.getCheckedItemCount();
        count_all = checklistAdapter.getCount();

        if (count == 0) {
            tv_goal.setText("0 %");
        } else {
            double rate = Double.parseDouble(String.format("%.2f", count / count_all));
            int goal_rate = (int) (rate * 100);
            tv_goal.setText(goal_rate + " %");
        }
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());

    }
}