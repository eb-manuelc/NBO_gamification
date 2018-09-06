package com.example.manuelc.nbo_gamification.game;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ProductCheckActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_check);

        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText input = (EditText) findViewById(R.id.editText);
                System.out.println(input.getText().toString());
                if (input.getText().toString().equalsIgnoreCase("1234")) {
                    Toast.makeText( getApplicationContext(),"Correct!!  1 Point! ",Toast.LENGTH_LONG).show();
                    Score.score += 1;
                    finish();
                } else {
                    Toast.makeText( getApplicationContext(),"Are you sure this is the correct event?",Toast.LENGTH_LONG).show();
                }
            }
        });


    }


}
