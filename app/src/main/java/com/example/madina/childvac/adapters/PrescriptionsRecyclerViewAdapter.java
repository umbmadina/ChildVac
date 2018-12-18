package com.example.madina.childvac.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.madina.childvac.R;

import java.util.ArrayList;

public class PrescriptionsRecyclerViewAdapter extends RecyclerView.Adapter<PrescriptionsRecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "PrescriptionsAdapter";

    //vars
    private ArrayList<String> list_diagnosis = new ArrayList<>();
    private ArrayList<String> list_doctor = new ArrayList<>();
    private ArrayList<String> list_visit_type = new ArrayList<>();
    private ArrayList<String> list_date = new ArrayList<>();
    private ArrayList<String> list_description = new ArrayList<>();
    private ArrayList<String> list_prescription = new ArrayList<>();
    private Context mContext;


    public PrescriptionsRecyclerViewAdapter(Context context, ArrayList<String> diagnosis, ArrayList<String> doctor,
                                            ArrayList<String> visit_type, ArrayList<String> date, ArrayList<String> description,
                                            ArrayList<String> prescription) {
        list_diagnosis = diagnosis;
        list_doctor = doctor;
        list_visit_type = visit_type;
        list_date = date;
        list_description = description;
        list_prescription = prescription;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_prescription, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: called.");

        holder.diagnosis.setText(list_diagnosis.get(position));
        holder.doctor.setText(list_doctor.get(position));
        holder.visit_type.setText(list_visit_type.get(position));
        holder.date.setText(list_date.get(position));
        holder.description.setText(list_description.get(position));
        holder.prescription.setText(list_prescription.get(position));
    }

    @Override
    public int getItemCount() {
        return list_diagnosis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView diagnosis;
        TextView doctor;
        TextView visit_type;
        TextView date;
        TextView description;
        TextView prescription;
        CardView prescription_card;

        public ViewHolder(View itemView) {
            super(itemView);
            diagnosis = itemView.findViewById(R.id.text_diagnosis);
            doctor = itemView.findViewById(R.id.text_doctor);
            visit_type = itemView.findViewById(R.id.text_visit_type);
            date = itemView.findViewById(R.id.text_date);
            prescription = itemView.findViewById(R.id.text_prescription);
            description = itemView.findViewById(R.id.text_description);
            prescription_card = itemView.findViewById(R.id.prescription_card);
        }
    }
}
