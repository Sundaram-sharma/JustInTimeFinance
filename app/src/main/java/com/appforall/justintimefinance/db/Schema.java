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
        String query = "create table IF NOT EXISTS cv_user(id integer primary key autoincrement, firstname varchar(255), lastname varchar(255), username varchar(255), password varchar(255), accountnumber text, email text,  phonenumber text)";
        Log.i("schema:", query);
        db.execSQL(query);
        Log.i("info","executed");
    }
}
