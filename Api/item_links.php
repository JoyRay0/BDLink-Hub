<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

links();

function links(){

    $method = $_SERVER['REQUEST_METHOD'];
    $res = $_GET['res'];

    $item = [];

    if($method != 'GET'){

        echo json_encode([

            'status' => 'Failed',
            'message' => 'Invalid method'

        ]);
        exit();

    }

    switch($res){

        case('popular_item'):

            $item = [

                [
                'id' => '1',
                'item_name' => 'AI',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png'
                ],
                [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png'
                ],
                [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png'
                ],
                [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png'
                ],
                [
                'id' => '5',
                'item_name' => 'ইসলামিক সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/muslim.png'
                ],
                [
                'id' => '6',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/e_commerce.png'
                ],
                [
                'id' => '7',
                'item_name' => 'টিকিট বুকিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/bus.png'
                ],
                [
                'id' => '8',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png'
                ]


            ];

            break;

        case ('all_item'):


            $item = [

                [
                'id' => '1',
                'item_name' => 'AI',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png'
                ],
                [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png'
                ],
                [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png'
                ],
                [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png'
                ],
                [
                'id' => '5',
                'item_name' => 'ইসলামিক সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/muslim.png'
                ],
                [
                'id' => '6',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/e_commerce.png'
                ],
                [
                'id' => '7',
                'item_name' => 'টিকিট বুকিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/bus.png'
                ],
                [
                'id' => '8',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png'
                ]


            ];

            break;
        
        default:
            
            die(json_encode([

                'status' => 'Failed',
                'message' => 'Invalid res'

            ]));

    }

    echo json_encode([

        'status' => 'Successful',
        'item' => $item

    ]);

}


?>