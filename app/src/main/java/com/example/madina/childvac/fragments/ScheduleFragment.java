package com.example.madina.childvac.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.madina.childvac.R;
import com.example.madina.childvac.models.CalendarEvent;
import com.github.sundeepk.compactcalendarview.CompactCalendarView;
import com.github.sundeepk.compactcalendarview.domain.Event;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        compactCalendar = view.findViewById(R.id.compactcalendar_view);
        compactCalendar.setUseThreeLetterAbbreviation(true);
        monthName = view.findViewById(R.id.shedule_month_textView);
        monthName.setText(dateFormatForMonth.format(compactCalendar.getFirstDayOfCurrentMonth()));
        eventDateTextView = view.findViewById(R.id.schedule_event_date);
        eventTitleTextView = view.findViewById(R.id.schedule_event_title);
        eventBodyTextView = view.findViewById(R.id.schedule_event_body);
        eventTimeTextView = view.findViewById(R.id.schedule_event_time);
        eventRoomTextView = view.findViewById(R.id.schedule_event_room);


        // Sample Calendar Event
        CalendarEvent calendarEvent1 = new CalendarEvent("Post-vaccination health check",
                "After receiving a flu vaccine your child may have high temperature, stomachaches and stuff",
                1545361200000L, 401);

        CalendarEvent calendarEvent2 = new CalendarEvent("Last Day of Year",
                "TODAY IS YOUR LAST CHANCHE TO DO THINGS YOU'VE ALWAYS BEEN POSTPONING",
                1546192800000L, 2018);

        Event ev1 = new Event(Color.RED, calendarEvent1.getEpoch(), calendarEvent1);
        Event ev2 = new Event(Color.GREEN, calendarEvent2.getEpoch(), calendarEvent2);
        compactCalendar.addEvent(ev1);
        compactCalendar.addEvent(ev2);

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


        // Inflate the layout for this fragment
        return view;
    }

}
