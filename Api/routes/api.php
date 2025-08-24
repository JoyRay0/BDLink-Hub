<?php


use App\Http\Controllers\Api_configController;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\Get_all_linkController;
use App\Http\Controllers\Item_linksController;
use App\Http\Controllers\Other_apiController;
use App\Http\Controllers\SearchController;
use Illuminate\Support\Facades\Route;


//for GET Request------------------------------------------

Route::middleware('throttle:20, 1')->group(function () {

    Route::middleware('No_post_put_delete')->group(function () {

        Route::get('/api_config', [Api_configController::class, 'api_links']);

        Route::get('/google_ad', [Other_apiController::class, 'google_ad']);

        Route::get('/viewpager', [Other_apiController::class, 'viewpager']);

        Route::get('/popular_item', [Item_linksController::class, 'popular_item']);

        Route::get('/all_item', [Item_linksController::class, 'all_item']);

        Route::get('/search', [SearchController::class, 'search']);

        Route::get('/search_index', [SearchController::class, 'search_index']);

        Route::get('/get_links', [Get_all_linkController::class, 'get_all_links']);

    });

});


//for Post , put, delete Request------------------------------------------

Route::middleware('throttle:3, 1')->group(function () {

    Route::middleware('No_get')->group(function () {

        Route::post('/registration', [AuthController::class, 'user_first_reg']);

        Route::post('/reg_code', [AuthController::class, 'second_step_reg']);

        Route::post('/user_login', [AuthController::class, 'user_Login']);

        Route::post('/Oauth_login_reg', [AuthController::class, 'OAuth_login_or_register']);

        Route::post('/update_password', [AuthController::class, 'user_update_password_first']);

        Route::post('/update_password_code', [AuthController::class, 'second_step_password_update']);


    });

});
