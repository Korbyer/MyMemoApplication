package com.example.admin.mymemoapp;

/**
 * Created by admin on 2017-01-31.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by admin on 2017-01-30.
 */

public class DBController extends SQLiteOpenHelper{
    private static final String DB_NAME="DBNote.db";
    private static final int DB_VERSION=1;
    private SQLiteDatabase db;
    /*싱글톤패턴 구현!*/
    public static DBController dbController=null;

    public static DBController getInstance(Context context){
        if(dbController == null){
            dbController=new DBController(context);

        }
        return dbController;
    }

    protected DBController(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        db=this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ Constants.TABLE);
        onCreate(db);
    }
    private static final String CREATE_TABLE = "CREATE TABLE "
            +Constants.TABLE+ "("
            +Constants._ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, "
            +Constants._NAME+" TEXT, "
            +Constants._TITLE+" TEXT, "
            +Constants._MODIFIED_TIME+" INTEGER, "
            +Constants._CREATED_TIME+" INTEGER"+")";


    public void insert() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("INSERT INTO "+Constants.TABLE+" VALUES(null,'" + Constants._NAME + "', '" + Constants._TITLE + "', '" + Constants._CREATED_TIME + "','" + Constants._MODIFIED_TIME + "');");
        db.close();
    }

    //데이터 갱신
    public void update() {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행의 가격 정보 수정
        db.execSQL("UPDATE "+Constants.TABLE+" SET name=" + Constants._NAME + ", title="+Constants._TITLE+", created_time="+Constants._CREATED_TIME+", modified_time="+Constants._MODIFIED_TIME+" WHERE name='" + Constants._NAME + "';");
        db.close();
    }

    //데이터 삭제
    public void delete() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM "+Constants.TABLE+" WHERE name='" + Constants._NAME + "';");
        db.close();
    }
    public Cursor fetchAll() {
        db = this.getReadableDatabase();
        Cursor mCursor = db.query(Constants.TABLE, new String[] { "_id", "name",
                "title", "created_time","modified_time" }, null, null, null, null, null, null);
        if (mCursor != null) {
            mCursor.moveToFirst();
        }
        return mCursor;
    }
    public boolean insertNotes(String name, String title, int created_time, int modified_time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("title", title);
        contentValues.put("created_time", created_time);
        contentValues.put("modified_time",modified_time);
        db.insert(Constants.TABLE, null, contentValues);
        return true;
    }
    public Cursor getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor z = db.rawQuery("select * from " + Constants.TABLE + " where _id=" + id
                + "", null);
        return z;
    }
    public int numberOfRows() {
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, Constants.TABLE);
        return numRows;
    }
    public boolean updateNotes(Integer id, String name, String dates,
                               String remark) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dates", dates);
        contentValues.put("remark", remark);
        db.update(Constants.TABLE, contentValues, "_id = ? ",
                new String[] { Integer.toString(id) });
        return true;
    }
    public Integer deleteNotes(Integer id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Constants.TABLE, "_id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList getAll() {
        ArrayList array_list = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from " + Constants.TABLE, null);
        res.moveToFirst();
        while (res.isAfterLast() == false) {
            array_list.add(res.getString(res.getColumnIndex("_id")));
            array_list.add(res.getString(res.getColumnIndex("name")));
            array_list.add(res.getString(res.getColumnIndex("title")));
            array_list.add(res.getString(res.getColumnIndex("created_time")));
            array_list.add(res.getString(res.getColumnIndex("modified_time")));
            res.moveToNext();
        }
        return array_list;
    }
}
