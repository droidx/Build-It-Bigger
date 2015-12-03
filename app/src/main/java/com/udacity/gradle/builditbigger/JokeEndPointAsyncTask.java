package com.udacity.gradle.builditbigger;

import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.udacity.gradle.joketeller.backend.jokeApi.JokeApi;

import java.io.IOException;

/**
 * Created by priteshsankhe on 23/11/15.
 */
public class JokeEndPointAsyncTask extends AsyncTask<Void, Void, String> {

    private static final String TAG = JokeEndPointAsyncTask.class.getSimpleName();
    private static JokeApi jokeApiService;
    private Callback callback;

    public JokeEndPointAsyncTask(Callback callback) {
        this.callback = callback;
    }

    @Override
    protected String doInBackground(Void... params) {
        if (jokeApiService == null) {
            JokeApi.Builder builder = new JokeApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/");
            jokeApiService = builder.build();
        }

        try {
            return jokeApiService.tellJoke().execute().getJoke();
        } catch (IOException e) {
            Log.d(TAG, e.getMessage());
            return null;
        }
    }

    @Override
    protected void onPostExecute(String s) {
        if (callback != null) {
            callback.onProcessFinished(s != null, s);
        }
    }
}
