package com.example.aly.instagramcloneassignment;

import android.app.Application;

import com.parse.Parse;


public class App extends Application
{
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("s5DCr5aJlfFpixOQTY7HtjXNGIqB357zVq3JCYvk")
                // if defined
                .clientKey("jrItx1MoI42AK4H7qwSCGjmKFaZxsaR3YD00h1pj")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
