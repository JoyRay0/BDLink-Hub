<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;
use Illuminate\Notifications\Notifiable;

class User_info extends Model
{
    use Notifiable;

    protected $table = 'user_info';

    protected $fillable = [
        'name',
        'email',
        'password',
        'date_of_birth',
    ];

    protected $hidden = [
        'password',
        'date_of_birth',
        'email'
    ];

    public $timestamps = true;

}
