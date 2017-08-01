package com.olegkuchura.android.firsthometask.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.olegkuchura.android.firsthometask.R;
import com.olegkuchura.android.firsthometask.SecondActivity;
import com.olegkuchura.android.firsthometask.adapters.UsersRecyclerAdapter;
import com.olegkuchura.android.firsthometask.listeners.OnRecyclerItemClickListener;
import com.olegkuchura.android.firsthometask.model.User;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Oleg on 23.07.2017.
 */

public class FirstFragment extends Fragment{
    private static final String fragmentName = FirstFragment.class.getSimpleName();

    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.rv_list);

        final List<User> list = new ArrayList();
        list.add(new User(0, "Alexander", true, "Alex@gmail.com", User.Category.WORK));
        list.add(new User(1, "Михаил", false, "Michael@gmail.com", User.Category.WORK));
        list.add(new User(2, "Толик", false, "Tolian@gmail.com", User.Category.FRIENDS));
        list.add(new User(3, "Олег", true, "Oleg@gmail.com", User.Category.FRIENDS));
        list.add(new User(4, "Анастасия", true, "Anastasia@gmail.com", User.Category.FRIENDS));
        list.add(new User(5, "Вадим", false, "Vadim@gmail.com", User.Category.FAMILY));
        list.add(new User(6, "Марина", false, "Marina@gmail.com", User.Category.FAMILY));
        list.add(new User(7, "Виталий", true, "Vitaliy@gmail.com", User.Category.FRIENDS));
        list.add(new User(8, "Рома", false, "Roma@gmail.com", User.Category.FRIENDS));
        list.add(new User(9, "Таня", true, "Tanya@gmail.com", User.Category.FAMILY));
        list.add(new User(10, "Кристина", true, "Christina@gmail.com", User.Category.OTHERS));
        list.add(new User(11, "Сергей", false, "Sergei@gmail.com", User.Category.WORK));
        list.add(new User(12, "Виктория", true, "Victoria@gmail.com", User.Category.FRIENDS));
        list.add(new User(13, "Андрей", true, "Andrei@gmail.com", User.Category.FRIENDS));
        list.add(new User(14, "Юрий", true, "Yuri@gmail.com", User.Category.OTHERS));

        sortByName(list);

        UsersRecyclerAdapter adapter = new UsersRecyclerAdapter( getActivity(), list, new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(getActivity(), SecondActivity.class);
                intent.putExtra("userName", list.get(position).getUserName());
                intent.putExtra("isUserOnline", list.get(position).isOnline());
                intent.putExtra("userCategory", list.get(position).getCategory().toString());
                intent.putExtra("userAddress", list.get(position).getUserAddress());
                getActivity().startActivityForResult(intent, SecondActivity.REQUEST_CODE);
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }

    private void sortByName(List<User> list) {
        Collections.sort(list, new Comparator<User>() {
            public int compare(User u1, User u2) {
                return u1.getUserName().compareTo(u2.getUserName());
            }
        });
    }

}
