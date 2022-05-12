package com.example.mad_utsnomor3.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


import com.example.mad_utsnomor3.object.Obat;
import com.example.mad_utsnomor3.object.User;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;

    public static final String SQL_CREATE_OBAT_TABLE =
            "CREATE TABLE IF NOT EXISTS obat(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, deskripsi TEXT, jenis TEXT, qty INTEGER)";

    public static final String SQL_CREATE_USER_TABLE =
            "CREATE TABLE IF NOT EXISTS user(id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, email TEXT, password TEXT, gender TEXT, umur INTEGER)";

    public static final String SQL_DROP_OBAT_TABLE =
            "DROP TABLE IF EXISTS obat";

    public static final String SQL_DROP_USER_TABLE =
            "DROP TABLE IF EXISTS user";

    public DBHelper(Context context) {
        super(context, "MyMeds", null, 5);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d("DBHELPER", "CREATING TABLES...");
        sqLiteDatabase.execSQL(SQL_CREATE_OBAT_TABLE);
        sqLiteDatabase.execSQL(SQL_CREATE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d("DBHELPER", "Updating table's structure to the latest version");
        sqLiteDatabase.execSQL(SQL_DROP_OBAT_TABLE);
        sqLiteDatabase.execSQL(SQL_DROP_USER_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertData(String nama, String deskripsi, String jenis, int qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("deskripsi", deskripsi);
        contentValues.put("jenis", jenis);
        contentValues.put("qty", qty);

        long result = db.insert("obat", null, contentValues);

        if(result == -1) {
            Toast.makeText(context, "Failed to input obat!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully input obat!", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteData(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete("obat", "id = ?", new String[]{String.valueOf(id)});

        if(result == -1) {
            Toast.makeText(context, "Failed to delete obat!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully delete obat!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public void updateData(int id, String nama, String deskripsi, String jenis, int qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("deskripsi", deskripsi);
        contentValues.put("jenis", jenis);
        contentValues.put("qty", qty);

        long result = db.update("obat", contentValues, "id = ?", new String[]{String.valueOf(id)});

        if(result == -1) {
            Toast.makeText(context, "Failed to update obat!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully update obat!", Toast.LENGTH_SHORT).show();
        }

        db.close();
    }

    public ArrayList<Obat> getObatData() {
        ArrayList<Obat> obatList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM obat", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nama = cursor.getString(1);
            String deskripsi = cursor.getString(2);
            String jenis = cursor.getString(3);
            int qty = cursor.getInt(4);

            Obat obat = new Obat(id, nama, deskripsi, jenis, qty);
            obatList.add(obat);
        }
        return obatList;
    }

    public void insertUser(String nama, String email, String password, String gender, int umur) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama", nama);
        contentValues.put("email", email);
        contentValues.put("password", password);
        contentValues.put("gender", gender);
        contentValues.put("umur", umur);

        long result = db.insert("user", null, contentValues);

        if(result == -1) {
            Toast.makeText(context, "Failed to input obat!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully input obat!", Toast.LENGTH_SHORT).show();
        }
    }

    public ArrayList<User> getUserData() {
        ArrayList<User> userList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user", null);

        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String nama = cursor.getString(1);
            String email = cursor.getString(2);
            String password = cursor.getString(3);
            String gender = cursor.getString(4);
            int umur = cursor.getInt(5);

            User user = new User(id, nama, email, password, gender, umur);
            userList.add(user);
        }
        return userList;
    }

}
