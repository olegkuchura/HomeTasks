package com.olegkuchura.android.firsthometask.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.olegkuchura.android.firsthometask.R;
import com.olegkuchura.android.firsthometask.listeners.OnRecyclerItemClickListener;
import com.olegkuchura.android.firsthometask.model.User;

import java.util.List;

/**
 * Created by Oleg on 31.07.2017.
 */

public class UsersRecyclerAdapter extends RecyclerView.Adapter<UsersRecyclerAdapter.ViewHolder> {

    private Context ctx;

    private List<User> users;

    private OnRecyclerItemClickListener listener;

    public UsersRecyclerAdapter(Context ctx, List<User> users, OnRecyclerItemClickListener listener) {
        this.ctx = ctx;
        this.users = users;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_list_item, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(v, viewHolder.getAdapterPosition());
                }
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User user = users.get(position);
        holder.textViewUserName.setText(user.getUserName());

        if (user.isOnline()) {
            holder.imageViewIndicator.setImageResource(R.drawable.green_circle);
        } else {
            holder.imageViewIndicator.setImageResource(R.drawable.gray_circle);
        }

        switch (user.getCategory()) {
            case FAMILY:
                holder.imageViewUserGroup.setImageResource(R.drawable.family);
                break;
            case FRIENDS:
                holder.imageViewUserGroup.setImageResource(R.drawable.friends);
                break;
            case WORK:
                holder.imageViewUserGroup.setImageResource(R.drawable.work_group);
                break;
            case OTHERS:
                holder.imageViewUserGroup.setImageResource(R.drawable.others);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewIndicator;
        TextView textViewUserName;
        ImageView imageViewUserGroup;

        public ViewHolder(View itemView) {
            super(itemView);

            imageViewIndicator = (ImageView) itemView.findViewById(R.id.iv_indicator);
            textViewUserName = (TextView) itemView.findViewById(R.id.tv_user_name);
            imageViewUserGroup = (ImageView) itemView.findViewById(R.id.iv_user_group);
        }
    }
}
