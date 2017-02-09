package com.example.shitalbharatlondhe.hackerranktic_tac_toe;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    int check[][];
    int i,j;
    Button b[][];
    int player=0;




    Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        block1 = (Button) findViewById(R.id.bt_block1);
        block2 = (Button) findViewById(R.id.bt_block2);
        block3 = (Button) findViewById(R.id.bt_block3);
        block4 = (Button) findViewById(R.id.bt_block4);
        block5 = (Button) findViewById(R.id.bt_block5);
        block6 = (Button) findViewById(R.id.bt_block6);
        block7 = (Button) findViewById(R.id.bt_block7);
        block8 = (Button) findViewById(R.id.bt_block8);
        block9 = (Button) findViewById(R.id.bt_block9);
        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        setBoard();

        /**
         * Restarts the game
         */
        restart.setText("Start New Game");
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(restart.isEnabled())
                {
                    restart.setText("Restart Game");

                }

                AlertDialog.Builder alert =new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Tic-Tac-Toe");

                alert.setMessage("Do you want to restart the game?");
                // alert.setMessage("Message");

                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        player=0;
                        setBoard();

                        restart.setText("Start New Game");
                        result.setText("");
                    }

                });

                alert.setNegativeButton("Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                restart.setText("Start New Game");
                                result.setText("");
                            }
                        });

                alert.show();

            }
        });
        result.setText("");

    }

        private void setBoard()
        {

            b = new Button[4][4];
            check = new int[4][4];


            b[1][3] = block1;
            b[1][2] =  block2;
            b[1][1] = block3;


            b[2][3] = block4;
            b[2][2] = block5;
            b[2][1] = block6;


            b[3][3] = block7;
            b[3][2] = block8;
            b[3][1] = block9;

            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++)
                    check[i][j] = 2;
            }

            for (i = 1; i <= 3; i++) {
                for (j = 1; j <= 3; j++) {
                    b[i][j].setOnClickListener(new MyClickListener(i, j));
                    if (!b[i][j].isEnabled()) {
                        b[i][j].setText("");
                        b[i][j].setEnabled(true);
                    }
                }
            }
        }

        class MyClickListener implements View.OnClickListener
        {
            int x;
            int y;


            public MyClickListener(int x, int y)
            {
                this.x = x;
                this.y = y;
            }

            public void onClick(View view)
            {
                if (b[x][y].isEnabled())
                {
                    b[x][y].setEnabled(false);
                    if (player == 0)
                    {
                        b[x][y].setText("O");
                        check[x][y] = 0;
                        player = 1;
                        checkBoard();
                    } else
                    {
                        b[x][y].setText("X");
                        check[x][y] = 1;
                        player = 0;
                        checkBoard();
                    }
                }
            }


            private boolean checkBoard() {
                boolean gameOver = false;
                if (( check[1][1] == 0 && check[2][2] == 0 &&  check[3][3] == 0)
                        || ( check[1][3] == 0 && check[2][2] == 0 &&  check[3][1] == 0)
                        || ( check[1][2] == 0 &&  check[2][2] == 0 &&  check[3][2] == 0)
                        || ( check[1][3] == 0 &&  check[2][3] == 0 &&  check[3][3] == 0)
                        || ( check[1][1] == 0 &&  check[1][2] == 0 &&  check[1][3] == 0)
                        || ( check[2][1] == 0 &&  check[2][2] == 0 &&  check[2][3] == 0)
                        || ( check[3][1] == 0 &&  check[3][2] == 0 &&  check[3][3] == 0)
                        || ( check[1][1] == 0 &&  check[2][1] == 0 &&  check[3][1] == 0)) {
                    result.setText("Player 1 Wins");
                    gameOver = true;
                } else if (( check[1][1] == 1 && check[2][2] == 1 && check[3][3] == 1)
                        || ( check[1][3] == 1 && check[2][2] == 1 && check[3][1] == 1)
                        || ( check[1][2] == 1 && check[2][2] == 1 && check[3][2] == 1)
                        || ( check[1][3] == 1 && check[2][3] == 1 && check[3][3] == 1)
                        || ( check[1][1] == 1 && check[1][2] == 1 && check[1][3] == 1)
                        || ( check[2][1] == 1 && check[2][2] == 1 && check[2][3] == 1)
                        || ( check[3][1] == 1 && check[3][2] == 1 && check[3][3] == 1)
                        || ( check[1][1] == 1 &&  check[2][1] == 1 && check[3][1] == 1)) {
                    result.setText("Player 2 Wins");
                    gameOver = true;
                } else {
                    boolean empty = false;
                    for (i = 1; i <= 3; i++) {
                        for (j = 1; j <= 3; j++) {
                            if (check[i][j] == 2) {
                                empty = true;
                                break;
                            }
                        }
                    }
                    if (!empty) {
                        gameOver = true;
                        result.setText("It\'s a Tie");

                    }
                }if(gameOver)

                    for(i=1;i<=3;i++)
                    {
                        for(j=1;j<=3;j++)
                        {
                            b[i][j].setEnabled(false);
                        }
                    }

                return gameOver;
            }
        }

    }


