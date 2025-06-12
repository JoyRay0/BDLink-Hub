<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

//1

notification($token);

function notification($token){

    $title = "";
    $body = "";

    $url = "https://fcm.googleapis.com/fcm/send";
    $server_key = "";

    $data = [

        'title' => $title,
        'body' => $body

    ];

    $json = json_encode([

        'to' => $token,
        'priority' => 'high',
        'data' => $data

    ]);

    $header = [

        'Content-Type: application/json',
        'Authorization: key='.$server_key

    ];

    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_CUSTOMREQUEST, "POST");
    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);

    $response = curl_exec($ch);

    if($response){

        echo json_encode([

            'status' => 'successful'

        ]);

    }else{

        die(json_encode([

            'status' => 'Failed' . curl_errno($ch)

        ]));

    }

    curl_close($ch);


}

?>