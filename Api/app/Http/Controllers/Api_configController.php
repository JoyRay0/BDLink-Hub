<?php

namespace App\Http\Controllers;

use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Cache;

class Api_configController extends Controller
{

    public function api_links(): ?JsonResponse
    {

        $cache = Cache::get('api_links', 0);

        if (!empty($cache)) {

            return response()->json([
                'status' => 'successful',
                'source' => 'cache',
                'links' => $cache
            ]);

        }

        $apis = [
            'viewpager' =>'https://bdlinkhub.rksoftwares.xyz/api/viewpager',
            'google_ad' => 'https://bdlinkhub.rksoftwares.xyz/api/google_ad',
            'item_links' => 'https://bdlinkhub.rksoftwares.xyz/api/all_item',
            'P_item_links' => 'https://bdlinkhub.rksoftwares.xyz/api/popular_item',

            'user_reg_login_reg' => 'https://bdlinkhub.rksoftwares.xyz/api/register',
            'user_reg_code' => 'https://bdlinkhub.rksoftwares.xyz/api/register_code',

            'user_reg_login_reset_password' => 'https://bdlinkhub.rksoftwares.xyz/api/update_password',
            'user_update_password_code' => 'https://bdlinkhub.rksoftwares.xyz/api/update_password_code',

            'user_reg_login_login' => 'https://bdlinkhub.rksoftwares.xyz/api/login',
            'user_reg_login_gAuth' => 'https://bdlinkhub.rksoftwares.xyz/api/Oauth_login_reg',

            //'get_links_user_info' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/get_links?res=info',
            'get_links' => 'https://bdlinkhub.rksoftwares.xyz/api/get_links?endpoint=',
            'search' =>'https://bdlinkhub.rksoftwares.xyz/api/search?query=',
            'search_index' => 'https://bdlinkhub.rksoftwares.xyz/api/search_index',
        ];

        Cache::put('api_links', $apis, now()->addMinutes(10));

        return response()->json([
            'status' => 'successful',
            'links' => $apis
        ]);
    }

}
