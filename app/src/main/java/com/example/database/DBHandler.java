package com.example.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String TAG = "MyActivity";
    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "database";

    private static final String TB_NAME = "Users";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_PASS = "password";

    public DBHandler(Context c) {
        super(c, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE "+TB_NAME+"("+KEY_ID+" INTEGER PRIMARY KEY,"+KEY_NAMA+" TEXT,"+KEY_PASS+" TEXT"+")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TB_NAME);
        onCreate(db);
    }
    /*public boolean insertUser(String nama, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nama", nama);
        cv.put("password", password);
        db.insert(TB_NAME, null, cv);
        return true;
    }*/

    public boolean insertUser(DataModel user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NAMA, user.getNama());
        cv.put(KEY_PASS, user.getPassword());
        db.insert(TB_NAME, null, cv);
        return true;
    }

    public Cursor getData(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM"+TB_NAME+" WHERE id="+id,null);
        return result;
    }

    public Cursor getAllData() {
        String query = "SELECT * FROM "+TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = null;

        if(db != null){
            c = db.rawQuery(query,null);
        }
        return c;
    }

    public ArrayList getAll() {
        ArrayList semuaData = new ArrayList();
        String query = "SELECT * FROM "+TB_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(query,null);

        if(c.moveToFirst()) {
            do {
                semuaData.add("\n"+c.getString(0)+".\tUsername : "+c.getString(1)+"\tPassword : "+c.getString(2));
            } while (c.moveToNext());
        };
        return semuaData;
    }
}
