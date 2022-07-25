package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
private static final String JSON_URL="http://candidate.scid.ru/api/books?page=1";
ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView  = (ListView)findViewById(R.id.list);
        LoadJSONFroURL(JSON_URL);
    }
    private void LoadJSONFroURL(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
        new Response.Listener<String>(){
            @Override
            public void onResponse (String response){
                try {
                    JSONObject object =  new JSONObject(response);
                    JSONArray jsonArray  = object.getJSONArray("data");
                    ArrayList<JSONObject> listItems =getArrayListFromJSONArray(jsonArray);
                    ListAdapter adapter = new ListViewAdapter(getApplicationContext(), R.layout.row,R.id.titleName,listItems);
                    listView.setAdapter(adapter);

                }catch (JSONException e){
                    e. printStackTrace();
                }
            }
        }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    private ArrayList<JSONObject> getArrayListFromJSONArray(JSONArray jsonArray){
        ArrayList<JSONObject> aList = new ArrayList<JSONObject>();
        try {
            if(jsonArray!=null){
                for ( int i=0;i<jsonArray.length();i++){
                    aList.add(jsonArray.getJSONObject(i));
                }
            }
        }catch (JSONException js){
            js.printStackTrace();
        }
        return aList;
    }
}