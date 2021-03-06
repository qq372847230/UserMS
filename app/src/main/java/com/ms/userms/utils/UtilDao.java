package com.ms.userms.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ms.userms.models.Info;

import java.util.ArrayList;
import java.util.List;
 

public class UtilDao {
    private DatabaseUtil du;
    private SQLiteDatabase db;
 
    public UtilDao(Context context){
        du = new DatabaseUtil(context);
        db = du.getWritableDatabase();
    }
 
 
    /**
     * 添加数据
     * */
    public void addData(String tableName,String[] key,String[] values){
        ContentValues contentValues = new ContentValues();
        for(int i = 0; i < key.length; i ++){
            contentValues.put(key[i],values[i]);
        }
        db.insert(tableName,null,contentValues);
        contentValues.clear();
    }
 
    /**
     * 删除数据
     * */
    public int delData(String where,String[] values){
        int del_data;
        del_data = db.delete("users",where,values);
        return del_data;
    }
 
    /**
     * 修改数据
     * */
    public void update(String[] values){
        db.execSQL("update users set name=?,phone=? where name=? ",values);
    }
 
    /**
     * 查询数据
     * */
    public List<Info> inquireData(){
        List<Info> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select id, name,phone" +
                " from users",null);
            while(cursor.moveToNext()){
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String phone = cursor.getString(2);

                Info user = new Info();
                user.setId(id);
                user.setName(name);
                user.setPhone(phone);
 
                list.add(user);
            }
 
        return list;
    }
 
    /**
     * 关闭数据库连接
     * */
    public void getClose(){
        if(db != null){
            db.close();
        }
    }
}