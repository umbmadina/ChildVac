package com.example.madina.childvac.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madina.childvac.App;
import com.example.madina.childvac.R;
import com.example.madina.childvac.adapters.TicketsRecyclerViewAdapter;
import com.example.madina.childvac.models.Ticket;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TicketFragment extends Fragment {

    private ArrayList<String> dayList = new ArrayList<>();
    private ArrayList<String> vaccineNameList = new ArrayList<>();
    private ArrayList<String> roomList = new ArrayList<>();
    private ArrayList<String> nurseList = new ArrayList<>();
    private ArrayList<String> timeList = new ArrayList<>();
    private ArrayList<String> statusList = new ArrayList<>();
    TicketsRecyclerViewAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;


    public TicketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);
        clearData();
        initData();
        initRecyclerView(view);

        swipeRefreshLayout = view.findViewById(R.id.ticketRefresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearData();
                initData();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // Populate ArrayLists with data.
    // In memory data is used.
    // * In the future should pull data using api.
    // !important Must be called before initRecyclerView() method.
    private void initData() {
        int childId = this.getActivity().getIntent().getIntExtra("childId", 1);
        final String dateFormat = "E d MMM";
        final String timeFormat = "HH:mm";

        App.getApi().getTickets(childId).enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                List<Ticket> tickets = response.body();

                for(Ticket t : tickets) {
                    String ticketDay = "Today";
                    String ticketTime = "now";
                    try {
                        ticketDay = new SimpleDateFormat(dateFormat)
                                .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .parse(t.getStartDate()));

                        String ticketTimeStart = new SimpleDateFormat(timeFormat)
                                .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .parse(t.getStartDate()));
                        String ticketTimeEnd = new SimpleDateFormat(timeFormat)
                                .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                .parse(t.getEndDate()));

                        ticketTime = ticketTimeStart + " - " + ticketTimeEnd;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    dayList.add(ticketDay);
                    vaccineNameList.add(t.getDiagnosis());
                    roomList.add(String.valueOf(t.getRoom()));
                    nurseList.add(t.getDoctorFullName());
                    timeList.add(ticketTime);
                    statusList.add("Accepted");
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {

            }
        });
    }

    private void clearData() {
        dayList.clear();
        vaccineNameList.clear();
        roomList.clear();
        nurseList.clear();
        timeList.clear();
        statusList.clear();
    }

    private void initRecyclerView(View view) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.tickets_recycler_view);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new TicketsRecyclerViewAdapter(getContext(),
                dayList, vaccineNameList, roomList, nurseList, timeList, statusList);
        recyclerView.setAdapter(adapter);

    }



}
