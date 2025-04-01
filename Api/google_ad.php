<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

$EnableAd = 1;    // 1 = true
$DisableAd = 0;   // 0 = false

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