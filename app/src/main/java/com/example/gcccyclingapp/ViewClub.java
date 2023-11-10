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
import android.widget.Toast;

public class ViewClub extends AppCompatActivity{

    ListView listView;
    DBAdmin DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_club);

        DB = new DBAdmin(this);
        String[] clubNames = DB.getAllClubs();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.account_list_item, R.id.list_item, clubNames);
        listView = (ListView) findViewById(R.id.clubList);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                final String selectedClubName = (String) parent.getItemAtPosition(position);

                deleteConfirmationPopUp(selectedClubName);
            }
        });
    }

    private void deleteConfirmationPopUp(final String clubName){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Confirm Deletion")
                .setIcon(android.R.drawable.ic_delete)
                .setMessage("Are you sure you want to delete club: " + clubName + " ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleteParticipant(clubName);
                        refreshListView();
                        Toast.makeText(ViewClub.this, "Participant " + clubName + " has been deleted.", Toast.LENGTH_LONG).show();
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

        String[] clubNames = DB.getAllClubs();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.account_list_item, R.id.list_item, clubNames);
        listView.setAdapter(adapter);
    }


}