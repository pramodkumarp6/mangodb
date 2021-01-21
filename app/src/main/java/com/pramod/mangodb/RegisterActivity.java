package com.pramod.mangodb;

import androidx.appcompat.app.AppCompatActivity;

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
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoDatabase;

public class RegisterActivity extends AppCompatActivity {
    private App app;
    private EditText editTextemail,editTextPassword;
    private Button register;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        editTextemail = findViewById(R.id.textemail);
        editTextPassword = findViewById(R.id.textpass);
        register = findViewById(R.id.buttonRegister);
       register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });


    }

    private void register() {
        final String email = editTextemail.getText().toString().trim();
        final String password = editTextemail.getText().toString().trim();

        if(email.isEmpty()){
            Toast.makeText(RegisterActivity.this,"email is required",Toast.LENGTH_LONG).show();
            return;
        }
        if(password.isEmpty()){
            Toast.makeText(RegisterActivity.this,"Password is required",Toast.LENGTH_LONG).show();
            return;
        }
        Realm.init(this);
        app = new App(new AppConfiguration.Builder(Config.appID)
                .build());




        app.getEmailPassword().registerUserAsync(email,password,it->{
            if(it.isSuccess())
            {
                Toast.makeText(RegisterActivity.this,"Registration Sucessfully",Toast.LENGTH_LONG).show();

            }
            else
            {
                Toast.makeText(RegisterActivity.this,"Registration Failed",Toast.LENGTH_LONG).show();

                Log.v("User","Registration Failed");
            }
        });
    }
}
