package com.udacity.gradle.joketeller.backend;

import com.udacity.gradle.jokes.JokeTeller;

/**
 * The object model for the data we are sending through endpoints
 */
public class JokeBean {

    private String joke;

    public String getJoke() {
        JokeTeller jokeTeller = new JokeTeller();
        joke = jokeTeller.tellJoke();
        return joke;
    }

    public void setJoke(String data) {
        joke = data;
    }
}