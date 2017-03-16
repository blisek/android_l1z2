package com.blisek.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.swapi.models.People;

public class PersonActivity extends AppCompatActivity {
    public static final String PEOPLE_PARAM = "PEOPLE";
    private People person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person);

        Intent intent = getIntent();
        person = (People)intent.getSerializableExtra(PEOPLE_PARAM);

        ((TextView)findViewById(R.id.text_view_person_name)).setText(person.name);
        ((TextView)findViewById(R.id.text_view_person_height)).setText(person.height);
        ((TextView)findViewById(R.id.text_view_person_mass)).setText(person.mass);

    }

}
