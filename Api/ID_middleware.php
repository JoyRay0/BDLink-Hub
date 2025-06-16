<?php

header('Content-Type: application/json; charset=utf-8');

function check_deviceIds($token){

    $Api_key = "AIzaSyDy-lLcqoC3c6vgOiAydNjlKugAftHqJJQ";

    $url = "https://playintegrity.googleapis.com/v1/com.google.android.play.integrity.v1.PlayIntegrityService/DecodeIntegrityToken";


    $post_data = json_encode([

        'integrity_token' => $token

    ]);

     $headers = [
        "Content-Type: application/json",
        "Authorization: Bearer $Api_key"
    ];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $post_data);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);

    $response = curl_exec($ch);

    if($response){

        echo json_encode([

            'status' => 'successful'

        ]);
    }else{

        curl_error($ch);

    }
    curl_close($ch);

}

?>