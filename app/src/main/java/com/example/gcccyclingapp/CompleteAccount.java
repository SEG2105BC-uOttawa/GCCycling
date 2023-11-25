package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CompleteAccount extends AppCompatActivity {
    private String clubUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_account);

        Intent intent = getIntent();
        if (intent.hasExtra("name")) {
            String clubUser = intent.getStringExtra("name");
        }
    }

    public void complete(View view){
        DBAdmin db = new DBAdmin(this);

        EditText link = (EditText) findViewById(R.id.socialTxt);
        EditText name = (EditText) findViewById(R.id.nameTxt);
        EditText number = (EditText) findViewById(R.id.phoneTxt);
        EditText clubName = (EditText) findViewById(R.id.clubNameTxt);

        String strLink = link.getText().toString();
        String strName = name.getText().toString();
        String strNumber = number.getText().toString();
        String strClubName = clubName.getText().toString();

        Boolean check = false;
        if(Validate.isNotValidLink(strLink) || Validate.isNotValidPhoneNumber(strNumber)){
            TextView warning = (TextView) findViewById(R.id.warningTxt);
            warning.setText("Either Social Media Link or Phone Number are incorrect please fill those out");
        } else {
            check = true;
        }

//        if(check == true){
//            db.completeClubAccount(strClubName,strLink,strName,strNumber);
//            Toast.makeText(CompleteAccount.this, "Club " + strClubName + " has been created.", Toast.LENGTH_LONG).show();
//            finish();
//        }
        if(check){
            db.completeClubAccount(clubUser, strClubName, strLink, strName, strNumber);
            Toast.makeText(CompleteAccount.this, "Club " + strClubName + " has been created.", Toast.LENGTH_LONG).show();
            finish();
        }

    }
}
