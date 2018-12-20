package com.example.madina.childvac.fragments;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madina.childvac.App;
import com.example.madina.childvac.R;
import com.example.madina.childvac.models.CalendarEvent;
import com.example.madina.childvac.models.Ticket;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScheduleFragment extends Fragment {

    private static final String TAG = "ScheduleFragment";

    private CompactCalendarView compactCalendar;
    private TextView monthName;
    private TextView eventDateTextView;
    private TextView eventTitleTextView;
    private TextView eventBodyTextView;
    private TextView eventTimeTextView;
    private TextView eventRoomTextView;
    private SwipeRefreshLayout swipeRefreshLayout;

    private SimpleDateFormat dateFormatForMonth = new SimpleDateFormat("MMM YYYY", Locale.getDefault());
    private SimpleDateFormat dateFormatForEventTitle = new SimpleDateFormat("EEE, MMM d", Locale.getDefault());

    public ScheduleFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_schedule, container, false);

        // Initializing ViewGroups

        eventDateTextView = view.findViewById(R.id.schedule_event_date);
        eventTitleTextView = view.findViewById(R.id.schedule_event_title);
        eventBodyTextView = view.findViewById(R.id.schedule_event_body);
        eventTimeTextView = view.findViewById(R.id.schedule_event_time);
        eventRoomTextView = view.findViewById(R.id.schedule_event_room);

        swipeRefreshLayout = view.findViewById(R.id.scheduleRefresh);

        addEvents(getEvents(view), view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                clearEvents(view);
                addEvents(getEvents(view), view);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    public void clearEvents(View view){
        compactCalendar = view.findViewById(R.id.compactcalendar_view);
        compactCalendar.hideCalendarWithAnimation();
        compactCalendar.clearAnimation();
        compactCalendar.removeAllEvents();
    }

    public void addEvents(List<Event> eventList, View view) {
        compactCalendar = view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        monthName = view.findViewById(R.id.shedule_month_textView);
        monthName.setText(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
        compactCalendar.showCalendarWithAnimation();

        for(Event e : eventList) {
            compactCalendar.addEvent(e);
        }

        compactCalendar.setListener(new CompactCalendarView.CompactCalendarViewListener() {
            @Override
            public void onDayClick(Date dateClicked) {
                eventDateTextView.setText(dateFormatForEventTitle.format(dateClicked));
                List<Event> events = compactCalendar.getEvents(dateClicked);
                if (events.size() != 0) {
                    CalendarEvent ev = (CalendarEvent) events.get(0).getData();
                    eventTitleTextView.setText(ev.getTitle());
                    eventBodyTextView.setText(ev.getBody());
                    eventTimeTextView.setText(ev.getTime());
                    eventRoomTextView.setText(ev.getRoom());
                } else {
                    eventTitleTextView.setText("No Event");
                    eventBodyTextView.setText("");
                    eventTimeTextView.setText("");
                    eventRoomTextView.setText("");
                }
            }

            @Override
            public void onMonthScroll(Date firstDayOfNewMonth) {
                monthName.setText(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
            }
        });
    }

    List<Event> getEvents(final View view){

        int childId = this.getActivity().getIntent().getIntExtra("childId", 1);
        final List<Event> events = new ArrayList<>();

        App.getApi().getTickets(childId).enqueue(new Callback<List<Ticket>>() {
            @Override
            public void onResponse(Call<List<Ticket>> call, Response<List<Ticket>> response) {
                List<Ticket> tickets = response.body();
                final String datePattern = "MM-dd-YYYY HH:mm:ss";

                String myDate = "29-10-2014 18:10:45";
                LocalDateTime localDateTime = LocalDateTime.parse(myDate,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss") );
                for(Ticket t : tickets) {
                    try {
                        String ticketDateString = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
                                .format(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                                        .parse(t.getStartDate()));

                        int a = ticketDateString.length();

                        localDateTime = LocalDateTime.parse(ticketDateString, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                    CalendarEvent calendarEvent1 = new CalendarEvent(t.getDiagnosis(),
                            t.getPrescription() == null ? "" : t.getPrescription(),
                            localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli(),
                            t.getRoom());
                    Event ev1 = new Event(Color.RED, calendarEvent1.getEpoch(), calendarEvent1);
                    events.add(ev1);
                }
                addEvents(events, view);
            }

            @Override
            public void onFailure(Call<List<Ticket>> call, Throwable t) {

            }
        });
        return events;
    }
}
