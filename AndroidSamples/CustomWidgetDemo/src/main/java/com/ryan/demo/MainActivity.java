package com.ryan.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.ryan.demo.activity.CircleImageDrawableActivity;
import com.ryan.demo.activity.CircleImageViewActivity;
import com.ryan.demo.activity.RoundImageDrawableActivity;


public class MainActivity extends Activity {

    private Button mGoRoundImageDrawable;
    private Button mGoCircleImageDrawable;
    private Button mGoCircleImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
    }

    private void initButtons() {
        mGoRoundImageDrawable = (Button) findViewById(R.id.round_image_drawable);
        mGoRoundImageDrawable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, RoundImageDrawableActivity.class);
                startActivity(intent);
            }
        });

        mGoCircleImageDrawable = (Button) findViewById(R.id.circle_image_drawable);
        mGoCircleImageDrawable.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CircleImageDrawableActivity.class);
                startActivity(intent);
            }
        });

        mGoCircleImageView = (Button) findViewById(R.id.circle_image_view);
        mGoCircleImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CircleImageViewActivity.class);
                startActivity(intent);
            }
        });

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
