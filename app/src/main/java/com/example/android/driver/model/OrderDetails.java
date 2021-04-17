package com.example.android.driver.model;

import com.google.gson.annotations.SerializedName;

public class OrderDetails {

    @SerializedName("order_id")
    private String order_id;

    @SerializedName("pick_location")
    private String pick_location;

    @SerializedName("drop_location")
    private String drop_location;

    @SerializedName("pick_date")
    private String pick_date;

    @SerializedName("vehicle")
    private String vehicle;

    @SerializedName("amount")
    private String amount;

    @SerializedName("full_name")
    private String full_name;

    @SerializedName("email_id")
    private String email_id;

    @SerializedName("contact_number")
    private String contact_number;

    @SerializedName("parcel")
    private String parcel;

    @SerializedName("driver_id")
    private String driver_id;

    @SerializedName("is_accepted")
    private String is_accepted;

    @SerializedName("is_completed")
    private String is_completed;

    public OrderDetails(String order_id, String pick_location, String drop_location, String pick_date, String vehicle, String amount, String full_name, String email_id, String contact_number, String parcel, String driver_id, String is_accepted, String is_completed) {
        this.order_id = order_id;
        this.pick_location = pick_location;
        this.drop_location = drop_location;
        this.pick_date = pick_date;
        this.vehicle = vehicle;
        this.amount = amount;
        this.full_name = full_name;
        this.email_id = email_id;
        this.contact_number = contact_number;
        this.parcel = parcel;
        this.driver_id = driver_id;
        this.is_accepted = is_accepted;
        this.is_completed = is_completed;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getPick_location() {
        return pick_location;
    }

    public void setPick_location(String pick_location) {
        this.pick_location = pick_location;
    }

    public String getDrop_location() {
        return drop_location;
    }

    public void setDrop_location(String drop_location) {
        this.drop_location = drop_location;
    }

    public String getPick_date() {
        return pick_date;
    }

    public void setPick_date(String pick_date) {
        this.pick_date = pick_date;
    }

    public String getVehicle() {
        return vehicle;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    public String getParcel() {
        return parcel;
    }

    public void setParcel(String parcel) {
        this.parcel = parcel;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getIs_accepted() {
        return is_accepted;
    }

    public void setIs_accepted(String is_accepted) {
        this.is_accepted = is_accepted;
    }

    public String getIs_completed() {
        return is_completed;
    }

    public void setIs_completed(String is_completed) {
        this.is_completed = is_completed;
    }
}

