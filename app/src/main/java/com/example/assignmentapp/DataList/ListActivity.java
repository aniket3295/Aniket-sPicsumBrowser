package com.example.assignmentapp.DataList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.assignmentapp.Adapter.RecyclerItemClickListener;
import com.example.assignmentapp.Adapter.UserAdapter;
import com.example.assignmentapp.Model.UserResponseModel;
import com.example.assignmentapp.Presenter.GetNoticeIntractorImpl;
import com.example.assignmentapp.Presenter.MainContract;
import com.example.assignmentapp.Presenter.MainPresenterImpl;
import com.example.assignmentapp.R;

import java.util.ArrayList;
import java.util.Objects;

public class ListActivity extends AppCompatActivity implements MainContract.MainView{

    private RecyclerView data_list;
    private ProgressBar progressBar;
    private MainContract.presenter presenter;
    private int page= 1;
    private RecyclerView.LayoutManager layoutManager;
    private boolean isLoading = true;
    private ArrayList<UserResponseModel> totalList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initialization();
        initProgressBar();

        presenter = new MainPresenterImpl(this, new GetNoticeIntractorImpl());
        if (isConnected()) {
            presenter.requestDataFromServer(page);
        }else {
            AlertDialog.Builder builder = new AlertDialog.Builder(ListActivity.this);
            builder.setMessage("No internet connection");
            builder.setCancelable(false);
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList("userList", totalList);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        totalList = savedInstanceState.getParcelableArrayList("userList");
    }

    private void initialization() {

        data_list = findViewById(R.id.data_list);

        layoutManager =  new GridLayoutManager(this, 2);
        data_list.setLayoutManager(layoutManager);
        totalList = new ArrayList<>();

    }

    private void initProgressBar() {
        progressBar = new ProgressBar(this, null, android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);

        RelativeLayout relativeLayout = new RelativeLayout(this);
        relativeLayout.setGravity(Gravity.CENTER);
        relativeLayout.addView(progressBar);

        RelativeLayout.LayoutParams params = new
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        progressBar.setVisibility(View.INVISIBLE);

        this.addContentView(relativeLayout, params);
    }

    private RecyclerItemClickListener recyclerItemClickListener = new RecyclerItemClickListener() {
        @Override
        public void onItemClick(UserResponseModel userResponseModel) {

            Toast.makeText(ListActivity.this,
                    "List title:  " + userResponseModel.getAuthor(),
                    Toast.LENGTH_LONG).show();

        }
    };

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void setDataToRecyclerView(ArrayList<UserResponseModel> userResponseModels) {
        if(userResponseModels.size() != 0)
        {
            totalList.addAll(userResponseModels);
        }

        Log.e("Size",""+totalList.size());
        UserAdapter adapter = new UserAdapter(totalList , recyclerItemClickListener,this);
        data_list.setAdapter(adapter);
    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(ListActivity.this,
                "Something went wrong...Error message: " + throwable.getMessage(),
                Toast.LENGTH_LONG).show();

        Log.e("Throwable error",""+throwable.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    public boolean isConnected() {
        boolean connected = false;
        try {
            ConnectivityManager cm = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo nInfo = cm.getActiveNetworkInfo();
            connected = nInfo != null && nInfo.isAvailable() && nInfo.isConnected();
            return connected;

        } catch (Exception e) {
            Log.e("Connectivity Exception", e.getMessage());
        }
        return connected;
    }

}