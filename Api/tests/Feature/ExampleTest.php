<?php

namespace Tests\Feature;

// use Illuminate\Foundation\Testing\RefreshDatabase;
use Tests\TestCase;

class ExampleTest extends TestCase
{
    public function test_api_config(): void
    {
        $req = $this->get('/api/api_config');

        $req->assertStatus(200);
        $req->assertJson([
            'status' => 'successful'
        ]);
    }

    public function test_googleAd():void
    {
        $req = $this->get('/api/google_ad');

        $req->assertStatus(200);
        $req->assertJson([
            'status' => 'AD_DISABLE'
        ]);
    }

    public function test_view_pager():void
    {
        $viewpager = [
            [
                'id' => '1',
                'image' =>'https://img.freepik.com/free-photo/ui-ux-representations-with-laptop_23-2150201871.jpg?semt=ais_items_boosted&w=740'
            ],
            [
                'id' => '2',
                'image' =>'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTENX10pvQGsAR15nkw4q8nCKJJJJaI_f4UkA&s'
            ],
            [
                'id' => '3',
                'image' =>'https://media.istockphoto.com/id/1371339413/photo/co-working-team-meeting-concept-businessman-using-smart-phone-and-digital-tablet-and-laptop.jpg?s=612x612&w=0&k=20&c=ysEsVw3q2axYt3oVZAuQjtHRlN3lY-U_e0ikK5yKIXQ='
            ],
        ];

        $req = $this->get('/api/viewpager');

        $req->assertStatus(200);
        $req->assertJson([
            'status' => 'successful',
            'images' => $viewpager
        ]);
    }

    public function test_popular_item():void
    {
        $req = $this->get('/api/popular_item');
        $req->assertStatus(200);
        $req->assertJson([
            'status' => 'successful'
        ]);
    }

    public function test_all_item():void{

        $req = $this->get('/api/all_item');
        $req->assertStatus(200);
        $req->assertJson([
            'status' => 'successful'
        ]);
    }



    public function test_login():void
    {

        $p_json = [
            'email' => 'google@gmail.com'
        ];

        $req = $this->post('/api/user_login', $p_json);

        $req->assertStatus(200);
        $req->assertJsonFragment([
            'status' => 'successful',
            'message' => 'google@gmail.com'
        ]);
    }

}
