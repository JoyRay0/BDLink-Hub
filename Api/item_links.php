<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Cache-Control: public, max-age=3600");

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
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png',
                'endLink' => 'ai_links'
                ],
                [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png',
                'endLink' => ''
                ],
                [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png',
                'endLink' => ''
                ],
                [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png',
                'endLink' => ''
                ],
                [
                'id' => '5',
                'item_name' => 'শিক্ষা ও কোর্স',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/education.png',
                'endLink' => ''
                ],
                [
                'id' => '6',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/E_commerce.png',
                'endLink' => ''
                ],
                [
                'id' => '7',
                'item_name' => 'খাওয়া দাওয়া',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/food.png',
                'endLink' => ''
                ],
                [
                'id' => '8',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png',
                'endLink' => ''
                ]


            ];

            break;

        case ('all_item'):


            $item = [

                [
                'id' => '1',
                'item_name' => 'AI',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png',
                'endLink' => 'ai'
                ],
                [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png',
                'endLink' => ''
                ],
                [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png',
                'endLink' => ''
                ],
                [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png',
                'endLink' => ''
                ],
                [
                'id' => '5',
                'item_name' => 'হিন্দুধর্মীয় সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/hindu.png',
                'endLink' => ''
                ],
                [
                'id' => '6',
                'item_name' => 'ইসলামিক সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/muslim.png',
                'endLink' => ''
                ],
                [
                'id' => '7',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/E_commerce.png',
                'endLink' => ''
                ],
                [
                'id' => '8',
                'item_name' => 'টিকিট বুকিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tickets.png',
                'endLink' => ''
                ],
                [
                'id' => '9',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png',
                'endLink' => ''
                ],
                [
                'id' => '10',
                'item_name' => 'শিহ্মা ও কোর্স',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/education.png',
                'endLink' => ''
                ],
                [
                'id' => '11',
                'item_name' => 'ক্রয় ও বিক্রয়',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/sales&purchase.png',
                'endLink' => ''
                ],
                [
                'id' => '12',
                'item_name' => 'খাওয়া দাওয়া',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/food.png',
                'endLink' => ''
                ],
                [
                'id' => '13',
                'item_name' => 'বাংলার পর্যটন',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tour.png',
                'endLink' => ''
                ],
                [
                'id' => '14',
                'item_name' => 'চিকিৎসা ও পরামর্শ',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/medical.png',
                'endLink' => ''
                ],
                [
                'id' => '15',
                'item_name' => 'আইনি সহায়তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/law.png',
                'endLink' => ''
                ],
                [
                'id' => '16',
                'item_name' => 'অনলাইন সরকারি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/gov.png',
                'endLink' => ''
                ],
                [
                'id' => '17',
                'item_name' => 'ভর্তি ও রেজাল্ট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/result.png',
                'endLink' => ''
                ],
                [
                'id' => '18',
                'item_name' => 'কুরিয়ার/ডেলিভারি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/dilevery.png',
                'endLink' => ''
                ],
                [
                'id' => '19',
                'item_name' => 'ট্যাক্স ও ভ্যাট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tax.png',
                'endLink' => ''
                ],
                [
                'id' => '20',
                'item_name' => 'বিজ্ঞাপন ও প্রচার',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ad.png',
                'endLink' => ''
                ],
                [
                'id' => '21',
                'item_name' => 'প্রবাসী সেবা ও সহায়তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/migrant.png',
                'endLink' => ''
                ],
                [
                'id' => '22',
                'item_name' => 'ফ্রিল্যান্সিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/freelancing.png',
                'endLink' => ''
                ],
                [
                'id' => '23',
                'item_name' => 'ব্যাংকিং সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/bank.png',
                'endLink' => ''
                ],
                [
                'id' => '24',
                'item_name' => 'ম্যাট্রিমনি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/matrimony.png',
                'endLink' => ''
                ],
                [
                'id' => '25',
                'item_name' => 'ইন্টারনেট সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/internet.png',
                'endLink' => ''
                ],
                [
                'id' => '26',
                'item_name' => 'আমদানি-রপ্তানি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/port.png',
                'endLink' => ''
                ],
                [
                'id' => '27',
                'item_name' => 'আবহাওয়া বার্তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/weather.png',
                'endLink' => ''
                ],
                [
                'id' => '28',
                'item_name' => 'স্টক মার্কেট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/stock.png',
                'endLink' => ''
                ],
                [
                'id' => '29',
                'item_name' => 'ভিসা সেন্টার ও দূতাবাস',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/visa_center.png',
                'endLink' => ''
                ],
                [
                'id' => '30',
                'item_name' => 'অনলাইন টুলস',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tools.png',
                'endLink' => ''
                ],
                [
                'id' => '31',
                'item_name' => 'বাড়িভাড়া ও সম্পত্তি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/rent.png',
                'endLink' => ''
                ],
                [
                'id' => '32',
                'item_name' => 'ফর্ম ও সনদ',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/form.png',
                'endLink' => ''
                ],
                [
                'id' => '33',
                'item_name' => 'কৃষিসেবা কেন্দ্র',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/agriculture.png',
                'endLink' => ''
                ],


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