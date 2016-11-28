package net.uoit.mcjb.csci4100_finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by SherOn on 24/11/2016.
 */

public class ScoreDBHelper extends SQLiteOpenHelper {

    // DB settings
    public static final int DATABASE_VERSION = 1;
    public static final String DB_NAME = "scores.db";
    public static final String TABLE_NAME = "scores";
    public static final String NAME = "player_name";
    public static final String SCORE = "score";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + "(" + NAME + " TEXT PRIMARY KEY,"
                                               + SCORE + " INTEGER)";

    public ScoreDBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        // create new tables
        onCreate(db);
    }

    ArrayList<Score> getAllScores() {
        ArrayList<Score> allScores = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Score score;

        try {
            Cursor res = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
            // looping through all rows and adding to list
            if (res.moveToFirst()) {
                do {
                    score = new Score(res.getString(res.getColumnIndex(NAME)),
                            Long.valueOf(res.getString(res.getColumnIndex(SCORE))));
                    allScores.add(score);
                } while (res.moveToNext());
            }

            res.close();
        } catch (Exception e) {
            Log.d("ScoreDBHelper", "Exception", e);
        }

        return allScores;
    }


    // Delete score by name
    public Integer deleteScore(String playerName) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                NAME + "= ?",
                new String[] { playerName });
    }

    // Add score
    boolean addScore(Score score) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(NAME, score.getName());
        contentValues.put(SCORE, score.getScore());
        if(db.insert(TABLE_NAME, null, contentValues) != -1) {
            return true;
        } else {
            return false;
        }
    }
}
