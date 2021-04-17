package com.example.android.driver.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.driver.R;
import com.example.android.driver.model.OrderDetails;

import java.util.List;

public class AcceptedOrdersAdapter extends RecyclerView.Adapter<AcceptedOrdersAdapter.MyViewHolder> {


    List<OrderDetails> dataList1;


    Context context;


    public AcceptedOrdersAdapter( Context context, List<OrderDetails> dataList1) {
        this.context = context;
        this.dataList1 = dataList1;
    }
    public void setAcceptedDataList(List<OrderDetails> dataList) {
        this.dataList1 = dataList;
        notifyDataSetChanged();
    }


    @Override
    public AcceptedOrdersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_accepted_orders,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.orderID.setText(dataList1.get(position).getOrder_id().toString());
        holder.vehicleType.setText(dataList1.get(position).getVehicle().toString());
        holder.pickupLocation.setText(dataList1.get(position).getPick_location().toString());
        holder.dropLocation.setText(dataList1.get(position).getDrop_location().toString());
        holder.date.setText(dataList1.get(position).getPick_date().toString());
        holder.fullname.setText(dataList1.get(position).getFull_name().toString());
        holder.contact.setText(dataList1.get(position).getContact_number().toString());
        holder.parcel.setText(dataList1.get(position).getParcel().toString());

    }



    @Override
    public int getItemCount() {
        if(dataList1 != null){
            return dataList1.size();
        }
        return 0;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView orderID,vehicleType,pickupLocation,dropLocation,date,fullname,contact,parcel;

        public MyViewHolder(View itemView){
            super(itemView);

            this.orderID=itemView.findViewById(R.id.orderID);
            this.vehicleType=itemView.findViewById(R.id.vehicleType);
            this.pickupLocation=itemView.findViewById(R.id.pickupLocation);
            this.dropLocation=itemView.findViewById(R.id.dropLocation);
            this.date=itemView.findViewById(R.id.date);
            this.fullname=itemView.findViewById(R.id.full_name);
            this.contact=itemView.findViewById(R.id.contact);
            this.parcel=itemView.findViewById(R.id.parcel);

        }

    }

}


