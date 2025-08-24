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
        'table_name',
        'record_id'
    ];

    protected $hidden = [
        'id',
        'category',
        'title',
        'description',
        'link',
        'created_at',
        'updated_at',
        'table_name',
        'record_id'
    ];

    public $timestamps = true;

}
