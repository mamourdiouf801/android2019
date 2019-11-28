package com.uadb.eschool.controller.eschool;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class BDSchool extends SQLiteOpenHelper {
    public BDSchool(Context context)
    {
        super(context, "school.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE user(id INTEGER PRIMARY KEY AUTOINCREMENT, login VARCHAR(50), password VARCHAR(50));");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //si la version de la base de données change
        //je vide toutes les données de la table user
        db.execSQL("DELETE FROM user;");
    }

    public boolean addUser(String login, String password){
        try{
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("login",login);
            cv.put("password",password);
            db.insert("user",null,cv);
            db.close();
            return  true;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
    public boolean updateUser(String login, String password){
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            //cv.put("login", login);
            cv.put("password", password);
            db.update("user", cv, "login='"+login+"'", null);
            db.close();
            return true;
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String login)
    {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete("user", "login='"+login+"'", null);
            db.close();
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public List<String> getUsers(){
        List<String> list =new ArrayList<>();
        try {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor c =db.query("user",null, null, null, null, null, null);
            c.moveToFirst();
            if(c.getCount()>0)
            {
                do {
                    String login = c.getString(c.getColumnIndex("login"));
                    String password = c.getString(c.getColumnIndex("password"));
                    list.add(login+"--"+password);

                    c.moveToNext();
                }while (!c.isAfterLast());
            }


        }
        catch (Exception e)
        {

        }
        return list;
    }
}
