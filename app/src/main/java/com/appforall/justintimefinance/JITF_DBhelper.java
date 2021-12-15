package com.appforall.justintimefinance;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class JITF_DBhelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";

    public JITF_DBhelper( Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users(accountNumber TEXT, email TEXT, name TEXT, password TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int oldVersion, int newVersion) {
        MyDB.execSQL("drop Table if exists users");

    }

    public Boolean insertingData(String accountNumber, String email, String name, String password){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues CV = new ContentValues();
        CV.put("accountNumber", accountNumber);
        CV.put("email", email);
        CV.put("name", name);
        CV.put("password", password);
        long result = MyDB.insert("users",null,CV); //all the vales from CV will be put inside the database
        if(result == -1) // if data insertion in database is not possible
            return false;
        else            // if data insertion in database is possible
            return true;

    }

    public Boolean checkingEmail(String email){ //checking if the user email id exist

        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ?", new String[]{email});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkingEmailPassword(String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM users WHERE email = ? AND password = ?", new String[]{email,password});
        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }

        


}
