package com.example.gcccyclingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_view_club, clubNames);
        listView = (ListView) findViewById(R.id.clubList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                String selectedClubName = (String) parent.getItemAtPosition(position);

                DB.deleteClub(selectedClubName);

                refreshListView();

                Toast.makeText(ViewClub.this, "Club " + selectedClubName + "Has Been Deleted.", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void refreshListView(){

        String[] clubNames = DB.getAllClubs();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_view_club, clubNames);
        listView.setAdapter(adapter);
    }


}