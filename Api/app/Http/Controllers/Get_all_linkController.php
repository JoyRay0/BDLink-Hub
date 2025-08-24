<?php

namespace App\Http\Controllers;

use App\Service\Validaton;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\DB;

class Get_all_linkController extends Controller
{
    public function get_all_links(Request $request): ?JsonResponse
    {
        $endpoint = $request->query('endpoint');

        $validation_endpoint = Validaton::filter_validator($request->validate(Validaton::get_links($endpoint)));

        $allowed_tables = [
            'ai_links',
            'jobs',
            'news',
            'tv',
            'education',
            'shopping',
            'food',
            'ride',
        ];

        $table_name = $validation_endpoint['endpoint'];

        if (!in_array($table_name, $allowed_tables, true)){
            return response()->json([
                'status' => 'failed',
                'message' => 'Invalid name'
            ]);

        }

        $cache = Cache::get('temp_links_'.$table_name,0);

        if ($cache && !$cache->isEmpty()) {

           return response()->json([
               'status' => 'successful',
               'data' => $cache
           ]);

        }

        try {

            $result = DB::table($table_name)->get();

            if ($result->isEmpty()){

                return response()->json([
                    'status' => 'failed',
                    'message' => 'No data found'

                ]);
            }

            Cache::put('temp_links_'.$table_name, $result, now()->addMinutes(10));

            return response()->json([
                'status' => 'successful',
                'data' => $result
            ]);

        }catch (\Exception $exception){

            return response()->json([
                'status' => 'failed',
                'message' => 'Database connection failed'
            ]);
        }


    }
}
