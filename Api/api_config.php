<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Cache-Control: public, max-age=3600");

$method = $_SERVER['REQUEST_METHOD'];

if($method != 'GET'){

    die(json_encode([

        'status' => 'Failed',
        'message' => 'invalid method'

    ]));

}

$apis = [

    'viewpager' =>'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/viewPagerimage?res=vp_images',
    'google_ad' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/google_ad',
    'item_li nks' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/item_links?res=all_item',
    'P_item_links' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/item_links?res=popular_item',

    'user_reg_login_reg' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_reg',
    'user_reg_login_reset_password' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=put_user_password',
    'user_reg_login_login' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_userLogin',
    'user_reg_login_gAuth' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=post_google_OAuth',
    'user_reg_login_delete_account' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/user_reg_login?res=delete_user',

    'get_links_user_info' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/get_links?res=info',
    'get_links' => 'https://rksoftwares.xyz/All_app/BDLink_Hub/Api/get_links?res=',

 
];

echo json_encode($apis);

?>