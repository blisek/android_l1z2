package com.blisek.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.swapi.models.People;
import com.swapi.models.SWModelList;
import com.swapi.sw.StarWarsApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private ArrayList<People> peoples;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StarWarsApi.init();

        peoples = new ArrayList<>();
        listView = (ListView)findViewById(R.id.listView);
        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, peoples);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                People people = peoples.get(position);
                Intent intent = new Intent(getApplicationContext(), PersonActivity.class);
                intent.putExtra(PersonActivity.PEOPLE_PARAM, people);
                startActivity(intent);
            }
        });

        StarWarsApi.getApi().getAllPeople(1, new Callback<SWModelList<People>>() {
            @Override
            public void success(SWModelList<People> peopleSWModelList, Response response) {
                peoples.addAll(peopleSWModelList.results);
                listView.requestLayout();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.i("person", "failure");
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();

    }

}
