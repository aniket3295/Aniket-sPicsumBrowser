package com.example.assignmentapp.API;

import com.example.assignmentapp.Model.ResponseModel;
import com.example.assignmentapp.Model.UserResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("/list")
    Call<ArrayList<UserResponseModel>> getAuthorList();
}
