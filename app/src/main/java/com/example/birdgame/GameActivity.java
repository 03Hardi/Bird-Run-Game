package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {
    private ImageView bird, adjudicator, anivia, skeletonfly, coin1, coin2, score, right1, right2, right3;
    private TextView tv, count;
    private ConstraintLayout constraintLayout;
    private boolean touchControl = false;
    private boolean beginControl = false; //app is not crash that why become a more than one variable
    private Runnable runnable, runnable2; // runnable method first execute than run method is execute
    private Handler handler, handler2;

    //positions
    int birdX, adjudicator1x, anivia2x, skeletonfly3x, coin1x, coin2x;
    int birdY, adjudicator1Y, anivia2Y, skeletonfly3Y, coin1Y, coin2Y;

    //dimensions of screen
    int screenWidth;//width represent horizontal length of the screen
    int screenHeight; //height of the  represent vertical length of the screen

    //remaining of screen
    int right = 3;

    //points
    int point = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        bird = findViewById(R.id.bird);
        adjudicator = findViewById(R.id.adct);
        anivia = findViewById(R.id.anivia);
        skeletonfly = findViewById(R.id.sfly);
        coin1 = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        score = findViewById(R.id.score);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        count = findViewById(R.id.count);
        tv = findViewById(R.id.tv);
        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                tv.setVisibility(View.INVISIBLE);

                if (!beginControl) {
                    beginControl = true;
                    screenWidth = (int) constraintLayout.getWidth();
                    screenHeight = (int) constraintLayout.getHeight();
                    birdX = (int) bird.getX(); // we can determine the birdX position
                    birdY = (int) bird.getY();
                    handler = new Handler();
                    runnable = new Runnable() { //for character moving
                        @Override
                        public void run() {
                            moveToBird();
                            enemyControl();
                            collisionControl();

//                            handler.postDelayed(runnable,20); //how much time characters move in the run method
                        }

                    };
                    handler.post(runnable);
                } else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        touchControl = true;

                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        touchControl = false;
                    }
                }
                return true;
            }
        });
    }

    private void moveToBird() {
        if (touchControl) {
            birdY = birdY - (screenHeight / 50); //for move up of potion for every screen
        } else {
            birdY = birdY + (screenHeight / 50);   //for move down of potion
        }
        if (birdY <= 0) {
            birdY = 0;
        }
        if (birdY >= (screenHeight - bird.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
        {
            birdY = (screenHeight - bird.getHeight());
        }
        bird.setY(birdY);//final position of the bird,it can be move
    }

    public void enemyControl()  //run method for enemy control
    {
        adjudicator.setVisibility(View.VISIBLE);
        anivia.setVisibility(View.VISIBLE);
        skeletonfly.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        adjudicator1x = adjudicator1x - (screenWidth / 150); //when call the run method ,this char move the x-axis on the screen

        if (point >=50 && point<80)
        {
            adjudicator1x = adjudicator1x - (screenWidth / 145);
        }
        if (point >=80 && point<120) {
            adjudicator1x = adjudicator1x - (screenWidth / 135);
        }
        if (point >=120 && point<150)
        {
            adjudicator1x = adjudicator1x - (screenWidth / 125);
        }
        if (point >150)
        {
            adjudicator1x = adjudicator1x - (screenWidth / 115);
        }

        if (adjudicator1x < 0)
        {
            adjudicator1x = screenWidth + 200; //it can repeatly appear in screen
            adjudicator1Y = (int) Math.floor(Math.random() * screenHeight); // it can generate the random number b/w 0 and 1 of y axis

            if (adjudicator1Y <= 0) {
                adjudicator1Y = 0;
            }
            if (adjudicator1Y >= (screenHeight - adjudicator.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
            {
                adjudicator1Y = (screenHeight - adjudicator.getHeight());
            }
        }
        adjudicator.setX(adjudicator1x);
        adjudicator.setY(adjudicator1Y);

        anivia2x = anivia2x - (screenWidth / 140);//when call the run method ,this char move the x-axis on the screen

        if (point >=50 && point<80)
        {
            anivia2x = anivia2x - (screenWidth / 135);
        }
        if (point >=80 && point<120) {
            anivia2x = anivia2x - (screenWidth / 125);
        }
        if (point >=120 && point<150)
        {
            anivia2x= anivia2x - (screenWidth / 115);
        }
        if (point >150)
        {
            anivia2x = anivia2x - (screenWidth / 105);
        }


        if (anivia2x < 0) {
            anivia2x = screenWidth + 200; //it can repeatly appear in screen
            anivia2Y = (int) Math.floor(Math.random() * screenHeight); // it can generate the random number b/w 0 and 1 of y axis

            if (anivia2Y <= 0) {
                anivia2Y = 0;
            }
            if (anivia2Y >= (screenHeight - anivia.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
            {
                anivia2Y = (screenHeight - anivia.getHeight());
            }
        }
        anivia.setX(anivia2x);
        anivia.setY(anivia2Y);

        skeletonfly3x = skeletonfly3x - (screenWidth / 130);
        if (point >=50 && point<80)
        {
            skeletonfly3x = skeletonfly3x - (screenWidth / 125);
        }
        if (point >=80 && point<120)
        {
            skeletonfly3x = skeletonfly3x - (screenWidth / 115);
        }
        if (point >=120 && point<150)
        {
            skeletonfly3x = skeletonfly3x - (screenWidth / 105);
        }
        if (point >150)
        {
            skeletonfly3x = skeletonfly3x - (screenWidth / 95);
        }
        //when call the run method ,this char move the x-axis on the screen

        if (skeletonfly3x < 0) {
            skeletonfly3x = screenWidth + 200; //it can repeatly appear in screen
            skeletonfly3Y = (int) Math.floor(Math.random() * screenHeight); // it can generate the random number b/w 0 and 1 of y axis

            if (skeletonfly3Y <= 0) {
                skeletonfly3Y = 0;
            }
            if (skeletonfly3Y >= (screenHeight - skeletonfly.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
            {
                skeletonfly3Y = (screenHeight - skeletonfly.getHeight());
            }
        }
        skeletonfly.setX(skeletonfly3x);
        skeletonfly.setY(skeletonfly3Y);

        coin1x = coin1x - (screenWidth / 120); //when call the run method ,this char move the x-axis on the screen

        if (coin1x < 0) {
            coin1x = screenWidth + 200; //it can repeatly appear in screen
            coin1Y = (int) Math.floor(Math.random() * screenHeight); // it can generate the random number b/w 0 and 1 of y axis

            if (coin1Y <= 0) {
                coin1Y = 0;
            }
            if (coin1Y >= (screenHeight - coin1.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
            {
                coin1Y = (screenHeight - coin1.getHeight());
            }
        }
        coin1.setX(coin1x);
        coin1.setY(coin1Y);

        coin2x = coin2x - (screenWidth / 110); //when call the run method ,this char move the x-axis on the screen

        if (coin2x < 0) {
            coin2x = screenWidth + 200; //it can repeatly appear in screen
            coin2Y = (int) Math.floor(Math.random() * screenHeight); // it can generate the random number b/w 0 and 1 of y axis

            if (coin2Y <= 0) {
                coin2Y = 0;
            }
            if (coin2Y >= (screenHeight - coin2.getHeight())) //bird hight - y axis height ,it can not disappear of the screen
            {
                coin2Y = (screenHeight - coin2.getHeight());
            }
        }
        coin2.setX(coin2x);
        coin2.setY(coin2Y);

    }

    //collision
    public void collisionControl() {
        int centerAdjudicator1x = adjudicator1x + adjudicator.getWidth() / 2;
        int centerAdjudicator1y = adjudicator1Y + adjudicator.getHeight() / 2;

        if (centerAdjudicator1x >= birdX
                && centerAdjudicator1x <= (birdX + bird.getWidth())
                && centerAdjudicator1y <= birdY
                && centerAdjudicator1y <= (birdY + bird.getHeight())
        ) {
            adjudicator1x = screenWidth + 200;
            right--;
        }
        int centeranivia2x = anivia2x + anivia.getWidth() / 2;
        int centeranivia2y = anivia2Y + anivia.getHeight() / 2;

        if (centeranivia2x >= birdX
                && centeranivia2x <= (birdX + bird.getWidth())
                && centeranivia2y <= birdY
                && centeranivia2y <= (birdY + bird.getHeight())
        ) {
            anivia2x = screenWidth + 200;
            right--;
        }
        int centerskeletonfly3x = skeletonfly3x + skeletonfly.getWidth() / 2;
        int centerskekletonfly3y = skeletonfly3Y + skeletonfly.getHeight() / 2;

        if (centerskeletonfly3x >= birdX
                && centerskeletonfly3x <= (birdX + bird.getWidth())
                && centerskekletonfly3y <= birdY
                && centerskekletonfly3y <= (birdY + bird.getHeight())
        ) {
            skeletonfly3x = screenWidth + 200;
            right--;
        }
        int centercoin1x = coin1x + coin1.getWidth() / 2;
        int centercoin1Y = coin1Y + coin1.getHeight() / 2;

        if (centercoin1x >= birdX
                && centercoin1x <= (birdX + bird.getWidth())
                && centercoin1Y <= birdY
                && centercoin1Y <= (birdY + bird.getHeight())
        ) {
            coin1x = screenWidth + 200;
            point = point+10;
            count.setText(""+point);


        }
        int centercoin2x = coin2x + coin2.getWidth() / 2;
        int centercoin2Y = coin2Y + coin2.getHeight() / 2;

        if (centercoin2x >= birdX
                && centercoin2x <= (birdX + bird.getWidth())
                && centercoin2Y <= birdY
                && centercoin2Y <= (birdY + bird.getHeight())
        ) {
            coin2x = screenWidth + 200;
            point = point + 10;
            count.setText(""+point);
        }
        if (right > 0 && point < 200)
        {
            if (right == 2)
            {
                right1.setImageResource(R.drawable.loseheart);
            }
            if (right == 1)
            {
                right2.setImageResource(R.drawable.loseheart);
            }

            handler.postDelayed(runnable, 20);
        }
        else if (point >= 200)
        {
            handler.removeCallbacks(runnable);
            constraintLayout.setEnabled(false);
            tv.setVisibility(View.VISIBLE);
            tv.setText("Congratulations.You Won the Game");
            adjudicator.setVisibility(View.INVISIBLE);
            anivia.setVisibility(View.INVISIBLE);
            skeletonfly.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    birdX = birdX + (screenWidth / 300);
                    bird.setX(birdX);
                    bird.setY(screenHeight / 2f);
                    if (birdX <= screenWidth) //bird is running on the screen
                    {
                        handler2.postDelayed(runnable2, 20);

                    } else {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("point", point);
                        startActivity(intent);
                        finish();
                    }
                }
            };
            handler2.post(runnable2);

        } else if (right == 0) {
            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.loseheart);
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("point", point);
            startActivity(intent);
            finish();
        }

    }
                }