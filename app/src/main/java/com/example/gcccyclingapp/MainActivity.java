package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    

    public void verify(View view) {
        EditText username = (EditText) findViewById(R.id.edtTxtUsername);
        EditText password = (EditText) findViewById(R.id.edtTxtPassword);
        String strUsername = username.getText().toString();
        String strPassword = password.getText().toString();
        //place holder to make sure that we can use this if statement, you can change the if conditions if you have to
        //using admin for username and password just to test change to be more general
        if (strUsername.equals("admin") && strPassword.equals("admin")) {
            Intent i = new Intent(getApplicationContext(), Welcome.class);
            i.putExtra("name", strUsername);
            startActivity(i);
        } else {
            TextView tv1 = (TextView) findViewById(R.id.txtTryAgain);
            tv1.setText("Either password or username does not match");
        }
    }

    public void create(View view) {
        Intent i = new Intent(getApplicationContext(), Creation.class);
        startActivity(i);
    }

        public EditText getUsername(){
            EditText username = (EditText) findViewById(R.id.edtTxtUsername);
            return username;
        }
        public EditText getPassword(){
            EditText password = (EditText) findViewById(R.id.edtTxtPassword);
            return password;
        }
}
