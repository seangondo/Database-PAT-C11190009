package com.example.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    DBHandler mydb;
    DataModel usermodel;
    Button b1;
    ListView mylv;

    ArrayList<String> myId, myName, myPwd;

    private final View.OnClickListener myClickList = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    goToPage2();
                    break;
            }
        }
    };

    private void goToPage2() {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
        overridePendingTransition(0,0);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHandler(this);
        myId = new ArrayList<>();
        myName = new ArrayList<>();
        myPwd = new ArrayList<>();

        displayData();

        mylv = (ListView) findViewById(R.id.lv);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), myId, myName, myPwd);
        mylv.setAdapter(customAdapter);

        b1 = findViewById(R.id.button);
        b1.setOnClickListener(myClickList);
        List<ArrayList> data = mydb.getAll();
        Log.d("MyApps", String.valueOf(data));
    }

    void displayData(){
        Cursor c = mydb.getAllData();
        if(c.getCount() == 0){
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        } else {
            while (c.moveToNext()) {
                myId.add(c.getString(0));
                myName.add(c.getString(1));
                myPwd.add(c.getString(2));
            }
        }
    }
}