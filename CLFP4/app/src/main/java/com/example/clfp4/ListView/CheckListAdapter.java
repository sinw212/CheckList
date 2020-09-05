package com.example.clfp4.ListView;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.clfp4.Network.AppHelper;
import com.example.clfp4.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CheckListAdapter extends BaseAdapter {

    private String url_daily = "http://192.168.35.92:8080/CheckList/Daily.jsp";

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
        TextView checklist_todo = convertView.findViewById(R.id.checklist_todo);

        // Data Set(checklistData)에서 position에 위치한 데이터 참조 획득
        CheckListData checklistData = checkListDataArrayList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
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

        item.setTodo(todo);

        checkListDataArrayList.add(item);
    }

    // 아이템 삭제를 위한 함수.
    public void removeItem(int i){
        checkListDataArrayList.remove(i);
    }

    // 아이템 추가
    public void todolist_AddRequest(final String date, final String todo) {

        // Request Obejct인 StringRequest 생성
        StringRequest request = new StringRequest(Request.Method.POST, url_daily,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("todoAddSuccess"))
                        {
                            Log.d("통신 메세지", "[" + response + "]"); // 서버와의 통신 결과 확인 목적
                        }
                        else if(response.equals("error")){
                            Log.d("통신 메세지", "[" + response + "]"); // 서버와의 통신 결과 확인 목적
                        }
                        else {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("통신 에러", "[" + error.getMessage() + "]");
                        Log.v("통신 에러 이유",error.getStackTrace().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date",date);
                params.put("todo",todo);
                params.put("type", "todoAdd");
                return params;
            }
        };

        request.setShouldCache(false); // 이전 결과가 있더라도 새로 요청해서 응답을 보여줌
        AppHelper.requestqueue.add(request); // request queue 에 request 객체를 넣어준다.

    }

    // 아이템 삭제
    public void todolist_DeleteRequest(final String date, final String todo) {

        // Request Obejct인 StringRequest 생성
        StringRequest request = new StringRequest(Request.Method.POST, url_daily,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("todoDelete")){
                            Log.d("통신 메세지", "[" + response + "]"); // 서버와의 통신 결과 확인 목적
                        }
                        else if(response.equals("error")){
                            Log.d("통신 메세지", "[" + response + "]"); // 서버와의 통신 결과 확인 목적
                        }
                        else {

                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("통신 에러", "[" + error.getMessage() + "]");
                        Log.v("통신 에러 이유",error.getStackTrace().toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("date",date);
                params.put("todo",todo);
                params.put("type", "todoDelete");
                return params;
            }
        };

        request.setShouldCache(false); // 이전 결과가 있더라도 새로 요청해서 응답을 보여줌
        AppHelper.requestqueue.add(request); // request queue 에 request 객체를 넣어준다.

    }
}