package com.appforall.justintimefinance.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.appforall.justintimefinance.MenuActions.FundTransfer;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.CardDetail;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.FundTransfers;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.Transaction;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "justfinancee.db";
    private static final int DATABASE_Version = 1;
    private static String userid = "";

    public DatabaseHandler(Context context) { //going to create database
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("info:", "Worked dff dfggggd ");
        Schema schema = new Schema(db); //going to run the installer
        schema.ConfigInstaller(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cv_user");
        onCreate(db);
    }

    public Boolean Authentication(User user) { //checking if user exists or not
        boolean check = false;
        String query = "";
        try {
            SQLiteDatabase db = getWritableDatabase();
            Cursor c = db.rawQuery(query, null);
//        Log.i("info","after execute");
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            if (c.getColumnIndex(COLUMN_STUDENT_FIRST_NAME) > 0) {

            query = "select id from cv_user where username=? and password = ?";
            Log.i("authentication:", query);

            Log.i("info:", user.getUsername() + " val= " + user.getPassword());

            Cursor cursor = db.rawQuery(query, new String[]{user.getUsername(), user.getPassword()});
            //db.close();
            if (cursor.getCount() > 0)
                check = true;
            else
                check = false;
        } catch (SQLiteConstraintException e) {
            Log.i("Authentication:", "SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Authentication:", String.valueOf(check));
        return check;
    }

    public long Registration(User user) { // saving data to register the user
        boolean check = false;
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("firstname", user.getFirstname());
            values.put("lastname", user.getLastname());
            values.put("username", user.getUsername());
            values.put("password", user.getPassword());
            values.put("email", user.getEmail());
            values.put("phonenumber", user.getPhonenumber());

            SQLiteDatabase db = getWritableDatabase();
            result = db.insert("cv_user", null, values);
            String query = "select last_insert_rowid()";
            Log.i("registeration:", "query:" + query);
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            if (!c.isAfterLast()) {
                userid = c.getString(0);
                Log.i("USERID:", "Userid of registered User:" + userid);
            }

        } catch (SQLiteConstraintException e) {
            Log.i("Registration:", "SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Registration:", String.valueOf(result));

        return result;
    }

    public void DeleteUser(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from user where id = " + id);
        db.close();
    }

    public long SaveTransaction(FundTransfers fund) {
        boolean check = false;
        long result = 0;
        try {
            SQLiteDatabase db = getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("amount", fund.getAmount());
            values.put("description", "Money Sent to " + fund.getEmail() + "");
            values.put("transferto", fund.getEmail());
            values.put("transfermethod", "NEFT");
            values.put("transfertype", "DEBIT CARD");
            values.put("userid", userid);
            result = db.insert("cv_transaction", null, values);

            // db.close();
        } catch (SQLiteConstraintException e) {
            Log.i("Transaction Details:", "SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Transaction Details:", String.valueOf(result));
        return result;
    }

    public long SaveCardDetails(CardDetail cardDetail) // register your card to see the transactions and trasfer the fund and my account
    {
        boolean check = false;
        long result = 0;
        try {
            ContentValues values = new ContentValues();
            values.put("bankid", cardDetail.getBankid());
            values.put("cardnumber", cardDetail.getCardnumber());
            values.put("expirydate", cardDetail.getExpirydate());
            values.put("cvv", cardDetail.getCvv());
            values.put("userid", userid);

            SQLiteDatabase db = getWritableDatabase();
            result = db.insert("cv_card", null, values);

            // db.close();
        } catch (SQLiteConstraintException e) {
            Log.i("Card Details:", "SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Card Details:", String.valueOf(result));
        return result;
    }

    public List<CardDetail> GetCards() { // Get all the card details
        List<CardDetail> cards = new ArrayList<CardDetail>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select bankname,cardnumber from cv_bank cb inner join cv_card cc on cb.id=cc.bankid where userid='" + userid + "'";
        Log.i("GetCards", "db_query:" + query);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            CardDetail card = new CardDetail();
            card.setBankname(c.getString(c.getColumnIndex("bankname")));
            card.setCardnumber(c.getString(c.getColumnIndex("cardnumber")));
            Log.i("Going to add cards", "card:" + card.getBankname());
            Log.i("Going to add cards", "card:" + card.getCardnumber());
            cards.add(card);
            Log.i("Going to add cards", "cardsize:" + cards.size());
            c.moveToNext();
        }
        return cards;
    }

    public List<Transaction> GetTransactions() { //Get all the transactions details
        List<Transaction> transactions = new ArrayList<Transaction>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select description,amount from cv_transaction where userid='" + userid + "'";
        Log.i("GetCards", "db_query:" + query);
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Transaction transaction = new Transaction();
            transaction.setDescription(c.getString(c.getColumnIndex("description")));
            transaction.setAmount(c.getString(c.getColumnIndex("amount")));
            Log.i("Going to list transaction", "transaction:" + transaction.getDescription());
            Log.i("Going to list transaction", "transaction:" + transaction.getAmount());
            transactions.add(transaction);
            Log.i("Going to list transaction", "transactionsize:" + transactions.size());
            c.moveToNext();
        }
        return transactions;
    }

}