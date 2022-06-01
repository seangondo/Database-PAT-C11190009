package com.example.database;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context c;
    ArrayList id, name, pwd;
    LayoutInflater inflater;

    public CustomAdapter(Context c, ArrayList id, ArrayList name, ArrayList pwd) {
        this.c = c;
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        inflater = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return id.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.my_lvelement, null);
        TextView myId = (TextView) view.findViewById(R.id.tv4);
        TextView myName = (TextView) view.findViewById(R.id.tv5);
        TextView myPwd = (TextView) view.findViewById(R.id.tv6);
        myId.setText(String.valueOf(id.get(i))+".");
        myName.setText("ID : " + String.valueOf(name.get(i)));
        myPwd.setText("Password : " + String.valueOf(pwd.get(i)));
        return view;
    }
}
