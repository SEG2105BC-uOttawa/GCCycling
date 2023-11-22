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
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.Toast;

public class ViewParticipant extends AppCompatActivity {
    ListView listView;
    DBAdmin DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_participant);

        DB = new DBAdmin(this);
        String[] participantNames = DB.getAllParticipants();

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.account_list_item, R.id.list_item, participantNames);
        listView = (ListView) findViewById(R.id.participantList);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                final String selectedParticipantName = (String) parent.getItemAtPosition(position);

                deleteConfirmationPopUp(selectedParticipantName);
            }
        });
    }

    private void deleteConfirmationPopUp(final String participantName){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Confirm Deletion")
                .setIcon(android.R.drawable.ic_delete)
                .setMessage("Are you sure you want to delete participant " + participantName + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleteParticipant(participantName);
                        refreshListView();
                        Toast.makeText(ViewParticipant.this, "Participant " + participantName + " has been deleted.", Toast.LENGTH_LONG).show();
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

    private void refreshListView(){

        String[] participantNames = DB.getAllParticipants();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.account_list_item, R.id.list_item, participantNames);
        listView.setAdapter(adapter);
    }

}