package com.example.assignmentapp.Presenter;

import android.util.Log;

import com.example.assignmentapp.API.APIClient;
import com.example.assignmentapp.API.APIInterface;
import com.example.assignmentapp.Model.ResponseModel;
import com.example.assignmentapp.Model.UserResponseModel;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetNoticeIntractorImpl implements MainContract.GetNoticeIntractor {

    @Override
    public void getNoticeArrayList(final OnFinishedListener onFinishedListener,int page) {

        //int pageSize = 5;
        APIInterface service = APIClient.getClient().create(APIInterface.class);

        Call<ArrayList<UserResponseModel>> call = service.getAuthorList();
        call.enqueue(new Callback<ArrayList<UserResponseModel>>() {
            @Override
            public void onResponse(Call<ArrayList<UserResponseModel>> call, Response<ArrayList<UserResponseModel>> response) {
                Gson gson = new Gson();

                Log.e("listArray",""+gson.toJson(response.body()));
                onFinishedListener.onFinished(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<UserResponseModel>> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });
        /*call.enqueue(new Call<List<UserResponseModel>>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Gson gson = new Gson();

                Log.e("listArray",""+gson.toJson(response.body().getData()));
                onFinishedListener.onFinished(response.body().getData());

            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                onFinishedListener.onFailure(t);
            }
        });*/

    }

}

