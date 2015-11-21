package com.udacity.gradle.jokedisplay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class JokeDisplayActivity extends AppCompatActivity {

    private TextView jokeTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_display);

        jokeTextView = (TextView) findViewById(R.id.jokeTextView);

        Intent intent = getIntent();
        final String joke = intent.getStringExtra(Intent.EXTRA_TEXT);
        jokeTextView.setText(joke);
    }
}
