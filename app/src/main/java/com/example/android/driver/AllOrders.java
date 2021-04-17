package com.example.android.driver;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
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

import com.example.android.driver.adapter.AllOrdersAdapter;
import com.example.android.driver.api.ApiClient;
import com.example.android.driver.api.ApiInterface;
import com.example.android.driver.model.LoginResponse;
import com.example.android.driver.model.OrderDetails;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllOrders extends AppCompatActivity implements AllOrdersAdapter.AcceptButtonClickListener {
    private static final String TAG = "TAG";

    SharedPreferences sharedPreferences;

    List<OrderDetails> dataList;
    RecyclerView recyclerView;
    AllOrdersAdapter myAdapter;

    String driverId;
    String driverEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_orders);

        sharedPreferences = this.getSharedPreferences("DRIVER", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black, this.getTheme()));
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }

        driverId = sharedPreferences.getString("driverId","");
        driverEmail = sharedPreferences.getString("driverEmail","");

        TextView toolbar_driver_detail = findViewById(R.id.driver_detail);
        toolbar_driver_detail.setText(driverEmail);



        TextView toolbarTitle = findViewById(R.id.toolbarbanner);
        toolbarTitle.setText("All Orders");


        DrawerLayout drawer = findViewById(R.id.drawer);
        ImageView hamburger = findViewById(R.id.hamburger);
        NavigationView nav = findViewById(R.id.navigation);

        hamburger.setOnClickListener(v->{
            drawer.openDrawer(GravityCompat.START);
        });

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                item.setChecked(true);
                drawer.closeDrawers();

                if (id == R.id.allorders) {
                    finish();
                    Intent i = new Intent(AllOrders.this,
                            AllOrders.class);
                    startActivity(i);


                } else if (id == R.id.acceptedorders) {
                    Intent i = new Intent(AllOrders.this,
                            AcceptedOrders.class);
                    startActivity(i);

                }  else if (id == R.id.support) {
                    Intent i = new Intent(AllOrders.this,
                            Support.class);
                    startActivity(i);

                } else if (id == R.id.signout) {
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(AllOrders.this);
                    builder1.setMessage("Are you sure?");
                    builder1.setCancelable(true);
                    builder1.setTitle("Sign Out");

                    builder1.setPositiveButton(
                            "Yes",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    editor.clear();
                                    editor.apply();
                                    editor.commit();

                                    Intent i = new Intent(AllOrders.this,
                                            Signin.class);
                                    startActivity(i);
                                    finish();
                                }
                            });

                    builder1.setNegativeButton(
                            "No",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alert11 = builder1.create();
                    alert11.show();

                }

                DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
                drawer.closeDrawer(GravityCompat.START);

                return true;
            }
        });

        dataList = new ArrayList<>();
        recyclerView=(RecyclerView) findViewById(R.id.orderRecycler);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter=new AllOrdersAdapter(this, getApplicationContext(),dataList);
        recyclerView.setAdapter(myAdapter);




        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<OrderDetails>> call = apiService.getOrders();

        call.enqueue(new Callback<List<OrderDetails>>() {
            @Override
            public void onResponse(Call<List<OrderDetails>> call, Response<List<OrderDetails>> response) {

                dataList = response.body();
                Log.d(TAG, "onResponse: " +dataList);
                myAdapter.setDataList(dataList);
            }

            @Override
            public void onFailure(Call<List<OrderDetails>> call, Throwable t) {
                Log.d("TAG","Response = "+t.toString());
            }
        });
    }

    @Override
    public void onAcceptButtonClickListener(int position) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(AllOrders.this);
        builder1.setTitle("Accept Order");
        builder1.setMessage("Are you sure?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Accept",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {



                        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
                        Call<LoginResponse> call = apiService.accept(driverId,dataList.get(position).getOrder_id());

                        call.enqueue(new Callback<LoginResponse>() {
                            @Override
                            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {


                                if (response.body().getResult_code() == 1 ) {



                                    Log.d(TAG, "onResponse: 1" + response.body().getResult_code());
                                    Toast.makeText(AllOrders.this, dataList.get(position).getOrder_id(), Toast.LENGTH_SHORT).show();
                                    Toast.makeText( AllOrders.this, "Order Accepted", Toast.LENGTH_LONG).show();



                                }
                                else
                                {

                                    Toast.makeText( AllOrders.this, "Fail !!", Toast.LENGTH_LONG).show();
                                }


                            }

                            @Override
                            public void onFailure(Call<LoginResponse> call, Throwable t) {
                                Log.d("TAG","Server Down !");
                            }
                        });



                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }
}
