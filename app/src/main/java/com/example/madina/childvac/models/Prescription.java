package com.example.madina.childvac.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prescription {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("childId")
    @Expose
    private int childId;
    @SerializedName("room")
    @Expose
    private int room;
    @SerializedName("dateTime")
    @Expose
    private String dateTime;
    @SerializedName("doctorFullName")
    @Expose
    private String doctorFullName;
    @SerializedName("diagnosis")
    @Expose
    private String diagnosis;
    @SerializedName("visitType")
    @Expose
    private String visitType;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("prescriptionText")
    @Expose
    private String prescriptionText;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getDoctorFullName() {
        return doctorFullName;
    }

    public void setDoctorFullName(String doctorFullName) {
        this.doctorFullName = doctorFullName;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrescriptionText() {
        return prescriptionText;
    }

    public void setPrescriptionText(String prescriptionText) {
        this.prescriptionText = prescriptionText;
    }

}