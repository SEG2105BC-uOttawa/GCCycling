package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class Creation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String accountType;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.creation_screen);

            Spinner accountType = findViewById(R.id.accountType);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            accountType.setAdapter(adapter);
            accountType.setOnItemSelectedListener(this);
        }

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
             accountType = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {}

        public void createAccount(View view) {
            Admin admin = new Admin(getApplicationContext());
            Club club = new Club("No Club", "test", "test", "Club");

            TextView name = findViewById(R.id.editTextName);
            TextView username = findViewById(R.id.editTxtUsername);
            TextView password = findViewById(R.id.editTxtPassword);

            String strName = name.getText().toString();
            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();

            if (strName.isEmpty() || strUsername.isEmpty() || strPassword.isEmpty()) {
                TextView tv1 = (TextView) findViewById(R.id.txtIncomplete);
                tv1.setText("Please fill in all fields.");
            }
            else if (accountType.equals("Participant")) {
                admin.addParticipant(club, new Participant(strName, strUsername, strPassword, "Participant"));
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", "Participant");
                startActivity(i);
            }
            else if (accountType.equals("Club Owner")) {
                admin.createClub(new Club(strName, strUsername, strPassword, "Club Owner"));
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", "Club Owner");
                startActivity(i);
            }
        }


}
