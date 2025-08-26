<?php

namespace App\Http\Controllers;

use Illuminate\Http\JsonResponse;
use Illuminate\Support\Facades\Cache;

class Other_apiController extends Controller
{

    //google ad------------------------------------
    public function google_ad(): ?JsonResponse
    {
        $EnableAD = 0;
        $DisableAD = 1;


        if ($EnableAD > $DisableAD){

            return response()->json([
                'status' => 'AD_ENABLE'
            ]);

        }else{


            return response()->json([
                'status' => 'AD_DISABLE'
            ]);
        }

    }
    //google ad------------------------------------

    //viewpager----------------------------------------
    public function viewpager(): ?JsonResponse
    {

        $cache = Cache::get('viewpager', 0);

        if (!empty($cache)){

            return response()->json([
                'status' => 'successful',
                'source' => 'cache',
                'images' => $cache
            ]);
        }

        $viewpager = [
            [
                'id' => '1',
                'image' =>'https://img.freepik.com/free-photo/ui-ux-representations-with-laptop_23-2150201871.jpg?semt=ais_items_boosted&w=740'
            ],
            [
                'id' => '2',
                'image' =>'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTENX10pvQGsAR15nkw4q8nCKJJJJaI_f4UkA&s'
            ],
            [
                'id' => '3',
                'image' =>'https://media.istockphoto.com/id/1371339413/photo/co-working-team-meeting-concept-businessman-using-smart-phone-and-digital-tablet-and-laptop.jpg?s=612x612&w=0&k=20&c=ysEsVw3q2axYt3oVZAuQjtHRlN3lY-U_e0ikK5yKIXQ='
            ],
        ];

        Cache::put('viewpager', $viewpager, now()->addMinutes(10));

        return response()->json([
            'status' => 'successful',
            'images' => $viewpager
        ]);
    }
    //viewpager----------------------------------------

}//Other api==============
