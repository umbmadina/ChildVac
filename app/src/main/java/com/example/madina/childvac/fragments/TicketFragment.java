package com.example.madina.childvac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madina.childvac.R;
import com.example.madina.childvac.adapters.TicketsRecyclerViewAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {

    private ArrayList<String> dayList;
    private ArrayList<String> vaccineNameList;
    private ArrayList<String> roomList;
    private ArrayList<String> nurseList;
    private ArrayList<String> timeList;
    private ArrayList<String> statusList;

    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        initData();
        initRecyclerView(view);
        // Inflate the layout for this fragment
        return view;
    }

    // Populate ArrayLists with data.
    // In memory data is used.
    // * In the future should pull data using api.
    // !important Must be called before initRecyclerView() method.
    private void initData() {
        dayList = new ArrayList<>();
        vaccineNameList = new ArrayList<>();
        roomList = new ArrayList<>();
        nurseList = new ArrayList<>();
        timeList = new ArrayList<>();
        statusList = new ArrayList<>();

        dayList.add("Tue 16 Oct");
        vaccineNameList.add("Influenza");
        roomList.add("401");
        nurseList.add("Gigi Hadid");
        timeList.add("9:00 - 9:15");
        statusList.add("Pending");

        dayList.add("Tue 16 Oct");
        vaccineNameList.add("Influenza 2");
        roomList.add("401");
        nurseList.add("Gigi Hadid");
        timeList.add("9:00 - 9:15");
        statusList.add("Pending");

        dayList.add("Tue 16 Oct");
        vaccineNameList.add("Influenza 3");
        roomList.add("401");
        nurseList.add("Gigi Hadid");
        timeList.add("9:00 - 9:15");
        statusList.add("Pending");

        dayList.add("Tue 16 Oct");
        vaccineNameList.add("Influenza 4");
        roomList.add("401");
        nurseList.add("Gigi Hadid");
        timeList.add("9:00 - 9:15");
        statusList.add("Pending");

        dayList.add("Tue 16 Oct");
        vaccineNameList.add("Influenza 5");
        roomList.add("401");
        nurseList.add("Gigi Hadid");
        timeList.add("9:00 - 9:15");
        statusList.add("Pending");
    }

    private void initRecyclerView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.tickets_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        TicketsRecyclerViewAdapter adapter = new TicketsRecyclerViewAdapter(getContext(),
                dayList, vaccineNameList, roomList, nurseList, timeList, statusList);
        recyclerView.setAdapter(adapter);

    }



}
