package com.example.quanlynhansu.database_layer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quanlynhansu.data_models.NhanSu;

import java.util.ArrayList;

public class MyDatabaseLayer extends SQLiteOpenHelper {
    private static String DB_NAME = "database";
    private static int DB_VERSION = 1;

    //table properties
    private static String TABLE_NAME = "members";

    private static String ID = "_id";
    private static String NAME = "name";
    private static String DEGREE = "degree";
    private static String HOPPIES = "hoppies";

    public MyDatabaseLayer(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + "("+
                ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT, " +
                DEGREE + " TEXT, " +
                HOPPIES + " TEXT)";
        db.execSQL(sql);
    }

    //APIs for upper layer
    public void saveMembers(ArrayList<NhanSu> members)
    {
        SQLiteDatabase database = getReadableDatabase();
        for(NhanSu nhanSu: members)
        {
            ContentValues values = new ContentValues();
            values.put(NAME, nhanSu.getName());
            values.put(DEGREE, nhanSu.getDegree());
            values.put(HOPPIES, nhanSu.getHoppies());
            //Insert it to the database
            database.insert(TABLE_NAME, null, values);
        }
        database.close();
    }

    public void getMembers(ArrayList<NhanSu> members)
    {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, new String[]{NAME, DEGREE, HOPPIES}, null, null, null, null, NAME);
        if (cursor.moveToFirst()){
            do {
                NhanSu nhanSu = new NhanSu();
                nhanSu.setName(cursor.getString(cursor.getColumnIndex(NAME)));
                nhanSu.setDegree(cursor.getString(cursor.getColumnIndex(DEGREE)));
                nhanSu.setHoppies(cursor.getString(cursor.getColumnIndex(HOPPIES)));
                members.add(nhanSu);
            } while (cursor.moveToNext());
        }
        db.close();
    }

    public void removeMembers(NhanSu nhanSu) {
        SQLiteDatabase db = getWritableDatabase();

        db.delete(TABLE_NAME, NAME + "= ? AND " + DEGREE + "= ? AND " + HOPPIES + "=?",
                new String[]{nhanSu.getName(), nhanSu.getDegree(), nhanSu.getHoppies()} );
        db.close();

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
