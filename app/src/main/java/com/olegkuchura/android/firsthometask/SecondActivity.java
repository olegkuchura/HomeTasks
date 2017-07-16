package com.olegkuchura.android.firsthometask;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    private TextView textViewEmail;
    private Button buttonConfirm;
    private Button buttonReject;

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewEmail = (TextView) findViewById(R.id.tv_email);
        buttonConfirm = (Button) findViewById(R.id.b_confirm);
        buttonReject = (Button) findViewById(R.id.b_reject);

        email = getIntent().getStringExtra("email");
        if (email != null) {
            textViewEmail.setText(email);
        }

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isConfirmed", true);
                setResult(RESULT_OK, intent);

                Intent mailIntent = new Intent(Intent.ACTION_SEND);
                mailIntent.setType("message/rfc822");
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                startActivity(Intent.createChooser(mailIntent, "Отправка email"));

                Toast.makeText(SecondActivity.this, R.string.sending, Toast.LENGTH_SHORT)
                        .show();

                finish();
            }
        });

        buttonReject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("isConfirmed", false);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}
