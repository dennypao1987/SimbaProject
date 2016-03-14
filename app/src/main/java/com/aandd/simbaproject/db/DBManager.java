package com.aandd.simbaproject.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

public class DBManager 
{
    private DBHelper dbhelper;
 
    public DBManager(Context ctx) 
    {
        dbhelper=new DBHelper(ctx);
    }
    
	public void save(String name, String intensity, String date , String path)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
 
        ContentValues cv=new ContentValues();
        cv.put(DBStrings.FIELD_NAME, name);
        cv.put(DBStrings.FIELD_INTENSITY, intensity);
        cv.put(DBStrings.FIELD_PATH, path);
        cv.put(DBStrings.FIELD_DATE, date);
        try
        {
            db.insert(DBStrings.TBL_NAME, null,cv);
        }
        catch (SQLiteException sqle)
        {
            // Gestione delle eccezioni
        }
    }
 
    public boolean delete(long id)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        try
        {
            if (db.delete(DBStrings.TBL_NAME, DBStrings.FIELD_ID+"=?", new String[]{Long.toString(id)})>0)
                return true;
            return false;
        }
        catch (SQLiteException sqle)
        {
            return false;
        }
 
    }
 
    public Cursor query()
    {
        Cursor crs=null;
        try
        {
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            crs=db.query(DBStrings.TBL_NAME, null, null, null, null, null, null, null);
        }
        catch(SQLiteException sqle)
        {
            return null;
        }
        return crs;
    }
 
}
