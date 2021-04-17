package com.example.android.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class NewAccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        TextView signinButton = findViewById(R.id.signin_newaccount);
        Button nextButton = findViewById(R.id.next_newaccount);

        signinButton.setOnClickListener(v ->{
            finish();
        });

        nextButton.setOnClickListener(view -> {
            Intent i = new Intent(NewAccount.this,
                    Verification.class);
            startActivity(i);
        });
    }
}