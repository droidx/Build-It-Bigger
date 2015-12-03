package com.udacity.gradle.builditbigger.paid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.udacity.gradle.builditbigger.Callback;
import com.udacity.gradle.builditbigger.JokeEndPointAsyncTask;
import com.udacity.gradle.builditbigger.R;
import com.udacity.gradle.jokedisplay.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements Callback {

    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (ProgressBar) findViewById(R.id.progress_bar);
    }

    public void tellJoke(View view) {
        spinner.setVisibility(View.VISIBLE);
        new JokeEndPointAsyncTask(this).execute();
    }

    @Override
    public void onProcessFinished(boolean success, String joke) {
        spinner.setVisibility(View.GONE);
        if (success) {
            Intent intent = new Intent(this, JokeDisplayActivity.class);
            intent.putExtra(Intent.EXTRA_TEXT, joke);
            startActivity(intent);
        } else {
            Toast.makeText(getApplicationContext(), R.string.failed_to_load_joke, Toast.LENGTH_LONG).show();
        }
    }
}
