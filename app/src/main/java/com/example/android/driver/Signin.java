package com.example.android.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.driver.api.ApiClient;
import com.example.android.driver.api.ApiInterface;
import com.example.android.driver.model.LoginResponse;
import com.example.android.driver.model.OrderDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class Signin extends AppCompatActivity {

    SharedPreferences sharedPreferences;





    String TAG = "TAG";

    EditText email;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        sharedPreferences = this.getSharedPreferences("DRIVER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        TextView forgotPassword = findViewById(R.id.forgotpassword);
        TextView newAccount = findViewById(R.id.newaccount);
        Button signinButton = findViewById(R.id.signin);

        email = findViewById(R.id.ed_email_id);
        pass  = findViewById(R.id.ed_password);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signin.this,
                        ForgotPassword.class);
                startActivity(i);
            }
        });

        newAccount.setOnClickListener(view -> {
            Intent i = new Intent(Signin.this,
                    NewAccount.class);
            startActivity(i);
        });
        signinButton.setOnClickListener(View -> {


            String _email = email.getText().toString();
            String _pass = pass.getText().toString();


            if (validation()) {

                ProgressDialog pd = new ProgressDialog(Signin.this);
                pd.setMessage("Please Wait ...");
                pd.show();




                ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                Call<LoginResponse> call = apiService.login(_email,_pass);

                call.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                        if (response.body().getResult_code() == 1 ) {
                            pd.dismiss();

                            String driverEmail = response.body().getEmail_id();
                            String driverId = response.body().getDriver_id();
                            editor.putString("driverEmail",driverEmail);
                            editor.putString("driverId",driverId);
                            editor.putBoolean("REMEMBER",true);
                            editor.apply();
                            editor.commit();

                          

                            Log.d(TAG, "onResponse: 1" + response.body().getResult_code());
                            Toast.makeText( Signin.this, "Login Successful", Toast.LENGTH_LONG).show();

                            Intent i = new Intent(Signin.this,
                                    AllOrders.class);
                            startActivity(i);
                            finish();


                        }
                        else
                            {

                                Toast.makeText( Signin.this, "Wrong email or password !!", Toast.LENGTH_LONG).show();
                            Log.d(TAG, "onResponse: 0" + response.body().getResult_code());
                            pd.dismiss();
                            }


                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Log.d("TAG","Server Down !");
                    }
                });

            }


        });
    }

    public Boolean validation() {

        Boolean value = true;

        String _email = email.getText().toString().trim();
        String _password = pass.getText().toString().trim();


        if (_email.isEmpty()) {
            email.setError("Email required");
            email.requestFocus();
            value = false;
        }

        if (_password.isEmpty()) {
            pass.setError("Password required");
            value = false;
        }

        return value;
    }
}