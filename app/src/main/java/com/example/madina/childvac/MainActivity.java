package com.example.madina.childvac;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.madina.childvac.adapters.FragmentListAdapter;
import com.example.madina.childvac.adapters.MenuRecyclerViewAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<Integer> mImages = new ArrayList<>();
    private MenuRecyclerViewAdapter.OnItemClickListener mItemClickListener;
    private ViewPager viewPager;
    private FragmentListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView profile_image = findViewById(R.id.pinButton);

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = getIntent().getStringExtra("childLogin");
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("childLogin",login);
                startActivity(intent);
            }
        });

        viewPager = findViewById(R.id.pager);
        adapter = new FragmentListAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        mItemClickListener = new MenuRecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Log.d(TAG, "onClick: clicked on an image: " + mNames.get(position));
                //Toast.makeText(MainActivity.this, mNames.get(position), Toast.LENGTH_LONG).show();
                viewPager.setCurrentItem(position);
            }
        };
        getImages();
    }

    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);

        MenuRecyclerViewAdapter adapter = new MenuRecyclerViewAdapter(this, mNames, mImages, mItemClickListener);
        recyclerView.setAdapter(adapter);
    }

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");


        mImages.add(R.drawable.calendar);
        mNames.add("Schedule");

        mImages.add(R.drawable.prescription);
        mNames.add("Prescriptions");

        mImages.add(R.drawable.tag);
        mNames.add("Tickets");
        mImages.add(R.drawable.settings_icon);
        mNames.add("Map");

        initRecyclerView();

    }

}

















