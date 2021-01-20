package com.pramod.mangodb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    App app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        String appID = "application-0-sxscw";
        app = new App(new AppConfiguration.Builder(appID)
                .build());

        Credentials credentials = Credentials.emailPassword("pramodkumarp6@gmail.com","pandey@1234");

        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {

                if(result.isSuccess()){
                    Log.v("users","Login user email");
                }else{
                    Log.v("users","Login is not");

                }
            }
        });



    }
}