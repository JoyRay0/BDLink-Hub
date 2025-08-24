<?php

namespace App\Http\Controllers;

use App\Service\EmailVerification;
use App\Service\Validaton;
use App\Service\Verified_code;
use Carbon\Carbon;
use Illuminate\Container\Attributes\DB;
use Illuminate\Http\JsonResponse;
use Illuminate\Http\Request;
use App\Models\User_info;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Mail;
use \Illuminate\Support\Str;
use \Illuminate\Support\Facades\Session;
use function Laravel\Prompts\search;

class AuthController extends Controller
{

    //Registration-------------------------------------------------------------
    public function user_first_reg(Request $request): ?JsonResponse
    {

        $validated_information = Validaton::filter_validator($request->validate(Validaton::user_Registration($request->all())));

        $verify = Verified_code::send_mail($validated_information['email']);

       if ($verify['status']) {

           $temp_token = Str::uuid();

          Cache::put('temp_user_'.$temp_token, [
              'name' => $validated_information['name'],
              'email' => $validated_information['email'],
              'password' => $validated_information['password'],
              'date_of_birth' => $validated_information['date_of_birth'],
              'code' => $verify['code'],

          ], now()->addMinutes(5));

           return response()->json([
               'status' => 'successful',
               'message' => 'আপনার ইমেল ঠিকানায় একটি ভেরিফিকেশন কোড পাঠানো হয়েছে।',
               'token' => $temp_token,
           ]);
       }
        return response()->json([
            'status' => 'failed',
            'message' => 'আপনার ইমেল ঠিকানায় ভেরিফিকেশন কোড পাঠানো যায়নি।'
        ]);

    }

    public function second_step_reg(Request $request): ?JsonResponse
    {

        $verify_code =  Validaton::verify_code($request->validate(Validaton::filter_validator($request->input('code'))));

        $temp_token = $request['token'];

        if (!Str::isUuid($temp_token)) {

            return response()->json([
                'status' => 'failed',
                'message' => 'Invalid Token'
            ]);

        }

        $code = Cache::get('temp_user_'.$temp_token);

        if (!$code){

            return response()->json([
                'status' => 'failed',
                'message' => 'আবার চেষ্টা করুন।'
            ]);
        }

        if ($verify_code['code'] === $code['code']){

                try {
                    $result = User_info::create([
                        'name' => $code['name'],
                        'email' => $code['email'],
                        'password' => Hash::make($code['password']),
                        'date_of_birth' => Carbon::parse($code['date_of_birth'])->format('Y-m-d'),

                    ]);

                    Cache::forget('temp_user_'.$temp_token);

                    if ($result) {

                        return response()->json([
                            'status' => 'successful',
                            'message' => 'নিবন্ধন সফল হয়েছে',
                            'name' => $code['name'],
                        ]);

                    }

                    return response()->json([
                        'status' => 'failed',
                        'message' => 'নিবন্ধন ব্যর্থ হয়েছে',
                    ]);

                }catch (\Exception $exception){

                    return response()->json([
                        'status' => 'failed',
                        'message' => 'connection error',
                    ]);

                }
        }

        return response()->json([
            'status' => 'failed',
            'message' => 'ভেরিফিকেশন ব্যর্থ হয়েছে',
        ]);

    }

    //Login--------------------------------------------------------
    public function user_Login(Request $request): ?JsonResponse
    {

        $validation_information = Validaton::filter_validator($request->validate(Validaton::user_Login($request->all())));

        try {

            $result = User_info::where('email', $validation_information['email'])->first();

            if ($result && Hash::check($validation_information['password'], $result->password)) {

                return response()->json([
                    'status' => 'successful',
                    'message' => 'লগইন সফল হয়েছে।',
                    'name' => $result->name,
                ]);

            }

            return response()->json([
                'status' => 'failed',
                'message' => 'লগইন ব্যর্থ হয়েছে।'
            ]);

        }catch (\Exception $exception){

            return response()->json([
                'status' => 'failed',
                'message' => 'connection error',
            ]);
        }

    }

    //update password-----------------------------------------------------
    public function user_update_password_first(Request $request): ?JsonResponse
    {

        $validation_information = Validaton::filter_validator($request->validate(Validaton::user_update_password($request->all())));

        $verify = Verified_code::send_mail($validation_information['email']);

        $temp_token = Str::uuid();

        if ($verify['status']) {
            Cache::put('temp_user_'.$temp_token, [

                'email' => $validation_information['email'],
                'new_password' => $validation_information['new_password'],
                'code' => $verify['code'],

            ], now()->addMinutes(5));

            return response()->json([
                'status' => 'successful',
                'message' => 'আপনার ইমেল ঠিকানায় একটি ভেরিফিকেশন কোড পাঠানো হয়েছে।',
                'token' => $temp_token,
            ]);
        }
        return response()->json([
            'status' => 'failed',
            'message' => 'আপনার ইমেল ঠিকানায় ভেরিফিকেশন কোড পাঠানো যায়নি।'
        ]);

    }

    public function second_step_password_update(Request $request): ?JsonResponse
    {

        $verify_code = Validaton::filter_validator($request->validate(Validaton::verify_code($request->input('code'))));

        $temp_token = $request['token'];

        if (!Str::isUuid($temp_token)) {

            return response()->json([
                'status' => 'failed',
                'message' => 'Invalid Token'
            ]);

        }

        $code = Cache::get('temp_user_'.$temp_token);

        if (!$code){

            return response()->json([
                'status' => 'failed',
                'message' => 'আবার চেষ্টা করুন।'
            ]);
        }

        if ($verify_code['code'] === $code['code']){

            try {

                $new_password = Hash::make($code['new_password']);

                $result = User_info::where('email', $code['email'])
                    ->update(['password' => $new_password]);

                Cache::forget('temp_user_'.$temp_token);

                if ($result) {

                    return response()->json([
                        'status' => 'successful',
                        'message' => 'পাসওয়ার্ড পরিবর্তন হয়েছে।'
                    ]);

                }

                return response()->json([
                    'status' => 'failed',
                    'message' => 'পাসওয়ার্ড পরিবর্তন ব্যর্থ হয়েছে।'
                ]);


            }catch (\Exception $exception){
                return response()->json([
                    'status' => 'failed',
                    'message' => 'connection error',
                ]);
            }

        }

        return response()->json([
            'status' => 'failed',
            'message' => 'ভেরিফিকেশন ব্যর্থ হয়েছে',
        ]);

    }

    //user Oauth login or registration------------------------------------------------
    public function OAuth_login_or_register(Request $request): ?JsonResponse
    {

        $validate_information = Validaton::filter_validator($request->validate(Validaton::user_oAuth($request->all())));

        try {

            $result = User_info::where('email', $validate_information['email'])->first();

            if ($result){

                return response()->json([
                    'status' => 'successful',
                    'message' => 'লগইন সফল হয়েছে।',
                    'name' => $validate_information['name'],
                ]);

            }else{

                $result_reg = User_info::create([
                    'name' => $validate_information['name'],
                    'email' => $validate_information['email'],
                ]);

                if ($result_reg){

                    return response()->json([
                        'status' => 'successful',
                        'message' => 'নিবন্ধন সফল হয়েছে',
                        'name' => $validate_information['name'],
                    ]);
                }

            }

            return response()->json([
                'status' => 'failed',
                'message' => 'আবার চেষ্টা করুন।'
            ]);

        }catch (\Exception $exception){

            return response()->json([
                'status' => 'failed',
                'message' => 'connection error',
            ]);
        }

    }

    //for delete user-------------------------------------------------

}//class ended====================================
