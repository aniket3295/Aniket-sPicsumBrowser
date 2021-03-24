package com.example.assignmentapp.Presenter;

import com.example.assignmentapp.Model.UserResponseModel;

import java.util.ArrayList;

public interface MainContract {


    interface presenter{

        void onDestroy();

        void onRefreshButtonClick();

        void requestDataFromServer(int page);

    }


    interface MainView {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(ArrayList<UserResponseModel> noticeArrayList);

        void onResponseFailure(Throwable throwable);

    }


    interface GetNoticeIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<UserResponseModel> noticeArrayList);
            void onFailure(Throwable t);
        }

        void getNoticeArrayList(OnFinishedListener onFinishedListener,int page);
    }

}
