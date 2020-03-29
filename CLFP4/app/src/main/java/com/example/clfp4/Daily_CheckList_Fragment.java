package com.example.clfp4;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clfp4.ListView.CheckListAdapter;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Daily_CheckList_Fragment extends Fragment {
    private long mNow;
    private Date mDate;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");

    private Calendar c;
    private int nYear, nMon, nDay;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private TextView tv_date, tv_goal;
    private ImageButton btn_calendar;
    private Button btn_add;
    private EditText et_today_todo;
    private ListView listview_todolist;
    private CheckListAdapter checklistAdapter;

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
        getEdit = "";

        // 오늘 날짜 표현
        tv_date.setText(getTime(mFormat));

        //Adapter 생성
        checklistAdapter = new CheckListAdapter();

        // 리스트뷰 참조 및 Adapter달기
        listview_todolist.setAdapter(checklistAdapter);

        // 리스트뷰 리스너
        listview_todolist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setGoal();
            }
        });

        listview_todolist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                           final int position, long id) {

                AlertDialog.Builder alertDlg = new AlertDialog.Builder(view.getContext());
                alertDlg.setTitle("삭제");

                // '예' 버튼이 클릭되면
                alertDlg.setPositiveButton( "예", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which )
                    {
                        checklistAdapter.removeItem(position);

                        // 아래 method를 호출하지 않을 경우, 삭제된 item이 화면에 계속 보여진다.
                        checklistAdapter.notifyDataSetChanged();
                        dialog.dismiss();  // AlertDialog를 닫는다.
                    }
                });

                // '아니오' 버튼이 클릭되면
                alertDlg.setNegativeButton( "아니오", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick( DialogInterface dialog, int which ) {
                        dialog.dismiss();  // AlertDialog를 닫는다.
                    }
                });

                alertDlg.setMessage( "삭제하시겠습니까?");
                alertDlg.show();

                setGoal();

                // 이벤트 처리 종료 , 여기만 리스너 적용시키고 싶으면 true , 아니면 false
                return true;
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

                if (getEdit.getBytes().length <= 0) {
                    Toast.makeText(getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    checklistAdapter.addItem(getEdit);

                    // listview 갱신
                    checklistAdapter.notifyDataSetChanged();
                    et_today_todo.setText("");
                }
                setGoal();
            }
        });

//        이거 써야함
//        public void onClick(View view) {
//            try {
//                String result;
//                CustomTask task = new CustomTask();
//                result = task.execute("rain483","1234").get();
//                Log.i("리턴 값",result);
//            } catch (Exception e) {
//
//            }
//        }
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

        if (count > 0) {
            double rate = Double.parseDouble(String.format("%.2f", count / count_all));
            int goal_rate = (int) (rate * 100);
            tv_goal.setText(goal_rate + " %");
        } else {
            tv_goal.setText("0 %");
        }
    }
}