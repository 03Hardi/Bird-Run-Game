package com.example.birdgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView bird, adjudicator, anivia, skeletonfly, volume, coin;
    private Button buttonstart;
    private Animation animation;
    private MediaPlayer mediaPlayer;
    boolean status = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bird = findViewById(R.id.bird);
        adjudicator = findViewById(R.id.adct);
        anivia = findViewById(R.id.anivia);
        skeletonfly = findViewById(R.id.sfly);
        coin = findViewById(R.id.coin1);
        volume = findViewById(R.id.volume);
        buttonstart = findViewById(R.id.btn);
        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_animation);
        bird.setAnimation(animation);
        adjudicator.setAnimation(animation);
        anivia.setAnimation(animation);
        skeletonfly.setAnimation(animation);
        coin.setAnimation(animation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music_game);
        mediaPlayer.start();
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!status) {
                    mediaPlayer.setVolume(0, 0);
                    volume.setImageResource(R.drawable.volume_off);
                    status = true;
                } else {
                    mediaPlayer.setVolume(1, 1);
                    volume.setImageResource(R.drawable.volume);
                    status = false;
                }
            }
        });
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                volume.setImageResource(R.drawable.volume);
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);


            }
        });
    }
}
