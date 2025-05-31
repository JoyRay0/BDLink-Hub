package com.rk_softwares.bdlinkhub.Utils;

import com.rk_softwares.bdlinkhub.Model.Api_config;

public interface ApiResponseListener {

    void onApiResponse(Api_config config);
    void onApiFailed(String error);

}
