<?php

header('Content-Type: application/json; charset=utf-8');

function check_deviceIds(){

    session_start();

    if(!isset($_SESSION['valid_id'])){

        $_SESSION['valid_id'] =[];

    }

    $client_id = $_SERVER['HTTP_X_UUID'] ?? '';

    if(!$client_id){

        die(json_encode([

            'status' => 'Error',
            'message' => 'Invalid device ID'

        ]));

    }

    if(in_array($client_id, $_SESSION['valid_id'])){
    
        die(json_encode([

            'status' => 'Error',
            'message' => 'Duplicate client id'

        ]));
    
    }

    $_SESSION['valid_id'][] = $client_id;

    if(count($_SESSION['valid_id']) > 100){

        array_shift($_SESSION['valid_id']);

    }

    echo json_encode([

        'status' => 'Success',
        'message' => 'Access granted'

    ]);



}

?>