package com.faruk.android.mvvm_sample.service.model;


public class Data {
    public int userId;
    public int id;
    public String title;
    public String body;

    public Data() {
    }

    public Data(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
