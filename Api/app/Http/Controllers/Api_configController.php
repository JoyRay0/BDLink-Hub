<?php

namespace App\Http\Controllers;

use Illuminate\Http\JsonResponse;

class Api_configController extends Controller
{

    public function api_links(): ?JsonResponse
    {
        $apis = [
            'viewpager' =>'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/viewpager',
            'google_ad' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/google_ad',
            'item_links' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/all_item',
            'P_item_links' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/popular_item',

            'user_reg_login_reg' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/register',
            'user_reg_code' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/register_code',

            'user_reg_login_reset_password' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/update_password',
            'user_update_password_code' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/update_password_code',

            'user_reg_login_login' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/login',
            'user_reg_login_gAuth' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/Oauth_login_reg',

            //'get_links_user_info' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/get_links?res=info',
            'get_links' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/get_links?endpoint=',
            'search' =>'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/api/search?query=',
        ];

        return response()->json([
            'status' => 'successful',
            'data' => $apis
        ]);
    }

}
