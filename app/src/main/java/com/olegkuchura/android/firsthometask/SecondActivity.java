package com.olegkuchura.android.firsthometask;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_FOR_SEND = 2;

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

                Intent mailIntent = new Intent(Intent.ACTION_SENDTO);
                mailIntent.setData(Uri.parse("mailto:"));
                mailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {email});
                startActivityForResult(Intent.createChooser(mailIntent, "Отправка email"), REQUEST_CODE_FOR_SEND);

                Toast.makeText(SecondActivity.this, R.string.sending, Toast.LENGTH_SHORT)
                        .show();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_FOR_SEND) {
            finish();
        }
    }
}
