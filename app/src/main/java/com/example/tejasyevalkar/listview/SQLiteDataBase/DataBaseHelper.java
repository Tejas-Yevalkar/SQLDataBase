package com.example.tejasyevalkar.listview.SQLiteDataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.tejasyevalkar.listview.Constants;
import com.example.tejasyevalkar.listview.POJO.FruitPOJO;

import java.util.ArrayList;

/**
 * Created by tejas yevalkar on 22/12/2017.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    private static final Integer DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Fruit List";
    private static final String TABLE_NAME = "fruit_table";
    private static final String KEY_ID = "ID";
    private static final String KEY_FRUIT_NAME = "FRUIT_NAME";
    private static final String KEY_FRUIT_PKG_DATE = "FRUIT_PKG_DATE";
    private static final String KEY_FRUIT_EXP_DATE = "FRUIT_EXP_DATE";
    private static final String KEY_FRUIT_PRICE = "FRUIT_PRICE";
    private static final String COL_5 = "NEW_FRUIT_PRICE";

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " ("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_FRUIT_NAME + " TEXT,"
                + KEY_FRUIT_PKG_DATE + " INTEGER,"
                + KEY_FRUIT_EXP_DATE + " INTEGER,"
                + KEY_FRUIT_PRICE + " INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(FruitPOJO fruitPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FRUIT_NAME, fruitPOJO.getmFName());
        contentValues.put(KEY_FRUIT_PKG_DATE, fruitPOJO.getmFPkgDate());
        contentValues.put(KEY_FRUIT_EXP_DATE, fruitPOJO.getmFExpDate());
        contentValues.put(KEY_FRUIT_PRICE, fruitPOJO.getmFPrice());
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public ArrayList<FruitPOJO> getList() {
        ArrayList<FruitPOJO> fruitPOJOArrayList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        Log.d("my_tag", "getList: " + cursor.getCount());
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                fruitPOJOArrayList.add(new FruitPOJO(
                        cursor.getString(cursor.getColumnIndex(KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(KEY_FRUIT_NAME)),
                        cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_PKG_DATE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_EXP_DATE)),
                        cursor.getInt(cursor.getColumnIndex(KEY_FRUIT_PRICE))));
                cursor.moveToNext();
            }
        }
        return fruitPOJOArrayList;
    }

    public boolean updateData(FruitPOJO fruitPOJO) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FRUIT_NAME, fruitPOJO.getmFName());
        contentValues.put(KEY_FRUIT_PRICE, fruitPOJO.getmFPrice());
        db.update(TABLE_NAME, contentValues, KEY_ID + " = ?", new String[]{fruitPOJO.getId()});
        return true;
    }

    public boolean deleteData(String id) {
        boolean result = true;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_FRUIT_NAME, id);
        int res = db.delete(TABLE_NAME, KEY_ID + "= ?", new String[]{id});
        if (res == 1)
            result = false;
        return result;
    }
}
