package com.example.gcccyclingapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Spinner;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AccountCreation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String accountType;
    public Club c;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.creation_screen);

            ActionBar actionBar = getSupportActionBar();
            actionBar.setDisplayHomeAsUpEnabled(true);

            Spinner accountType = findViewById(R.id.accountType);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.account_types, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            accountType.setAdapter(adapter);
            accountType.setOnItemSelectedListener(this);

            Button button = findViewById(R.id.createAccount);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    createAccount();
                }
            });
        }

        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
             accountType = parent.getItemAtPosition(pos).toString();
        }

        public void onNothingSelected(AdapterView<?> parent) {}

        public void createAccount() {
            Admin admin = new Admin(getApplicationContext());

            TextView name = findViewById(R.id.editTextName);
            TextView username = findViewById(R.id.editTxtUsername);
            TextView password = findViewById(R.id.editTxtPassword);

            String strName = name.getText().toString();
            String strUsername = username.getText().toString();
            String strPassword = password.getText().toString();

            if (Validate.isNotAlphaNumeric(strName) |Validate.isNotValidUsername(strUsername) || strPassword.isEmpty()) {
                TextView tv1 = (TextView) findViewById(R.id.txtIncomplete);
                tv1.setText("Please fill in all fields with valid characters.");
            }
            else if (accountType.equals("Participant")) {
                System.out.println("Participant");
                Participant p = new Participant(strName, strUsername, strPassword, accountType, this);
                admin.createParticipant(p);
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", accountType);
                startActivity(i);
            }
            else if (accountType.equals("Club Owner")) {
                System.out.println("Club");
                c = new Club(strName, strUsername, strPassword, accountType);
                admin.createClub(c);
                Intent i = new Intent(getApplicationContext(), Welcome.class);
                i.putExtra("name", strUsername);
                i.putExtra("role", "Club");
                startActivity(i);
            }
        }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
