package com.example.madina.childvac.api;

import com.example.madina.childvac.models.Child;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ChildVacApi {

    @GET("api/children/{login}")
    Call <Child> getChild(@Path("login") String login);
}

