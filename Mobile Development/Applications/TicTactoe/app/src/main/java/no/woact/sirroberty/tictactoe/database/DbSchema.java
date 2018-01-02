package no.woact.sirroberty.tictactoe.database;

/**
 * This class exists only to define the String constants of the database table.
 *
 * Inspired by: Android Programming,
 * The Big Nerd Ranch 2nd edition,
 * Chapter 14, SQLite Databases
 * @authors Bill Phillips, Chris Stewart,
 * Brian Hardy And Kristen Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.00
 */

public class DbSchema {

    //Inner class HighScoreTable
    public static final class HighScoreTable {
        public static final String TABLE_NAME = "highScores";

        //Definition of database columns
        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String PLAYERNAME = "playername";
            public static final String HIGHSCORE = "highscore";

            public static final String[] ALL_COLUMNS =
                    { UUID, PLAYERNAME, HIGHSCORE };

        }
    }
}