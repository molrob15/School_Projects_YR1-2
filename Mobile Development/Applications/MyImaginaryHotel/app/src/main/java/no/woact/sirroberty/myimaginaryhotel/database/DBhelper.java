package no.woact.sirroberty.myimaginaryhotel.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable;

import static no.woact.sirroberty.myimaginaryhotel.database.HotelDbSchema.GuestTable.TABLE_NAME;

/**
 * Helper class for setting up DB
 * creates database schema
 *
 * Inspired by: Android Programming,
 * The big nerd ranch guide 2:nd edition,
 * Chapters 7 to 14, Project Criminal Intent.
 *@authors Bill Phillips, Chris Stewart,
 * Brian Hardy and Kristin Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */

public class DBhelper extends SQLiteOpenHelper {

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "hotel.db";

    public DBhelper(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + GuestTable.TABLE_NAME + "(" +
                "_id integer primary key autoincrement, " +
                GuestTable.Columns.UUID + ", " +
                GuestTable.Columns.NAME + ", " +
                GuestTable.Columns.CHECKIN_DATE +
                ")"
        );

    }

    //If upgrade takes place, just drop table and create a new table.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
