package com.example.gcccyclingapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ParticipantRateClub extends AppCompatActivity {

    ListView listView;
    DBAdmin DB;
    DBClubs DBc;
    String participant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_participant_rate_club);

        Bundle bundle = getIntent().getExtras();
        if(bundle.getString("participant")!=null){
            participant = bundle.getString("participant");
        }

        DB = new DBAdmin(this);
        DBc = new DBClubs(this);
        List<String> clubNames = DB.getParticipantClubs(participant);
        List<String> clubsWithoutSpace = new ArrayList<>(); // ignores empty indexes

        for (int i = 1; i < clubNames.size(); i++) {
            clubsWithoutSpace.add(clubNames.get(i));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.rate_club_list_item, R.id.list_item, clubsWithoutSpace);
        listView = (ListView) findViewById(R.id.clubList);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){

                showRatingPopUp(); // rating popup
            }
        });

    }

    private void showRatingPopUp() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this); // create new alert
        LayoutInflater inflater = getLayoutInflater();
        View dialog = inflater.inflate(R.layout.rating_popup, null);

        Spinner stars = dialog.findViewById(R.id.rating);
        EditText comments = dialog.findViewById(R.id.comments);

        builder.setView(dialog)
                .setTitle("Rate and Comment on this Club!") // dialog title
                .setPositiveButton("Send", new DialogInterface.OnClickListener() { // send button
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String rating = stars.getSelectedItem().toString();
                        String comment = comments.getText().toString();
                        Toast.makeText(ParticipantRateClub.this, "Rating sent!", Toast.LENGTH_SHORT).show();
                    }
                });

        AlertDialog rateDialog = builder.create();
        rateDialog.show();
    }


}