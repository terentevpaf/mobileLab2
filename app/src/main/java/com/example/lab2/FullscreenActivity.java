package com.example.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class FullscreenActivity extends AppCompatActivity {
    private RecyclerView rView;
    private RecyclerView.Adapter dAdapter;
    private RecyclerView.LayoutManager lManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        rView = (RecyclerView) findViewById(R.id.list);
        rView.setHasFixedSize(true);
        lManager = new LinearLayoutManager(this);
        rView.setLayoutManager(lManager);

        Intent intent = getIntent();
        String jsonArray = intent.getStringExtra("json");

        dAdapter = new DataAdapter(this);
        ((DataAdapter) dAdapter).JsonData(jsonArray);

        rView.setAdapter(dAdapter);
    }
}