package com.aandd.simbaproject.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper
{
    public static final String DBNAME="SIMBA_DB";
 
    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
    }
 
    @Override
    public void onCreate(SQLiteDatabase db)  
    {
        String q="CREATE TABLE "+DBStrings.TBL_NAME+
                " ( _id INTEGER PRIMARY KEY AUTOINCREMENT," +
                DBStrings.FIELD_NAME+" TEXT," +
                DBStrings.FIELD_INTENSITY+" TEXT," +
                DBStrings.FIELD_PATH+" TEXT," +
                DBStrings.FIELD_DATE+" TEXT)";
        db.execSQL(q);
    }
 
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
    {  }
 
}
