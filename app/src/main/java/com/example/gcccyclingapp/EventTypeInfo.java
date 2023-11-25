package com.example.gcccyclingapp;


import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class EventTypeInfo extends AppCompatActivity {

    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_info);

        DBAdmin db = new DBAdmin(this);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

//        String name = "no name";

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("name")!=null){
            name = bundle.getString("name");
        }

        TextView type = (TextView) findViewById(R.id.eventTypetxt);
        TextView age = (TextView) findViewById(R.id.agetxt);
        TextView pace = (TextView) findViewById(R.id.pacetxt);
        TextView level = (TextView) findViewById(R.id.leveltxt);
        TextView location = (TextView) findViewById(R.id.locationtxt);
        TextView time = (TextView) findViewById(R.id.timetxt);
        TextView details = (TextView) findViewById(R.id.detailstxt);

        String[] info = db.getEventInfo(name); // get previous data

        // set previous data to text edits
        type.setText(name);
        age.setText(info[2]);
        pace.setText(info[3]);
        level.setText(info[4]);
        location.setText(info[5]);
        time.setText(info[6]);
        details.setText(info[7]);
//        for (String s:info
//             ) {
//            Log.d("info", s);
//        }

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
