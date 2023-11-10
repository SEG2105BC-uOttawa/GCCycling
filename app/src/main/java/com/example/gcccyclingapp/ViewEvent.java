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

import java.util.List;

public class ViewEvent extends AppCompatActivity {

    ListView listView;
    DBAdmin DB;
    List<String> eventTypes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event);

        DB = new DBAdmin(this);
        listView = (ListView) findViewById(R.id.eventList);

        refreshListView();

        unregisterForContextMenu(listView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                showEditDeleteOptions(eventTypes.get(i));
            }
        });
    }
    private void showEditDeleteOptions(final String eventType){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Edit or Delete Event")
                .setMessage("Choose an action ")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String updatedInfo = null;
                        DB.updateEvent(eventType, "category", updatedInfo);
                        refreshListView();
                        Toast.makeText(ViewEvent.this, "Event " + eventType + " has been updated.", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteConfirmationPopUp(eventType);
                    }
                }).setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // don't do anything.
                    }
                }).show();
    }

    private void deleteConfirmationPopUp(final String eventType){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Confirm Deletion")
                .setIcon(android.R.drawable.ic_delete)
                .setMessage("Are you sure you want to delete evemt " + eventType + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleteEvent(eventType);
                        refreshListView();
                        Toast.makeText(ViewEvent.this, "Event " + eventType + " has been deleted.", Toast.LENGTH_LONG).show();
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

        String[] eventNames = DB.getAllEvents();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.account_list_item, R.id.list_item, eventNames);
        listView.setAdapter(adapter);
    }

}