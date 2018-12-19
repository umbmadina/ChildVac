package com.example.madina.childvac.api;

import com.example.madina.childvac.models.Child;
import com.example.madina.childvac.models.Prescription;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ChildVacApi {

    @GET("api/children/{login}")
    Call <Child> getChild(@Path("login") String login);

    @GET("api/prescription/{childId}")
    Call <List<Prescription>> getChildPrescription(@Path("childId") int id);

    @PUT("api/children/{id}/")
    Call<Child> updateChild(@Path("id") int id, @Body Child child);
}

