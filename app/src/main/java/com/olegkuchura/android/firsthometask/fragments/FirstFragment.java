package com.olegkuchura.android.firsthometask.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.olegkuchura.android.firsthometask.MainActivity;
import com.olegkuchura.android.firsthometask.R;
import com.olegkuchura.android.firsthometask.SecondActivity;

/**
 * Created by Oleg on 23.07.2017.
 */

public class FirstFragment extends Fragment{
    private static final String fragmentName = FirstFragment.class.getSimpleName();

    private EditText editTextEmail;
    private CheckBox checkBoxLet;
    private Button buttonClear;
    private Button buttonSend;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        editTextEmail = (EditText) view.findViewById(R.id.et_input_email);
        checkBoxLet = (CheckBox) view.findViewById(R.id.cb_let);
        buttonClear = (Button) view.findViewById(R.id.b_clear);
        buttonSend = (Button) view.findViewById(R.id.b_send);

        checkBoxLet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(fragmentName, "onCheckedChanged() " + isChecked);
                if (isChecked){
                    buttonSend.setEnabled(true);
                } else {
                    buttonSend.setEnabled(false);
                }
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextEmail.setText("");
            }
        });

        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                if (email.equals("") || !email.contains("@")){
                    Toast.makeText(getActivity(), R.string.incorrect_email, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Intent intent = new Intent(getActivity(), SecondActivity.class);
                    intent.putExtra("email", editTextEmail.getText().toString());
                    getActivity().startActivityForResult(intent, SecondActivity.REQUEST_CODE);
                }
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        checkBoxLet.setChecked(false);
    }

    public void clearEditText(){
        editTextEmail.setText("");
    }
}
