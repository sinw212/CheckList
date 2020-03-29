package com.example.clfp4.ListView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.clfp4.R;

import java.util.ArrayList;

public class CheckListAdapter extends BaseAdapter {

    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<CheckListData> checkListDataArrayList = new ArrayList<CheckListData>() ;

    // ListViewAdapter의 생성자
    public CheckListAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return checkListDataArrayList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "list_checklist" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_checklist, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView checklist_checkbox = convertView.findViewById(R.id.checklist_checkbox);
        TextView checklist_todo = convertView.findViewById(R.id.checklist_todo);

        // Data Set(checklistData)에서 position에 위치한 데이터 참조 획득
        CheckListData checklistData = checkListDataArrayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        checklist_checkbox.setText(checklistData.getCheckbox());
        checklist_todo.setText(checklistData.getTodo());

        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return checkListDataArrayList.get(position);
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String todo) {
        CheckListData item = new CheckListData();

//        item.setCheckbox(checkbox); 체크박스 추가 확인
        item.setTodo(todo);

        checkListDataArrayList.add(item);
    }

    // 아이템 삭제를 위한 함수.
    public void removeItem(int i){
        checkListDataArrayList.remove(i);
    }
}