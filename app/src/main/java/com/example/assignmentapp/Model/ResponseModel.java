package com.example.assignmentapp.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseModel {


    private ArrayList<UserResponseModel>data;

    public ArrayList<UserResponseModel> getData() {
        return data;
    }

    public void setData(ArrayList<UserResponseModel> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "data=" + data +
                '}';
    }
}
