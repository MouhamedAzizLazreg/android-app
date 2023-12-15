package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;
import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "Formula.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "racers";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_RACER = "racer";
    private static final String COLUMN_WON = "races_won";
    private static final String COLUMN_FASTEST = "fastest_lap";

    private static final String COLUMN_POINTS = "points";
    private static final String COLUMN_NATIONALITY = "nationality";
    private static final String COLUMN_TEAM = "team";

    MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_RACER + " TEXT, " +
                COLUMN_WON + " TEXT, " +
                COLUMN_FASTEST + " TEXT, " +
                COLUMN_POINTS + " TEXT, " +
                COLUMN_NATIONALITY + " TEXT, " +
                COLUMN_TEAM + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void addRacer(String racer, String races_won, String fastest_lap, String points, String nationality, String team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RACER, racer);
        cv.put(COLUMN_WON, races_won);
        cv.put(COLUMN_FASTEST, fastest_lap);
        cv.put(COLUMN_POINTS, points);
        cv.put(COLUMN_NATIONALITY, nationality);
        cv.put(COLUMN_TEAM, team);

        long result = db.insert(TABLE_NAME, null, cv);
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData() {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    void updateData(String row_id,String racer, String races_won, String fastest_lap, String points, String nationality, String team) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_RACER, racer);
        cv.put(COLUMN_WON, races_won);
        cv.put(COLUMN_FASTEST, fastest_lap);
        cv.put(COLUMN_POINTS, points);
        cv.put(COLUMN_NATIONALITY, nationality);
        cv.put(COLUMN_TEAM, team);


        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteOneRow(String row_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }
}