package com.example.paozi.sqlcleartest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 作者：xiaofei
 * 日期：2017/3/10
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String SQL_CREATE_TABLE = "CREATE TABLE IF NOT EXISTS test(" +
            "id integer primary key autoincrement, " +
            "desc text)" ;

    private static DBHelper helper;

    public synchronized static DBHelper getInstance(Context context) {
        if (helper == null) {
            helper = new DBHelper(context, "test.db", null, 1);
        }
        return helper;
    }

    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
