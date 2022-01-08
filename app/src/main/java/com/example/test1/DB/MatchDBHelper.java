package com.example.test1.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.test1.DB.MatchContract.*;
import com.example.test1.Model.Match;


public class MatchDBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "temtemper.db";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " + MatchEntry.TABLE_NAME + "(" + MatchEntry.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            MatchEntry.TEXT_COL1 + " TEXT," + MatchEntry.TEXT_COL2 + " TEXT," + MatchEntry.TEXT_COL3 + " TEXT,"  + MatchEntry.TEXT_COL4 + " TEXT)";


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
            values.put(MatchEntry.TEXT_COL1, c.getOpp_name());
            values.put(MatchEntry.TEXT_COL2, c.getDraft_side());
            values.put(MatchEntry.TEXT_COL3, c.getRating());
            values.put(MatchEntry.TEXT_COL4, c.getResult());

            db.insert(MatchEntry.TABLE_NAME, null, values);
        }else{
            Log.i("sql","Database is closed");
        }
    }



}
