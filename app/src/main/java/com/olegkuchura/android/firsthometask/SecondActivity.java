package com.olegkuchura.android.firsthometask;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.olegkuchura.android.firsthometask.model.User;

public class SecondActivity extends AppCompatActivity {
    public static final int REQUEST_CODE = 1;
    private static final int REQUEST_CODE_FOR_SEND = 2;

    private ImageView imageViewIndicator;
    private TextView textViewUserName;
    private TextView textViewCategory;
    private TextView textViewEmail;
    private Button buttonSend;
    private Button buttonReject;

    private String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        imageViewIndicator = (ImageView) findViewById(R.id.iv_indicator);
        textViewUserName = (TextView) findViewById(R.id.tv_user_name);
        textViewCategory = (TextView) findViewById(R.id.tv_category);
        textViewEmail = (TextView) findViewById(R.id.tv_email);
        buttonSend = (Button) findViewById(R.id.b_send);
        buttonReject = (Button) findViewById(R.id.b_reject);

        boolean isOnline = getIntent().getBooleanExtra("isUserOnline", false);
        if (isOnline) {
            imageViewIndicator.setImageResource(R.drawable.green_circle);
        } else {
            imageViewIndicator.setImageResource(R.drawable.gray_circle);
        }

        String userName = getIntent().getStringExtra("userName");
        if (userName != null) {
            textViewUserName.setText(userName);
        }

        String str = getIntent().getStringExtra("userCategory");
        if (str != null) {
            User.Category userCategory = User.Category.valueOf(str);
            switch (userCategory) {
                case FAMILY:
                    textViewCategory.setText(R.string.user_category_family);
                    break;
                case FRIENDS:
                    textViewCategory.setText(R.string.user_category_friend);
                    break;
                case WORK:
                    textViewCategory.setText(R.string.user_category_work);
                    break;
                case OTHERS:
                    textViewCategory.setText(R.string.user_category_others);
                    break;
            }
        }

        email = getIntent().getStringExtra("userAddress");
        if (email != null) {
            textViewEmail.setText(email);
        }

        buttonSend.setOnClickListener(new View.OnClickListener() {
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
