package com.example.madina.childvac.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madina.childvac.R;
import com.example.madina.childvac.adapters.MenuRecyclerViewAdapter;
import com.example.madina.childvac.adapters.PrescriptionsRecyclerViewAdapter;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;

public class PrescriptionsFragment extends Fragment {

    private static final String TAG = "PrescriptionsFragment";

    //vars
    private ArrayList<String> list_diagnosis = new ArrayList<>();
    private ArrayList<String> list_doctor = new ArrayList<>();
    private ArrayList<String> list_visit_type = new ArrayList<>();
    private ArrayList<String> list_date = new ArrayList<>();
    private ArrayList<String> list_description = new ArrayList<>();
    private ArrayList<String> list_prescription = new ArrayList<>();

    public PrescriptionsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_prescriptions, container,false);
        getData(view);
        return view;
    }

    private void getData(View view){
        Log.d(TAG, "initData: preparing data.");

        //1st
        list_diagnosis.add("Influenza ");
        list_doctor.add("Dr. Ayzhan Zhumabekova");
        list_visit_type.add("Doctor Visit ");
        list_date.add("1/10/2018");
        list_description.add("Due to the cold weather and seasonal viruses the  pacient caught Influenza.Due to the cold weather and seasonal viruses the  pacient caught Influenza.Due to the cold weather and seasonal viruses the  pacient caught Influenza.");
        list_prescription.add("Ibuprofen 3 times/day during 2 weeks.");

        //2nd
        list_diagnosis.add("Broken Leg ");
        list_doctor.add("Dr. Ayzhan Zhumabekova");
        list_visit_type.add("Nurse Visit ");
        list_date.add("27/09/2018");
        list_description.add("Due to the cold weather and seasonal viruses the  pacient caught Influenza.");
        list_prescription.add("Ibuprofen 3 times/day during 2 weeks.");

        //3rd
        list_diagnosis.add("Nose Bleeding ");
        list_doctor.add("Dr. Ayzhan Zhumabekova");
        list_visit_type.add("Nurse Visit ");
        list_date.add("25/12/2018");
        list_description.add("Due to the cold weather and seasonal viruses the  pacient caught Influenza.");
        list_prescription.add("Ibuprofen 3 times/day during 2 weeks.");

        //4th
        list_diagnosis.add("Asdfgsdfgh ");
        list_doctor.add("Dr. Ayzhan Zhumabekova");
        list_visit_type.add("Nurse Visit ");
        list_date.add("27/09/2018");
        list_description.add("Due to the cold weather and seasonal viruses the  pacient caught Influenza.");
        list_prescription.add("Ibuprofen 3 times/day during 2 weeks.");


        initRecyclerView(view);

    }

    private void initRecyclerView(View view){
        Log.d(TAG, "initRecyclerView: init prescriptions_recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = view.findViewById(R.id.prescriptions_recycler_view);
        recyclerView.setLayoutManager(layoutManager);
        PrescriptionsRecyclerViewAdapter adapter = new PrescriptionsRecyclerViewAdapter(getContext(), list_diagnosis, list_doctor,
                list_visit_type, list_date,
                list_description, list_prescription);

        recyclerView.setAdapter(adapter);

    }

}
