package com.udacity.gradle.builditbigger;

/**
 * Created by priteshsankhe on 23/11/15.
 */
public interface Callback {
    void onProcessFinished(boolean success, String joke);

}
