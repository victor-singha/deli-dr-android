package com.example.android.driver.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("status")
    private String status;

    @SerializedName("result_code")
    private int result_code;

    @SerializedName("driver_id")
    private String driver_id;

    @SerializedName("email_id")
    private String email_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(String driver_id) {
        this.driver_id = driver_id;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public LoginResponse(String status, int result_code, String driver_id, String email_id) {
        this.status = status;
        this.result_code = result_code;
        this.driver_id = driver_id;
        this.email_id = email_id;
    }
}
