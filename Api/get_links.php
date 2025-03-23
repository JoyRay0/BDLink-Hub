<?php

header("Content-Type: application/json");
require 'db.php';

$method = $_SERVER['REQUEST_METHOD'];
$res = $_GET['res'] ?? '';

if($method != 'GET'){

    http_response_code(405);
    echo json_encode([

        'status' => 'Wrong Request',
        'message' =>'Only GET requests are allowed'

    ]);
    exit();
}else{

    switch($res){

        case ('info'):
            $sql = "SELECT *FROM user_table";
            break;

        case ('data'):
            $sql = "SELECT *FROM user_table2";
            break;

        default:

            http_response_code(400);
            echo json_encode([

                'status' => 'Invalid res',
                'message' => 'Check your res'
                

            ]);
            exit();

        
    }

    $result = $database_connect->query($sql);

    try {
         
        $data = [];

        while ($item = $result->fetch_assoc()) {

            $data[] = $item;
            # code...
        }

        http_response_code(200);
        echo json_encode([

            'status' => 'success',
            'data' => $data

        ]);


    } catch (Exception $e) {

        http_response_code(500);
        echo json_encode([

            'status' => 'database not connected',
            'message' => 'wrong database connection'

        ]);

    }
    $database_connect->close();

}

?>