package com.example.clfp4.ListView;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.example.clfp4.R;

public class CheckableLinearLayout extends LinearLayout implements Checkable {

    // 만약 CheckBox가 아닌 View를 추가한다면 아래의 변수 사용 가능.
    // private boolean mIsChecked ;

    public CheckableLinearLayout(Context context , AttributeSet attrs) {
        super(context, attrs);

        // mIsChecked = false ;
    }

    // 현재 Checked 상태를 리턴.
    @Override
    public boolean isChecked() {
        CheckBox cb = (CheckBox) findViewById(R.id.checklist_checkbox) ;

        return cb.isChecked() ;
        // return mIsChecked ;
    }

    // 현재 Checked 상태를 바꿈. (UI에 반영)
    @Override
    public void toggle() {
        CheckBox cb = (CheckBox) findViewById(R.id.checklist_checkbox) ;

        setChecked(cb.isChecked() ? false : true) ;
        // setChecked(mIsChecked ? false : true) ;
    }

    // Checked 상태를 checked 변수대로 설정.
    @Override
    public void setChecked(boolean checked) {
        CheckBox cb = (CheckBox) findViewById(R.id.checklist_checkbox) ;

        if (cb.isChecked() != checked) {
            cb.setChecked(checked) ;
        }
        // CheckBox 가 아닌 View의 상태 변경.
    }
}