package com.example.gcccyclingapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ParticipantAwards extends AppCompatActivity {
    DBAdmin DB;
    ListView listView;
    String participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_awards);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("participant")!=null){
            participant = bundle.getString("participant");
        }

        DB = new DBAdmin(this);
        String[] awards = DB.getParticipantAwards(participant);

        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, awards);
        listView = (ListView) findViewById(R.id.awardList);
        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
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
