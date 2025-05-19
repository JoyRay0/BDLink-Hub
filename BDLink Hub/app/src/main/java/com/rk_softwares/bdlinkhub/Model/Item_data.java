package com.rk_softwares.bdlinkhub.Model;

import java.util.List;

public class Item_data {

    private String image_url;
    private String status;
    private List<Item> data;


    public String getImage_url() {
        return image_url;
    }

    public List<Item> getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
