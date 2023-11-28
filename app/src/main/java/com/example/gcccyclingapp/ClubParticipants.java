package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ClubParticipants extends AppCompatActivity {
    ListView listView;
    DBAdmin DB;
    String clubName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participant);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("name")!=null){
            clubName = bundle.getString("name");
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        DB = new DBAdmin(this);
        String[] participantNames = DB.getAllParticipants(clubName);

        if (participantNames.length == 0) {
            participantNames = new String[]{"No participants"};
        }

        if (participantNames[0] == null) {
            participantNames = new String[]{"No participants"};
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.account_list_item, R.id.list_item, participantNames);
        listView = (ListView) findViewById(R.id.participantList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                final String selectedParticipantName = (String) parent.getItemAtPosition(position);

                addAwardPopUp(selectedParticipantName);
            }
        });
    }

    private void addAwardPopUp(final String participantName){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        EditText awardNameInput = new EditText(this);

        popUp.setTitle("Remove " + participantName + " from club?")
                .setView(awardNameInput)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.removeClubFromParticipant(participantName, clubName);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}