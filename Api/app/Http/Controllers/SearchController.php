<?php

namespace App\Http\Controllers;


use App\Models\Search;
use App\Service\Validaton;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\DB;

class SearchController extends Controller
{
    public function search(Request $request): ?JsonResponse
    {

        $search = $request->query('query');

        $search_data = Validaton::filter_search($request->validate(Validaton::search_words($search)));

        try {

            $find_data = Search::whereFullText(['title'], $search_data['query'])->get();

            if ($find_data->isEmpty()) {

                return response()->json([
                    'status' => 'failed',
                    'message' => 'No results found'
                ]);
            }

            return response()->json([
                'status' => 'successful',
                'data' => $find_data
            ]);

        }catch (\Exception $exception){

            return response()->json([
                'status' => 'failed',
                'message' => 'Database error'
            ]);

        }



    }

    //search index------------------------------------------------------
    public function search_index(): JsonResponse
    {

        $tables = [
            'ai_links',
            'jobs',
            'news',
            'tv',
            'education',
            'shopping',
            'food',
            'ride',
        ];

        $total_updated = 0;

        try {

            foreach ($tables as $table) {

                DB::table($table)->orderBy('id')
                    ->chunk(100, function ($records) use ($table, &$total_updated) {

                    foreach ($records as $record) {

                        DB::table('search_index')->updateOrInsert(
                            [
                                'table_name' => $table,
                                'record_id' => $record->id,
                            ],
                            [
                                'category' => $record->category ?? '',
                                'title' => $record->title ?? '',
                                'description' => $record->description ?? '',
                                'link' => $record->link ?? null,
                                'created_at' => now(),
                                'updated_at' => now(),
                            ]

                        );
                        $total_updated++;

                    }
                });

            }

            return response()->json([
                'status' => 'successful',
                'message' => 'Total worked done'.$total_updated
            ]);

        }catch (\Exception $exception){

            \Log::error('Search index update failed: '.$exception->getMessage());

            return response()->json([
                'status' => 'failed',
                'message' => 'Database error'
            ]);

        }

    }


}//class=========================
