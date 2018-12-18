package com.example.madina.childvac.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madina.childvac.R;

import java.util.ArrayList;

public class TicketsRecyclerViewAdapter extends RecyclerView.Adapter<TicketsRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> dayList;
    private ArrayList<String> vaccineNameList;
    private ArrayList<String> roomList;
    private ArrayList<String> nurseList;
    private ArrayList<String> timeList;
    private ArrayList<String> statusList;

    private Context context;

    public TicketsRecyclerViewAdapter(Context context,
                                      ArrayList<String> dayList,
                                      ArrayList<String> vaccineNameList,
                                      ArrayList<String> roomList,
                                      ArrayList<String> nurseList,
                                      ArrayList<String> timeList,
                                      ArrayList<String> statusList) {
        this.dayList = dayList;
        this.vaccineNameList = vaccineNameList;
        this.roomList = roomList;
        this.nurseList = nurseList;
        this.timeList = timeList;
        this.statusList = statusList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cardview_tickets, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        viewHolder.day.setText(dayList.get(i));
        viewHolder.vaccineName.setText(vaccineNameList.get(i));
        viewHolder.room.setText(roomList.get(i));
        viewHolder.nurse.setText(nurseList.get(i));
        viewHolder.time.setText(timeList.get(i));
        viewHolder.status.setText(statusList.get(i));
    }

    @Override
    public int getItemCount() {
        return vaccineNameList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView day;
        TextView vaccineName;
        TextView room;
        TextView nurse;
        TextView time;
        TextView status;
        CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            day = itemView.findViewById(R.id.ticket_day);
            vaccineName = itemView.findViewById(R.id.ticket_vaccine_name);
            room = itemView.findViewById(R.id.ticket_room_number);
            nurse = itemView.findViewById(R.id.ticket_nurse_name);
            time = itemView.findViewById(R.id.ticket_time);
            status = itemView.findViewById(R.id.ticket_status);
            cardView = itemView.findViewById(R.id.ticket_card);
        }
    }
}
