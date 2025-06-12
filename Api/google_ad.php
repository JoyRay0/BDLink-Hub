<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

$EnableAd = 1;    // 1 = true
$DisableAd = 0;   // 0 = false

$method = $_SERVER['REQUEST_METHOD'];

if($method != 'GET'){

    die(json_encode([

        'status' => 'Failed',
        'message' => 'Invalid method'

    ]));

}

if($EnableAd > $DisableAd){

    echo json_encode([

        'status' => 'Enable'

    ]);

}else{

    echo json_encode([

        'status' => 'Disable'

    ]);

}

?>