package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.udacity.gradle.jokedisplay.JokeDisplayActivity;


public class MainActivity extends AppCompatActivity implements Callback {

    private ProgressBar spinner;
    InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = (ProgressBar) findViewById(R.id.progress_bar);
        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.ad_unit_id));
        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                requestNewInterstitial();
                startJokeTask();
            }
        });
        requestNewInterstitial();
    }

    public void tellJoke(View view) {
        spinner.setVisibility(View.VISIBLE);
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        } else {
            startJokeTask();
        }
    }

    private void startJokeTask() {
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

    private void requestNewInterstitial() {
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();

        mInterstitialAd.loadAd(adRequest);
    }
}
