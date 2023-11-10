package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

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

                String selectedClubName = (String) parent.getItemAtPosition(position);

                DB.deleteClub(selectedClubName);

                refreshListView();

                Toast.makeText(ViewClub.this, "Club " + selectedClubName + "Has Been Deleted.", Toast.LENGTH_LONG).show();
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

        String[] clubNames = DB.getAllClubs();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.account_list_item, R.id.list_item, clubNames);
        listView.setAdapter(adapter);
    }


}