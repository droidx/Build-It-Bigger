/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.udacity.gradle.joketeller.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

/** An endpoint class we are exposing */
@Api(
  name = "jokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "backend.joketeller.gradle.udacity.com",
    ownerName = "backend.joketeller.gradle.udacity.com",
    packagePath=""
  )
)
public class JokeEndpoint {

    @ApiMethod(name = "tellJoke")
    public JokeBean getJoke() {
        JokeBean response = new JokeBean();
        return response;
    }

}
