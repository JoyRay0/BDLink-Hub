<?php
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

//1

notification($token);

function notification($token){

    $title = "";
    $body = "";
    $project_id = "";
    $path = "Api/";

    $url = "https://fcm.googleapis.com/v1/projects/{$project_id}/messages:send";
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
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_POSTFIELDS, $json);
    
    

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