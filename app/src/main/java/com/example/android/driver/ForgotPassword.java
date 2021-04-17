package com.example.android.driver;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.driver.api.ApiClient;
import com.example.android.driver.api.ApiInterface;
import com.example.android.driver.model.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassword extends AppCompatActivity {

    String TAG = "TAG";

    EditText ed_forgot_pass_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        ed_forgot_pass_email = findViewById(R.id.ed_forgot_password_email);

        Button submit = findViewById(R.id.submit_forgot_password);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String _email = ed_forgot_pass_email.getText().toString();

                if(validation()){

                    ProgressDialog pd = new ProgressDialog(ForgotPassword.this);
                    pd.setMessage("Please Wait ...");
                    pd.show();



                    ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                    Call<LoginResponse> call = apiService.forgotPassword(_email);

                    call.enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                            if (response.body().getResult_code() == 1 ) {
                                pd.dismiss();

                                Log.d(TAG, "onResponse: 1" + response.body().getResult_code());
                                Toast.makeText( ForgotPassword.this, "Password Sent to your mail.", Toast.LENGTH_LONG).show();
                                finish();


                            }
                            else
                            {

                                Toast.makeText( ForgotPassword.this, "Email Not Registered !!", Toast.LENGTH_LONG).show();
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
            }
        });
    }

    public Boolean validation() {

        Boolean value = true;

        String _email = ed_forgot_pass_email.getText().toString().trim();



        if (_email.isEmpty()) {
            ed_forgot_pass_email.setError("Email required");
            ed_forgot_pass_email.requestFocus();
            value = false;
        }

        return value;
    }
}