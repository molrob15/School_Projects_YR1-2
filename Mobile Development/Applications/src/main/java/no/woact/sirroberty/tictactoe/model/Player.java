package no.woact.sirroberty.tictactoe.model;


import java.util.UUID;

/**
 * Model class for TicTacToe
 *
 * Inspired by: Android Programming,
 * The Big Nerd Ranch 2nd edition,
 * Chapter 14, SQLite Databases
 * @authors Bill Phillips, Chris Stewart,
 * Brian Hardy And Kristen Marsicano
 *
 * @author Robert Mattias Molin
 * version: 2017.05.26
 */
public class Player  {

    private UUID mId; // Unique identifier
    private int gamesWon;
    private String playerName;

    //Constructor setting a random id
    public Player() {
        this(UUID.randomUUID());
    }

    //Constructor @param id
    public Player(UUID id){
        mId = id;
    }
    //Constructor
    public Player(String playerName) {
        this.playerName = playerName;
    }

    public UUID getId() {
        return mId;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }


}
