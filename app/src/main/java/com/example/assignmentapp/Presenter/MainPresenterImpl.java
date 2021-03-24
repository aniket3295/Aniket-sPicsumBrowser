package com.example.assignmentapp.Presenter;

import com.example.assignmentapp.Model.UserResponseModel;

import java.util.ArrayList;

public class MainPresenterImpl implements MainContract.presenter, MainContract.GetNoticeIntractor.OnFinishedListener {

    private MainContract.MainView mainView;
    private MainContract.GetNoticeIntractor getNoticeIntractor;

    public MainPresenterImpl(MainContract.MainView mainView, MainContract.GetNoticeIntractor getNoticeIntractor) {
        this.mainView = mainView;
        this.getNoticeIntractor = getNoticeIntractor;
    }

    @Override
    public void onDestroy() {

        mainView = null;

    }

    @Override
    public void onRefreshButtonClick() {

        /*if(mainView != null){
            mainView.showProgress();
        }*/
       // getNoticeIntractor.getNoticeArrayList(this,page);

    }

    @Override
    public void requestDataFromServer(int page) {
        getNoticeIntractor.getNoticeArrayList(this,page);
    }


    @Override
    public void onFinished(ArrayList<UserResponseModel> noticeArrayList) {
        if(mainView != null){
            mainView.setDataToRecyclerView(noticeArrayList);
            mainView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {
        if(mainView != null){
            mainView.onResponseFailure(t);
            mainView.hideProgress();
        }
    }
}

