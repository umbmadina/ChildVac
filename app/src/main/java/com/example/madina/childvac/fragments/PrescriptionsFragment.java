package com.example.madina.childvac.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.madina.childvac.App;
import com.example.madina.childvac.R;
import com.example.madina.childvac.adapters.PrescriptionsRecyclerViewAdapter;
import com.example.madina.childvac.models.Child;
import com.example.madina.childvac.models.Prescription;
import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        int childId = this.getActivity().getIntent().getIntExtra("childId", 1);

        App.getApi().getChildPrescription(childId).enqueue(new Callback<List<Prescription>>() {
            @Override
            public void onResponse(Call<List<Prescription>> call, Response<List<Prescription>> response) {
                List<Prescription> prescriptions = response.body();
                for (Prescription p : prescriptions) {
                    String date = "00/00/00";
                    try {
                         date = new SimpleDateFormat("dd/MM/YYYY")
                                        .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")
                                        .parse(p.getDateTime()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    list_diagnosis.add(p.getDiagnosis());
                    list_doctor.add("Dr " + p.getDoctorFullName());
                    list_visit_type.add(p.getVisitType());
                    list_date.add(date);
                    list_description.add(p.getDescription());
                    list_prescription.add(p.getPrescriptionText());
                }

            }

            @Override
            public void onFailure(Call<List<Prescription>> call, Throwable t) {
            }
        });


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
