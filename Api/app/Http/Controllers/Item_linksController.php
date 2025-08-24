<?php

namespace App\Http\Controllers;

use Illuminate\Http\JsonResponse;

class Item_linksController extends Controller
{
    private string $ai = 'ai';
    private string $news = 'news';
    private string $jobs = 'jobs';
    private string $tv = 'tv';
    private string $education = 'education';
    private string $shopping = 'shopping';
    private string $food = 'food';
    private string $ride = 'ride';
    private string $hindu = '';
    private string $muslim = '';
    private string $tickets = '';
    private string $sales_purchase = '';
    private string $tour = '';
    private string $medical = '';
    private string $law = '';
    private string $gov = '';
    private string $result = '';
    private string $delivery = '';
    private string $tax = '';
    private string $ad = '';
    private string $migrant = '';
    private string $freelancing = '';
    private string $bank = '';
    private string $matrimony = '';
    private string $internet = '';
    private string $port = '';
    private string $weather = '';
    private string $stock = '';
    private string $visa_center = '';
    private string $tools = '';
    private string $rent = '';
    private string $form = '';
    private string $agriculture = '';
    private string $bd_visa_center = '';

    //popular items-------------------------------------------------------
    public function popular_item(): ?JsonResponse
    {

        $popular_item = [
            [
                'id' => '1',
                'item_name' => 'AI',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png',
                'endLink' => $this->ai
            ],
            [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png',
                'endLink' => $this->news
            ],
            [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png',
                'endLink' => $this->jobs
            ],
            [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png',
                'endLink' => $this->tv
            ],
            [
                'id' => '5',
                'item_name' => 'শিক্ষা ও কোর্স',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/education.png',
                'endLink' => $this->education
            ],
            [
                'id' => '6',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/E_commerce.png',
                'endLink' => $this->shopping
            ],
            [
                'id' => '7',
                'item_name' => 'খাওয়া দাওয়া',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/food.png',
                'endLink' => $this->food
            ],
            [
                'id' => '8',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png',
                'endLink' => $this->ride
            ]
        ];

        return response()->json([
            'status' => 'successful',
            'item' => $popular_item
        ]);

    }

    //all item --------------------------------------------------
    public function all_item(): JsonResponse
    {

        $all_item = [

            [
                'id' => '1',
                'item_name' => 'AI',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ai.png',
                'endLink' => $this->ai
            ],
            [
                'id' => '2',
                'item_name' => 'পএিকা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/news.png',
                'endLink' => $this->news
            ],
            [
                'id' => '3',
                'item_name' => 'চাকরি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/job.png',
                'endLink' => $this->jobs
            ],
            [
                'id' => '4',
                'item_name' => 'বিনোদন ও টিভি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tv.png',
                'endLink' => $this->tv
            ],
            [
                'id' => '5',
                'item_name' => 'হিন্দুধর্মীয় সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/hindu.png',
                'endLink' => $this->hindu
            ],
            [
                'id' => '6',
                'item_name' => 'ইসলামিক সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/muslim.png',
                'endLink' => $this->muslim
            ],
            [
                'id' => '7',
                'item_name' => 'শপিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/E_commerce.png',
                'endLink' => $this->shopping
            ],
            [
                'id' => '8',
                'item_name' => 'টিকিট বুকিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tickets.png',
                'endLink' => $this->tickets
            ],
            [
                'id' => '9',
                'item_name' => 'রাইড শেয়ারিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ride.png',
                'endLink' => $this->ride
            ],
            [
                'id' => '10',
                'item_name' => 'শিহ্মা ও কোর্স',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/education.png',
                'endLink' => $this->education
            ],
            [
                'id' => '11',
                'item_name' => 'ক্রয় ও বিক্রয়',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/sales&purchase.png',
                'endLink' => $this->sales_purchase
            ],
            [
                'id' => '12',
                'item_name' => 'খাওয়া দাওয়া',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/food.png',
                'endLink' => $this->food
            ],
            [
                'id' => '13',
                'item_name' => 'বাংলার পর্যটন',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tour.png',
                'endLink' => $this->tour
            ],
            [
                'id' => '14',
                'item_name' => 'চিকিৎসা ও পরামর্শ',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/medical.png',
                'endLink' => $this->medical
            ],
            [
                'id' => '15',
                'item_name' => 'আইনি সহায়তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/law.png',
                'endLink' => $this->law
            ],
            [
                'id' => '16',
                'item_name' => 'অনলাইন সরকারি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/gov.png',
                'endLink' => $this->gov
            ],
            [
                'id' => '17',
                'item_name' => 'ভর্তি ও রেজাল্ট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/result.png',
                'endLink' => $this->result
            ],
            [
                'id' => '18',
                'item_name' => 'কুরিয়ার/ডেলিভারি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/dilevery.png',
                'endLink' => $this->delivery
            ],
            [
                'id' => '19',
                'item_name' => 'ট্যাক্স ও ভ্যাট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tax.png',
                'endLink' => $this->tax
            ],
            [
                'id' => '20',
                'item_name' => 'বিজ্ঞাপন ও প্রচার',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/ad.png',
                'endLink' => $this->ad
            ],
            [
                'id' => '21',
                'item_name' => 'প্রবাসী সেবা ও সহায়তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/migrant.png',
                'endLink' => $this->migrant
            ],
            [
                'id' => '22',
                'item_name' => 'ফ্রিল্যান্সিং',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/freelancing.png',
                'endLink' => $this->freelancing
            ],
            [
                'id' => '23',
                'item_name' => 'ব্যাংকিং সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/bank.png',
                'endLink' => $this->bank
            ],
            [
                'id' => '24',
                'item_name' => 'ম্যাট্রিমনি',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/matrimony.png',
                'endLink' => $this->matrimony
            ],
            [
                'id' => '25',
                'item_name' => 'ইন্টারনেট সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/internet.png',
                'endLink' => $this->internet
            ],
            [
                'id' => '26',
                'item_name' => 'আমদানি-রপ্তানি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/port.png',
                'endLink' => $this->port
            ],
            [
                'id' => '27',
                'item_name' => 'আবহাওয়া বার্তা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/weather.png',
                'endLink' => $this->weather
            ],
            [
                'id' => '28',
                'item_name' => 'স্টক মার্কেট',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/stock.png',
                'endLink' => $this->stock
            ],
            [
                'id' => '29',
                'item_name' => 'ভিসা সেন্টার ও দূতাবাস',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/visa_center.png',
                'endLink' => $this->visa_center
            ],
            [
                'id' => '30',
                'item_name' => 'অনলাইন টুলস',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/tools.png',
                'endLink' => $this->tools
            ],
            [
                'id' => '31',
                'item_name' => 'বাড়িভাড়া ও সম্পত্তি সেবা',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/rent.png',
                'endLink' => $this->rent
            ],
            [
                'id' => '32',
                'item_name' => 'ফর্ম ও সনদ',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/form.png',
                'endLink' => $this->form
            ],
            [
                'id' => '33',
                'item_name' => 'কৃষিসেবা কেন্দ্র',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/agriculture.png',
                'endLink' => $this->agriculture
            ],
            [
                'id' => '34',
                'item_name' => 'বাংলাদেশি দূতাবাস',
                'item_pic' => 'https://cdn.rksoftwares.xyz/images/bd_visa_center.png',
                'endLink' => $this->bd_visa_center
            ],
        ];

        return response()->json([
            'status' => 'successful',
            'item' => $all_item
        ]);

    }
}
