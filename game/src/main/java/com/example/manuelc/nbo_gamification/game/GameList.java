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

    }

    public void rowClick(View view){
        if (view.getId() == R.id.whosthis) {
            Intent i = new Intent(GameList.this, WhosThisActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        }
        else if (view.getId() == R.id.findit) {
            Intent i = new Intent(GameList.this, FindItActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        }
        else if (view.getId() == R.id.connect) {
            Intent i = new Intent(GameList.this, ConnectActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        }
        else if (view.getId() == R.id.productcheck) {
            Intent i = new Intent(GameList.this, ProductCheckActivity.class);
            i.putExtra("key", "bla");
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_list, menu);
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
