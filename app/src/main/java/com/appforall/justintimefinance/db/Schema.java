package com.appforall.justintimefinance.db;
import android.content.ContentValues;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;

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
        query = "create table IF NOT EXISTS cv_card(id integer primary key autoincrement, bankid varchar(255), cardnumber varchar(255) unique, expirydate varchar(255), cvv varchar(255), userid varchar(20))";
        Log.i("schema:", query);
        db.execSQL(query);
//        query = "create table IF NOT EXISTS cv_payee(id integer primary key autoincrement, firstname varchar(255), lastname varchar(255), username varchar(255), accountnumber text, email text,  phonenumber text, image text)";
//        Log.i("schema:", query);
        db.execSQL(query);
        query = "create table IF NOT EXISTS cv_transaction(id integer primary key autoincrement, payeeid varchar(255), amount varchar(255), transfermethod varchar(255), transfertype varchar(255), description text)";
        Log.i("schema:", query);
        query = "create table IF NOT EXISTS cv_bank(id integer primary key autoincrement, bankname varchar(255) unique)";
        db.execSQL(query);

        DefaultValues(db);
        Log.i("info","executed");
    }

    public long DefaultValues(SQLiteDatabase db)
    {
        boolean check = false;
        String query = "";
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("bankname", "ICICI Bank");
            result = db.insert("cv_bank", null, values);
            values = new ContentValues();
            values.put("bankname", "CIBC Bank");
            result = db.insert("cv_bank", null, values);
            values = new ContentValues();
            values.put("bankname", "Scotia Bank");
            result = db.insert("cv_bank", null, values);
            values = new ContentValues();
            values.put("bankname", "RBC Royal Bank");
            result = db.insert("cv_bank", null, values);
            values = new ContentValues();
            values.put("bankname", "SBI Canada Bank");
            result = db.insert("cv_bank", null, values);

            Log.i("Add Bank:", String.valueOf(result));

            // db.close();
        } catch (SQLiteConstraintException e)
        {
            Log.e("Add Bank in catch:","SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Add Bank out of catch:-", String.valueOf(result));
        return result;
    }
}
