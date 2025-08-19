<?php

require 'db.php';

header("Cache-Control: public, max-age=3600");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PUT, DELETE, OPTIONS");

$method = $_SERVER['REQUEST_METHOD'];
$query = $_GET['query'] ?? '';

$tables = [

    'ai_links',
    'jobs',
    'news',
    'tv',
    'education',
    'shoping',
    'food',
    'ride',

];

$sanitize_query = trim(strip_tags(filter_var($query, FILTER_SANITIZE_FULL_SPECIAL_CHARS)));


    if($method != 'GET'){
        
        die(json_encode([

            'status' => 'Failed',
            'message' => 'Invalid method'

        ]));

    }else{
        $data = [];

        foreach($tables as $table){

            $sql = "SELECT * FROM $table WHERE MATCH(title) AGAINST(? IN NATURAL LANGUAGE MODE) LIMIT 10";
            $result = $database_connect->prepare($sql);
            $result->bind_param("s",$sanitize_query);
            $result->execute();
            $search_data = $result->get_result();

            while($item = $search_data->fetch_assoc()){

                $data[] = $item;

            }


            

        }


        if(empty($data)){

            echo json_encode([

                'status' => 'Failed',
                'message' => 'keyword not found'

            ]);

        }else{

            echo json_encode([

            'status' => 'successful',
            'message' => 'keyword found',
            'data' => $data

            ], JSON_UNESCAPED_UNICODE);

        }

        $database_connect->close();

        
        

    }

?>