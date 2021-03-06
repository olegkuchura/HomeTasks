package com.olegkuchura.android.firsthometask.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentContainer;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.olegkuchura.android.firsthometask.R;

/**
 * Created by Oleg on 24.07.2017.
 */

public class RejectFragment extends Fragment {

    private ImageView imageViewGalka;
    private TextView textView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        imageViewGalka = (ImageView) view.findViewById(R.id.iv_result_icon);
        textView = (TextView) view.findViewById(R.id.tv_text);
        button = (Button) view.findViewById(R.id.b_ok);

        imageViewGalka.setImageResource(R.drawable.ic_reject);

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
