<?php


$databse_host = "p:localhost";
$database_user = "jekkhjpw";
$database_password = "qKAz40Y2@2A]dg";
$database_name = "jekkhjpw_rk_softwares";

$database_connect = new mysqli($databse_host,$database_user,$database_password,$database_name);
$database_connect->options(MYSQLI_OPT_CONNECT_TIMEOUT, 10); //10 seconds timeout

?>