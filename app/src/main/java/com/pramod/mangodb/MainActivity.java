package com.pramod.mangodb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.pramod.mangodb.app.Config;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class MainActivity extends AppCompatActivity {
    private App app;
    private EditText editTextemail,editTextPassword;
    private Button  buttonlogin;
    private Button  registerbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextemail = findViewById(R.id.textemail);
        editTextPassword = findViewById(R.id.textpass);
        buttonlogin = findViewById(R.id.b1);
        registerbutton = findViewById(R.id.b2);





        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RegisterActivity.class);
                startActivity(i);
            }
        });





        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login();
            }
        });



    }


        private void Login(){


        final String email = editTextemail.getText().toString().trim();
        final String password = editTextemail.getText().toString().trim();

        if(email.isEmpty()){

            Toast.makeText(MainActivity.this,"email is required",Toast.LENGTH_LONG).show();
            return;
        }
            if(password.isEmpty()){
                Toast.makeText(MainActivity.this,"Password is required",Toast.LENGTH_LONG).show();
                return;
            }

            Realm.init(this);



            app = new App(new AppConfiguration.Builder(Config.appID)
                    .build());

            Credentials credentials = Credentials.emailPassword(email,password);

            app.loginAsync(credentials, new App.Callback<User>() {
                @Override
                public void onResult(App.Result<User> result) {

                    if(result.isSuccess()){
                        Toast.makeText(MainActivity.this,"Login Sucess",Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(MainActivity.this,"Login is not Sucess",Toast.LENGTH_LONG).show();

                    }
                }
            });

        }



            
    }
