package com.example.assignmentapp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.assignmentapp.Model.UserResponseModel;
import com.example.assignmentapp.R;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

private ArrayList<UserResponseModel> dataList;
private RecyclerItemClickListener recyclerItemClickListener;
private Context context;

public UserAdapter(ArrayList<UserResponseModel> dataList , RecyclerItemClickListener recyclerItemClickListener,Context context) {
        this.dataList = dataList;
        this.recyclerItemClickListener = recyclerItemClickListener;
        this.context = context;
        }


    @Override
    public UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.list_items, parent, false);
            return new UserViewHolder(view);
            }

    @Override
    public void onBindViewHolder(UserViewHolder holder, final int position) {

            if(dataList.get(position).getAuthor()!= null || dataList.get(position).getAuthor() != null) {
                holder.user_name.setText(dataList.get(position).getAuthor());
            }else {
                holder.user_name.setText("");
            }

                Glide.with(context)
                        .load("https://picsum.photos/300/300?image"+dataList.get(position).getId())
                        .apply(new RequestOptions()
                                .placeholder(R.drawable.ic_launcher_background)
                        )
                        .into(holder.user_img);


            holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerItemClickListener.onItemClick(dataList.get(position));
            }

            });
            }

    @Override
    public int getItemCount() {
            return dataList.size();
            }

    class UserViewHolder extends RecyclerView.ViewHolder {

    TextView user_name, user_email;
    ImageView user_img;

        public UserViewHolder(View itemView) {
        super(itemView);
            user_name =  itemView.findViewById(R.id.user_name);
            user_email =  itemView.findViewById(R.id.user_name);
            user_img =  itemView.findViewById(R.id.user_img);

    }
}
}
