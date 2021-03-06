package com.ms.userms.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseUtil extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";  //数据库名
    private static final int DATABASE_VERSION = 1;               //数据库版本号

    public DatabaseUtil(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * 创建数据库
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createTable(sqLiteDatabase);
    }

    /**
     * 建立数据表
     */
    private void createTable(SQLiteDatabase db) {
        db.execSQL("create table users(" +
                "id integer primary key autoincrement,"
                + "name VARCHAR(20), "
                + "phone VARCHAR(20))");
        //初识化10条数据
        initData(db);
    }

    private void initData(SQLiteDatabase db) {
        db.execSQL("insert into users(name,phone) Values('小明','123654126')");
        db.execSQL("insert into users(name,phone) Values('晓东','15865521')");
        db.execSQL("insert into users(name,phone) Values('晓东2','1586565211')");
        db.execSQL("insert into users(name,phone) Values('晓东3','15863521')");
        db.execSQL("insert into users(name,phone) Values('晓东4','15865421')");
        db.execSQL("insert into users(name,phone) Values('晓东5','158656521')");
        db.execSQL("insert into users(name,phone) Values('晓东9','1588521')");
        db.execSQL("insert into users(name,phone) Values('晓东6','158601')");
        db.execSQL("insert into users(name,phone) Values('晓东1','1586521')");
    }

    /**
     * 升级数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}