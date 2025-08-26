<?php

namespace App\Service;

class Validaton
{

    //For search----------------------------------
    public static function search_words(string $search): array
    {
        return [
            'query' => 'required|string|max:100',
        ];

    }

    //For All links-----------------------------------------
    public static function get_links(string $endpoint): array
    {
        return [
            'endpoint' => 'required|string|max:100',
        ];
    }

    //For user Registration----------------------------------------

    public static function user_Registration(array $data):array
    {
        return [
            'name' => 'required|string|max:100',
            'email' => 'required|string|email|unique:user_info',
            'password' => 'required|string|min:12',
            'date of birth' => 'required|date',

        ];
    }

    //For Login user--------------------------------------------------------
    public static function user_Login(array $data):array{

        return [
            'email' => 'required|string|email',
            'password' => 'required|string|min:12',
        ];
    }

    //For Update user password-------------------------------------------------
    public static function user_update_password(array $data):array
    {
        return [
            'email' => 'required|string|email',
            'new_password' => 'required|string|min:12',
        ];
    }

    //For OAuth ---------------------------------------------------
    public static function user_oAuth(array $data):array
    {
        return [
            'name' => 'required|string|max:100',
            'email' => 'required|string|email',
        ];
    }

    public static function verify_code(array $data):array
    {
        return [
            'code' => 'required|numeric|min:6',
        ];

    }


    // reg1 data sanitizer ---------------------------------------
    public static function filter_reg1(array $input):array
    {
        return [
            'name' => !empty($input['name']) ? htmlspecialchars(trim($input['name']), ENT_QUOTES, 'UTF-8') : null,
            'email' => !empty($input['email']) ? filter_var(trim($input['email']), FILTER_SANITIZE_EMAIL) : null,
            'password' => $input['password'],
            'date of birth' => !empty($input['date of birth']) ? filter_var(trim($input['date of birth']), FILTER_VALIDATE_INT) : null,
        ];
    }

    //reg2 data sanitizer-----------------------------------------------
    public static function filter_reg2(array $input):array
    {

        return [
            'code' => $input['code'],
        ];
    }

    //login data sanitizer-----------------------------------------------
    public static function filter_login(array $input):array
    {
        return [
            'email' => !empty($input['email']) ? filter_var(trim($input['email']), FILTER_SANITIZE_EMAIL) : null,
            'password' => $input['password'],
        ];
    }

    //update password1 data sanitizer-----------------------------------------------
    public static function filter_update_password1(array $input):array
    {

        return [
            'email' => !empty($input['email']) ? filter_var(trim($input['email']), FILTER_SANITIZE_EMAIL) : null,
            'new_password' => $input['new_password'],
        ];
    }

    //update password2 data sanitizer-----------------------------------------------
    public static function filter_update_password2(array $input):array
    {

        return [
            'code' => $input['code'],
        ];
    }

    //get links data sanitizer-----------------------------------------------
    public static function filter_get_links(array $input):array
    {
        return [
            'endpoint' => !empty($input['endpoint']) ? filter_var(trim($input['endpoint']), FILTER_SANITIZE_SPECIAL_CHARS) : null,
        ];

    }

    //search data sanitizer-----------------------------------------------
    public static function filter_search(array $input):array
    {
        return [
            'query' => !empty($input['query']) ? htmlspecialchars(filter_var(trim($input['query']), FILTER_SANITIZE_SPECIAL_CHARS), ENT_QUOTES, 'UTF-8') : null,
        ];

    }

    //oAuth data sanitizer-----------------------------------------------
    public static function filter_oAuth(array $input):array
    {
        return [
            'name' => !empty($input['name']) ? htmlspecialchars(trim($input['name']), ENT_QUOTES, 'UTF-8') : null,
            'email' => !empty($input['email']) ? filter_var(trim($input['email']), FILTER_SANITIZE_EMAIL) : null,        ];

    }


    //validation error message---------------------------------------------
    public function messages():array
    {
        return[
            'search.required' => 'সঠিক তথ্য দিন',
            'search.string' => 'গ্রহনযোগ্য নয়',
            'endpoint.required' => 'মান সঠিক নয়',
            'endpoint.string' => 'গ্রহনযোগ্য নয়',
            'name.required' => 'সঠিক নাম দিন',
            'name.string' => 'গ্রহনযোগ্য নয়',
            'email.required' => 'সঠিক ইমেইল দিন',
            'email.string' => 'গ্রহনযোগ্য নয়',
            'email.email' => 'গ্রহনযোগ্য নয়',
            'email.unique' => 'এই ইমেইল ঠিকানা ইতিমধ্যে ব্যবহার হচ্ছে। অনুগ্রহ করে অন্য ইমেইল ঠিকানা প্রদান করুন।',
            'password.required' => 'সঠিক পাসওয়ার্ড দিন',
            'password.min' => 'পাসওয়ার্ড ১২ অক্ষর বা তার বেশি হতে হবে।',
            'password.string' => 'গ্রহনযোগ্য নয়',
            'new_password.required' => 'সঠিক পাসওয়ার্ড দিন',
            'new_password.min' => 'পাসওয়ার্ড ১২ অক্ষর বা তার বেশি হতে হবে।',
            'new_password.string' => 'গ্রহনযোগ্য নয়',
            'date of birth.required' => 'সঠিক তারিখ দিন',
            'date of birth.date' => 'গ্রহনযোগ্য নয়',
            'code.required' => 'অনুগ্রহ করে ভেরিফিকেশন কোড প্রদান করুন।',
            'code.numeric' => 'সঠিক তারিখ দিন',
            'code.min' => '৬ সংখ্যার বেশি গ্রহনযোগ্য নয়'
        ];
    }


}//class ended================================
