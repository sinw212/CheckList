package com.example.clfp4;

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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class daily4 extends Fragment {
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy년 MM월 dd일");
    ArrayList<String> items;
    EditText edit_text;
    ArrayAdapter adapter;
    ListView listview;
    SparseBooleanArray checkedItems;
    String getEdit;
    double count = 0.0;
    double count_all = 0.0;
    TextView textview_goal;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.daily4, container, false);

        ImageButton addButton = (ImageButton) view.findViewById(R.id.button_plus);
        ImageButton modifyButton = (ImageButton) view.findViewById(R.id.button_modify);
        ImageButton deleteButton = (ImageButton) view.findViewById(R.id.button_delete);

        edit_text = (EditText) view.findViewById(R.id.edit_text);

        items = new ArrayList<>();
        // ArrayAdapter 생성. 아이템 View를 선택(multiple choice)가능하도록 만듦.
        adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_multiple_choice, items) ;

        // listview 생성 및 adapter 지정.
        listview = (ListView) view.findViewById(R.id.listview1) ;
        listview.setAdapter(adapter) ;

        addButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                getEdit = edit_text.getText().toString();

                if(getEdit.getBytes().length <= 0) {
                    Toast.makeText(getContext(), "내용을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
                else {
                    items.add(getEdit);
                    // listview 갱신
                    adapter.notifyDataSetChanged();
                    edit_text.setText("");
                }

                count_all = adapter.getCount();
                if(count == 0 ) {
                    textview_goal.setText("0 %");
                }
                else {
                    double rate = Double.parseDouble(String.format("%.2f", count/count_all));
                    int goal_rate = (int) (rate * 100);
                    textview_goal.setText(goal_rate + " %");
                }
            }
        });

        modifyButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount() ;

                for (int i = count-1; i >= 0; i--) {
                    if (checkedItems.get(i)) {
                        items.set(i,"") ;
                        items.set(i,edit_text.getText().toString()) ;
                    }
                }
                adapter.notifyDataSetChanged();
                edit_text.setText("");
            }
        }) ;

        deleteButton.setOnClickListener(new ImageButton.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkedItems = listview.getCheckedItemPositions();
                int count = adapter.getCount();

                for(int i=count-1; i>=0; i--) {
                    if (checkedItems.get(i))
                        items.remove(i);
                }
                listview.clearChoices();
                adapter.notifyDataSetChanged();
                textview_goal.setText("0 %");
            }
        });

        TextView textview_date = (TextView) view.findViewById(R.id.textview_date);
        textview_date.setText(getTime());

        textview_goal = (TextView) view.findViewById(R.id.textview_goal);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                count = listview.getCheckedItemCount();
                count_all = adapter.getCount();

                if(count == 0 ) {
                    textview_goal.setText("0 %");
                }
                else {
                    double rate = Double.parseDouble(String.format("%.2f", count/count_all));
                    int goal_rate = (int) (rate * 100);
                    textview_goal.setText(goal_rate + " %");
                }
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