package codepath.com.parseservertest;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

public class ParseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG); // debugging, remove for production


        // for monitoring Parse OkHttp traffic
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.networkInterceptors().add(httpLoggingInterceptor);

        ParseObject.registerSubclass(GameScore.class);
        // set app id and server; add network interceptors here
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("appid")
                .clientKey(null)
                .clientBuilder(builder)
                .server("http://10.0.2.2:1337/parse/").build());

        // creates a new test object
        GameScore testObject = new GameScore();
        testObject.put("testKey", "testValue");
        testObject.saveInBackground();

        // ParseInstallation.getCurrentInstallation().saveInBackground();
    }
}


