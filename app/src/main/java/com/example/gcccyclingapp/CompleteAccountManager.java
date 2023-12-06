package com.example.gcccyclingapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class CompleteAccountManager extends AppCompatActivity {
    private String clubUser;
    Button btnCompleteAccount;
    Button btnClubLogo;
    ImageView imageView;
    ActivityResultLauncher<Intent> resultLauncher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complete_account);

        Intent intent = getIntent();
        if (intent.hasExtra("clubUser")) {
            this.clubUser = intent.getStringExtra("clubUser");
        }

        btnCompleteAccount = findViewById(R.id.completeAccountBtn2);
        btnClubLogo = findViewById(R.id.addLogoBtn);
        imageView = findViewById(R.id.clubLogo);

        registerResult();

        btnClubLogo.setOnClickListener(view -> pickImage());

        btnCompleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (complete()) {
                    Intent intent = new Intent(CompleteAccountManager.this, ClubOwnerPage.class);
                    intent.putExtra("name", clubUser);
                    startActivity(intent);
                }
            }
        });
    }

    public void pickImage(){
        Intent intent = new Intent(MediaStore.ACTION_PICK_IMAGES);
        resultLauncher.launch(intent);
    }

    public void registerResult(){
        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                try{
                    Uri imageUri = result.getData().getData();
                    imageView.setImageURI(imageUri);
                } catch (Exception e){
                    Toast.makeText(CompleteAccountManager.this, "No Image Selected", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean complete(){
        DBAdmin db = new DBAdmin(this);

        EditText link = (EditText) findViewById(R.id.socialTxt);
        EditText name = (EditText) findViewById(R.id.nameTxt);
        EditText number = (EditText) findViewById(R.id.phoneTxt);
//        EditText clubName = (EditText) findViewById(R.id.clubNameTxt);

        String strLink = link.getText().toString();
        String strName = name.getText().toString();
        String strNumber = number.getText().toString();
//        String strClubName = clubName.getText().toString();

        if(Validate.isNotValidLink(strLink) || Validate.isNotValidPhoneNumber(strNumber)){
            TextView warning = (TextView) findViewById(R.id.warningTxtMsg);
            warning.setText("Either Social Media Link or Phone Number are incorrect please fill those out");
            return false;
        }

//        if(check == true){
//            db.completeClubAccount(strClubName,strLink,strName,strNumber);
//            Toast.makeText(CompleteAccount.this, "Club " + strClubName + " has been created.", Toast.LENGTH_LONG).show();
//            finish();
//        }
        else {
//            Toast.makeText(CompleteAccountManager.this, "Club " + strClubName + " has been created.", Toast.LENGTH_LONG).show();
            Toast.makeText(CompleteAccountManager.this, "Club " + clubUser + " has been completed.", Toast.LENGTH_LONG).show();
            db.completeClubAccount(clubUser, strLink, strName, strNumber);
            return true;
        }

    }
}
