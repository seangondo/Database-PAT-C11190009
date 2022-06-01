package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity2 extends AppCompatActivity {
    DBHandler mydb;
    DataModel usermodel;
    Button b2, b3;
    EditText name, pass;
    TextView tv;

    private final View.OnClickListener myClickList = new View.OnClickListener() {
        @Override
        public void onClick(View view){
            switch (view.getId()) {
                case R.id.button2:
                    goToPage1();
                    break;
                case R.id.button3:
                    addUser();
                    break;
            }
        }
    };

    private void goToPage1() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        overridePendingTransition(0,0);
    }

    public void addUser(){
        mydb = new DBHandler(this);
        usermodel = new DataModel(1, name.getText().toString(), pass.getText().toString());
        mydb.insertUser(usermodel);
        Toast.makeText(this, "Berhasil Menambah User!", Toast.LENGTH_SHORT).show();

        name.setText("");
        pass.setText("");
        name.setVisibility(View.VISIBLE);
        pass.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = findViewById(R.id.tv1);
        b2 = findViewById(R.id.button2);
        b3 = findViewById(R.id.button3);
        name = findViewById(R.id.nama);
        pass = findViewById(R.id.pass);

        b2.setOnClickListener(myClickList);
        b3.setOnClickListener(myClickList);
    }
}
