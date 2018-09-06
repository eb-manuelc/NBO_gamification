package com.example.manuelc.nbo_gamification.game;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class GameList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.connect).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, ConnectActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

        findViewById(R.id.findit).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, FindItActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

        findViewById(R.id.whos_this).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, SwipeActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

        findViewById(R.id.product_check).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, ProductCheckActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

        findViewById(R.id.trivia).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, TriviaActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ((TextView) findViewById(R.id.score)).setText(String.valueOf(Score.score));
    }
}

//
