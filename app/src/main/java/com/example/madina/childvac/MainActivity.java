package com.example.madina.childvac;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getImages();
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImages.add(R.drawable.calendar);
        mNames.add("Schedule");

        mImages.add(R.drawable.prescription);
        mNames.add("Prescriptions");

        mImages.add(R.drawable.tag);
        mNames.add("Tickets");

        mImages.add(R.drawable.settings);
        mNames.add("Settings");

        initRecyclerView();

    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImages);
        recyclerView.setAdapter(adapter);

    }
}

















