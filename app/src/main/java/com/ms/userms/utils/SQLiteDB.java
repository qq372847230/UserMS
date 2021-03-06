package com.ms.userms.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.ms.userms.helper.OpenHelper;

public class SQLiteDB {

    /**  数据库名 */
    public static final String DB_NAME = "user";

    /** 数据库版本 */
    public static final int VERSION = 1;

    /** 数据库 */
    private static SQLiteDB sqliteDB;

    private SQLiteDatabase db;

    private SQLiteDB(Context context) {
        /** 初始化数据库 */
        OpenHelper dbHelper = new OpenHelper(context, DB_NAME, null, VERSION);
        /** 获取db */
        db = dbHelper.getWritableDatabase();
    }

    /**
     * 获取SqliteDB实例
     * @param context
     */
    public synchronized static SQLiteDB getInstance(Context context) {
        if (sqliteDB == null) {
            sqliteDB = new SQLiteDB(context);
        }
        return sqliteDB;
    }
}
