package com.example.manuelc.nbo_gamification.game;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;

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

        findViewById(R.id.whosThis).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, SwipeActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

        findViewById(R.id.productCheck).setOnClickListener(v -> {
            Intent i = new Intent(GameList.this, ProductCheckActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        });

    }
}
