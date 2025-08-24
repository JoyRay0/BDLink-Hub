<?php

namespace App\Service;

use Illuminate\Support\Facades\Mail;
use Illuminate\Support\Str;
use Illuminate\Support\Facades\Session;
use Random\RandomException;
use App\Service\EmailVerification;

class Verified_code
{



    //sending email--------------------------------------------
    public static function send_mail($email):array
    {
        $verification_code = Str::random(6);

        Mail::to($email)->send(new EmailVerification($verification_code));

        return [
            'status' => true,
            'code' => $verification_code,
        ];
    }

    //checking email-------------------------------------------------------
    public static function check($input_code):bool
    {
        $saved_code = session('code');

        if ($saved_code === $input_code) {

            Session::forget('code');

            return true;
        }

        return false;

    }

}
