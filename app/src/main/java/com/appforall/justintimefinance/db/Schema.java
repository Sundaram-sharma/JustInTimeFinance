package com.appforall.justintimefinance.db;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class Schema {
    Schema(SQLiteDatabase db)
    {
        ConfigInstaller(db);
    }

    public void ConfigInstaller(SQLiteDatabase db)
    {
        Log.i("info:", "to create table");
        String query = "create table IF NOT EXISTS cv_user(id integer primary key autoincrement, firstname varchar(255), lastname varchar(255), username varchar(255), password varchar(255), email text,  phonenumber text)";
        Log.i("schema:", query);
        db.execSQL(query);
        query = "create table IF NOT EXISTS cv_card(id integer primary key autoincrement, bankname varchar(255), accountnumber varchar(255), expirydate varchar(255), cvv varchar(255))";
        Log.i("schema:", query);
        db.execSQL(query);
        query = "create table IF NOT EXISTS cv_payee(id integer primary key autoincrement, firstname varchar(255), lastname varchar(255), username varchar(255), accountnumber text, email text,  phonenumber text)";
        Log.i("schema:", query);
        db.execSQL(query);
        query = "create table IF NOT EXISTS cv_transaction(id integer primary key autoincrement, payeeid varchar(255), amount varchar(255), transfermethod varchar(255), transfertype varchar(255), description text)";
        Log.i("schema:", query);
        db.execSQL(query);
        Log.i("info","executed");
    }
}
