package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;

public class ResultActivity extends AppCompatActivity {
    private TextView pleasure, myscore, highscore;
    private Button playagain;
    private int point;
    private SharedPreferences sharedPreferences;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        pleasure = findViewById(R.id.pleasure);
        myscore = findViewById(R.id.myscore);
        highscore = findViewById(R.id.highscore);
        playagain = findViewById(R.id.playagain);
        point = getIntent().getIntExtra("point", 0);
        myscore.setText("Your Score: "+point);
        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0); //if playing a game first time the score is 0.
        //store new highest score in DB
        if (point >= 200) {
            pleasure.setText("You won the Game");
            highscore.setText("Highest Score: "+point);
            sharedPreferences.edit().putInt("highestScore",point).apply();
        }
        else if (point >= highestScore) {
            pleasure.setText("Sorry,You lost the game");
            highscore.setText("Highest Score: "+point);
            sharedPreferences.edit().putInt("highestScore",point).apply();
        } else {
            pleasure.setText("Sorry,You lost the game");
            highscore.setText("Highest Score: "+highestScore);

        }
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ResultActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle(" Help the innocent Bird");
        builder.setMessage("Are you sure you want to exit from this game?");
        builder.setCancelable(false);
        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        }).setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                Process.killProcess(Process.myPid());
                System.exit(0);
            }
        });
        builder.create().show();

    }
        }
