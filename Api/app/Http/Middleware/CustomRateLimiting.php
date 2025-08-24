<?php

namespace App\Http\Middleware;

use Closure;
use Illuminate\Support\Facades\Cache;

class CustomRateLimiting
{

    public function handle($request, Closure $next, $max_request = 3, $delay_seconds = 60){

        $key = 'rate_limit_'.$request->ip();

        $req_started = Cache::get($key, 0);

        if ($req_started >= $max_request) {

            return response()->json([
                'status' => 'failed',
                'message' => 'Too many request, Please try again later'
            ]);

        }

        Cache::put($key, $req_started + 1, $dealy_seconds);

        return $next($request);
    }

}
