package no.woact.sirroberty.tictactoe.database;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

import no.woact.sirroberty.tictactoe.database.DbSchema.HighScoreTable;
import no.woact.sirroberty.tictactoe.model.Player;

/**
 * Wrapper class for database cursor
 *
 Inspired by: Android Programming,
 * The Big Nerd Ranch 2nd edition,
 * Chapter 14, SQLite Databases
 * @author Bill Phillips, Chris Stewart,
 * Brian Hardy And Kristen Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */


public class PlayerCursorWrapper extends CursorWrapper {

    public PlayerCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    //This method pulls out relevant column data from DB
    public Player getPlayer(){
        String uuidString = getString(getColumnIndex(HighScoreTable.Cols.UUID));
        String playername = getString(getColumnIndex(HighScoreTable.Cols.PLAYERNAME));
        int highscore = getInt(getColumnIndex(HighScoreTable.Cols.HIGHSCORE));

        Player player = new Player(UUID.fromString(uuidString));
        player.setPlayerName(playername);
        player.setGamesWon(highscore);

        return player;
    }

}
