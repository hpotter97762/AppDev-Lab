package com.example.aditya.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by 106115004 on 13-03-2018.
 */

public class MySQLiteAdapter {
    private Context context;
    private final static String TABLE_NAME="Student";
    private final static String COL_1="Roll";
    private final static String COL_2="Name";
    private final static int DB_VERSION=4;
    private final static String DB_NAME="Student.db";
    private  MySQLiteHelper mHelper;
    private SQLiteDatabase sql;
    private static final String DROP_TABLE="DROP TABLE IF EXISTS  "+TABLE_NAME;
    private static final String CREATE="CREATE TABLE "+TABLE_NAME+" ( "+COL_1+" INT PRIMARY KEY, "+COL_2+" VARCHAR(100));";
    MySQLiteAdapter(Context c){
        context=c;
        mHelper=new MySQLiteHelper(context,TABLE_NAME,null,DB_VERSION);
        sql=mHelper.getWritableDatabase();
    }

    public void AddDetails(int a,String s){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,s);
        contentValues.put(COL_1,a);
        sql.insert(TABLE_NAME,null,contentValues);
    }
    public ArrayList<items> List(){
        String [] Col={COL_1,COL_2};
        Cursor cursor=sql.query(TABLE_NAME,Col,null,null,null,null,COL_1+" asc");
        ArrayList<items> list=new ArrayList<>();
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            items item=new items();
            item.roll=Integer.parseInt(cursor.getString(cursor.getColumnIndex(COL_1)));
            item.name=cursor.getString(cursor.getColumnIndex(COL_2));
            list.add(item);
        }
        return list;
    }
    static public class MySQLiteHelper extends SQLiteOpenHelper {
        MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            sqLiteDatabase.execSQL(DROP_TABLE);
            onCreate(sqLiteDatabase);

        }
    }
}
