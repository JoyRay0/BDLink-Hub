<?php

namespace App\Http\Middleware;

use Closure;
class MethodMiddleware
{
    public function handle($request, Closure $next){

        if ($request->isMethod('POST') || $request->isMethod('PUT') || $request->isMethod('PATCH') || $request->isMethod('DELETE')) {

            return response()->json([
                'status' => 'Failed',
                'message' => 'Method Not Allowed'
            ]);

        }

        return $next($request);
    }

}
