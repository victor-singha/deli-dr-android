package com.example.android.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.driver.adapter.AcceptedOrdersAdapter;
import com.example.android.driver.adapter.AllOrdersAdapter;
import com.example.android.driver.api.ApiClient;
import com.example.android.driver.api.ApiInterface;
import com.example.android.driver.model.OrderDetails;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AcceptedOrders extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    private String TAG = "TAG";

    List<OrderDetails> dataList1;
    RecyclerView recyclerView;
    AcceptedOrdersAdapter myAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accepted_orders);









        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }


        TextView ActivityName = findViewById(R.id.toolbarbanner);
        ActivityName.setText("Accepted Orders");
        ImageView backArrowButton = findViewById(R.id.backArrow);
        backArrowButton.setOnClickListener(v->{
            finish();
        });


        dataList1 = new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.acceptedOrderRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter=new AcceptedOrdersAdapter(getApplicationContext(),dataList1);
        recyclerView.setAdapter(myAdapter);





        sharedPreferences = this.getSharedPreferences("DRIVER", Context.MODE_PRIVATE);
        String driverId = sharedPreferences.getString("driverId","");
        Log.d(TAG, "onCreate: " +driverId);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<OrderDetails>> call = apiService.getAcceptedOrders(driverId);

        call.enqueue(new Callback<List<OrderDetails>>() {
            @Override
            public void onResponse(Call<List<OrderDetails>> call, Response<List<OrderDetails>> response) {

                dataList1 = response.body();
                Log.d(TAG, "onResponse: " +dataList1);
                myAdapter.setAcceptedDataList(dataList1);
            }

            @Override
            public void onFailure(Call<List<OrderDetails>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });

    }
}