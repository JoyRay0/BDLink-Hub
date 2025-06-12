<?php

header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");
header("X-XSS-Protection: 1; mode=block");
header("X-Frame-Options: DENY");
header("Cache-Control: public, max-age=3600");

require 'db.php';
//require 'ID_middleware.php';

//check_deviceIds();    //middleware

getAllLinks();

function getAllLinks(){

    $method = $_SERVER['REQUEST_METHOD'];
    $res = $_GET['res'] ?? '';
    global $database_connect;

    if($method != 'GET'){

        http_response_code(405);
        echo json_encode([

            'status' => 'Wrong Request',
            'message' =>'Please try again later'

        ]);
        exit();
    }else{

        switch($res){

            case ('info'):
                $sql = "SELECT *FROM user_table";
                break;

            case ('ai'):
                $sql = "SELECT *FROM ai_links";
                break;

            case ('jobs'):
                $sql = "SELECT * FROM jobs";
                break;

            case ('news'):

                $sql = "SELECT * FROM news";

                break;

            case ('tv'):

                $sql = "SELECT * FROM tv";

                break;

            case ('education'):

                $sql = "SELECT * FROM education";

                break;

            case ('shoping'):

                $sql = "SELECT * FROM shoping";

                break;  
                
            case ('food'):

                $sql = "SELECT * FROM food";

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
               
            }

            http_response_code(200);
            echo json_encode([

                'status' => 'successful',
                'data' => $data

            ]);


        } catch (Exception $e) {

            http_response_code(500);
            echo json_encode([

                'status' => 'database not connected',
                'message' => 'wrong database connection'

            ]);
            exit();

        }

    }

}


?>