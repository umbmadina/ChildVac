package com.example.madina.childvac.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CalendarEvent {

    private String title;
    private String body;
    private long epoch;
    private int room;

    public CalendarEvent() {
    }

    public CalendarEvent(String title, String body, long epoch, int room) {
        this.title = title;
        this.body = body;
        this.epoch = epoch;
        this.room = room;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTime() {
        SimpleDateFormat format = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return format.format(new Date(epoch));
    }

    public long getEpoch() {
        return epoch;
    }

    public void setEpoch(long epoch) {
        this.epoch = epoch;
    }

    public String getRoom() {
        return String.valueOf(room);
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "CalendarEvent{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", epoch=" + epoch +
                ", room=" + room +
                '}';
    }
}
