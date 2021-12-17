package com.appforall.justintimefinance.db;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.appforall.justintimefinance.RecyclerAdaptor.Model.CardDetail;
import com.appforall.justintimefinance.RecyclerAdaptor.Model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "justfinances.db";
    private static final int DATABASE_Version = 1;
    private static String userid = "";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_Version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("info:", "Worked dff dfggggd ");
            Schema schema = new Schema(db);
            schema.ConfigInstaller(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cv_user");
        onCreate(db);
    }

    public Boolean Authentication(User user) {
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
            if(cursor.getCount() > 0)
                check =  true;
            else
                check = false;
            } catch (SQLiteConstraintException e)
            {
                Log.i("Authentication:","SQLiteConstraintException:" + e.getMessage());
            }
        Log.i("Authentication:", String.valueOf(check));
        return check;
    }

    public long Registration(User user) {
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
            Log.i("registeration:","query:" + query);
            Cursor c = db.rawQuery(query, null);
            c.moveToFirst();
            if (!c.isAfterLast()) {
                userid =  c.getString(0);
                Log.i("USERID:","Userid of registered User:" + userid);
            }

        } catch (SQLiteConstraintException e)
        {
            Log.i("Registration:","SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Registration:", String.valueOf(result));
        return result;
    }

    public void DeleteUser(String id) {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("delete from user where id = " + id);
        db.close();
    }

    public long SaveCardDetails(CardDetail cardDetail)
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
        }
        catch (SQLiteConstraintException e)
        {
            Log.i("Card Details:","SQLiteConstraintException:" + e.getMessage());
        }
        Log.i("Card Details:", String.valueOf(result));
        return result;
    }

    public List<CardDetail> GetCards() {
         List<CardDetail> cards = new ArrayList<CardDetail>();
        SQLiteDatabase db = getReadableDatabase();
        String query = "select bankname,cardnumber from cv_bank cb inner join cv_card cc on cb.id=cc.bankid where userid='" + userid + "'";
        Cursor c = db.rawQuery(query, null);
        c.moveToFirst();
        while (!c.isAfterLast()) {
            CardDetail card = new CardDetail();
            card.setBankname(c.getString(c.getColumnIndex("bankname")));
            card.setCardnumber(c.getString(c.getColumnIndex("cardnumber")));
            cards.add(card);
        }
        return cards;
    }

//    public List<User> databaseToList(String where) {
//        List<User> DBList = new ArrayList<User>();
//        SQLiteDatabase db = getReadableDatabase();
//        String query = "select;
//        Log.i("info",query);
//        Cursor c = db.rawQuery(query, null);
//        Log.i("info","after execute");
//        c.moveToFirst();
//        while (!c.isAfterLast()) {
//            if (c.getColumnIndex(COLUMN_STUDENT_FIRST_NAME) > 0) {
//
//                String name , course = null, marks = null, credits = null, per = null, id = null;
//
//                id = c.getString(c.getColumnIndex(COLUMN_ID));
//
//                name = c.getString(c.getColumnIndex(COLUMN_STUDENT_FIRST_NAME));
//                name += " ";
//                name += String.format("%10.10s", c.getString(c.getColumnIndex(COLUMN_STUDENT_LAST_NAME)));
//
//                course = c.getString(c.getColumnIndex(COURSE));
//
//                // get all subject marks
//                credit = Integer.valueOf(c.getString(c.getColumnIndex(CREDIT)));
//                totalMarks = Integer.valueOf(c.getString(c.getColumnIndex(TOTAL_MARKS)));
//
//                marks = c.getString(c.getColumnIndex(TOTAL_MARKS)); //3
//                credits = c.getString(c.getColumnIndex(CREDIT)); //4
//
//                // add percentage without credits
//                average = credit + totalMarks;
//                percentage = average.floatValue() / 4;
//                per = String.format("%.2f", percentage); //5
//                Log.i("info","Percemtage=" + per);
//
//                // add percentage with credits
////                average = 4 * credit;
////                percentage = average.floatValue() / 15;
////                marksObj.setPercentage(String.format("%.2f", percentage));
//                MarksCalculator marksObj = new MarksCalculator(id,name,course, marks, credits, per);
//                DBList.add(marksObj);
//            }
//            c.moveToNext();
//        }
//        return DBList;
//    }

}

