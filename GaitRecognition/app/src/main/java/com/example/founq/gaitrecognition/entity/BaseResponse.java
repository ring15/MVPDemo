package com.example.founq.gaitrecognition.entity;

public class BaseResponse {

    private String data;
    private String status;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setData(String data) {
        this.data = data;
    }


    public String getStatus() {
        return status;
    }

    public String getData() {
        return data;
    }
}
