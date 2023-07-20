package com.TEAM12.KidsUP;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class home extends AppCompatActivity implements View.OnClickListener {

    Button newfeed,speaking,reading,listening,writing,eap,profile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        newfeed = (Button) findViewById(R.id.newfeed);
        newfeed.setOnClickListener(this);

        speaking = (Button) findViewById(R.id.speaking);
        speaking.setOnClickListener(this);

        reading = (Button) findViewById(R.id.reading);
        reading.setOnClickListener(this);

        listening = (Button) findViewById(R.id.listening);
        listening.setOnClickListener(this);

        writing = (Button) findViewById(R.id.writing);
        writing.setOnClickListener(this);

        eap = (Button) findViewById(R.id.eap);
        eap.setOnClickListener(this);

        profile= (Button) findViewById(R.id.profile);
        profile.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.newfeed:
                startActivity(new Intent(getApplicationContext(),dashboard.class));
                break;
            case R.id.speaking:
                startActivity(new Intent(getApplicationContext(),speaking.class));
                break;
            case R.id.reading:
                startActivity(new Intent(getApplicationContext(),reading.class));
                break;
            case R.id.listening:
                startActivity(new Intent(getApplicationContext(),listening.class));
                break;
            case R.id.writing:
                startActivity(new Intent(getApplicationContext(),writing.class));
                break;
            case R.id.eap:
                startActivity(new Intent(getApplicationContext(),PronunciationmadeeasyActivity.class));
                break;
            case R.id.profile:
                startActivity(new Intent(home.this,update_profile.class));
        }
    }
}