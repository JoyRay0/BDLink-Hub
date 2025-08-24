<?php

namespace App\Service;

use Illuminate\Mail\Mailable;
use Illuminate\Support\Facades\Mail;

class EmailVerification extends Mailable
{

    public $code;

    public function __construct($code){

        $this->code = $code;

    }
    public function email(){

        return $this->subject('Email Verification Code')->view('emails.verification',['code'=>$this->code]);
    }

}
