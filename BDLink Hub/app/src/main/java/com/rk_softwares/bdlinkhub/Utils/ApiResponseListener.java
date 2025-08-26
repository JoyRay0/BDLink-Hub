package com.rk_softwares.bdlinkhub.Utils;

import com.rk_softwares.bdlinkhub.Model.c_api_config;

public interface ApiResponseListener {

    void onApiResponse(c_api_config config);
    void onApiFailed(String error);

}
