package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class ViewEventType extends AppCompatActivity {

    ListView listView;
    DBAdmin DB;
    String[] eventTypes;
    Button btnRefresh;
    private static final int EDIT_EVENT_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_event_type);

        DB = new DBAdmin(this);

        eventTypes = DB.getAllEvents();
        String[] eventInfo;

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.event_list_item, R.id.event, eventTypes);
        listView = (ListView) findViewById(R.id.eventList);
        listView.setAdapter(adapter);

        unregisterForContextMenu(listView);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String selectedEventType = (String) adapterView.getItemAtPosition(position);

                showEditDeleteOptions(selectedEventType);
            }
        });


    }


    private void showEditDeleteOptions(final String eventType){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Info, Delete, or Edit")
                .setMessage("Choose an action ")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent editPage = new Intent(getApplicationContext(), EditEventType.class);
                        editPage.putExtra("name", eventType);
//                        startActivity(editPage);
                        startActivityForResult(editPage, EDIT_EVENT_REQUEST_CODE);
                    }
                }).setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteConfirmationPopUp(eventType);
                    }
                }).setNeutralButton("Info", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent infoPage = new Intent(getApplicationContext(), EventTypeInfo.class);
                        infoPage.putExtra("name", eventType);
                        startActivity(infoPage);
                    }
                }).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == EDIT_EVENT_REQUEST_CODE && resultCode == RESULT_OK) {
            refreshListView();
        }
    }

    private void deleteConfirmationPopUp(final String eventType){
        AlertDialog.Builder popUp = new AlertDialog.Builder(this);
        popUp.setTitle("Confirm Deletion")
                .setIcon(android.R.drawable.ic_delete)
                .setMessage("Are you sure you want to delete event " + eventType + "?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DB.deleteEvent(eventType);
                        refreshListView();
                        Toast.makeText(ViewEventType.this, "Event " + eventType + " has been deleted.", Toast.LENGTH_LONG).show();
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

    void refreshListView(){

        String[] eventNames = DB.getAllEvents();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.event_list_item, R.id.event, eventNames);
        listView.setAdapter(adapter);
    }

}