<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Search extends Model
{

    protected $table = 'search_index';
    protected $fillable = [
        'category',
        'title',
        'description',
        'link',

    ];

    public $timestamps = true;

    protected $hidden = [
        'table_name',
        'record_id',
        'created_at',
        'updated_at',
    ];


}
