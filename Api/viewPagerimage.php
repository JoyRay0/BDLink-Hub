<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("Cache-Control: public, max-age=3600");


$method = $_SERVER['REQUEST_METHOD'];
$res = $_GET['res'];

if($method != 'GET'){

    die(json_encode([

        'status' => 'Failed',
        'messahe' => 'Invalied method'

    ]));

}

switch($res){

    case 'vp_images':

        $images = [

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

        break;


    default :
      die(json_encode([

        'status' => 'Failed',
        'message' => 'Invalied res'

      ]));

}
echo json_encode([

    'status' => 'Successful',
    'item' => $images

]);


?>