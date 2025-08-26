package com.rk_softwares.bdlinkhub.Model;

import java.util.List;

public class m_popular_item_link {

    private String status;
    private List<c_popular_item_link> item;

    public List<c_popular_item_link> getItem() {
        return item;
    }

    public String getStatus() {
        return status;
    }
}
