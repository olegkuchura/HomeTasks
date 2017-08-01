package com.olegkuchura.android.firsthometask.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.olegkuchura.android.firsthometask.R;

public class ConfirmFragment extends Fragment {

    private ImageView imageViewGalka;
    private TextView textView;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        imageViewGalka = (ImageView) view.findViewById(R.id.iv_result_icon);
        textView = (TextView) view.findViewById(R.id.tv_text);
        button = (Button) view.findViewById(R.id.b_ok);

        imageViewGalka.setImageResource(R.drawable.ic_galka);

        textView.setText(R.string.user_confirmed);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                if (fragmentManager.getBackStackEntryCount() > 0) {
                    fragmentManager.popBackStack();
                }
            }
        });

        return view;
    }

}
