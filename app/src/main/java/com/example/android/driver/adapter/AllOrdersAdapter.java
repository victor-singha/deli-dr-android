package com.example.android.driver.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.driver.R;
import com.example.android.driver.model.OrderDetails;

import java.lang.ref.WeakReference;
import java.util.List;

public class AllOrdersAdapter extends RecyclerView.Adapter<AllOrdersAdapter.MyViewHolder> {

    final private AcceptButtonClickListener mOnClickListener;
    Context context;
    List<OrderDetails> dataList;


    public interface AcceptButtonClickListener {

        void onAcceptButtonClickListener(int position);
    }


    public AllOrdersAdapter(AcceptButtonClickListener mOnClickListener, Context context, List<OrderDetails> dataList) {
        this.mOnClickListener = mOnClickListener;
        this.context = context;
        this.dataList = dataList;
    }
    public void setDataList(List<OrderDetails> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }




    @Override
    public AllOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_all_orders,parent,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderID.setText(dataList.get(position).getOrder_id().toString());
        holder.vehicleType.setText(dataList.get(position).getVehicle().toString());
        holder.pickupLocation.setText(dataList.get(position).getPick_location().toString());
        holder.dropLocation.setText(dataList.get(position).getDrop_location().toString());
        holder.date.setText(dataList.get(position).getPick_date().toString());

        holder.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mOnClickListener.onAcceptButtonClickListener(position);


            }
        });


    }



    @Override
    public int getItemCount() {
        if(dataList != null){
            return dataList.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView orderID,vehicleType,pickupLocation,dropLocation,date;
        Button acceptButton;

        public MyViewHolder(View itemView){
            super(itemView);

            this.orderID=itemView.findViewById(R.id.orderID);
            this.vehicleType=itemView.findViewById(R.id.vehicleType);
            this.pickupLocation=itemView.findViewById(R.id.pickupLocation);
            this.dropLocation=itemView.findViewById(R.id.dropLocation);
            this.date=itemView.findViewById(R.id.date);
            this.acceptButton=itemView.findViewById(R.id.acceptButton);


        }

    }

}


