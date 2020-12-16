package com.boss.homeworkout.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class WorkoutDB extends SQLiteAssetHelper {

    private static final String DB_NAME = "Workout.db";
    private static final int DB_VER = 1;

    public WorkoutDB(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    public int getSettingMode() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Mode"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Mode"));
    }

    public void saveSettingMode(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET MODE = "+value;
        db.execSQL(query);
    }

    public void saveAlarmChecked(int value) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET AlarmChecked = " + value;
        db.execSQL(query);
    }

    public int getAlarmChecked() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"AlarmChecked"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("AlarmChecked"));
    }

    public void saveAlarmHour(int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Hour = "+value;
        db.execSQL(query);
    }

    public void saveAlarmMinute (int value){
        SQLiteDatabase db = getReadableDatabase();
        String query = "UPDATE Setting SET Minute = "+value;
        db.execSQL(query);
    }

    public int getAlarmHour(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Hour"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Hour"));
    }

    public int getAlarmMinute(){
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Minute"};
        String sqlTable = "Setting";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("Minute"));
    }

    public void updateAlarmHour(){
        SQLiteDatabase db = getReadableDatabase();

        //sql statement
        String sql = "UPDATE Setting SET Hour = 0";

        db.execSQL(sql);
    }

    public void updateAlarmMinute(){
        SQLiteDatabase db = getReadableDatabase();

        //sql statement
        String sql = "UPDATE Setting SET Minute = 0";

        db.execSQL(sql);
    }

    public List<String> getWorkoutDays() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"Day"};
        String sqlTable = "WorkoutDays";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);

        List<String> result = new ArrayList<>();
        if (c.moveToFirst()){
            do{
                result.add(c.getString(c.getColumnIndex("Day")));
            }while (c.moveToNext());
        }

        return result;
    }

    public void saveDay(String value){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutDays(Day) VALUES(%s);", value);
        db.execSQL(query);
    }

    public int daysCount(){
        int dayCount = 0;

        //sql statement
        String sql = "SELECT COUNT(DISTINCT Day) FROM WorkoutDays WHERE Day!=0";

        //run the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            dayCount = cursor.getInt(0);
        }

        cursor.close();

        return dayCount;
    }

    public void updateWorkoutDays(){
        SQLiteDatabase db = getReadableDatabase();

        //sql statement
        String sql = "UPDATE WorkoutDays SET Day = 0";

        db.execSQL(sql);
    }

    public int getTrophyNo() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        String[] sqlSelect = {"TrophyNo"};
        String sqlTable = "Trophy";

        qb.setTables(sqlTable);
        Cursor c = qb.query(db, sqlSelect, null, null, null, null, null);
        c.moveToFirst();
        return c.getInt(c.getColumnIndex("TrophyNo"));
    }

    public void updateTrophyNo(int trophyNo){
        SQLiteDatabase db = getReadableDatabase();

        String sql = "UPDATE Trophy SET TrophyNo = "+trophyNo;

        db.execSQL(sql);
    }

    /*public Cursor getRandom() {
        String sql = "SELECT ID FROM MotivationImages ORDER BY RANDOM() LIMIT 1";

        //run the query
        Cursor cursor = getReadableDatabase().rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            return cursor;
        }
        return cursor;
    }

    public Bitmap getImage (int id){
        SQLiteDatabase db = getReadableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("SELECT * FROM MotivationImages WHERE ID=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            byte[] image = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(image, 0, image.length);

        }
        return bt;
    }*/

    public void saveSetDone(String value){
        SQLiteDatabase db = getReadableDatabase();
        String query = String.format("INSERT INTO WorkoutHistory(SetDone) VALUES(%s);", value);
        db.execSQL(query);
    }

    public Cursor getSetDone (){
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM WorkoutHistory";
        Cursor cursor = db.rawQuery(query, null);

        return cursor;
    }

    public void updateWorkoutHistory(){
        SQLiteDatabase db = getReadableDatabase();

        //sql statement
        String sql = "DELETE FROM WorkoutHistory";

        db.execSQL(sql);
    }

}

