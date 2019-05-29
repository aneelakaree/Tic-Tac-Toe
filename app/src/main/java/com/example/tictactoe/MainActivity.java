package com.example.tictactoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity   implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];

    private boolean player1Turn = true;

    private int roundCount;
    private int player1points;
    private int player2points;
    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);


        for (int i = 0; i < 3; i++) {   //we will use it for rounds
            for (int j = 0; j < 3; j++) {
                String buttonID = "button_" + i + j;     //as all buttons start with button_
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[i][j] = findViewById(resID);
                buttons[i][j].setOnClickListener(this);


            }


        }


        Button buttonReset = findViewById(R.id.button_reset);
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                resetGame();

            }
        });

    }

    @Override
    public void onClick(View v) {
        if (!((Button) v).getText().toString().equals("")) { // ! it chks whther this button clicked contains an empty string else it was already used before
            return;
        }

        if (player1Turn) {
            ((Button) v).setText("X");
        } else {

            ((Button) v).setText("0");

        }

        roundCount++;
        if (checkForWin()){
        if (player1Turn){
            player1Wins();}
            else {
            player2Wins();

        }  }
            else if (roundCount == 9){

               draw();}
               else {

                   player1Turn = !player1Turn;
        }

        }




    private boolean checkForWin() {
        String[][] field = new String[3][3];

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                field[i][j] = buttons[i][j].getText().toString();

            }


        }

        for (int i = 0; i < 3; i++) {
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])      //first loop chks all rows
                    && !field[i][0].equals("")) {
                return true;


            }


        }


        for (int i = 0; i < 3; i++) {
            if (field[0][i].equals(field[1][i])         //second loop checks columns
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;


            }


        }
        if (field[0][0].equals(field[1 ][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")) {
            return true;

                            //both if statements checks diagnols
        }
        if (field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")) {
            return true;
        }

        return false;
    }

    private void player1Wins(){
        player1points++;
        Toast.makeText(this, "player 1 wins!" ,Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();

    }

    private void player2Wins(){
        player2points++;
        Toast.makeText(this, "player 2 wins!" ,Toast.LENGTH_SHORT).show();
        updatePointsText();
        resetBoard();


    }

    private void draw(){

        Toast.makeText(this, "Draw!" , Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePointsText(){
    textViewPlayer1.setText("player 1: " + player1points);
    textViewPlayer2.setText("Player 2: " +player2points);

    }



    private void resetBoard(){

        for (int i = 0; i< 3; i++){
            for (int j = 0; j < 3;j++){
                buttons[i][j].setText("");
            }
        }

        roundCount = 0;
        player1Turn = true;

    }

        private void resetGame(){
        player1points = 0;
        player2points = 0;
        updatePointsText();
        resetBoard();




        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("roundCount", roundCount);
        outState.putInt("player1points", player1points);
        outState.putInt("player2Points",player2points);
        outState.putBoolean("player1Turn", player1Turn);

    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundCount= savedInstanceState.getInt("rountCount");
        player1points= savedInstanceState.getInt("player1Points");
        player2points=savedInstanceState.getInt("player2Points");
        player1Turn = savedInstanceState.getBoolean("player1Turn");


    }
}