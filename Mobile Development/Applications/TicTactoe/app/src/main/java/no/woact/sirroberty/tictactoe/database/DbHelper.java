package no.woact.sirroberty.tictactoe.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable;

/**
 * Helper class for setting up DB
 *
 * Inspired by: Android Programming,
 * The Big Nerd Ranch 2nd edition,
 * Chapter 14, SQLite Databases
 * @author Bill Phillips, Chris Stewart,
 * Brian Hardy And Kristen Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */
public class DbHelper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME = "highScores.db";
    private static final int VERSION = 1;
    //Constructor
    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    //Create DataBase Table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + HighScoreTable.TABLE_NAME + "(" +
        "_id integer primary key autoincrement, " +
                        HighScoreTable.Cols.UUID + ", " +
                        HighScoreTable.Cols.PLAYERNAME + ", " +
                        HighScoreTable.Cols.HIGHSCORE +
                ")"
        );
    }
    //On upgrade, delete schema and all create a new one.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS highScores");
        onCreate(db);
    }
}
