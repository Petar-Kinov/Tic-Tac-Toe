package com.example.connect3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[][] board = new String[3][3];
    private String currentMark = "x";
    private int turns = 0;

    public void placeMark(View clickedView){
        ImageView current = (ImageView) clickedView;
        if (current.getDrawable() == null) {
            if (currentMark.equals("x")){
                current.setImageResource(R.drawable.x);
                current.setScaleX(0.5f);
                current.setScaleY(0.5f);
                current.animate().scaleX(1.0f).scaleY(1.0f).setDuration(750);
                getXY((String)current.getTag());
                checkWinCondition();
                currentMark = "o";
                turns++;
            } else {
                current.setImageResource(R.drawable.o);
                current.setScaleX(0.5f);
                current.setScaleY(0.5f);
                current.animate().scaleX(1.0f).scaleY(1.0f).setDuration(750);
                getXY((String)current.getTag());
                checkWinCondition();
                currentMark = "x";
                turns++;
            }
        }
    }

    public void getXY(String tag){
        String[] parts = tag.split("-");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        board[x][y] = currentMark;
}

    public void checkWinCondition(){
        if (turns == 8){
            tie();
            disableBoard();
        }
        for (int i = 0; i < 3 ; i++){
            if (board[i][0].equals(currentMark) && board[i][1].equals(currentMark) && board[i][2].equals(currentMark)){
                win();
                disableBoard();
            }
        }
        for (int j = 0; j < 3 ; j++){
            if (board[0][j].equals(currentMark) && board[1][j].equals(currentMark) && board[2][j].equals(currentMark)){
                win();
                disableBoard();
            }
        }
        if (board[0][0].equals(currentMark) && board[1][1].equals(currentMark) && board[2][2].equals(currentMark)){
            win();
            disableBoard();
        } else if (board[0][2].equals(currentMark) && board[1][1].equals(currentMark) && board[2][0].equals(currentMark)){
            win();
            disableBoard();
        }

    }

    public void setUpGame(){

        TextView text = findViewById(R.id.TextView);
        Button button = findViewById(R.id.button);

        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board[i][j] = "a";
            }
        }
        ImageView one = findViewById(R.id.imageView1);
        ImageView two = findViewById(R.id.imageView2);
        ImageView three = findViewById(R.id.imageView3);
        ImageView four = findViewById(R.id.imageView4);
        ImageView five = findViewById(R.id.imageView5);
        ImageView six = findViewById(R.id.imageView6);
        ImageView seven = findViewById(R.id.imageView7);
        ImageView eight = findViewById(R.id.imageView8);
        ImageView nine = findViewById(R.id.imageView9);
        ImageView[] imageViews = new ImageView[] {one,two,three,four,five,six,seven,eight,nine};


        for (ImageView image: imageViews) {
            image.setImageResource(0);
            image.setEnabled(true);
        }

        text.setText("");
        button.setVisibility(View.INVISIBLE);
        currentMark = "x";
        turns = 0;

    }

    private void win(){
        TextView text = findViewById(R.id.TextView);
        Button button = findViewById(R.id.button);

        if (currentMark.equals("x")){
            text.setText(R.string.xWins);
        } else {
            text.setText(R.string.oWins);
        }

        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpGame();
            }
        });
    }

    private void tie(){
        TextView text = findViewById(R.id.TextView);
        Button button = findViewById(R.id.button);

        text.setText(R.string.tie);

        button.setVisibility(View.VISIBLE);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpGame();
            }
        });
    }

    private void disableBoard(){
        ImageView one = findViewById(R.id.imageView1);
        ImageView two = findViewById(R.id.imageView2);
        ImageView three = findViewById(R.id.imageView3);
        ImageView four = findViewById(R.id.imageView4);
        ImageView five = findViewById(R.id.imageView5);
        ImageView six = findViewById(R.id.imageView6);
        ImageView seven = findViewById(R.id.imageView7);
        ImageView eight = findViewById(R.id.imageView8);
        ImageView nine = findViewById(R.id.imageView9);
        ImageView[] imageViews = new ImageView[] {one,two,three,four,five,six,seven,eight,nine};

        for (ImageView image: imageViews) {
            image.setEnabled(false);
        }

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpGame();


        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
