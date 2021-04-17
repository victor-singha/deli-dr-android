package com.example.android.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Boolean isREMEMBERED;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("DRIVER", Context.MODE_PRIVATE);
        isREMEMBERED = sharedPreferences.getBoolean("REMEMBER",false);


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
                try {
                    super.run();
                    sleep(1000);  //Delay of 1 second
                } catch (Exception e) {

                } finally {

                    if(isREMEMBERED){
                        Intent i = new Intent(MainActivity.this, AllOrders.class);
                        startActivity(i);
                        finish();
                    }else{
                        Intent i = new Intent(MainActivity.this, Signin.class);
                        startActivity(i);
                        finish();

                    }


//                    Intent i = new Intent(MainActivity.this,
//                            Signin.class);
//                    startActivity(i);
//                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}