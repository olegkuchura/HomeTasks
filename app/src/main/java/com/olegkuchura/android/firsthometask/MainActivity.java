package com.olegkuchura.android.firsthometask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String activityName = MainActivity.class.getSimpleName();
    private static final int REQUEST_CODE_SECOND_ACTIVITY = 1;

    private EditText editTextEmail;
    private CheckBox checkBoxLet;
    private Button buttonClear;
    private Button buttonSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextEmail = (EditText) findViewById(R.id.et_input_email);
        checkBoxLet = (CheckBox) findViewById(R.id.cb_let);
        buttonClear = (Button) findViewById(R.id.b_clear);
        buttonSend = (Button) findViewById(R.id.b_send);

        checkBoxLet.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d(activityName, "onCheckedChanged() " + isChecked);
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
                    Toast.makeText(MainActivity.this, R.string.incorrect_email, Toast.LENGTH_SHORT)
                            .show();
                } else {
                    Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                    intent.putExtra("email", editTextEmail.getText().toString());
                    startActivityForResult(intent, REQUEST_CODE_SECOND_ACTIVITY);
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(activityName, "onActivityResult() requestCode=" + requestCode + " resultCode=" + resultCode);
        if (requestCode == REQUEST_CODE_SECOND_ACTIVITY) {
            if (resultCode == RESULT_OK) {
                if (data == null) return;
                if (data.getBooleanExtra("isConfirmed", false)) {
                    editTextEmail.setText("");
                } else {
                    Toast.makeText(this, R.string.rejected, Toast.LENGTH_SHORT)
                            .show();
                }
            } else {
                Toast.makeText(this, R.string.rejected, Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }
}
