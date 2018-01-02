/*
* This class sets the the two players names and sets the color
* on the player markers "X" and "O"
*
* @author Robert M Molin
* 2017.05.09
*/

package no.woact.sirroberty.tictactoe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class StartActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText playerX, playerO;
    private Button btnStartGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        btnStartGame = (Button) findViewById(R.id.btnStartGame);
        btnStartGame.setOnClickListener(this);
        initializeGame();
    }

    /**
     * Sets player names and
     * Set color on player marker
     * Color options are: "blue", "purple", "green" , "orange", "dark red"
     * "red", "dark blue", "dark purple", "dark green" , "dark orange"
     */
    private void initializeGame() {
        playerX = ((EditText) findViewById(R.id.playerX));
        playerO = ((EditText) findViewById(R.id.playerO));
        playerX.setTextColor(getResources().getColor(R.color.darkblue));
        playerO.setTextColor(getResources().getColor(R.color.darkpurple));
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("playerX", playerX.getText().toString());
        intent.putExtra("playerO", playerO.getText().toString());

        startActivity(intent);
    }
}

