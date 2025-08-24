<?php

namespace App\Http\Middleware;

use Closure;
class GetMiddleware
{

    public function handle($request, Closure $next)
    {
        if ($request->isMethod('GET')){

            return response()->json([
                'status' => 'failed',
                'message' => 'Method Not Allowed'
            ]);

        }

        return $next($request);
    }

}
