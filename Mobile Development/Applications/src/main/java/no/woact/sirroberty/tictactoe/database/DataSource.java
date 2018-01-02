package no.woact.sirroberty.tictactoe.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable;
import no.woact.sirroberty.tictactoe.model.Player;

import static no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable.Cols.HIGHSCORE;
import static no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable.Cols.PLAYERNAME;
import static no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable.Cols.UUID;
import static no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable.TABLE_NAME;

/**
 * This class handles all the database communications, like adding
 * items and returning them.
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
public class DataSource {
    private static DataSource sDataSource;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    //Private Constructor
    private DataSource(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new DbHelper(mContext)
                .getWritableDatabase();

    }

    public static DataSource get(Context context) {
        if (sDataSource == null) {
            sDataSource = new DataSource(context);
        }
        return sDataSource;
    }


    /**
     * This method makes a query for all players
     * in the table and fills up the ArrayList with data
     * and returns the list
     */
    public List<Player> getPlayers() {
        List<Player> players = new ArrayList<>();
        PlayerCursorWrapper cursor = playerQuery(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                players.add(cursor.getPlayer());
                cursor.moveToNext();
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        } finally {
            cursor.close();// Close to prevent leak
        }
        return players;
    }


    /*This method returns guest if a valid id is found in the list*/
    public Player getPlayer(UUID id) {
        PlayerCursorWrapper cursor = playerQuery(
                DbSchema.HighScoreTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if(cursor.getCount() == 0){
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPlayer();
        }
        finally {
            cursor.close();//Close to prevent any DB leaks
        }

    }


    public void addPlayer(Player p) {
        ContentValues values = getContentValues(p);
        mDatabase.insert(TABLE_NAME, null, values);
    }


    public int deletePlayer(Player player) {
        String uuidString = player.getId().toString();
        return mDatabase.delete(
                HighScoreTable.TABLE_NAME,
                UUID + " = ?",
                new String[] { uuidString }
        );
    }


    /**
     * This method updates specified row in DB
     * The third argument specifies the values in a where clause
     * (String [])
     */
    public void upDatePlayer(Player player) {
        String uuidString = player.getId().toString();
        ContentValues values = getContentValues(player);

        mDatabase.update(TABLE_NAME, values, UUID +
                " = ?", new String[]{uuidString}); //Prevents SQL injection attacks
    }

    /**
     * This method uses ContentValues to write to the database, on a key-value basis
     * It's job is to get a Guest instance into a ContentValues.
     */
    private static ContentValues getContentValues(Player player) {
        ContentValues values = new ContentValues();
        values.put(UUID, player.getId().toString());
        values.put(PLAYERNAME, player.getPlayerName());
        values.put(HIGHSCORE, player.getGamesWon());

        return values;
    }

    /**
     * This method makes a query
     * for reading data from the database table.
     *
     * The conditions for method public Cursor query are:
     * String table, String[] columns, String where,
     * String[] whereArgs, String groupBy, String having,
     * String orderBy,
     * String limit.
     */
    private PlayerCursorWrapper playerQuery(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                HighScoreTable.TABLE_NAME,
                null, //null gets all columns
                whereClause,
                whereArgs,
                null, //groupBy
                null, //having
                null  //orderBy
        );
        return new PlayerCursorWrapper(cursor);
    }


}



