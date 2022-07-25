package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ListViewAdapter extends ArrayAdapter<JSONObject> {
    int listLayout;
    ArrayList<JSONObject> userList;
    Context context;

    public ListViewAdapter(Context context, int listLayout, int fiald, ArrayList<JSONObject> userList) {
        super(context, listLayout, fiald, userList);
        this.context = context;
        this.listLayout = listLayout;
        this.userList = userList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View listViewItem = inflater.inflate(listLayout, null, false);
        TextView name = listViewItem.findViewById(R.id.titleName);
        try {
            name.setText(userList.get(position).getString("name"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listViewItem;


    }
}

