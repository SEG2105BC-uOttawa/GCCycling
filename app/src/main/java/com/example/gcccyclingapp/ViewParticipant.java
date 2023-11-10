package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

                String selectedParticipantName = (String) parent.getItemAtPosition(position);

                DB.deleteParticipant(selectedParticipantName);

                refreshListView();

                Toast.makeText(ViewParticipant.this, "Participant " + selectedParticipantName + " Has Been Deleted.", Toast.LENGTH_LONG).show();
            }
        });
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