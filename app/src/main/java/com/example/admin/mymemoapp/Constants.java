package com.example.admin.mymemoapp;

/**
 * Created by admin on 2017-01-31.
 */

public class Constants {
    /*기본 테이블 정보들*/
    public static final String TABLE = "NOTELIST";
    public static final String _ID="_id";
    public static final String _NAME="name";
    public static final String _TITLE="title";
    public static final String _MODIFIED_TIME="modified_time";
    public static final String _CREATED_TIME="created_time";

    public static final String[] COLUMNS={
            Constants._ID,
            Constants._NAME,
            Constants._TITLE,
            Constants._MODIFIED_TIME,
            Constants._CREATED_TIME
    };
}
