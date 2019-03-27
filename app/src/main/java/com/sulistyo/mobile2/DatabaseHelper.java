package com.sulistyo.mobile2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String nama_database = "dataLogin.db";
    private static final String nama_tabel = "user";
    private static final String kolom1 = "username";
    private static final String kolom2 = "password";

    public DatabaseHelper(Context context) {
        super(context, nama_database, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + nama_tabel + "(username, password)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + nama_tabel);
    }

    public Boolean insertData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolom1, username);
        contentValues.put(kolom2, password);

        long hasil = db.insert(nama_tabel, null, contentValues);
        if (hasil == -1)
            return false;
        else
            return true;
    }

    public Cursor lihatData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + nama_tabel, null);
        return data;
    }

    //cek user sudah ada atau belum
    public Boolean cekUser(String username) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + nama_tabel + " where username=?", new String[]{username});
        if (data.getCount() > 0) return false;
        else return true;

    }

    public Boolean login(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?", new String[]{username, password});
        if (data.getCount()>0) return true;
        else return false;
    }

    public Boolean editData(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(kolom1, username);
        contentValues.put(kolom2, password);
        db.update(nama_tabel, contentValues, "username = ?", new String[]{username});
        return true;
    }
}
