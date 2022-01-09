package com.example.test1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.test1.DB.MatchContract.*;
import com.example.test1.Model.Match;


public class MatchDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "temtemper.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + MatchEntry.TABLE_NAME + "(" + MatchEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MatchEntry.OPP_NAME + " TEXT," + MatchEntry.DRAFTSIDE + " TEXT," + MatchEntry.RATING + " TEXT,"  + MatchEntry.RESULT + " TEXT)";


    public MatchDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void insertMatch(SQLiteDatabase db, Match c){
        //Check the bd is open
        if (db.isOpen()){
            //Creation of the register for insert object with the content values
            ContentValues values = new ContentValues();

            //Insert the incidence getting all values
            values.put(MatchEntry.OPP_NAME, c.getOpp_name());
            values.put(MatchEntry.DRAFTSIDE, c.getDraft_side());
            values.put(MatchEntry.RATING, c.getRating());
            values.put(MatchEntry.RESULT, c.getResult());

            db.insert(MatchEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }

    //Returns a Cursor with all the values in the table, therefore returning the data for all matches stored
    public Cursor returnAllMatches(SQLiteDatabase db){
        return db.rawQuery("SELECT * FROM MATCHES", null);
    }

    //Wipes the record of all matches in the db
    public void wipeAllMatches(SQLiteDatabase db){
        db.execSQL("DELETE FROM MATCHES");
    }



}
