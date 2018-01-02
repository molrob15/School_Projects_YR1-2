package no.woact.sirroberty.tictactoe;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import no.woact.sirroberty.tictactoe.database.DataSource;



/**
 * This class holds the game logic for tic tac toe
 * The game's board is made out of buttons of three times three
 * nested in Linear-layout's
 * //TODO implement metods for saving games to DB.
 *
 * Inspired by Tihomir Radeff, source YouTube: https://www.youtube.com/watch?v=uHzNwhU_Nvg
 *
 * Inspired by: Android Programming,
 * The Big Nerd Ranch 2nd edition,
 * Chapter 14, SQLite Databases
 * @author Bill Phillips, Chris Stewart,
 * Brian Hardy And Kristen Marsicano
 *
 * @author Robert M Molin
 * 2017.05.26
 */


public class MainActivity extends AppCompatActivity {

    /* Buttons that represents squares on playing board */
    private Button square1, square2, square3, square4, square5, square6, square7, square8, square9;
    private Button newGame;
    private Button GameHistory;
    private int playerTurn;

    private int score;
    private TextView playerX, playerO;
    private Context mContext;
    private DataSource mDataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        square1 = (Button) findViewById(R.id.square1);
        square2 = (Button) findViewById(R.id.square2);
        square3 = (Button) findViewById(R.id.square3);
        square4 = (Button) findViewById(R.id.square4);
        square5 = (Button) findViewById(R.id.square5);
        square6 = (Button) findViewById(R.id.square6);
        square7 = (Button) findViewById(R.id.square7);
        square8 = (Button) findViewById(R.id.square8);
        square9 = (Button) findViewById(R.id.square9);
        newGame = (Button) findViewById(R.id.newGame);
        newGame.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });

        GameHistory = (Button) findViewById(R.id.gameHistory); //Show results from DB in HighScoreActivity
        GameHistory.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HighScoreActivity.class);
                startActivity(intent);

            }
        });
        playerX = (TextView) findViewById(R.id.playerX);
        playerO = (TextView) findViewById(R.id.playerO);


        playerTurn = 1; // 1 is player with marker "X", and 2 is player with marker "O"

        setPlayerNames();//Call method setPlayerNames

    }//OnCreate End

    //Sets the player names
    private void setPlayerNames() {

        playerX.setText(getIntent().getExtras().getString("playerX"));
        playerO.setText(getIntent().getExtras().getString("playerO"));
    }

    /*
     * This method checks if a square is empty, and if playerTurn is equal to 1, mark square with "X"
     * Or if a square is empty, and playerTurn is equal to 2, mark square with "O"
     */
    public void buttonOnClick(View view) {

        switch(view.getId()) {
            case R.id.square1:
                if (square1.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square1.setTextColor(getResources().getColor(R.color.darkblue));
                        square1.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square1.setTextColor(getResources().getColor(R.color.darkpurple));
                        square1.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square2:
                if (square2.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square2.setTextColor(getResources().getColor(R.color.darkblue));
                        square2.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square2.setTextColor(getResources().getColor(R.color.darkpurple));
                        square2.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square3:
                if (square3.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square3.setTextColor(getResources().getColor(R.color.darkblue));
                        square3.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square3.setTextColor(getResources().getColor(R.color.darkpurple));
                        square3.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square4:
                if (square4.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square4.setTextColor(getResources().getColor(R.color.darkblue));
                        square4.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square4.setTextColor(getResources().getColor(R.color.darkpurple));
                        square4.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square5:
                if (square5.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square5.setTextColor(getResources().getColor(R.color.darkblue));
                        square5.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square5.setTextColor(getResources().getColor(R.color.darkpurple));
                        square5.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square6:
                if (square6.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square6.setTextColor(getResources().getColor(R.color.darkblue));
                        square6.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square6.setTextColor(getResources().getColor(R.color.darkpurple));
                        square6.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square7:
                if (square7.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square7.setTextColor(getResources().getColor(R.color.darkblue));
                        square7.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square7.setTextColor(getResources().getColor(R.color.darkpurple));
                        square7.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square8:
                if (square8.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square8.setTextColor(getResources().getColor(R.color.darkblue));
                        square8.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square8.setTextColor(getResources().getColor(R.color.darkpurple));
                        square8.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;

            case R.id.square9:
                if (square9.getText().toString().equals("")) {
                    if (playerTurn == 1) {
                        playerTurn = 2;
                        square9.setTextColor(getResources().getColor(R.color.darkblue));
                        square9.setText("X");
                    } else if (playerTurn == 2) {
                        playerTurn = 1;
                        square9.setTextColor(getResources().getColor(R.color.darkpurple));
                        square9.setText("O");
                    }
                }
                checkSquares();//Check squares for a possible winner
                break;
        }//End SwitchStatement
    }//End buttonOnClick


    /**
     * This method checks for for winning combinations of three markers in a row
     * in all directions of the squares/buttons
     * .
     */
    public void checkSquares() {

        boolean gameOver = false;
        String squareOne, squareTwo, squareThree, squareFour, squareFive,
                squareSix, squareSeven, squareEight, squareNine;

        squareOne = square1.getText().toString();
        squareTwo = square2.getText().toString();
        squareThree = square3.getText().toString();
        squareFour = square4.getText().toString();
        squareFive = square5.getText().toString();
        squareSix = square6.getText().toString();
        squareSeven = square7.getText().toString();
        squareEight = square8.getText().toString();
        squareNine = square9.getText().toString();

        /* Checks for player symbol "X" */
        if (squareOne.equals("X") && squareTwo.equals("X") && squareThree.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareOne.equals("X") && squareFive.equals("X") && squareNine.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareOne.equals("X") && squareFour.equals("X") && squareSeven.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareTwo.equals("X") && squareFive.equals("X") && squareEight.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareThree.equals("X") && squareSix.equals("X") && squareNine.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareFour.equals("X") && squareFive.equals("X") && squareSix.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }


        if (squareSeven.equals("X") && squareEight.equals("X") && squareNine.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }


        if (squareSeven.equals("X") && squareFive.equals("X") && squareThree.equals("X")) {
            Toast.makeText(MainActivity.this, "Player X wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        /* Checks for player symbol "O" */
        if (squareOne.equals("O") && squareTwo.equals("O") && squareThree.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareOne.equals("O") && squareFive.equals("O") && squareNine.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareOne.equals("O") && squareFour.equals("O") && squareSeven.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareTwo.equals("O") && squareFive.equals("O") && squareEight.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareThree.equals("O") && squareSix.equals("O") && squareNine.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareFour.equals("O") && squareFive.equals("O") && squareSix.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }


        if (squareSeven.equals("O") && squareEight.equals("O") && squareNine.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        if (squareSeven.equals("O") && squareFive.equals("O") && squareThree.equals("O")) {
            Toast.makeText(MainActivity.this, "Player O wins!", Toast.LENGTH_LONG).show();

            gameOver = true;
        }

        /*When a player has three markers in row, game over! */
        if (gameOver)
        {
            square1.setEnabled(false);
            square2.setEnabled(false);
            square3.setEnabled(false);
            square4.setEnabled(false);
            square5.setEnabled(false);
            square6.setEnabled(false);
            square7.setEnabled(false);
            square8.setEnabled(false);
            square9.setEnabled(false);

            Toast.makeText(MainActivity.this, "GameOver", Toast.LENGTH_LONG).show();
        }

    }

}//Class End
