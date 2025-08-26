package com.rk_softwares.bdlinkhub.Model;


import java.util.List;

public class m_All_data {

    private String status;
    private String message;
    private List<c_all_data> data;

    public String getStatus() {
        return status;
    }

    public List<c_all_data> getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }
}
