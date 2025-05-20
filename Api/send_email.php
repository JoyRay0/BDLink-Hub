<?php

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

require __DIR__ . '/PHPMailer/src/PHPMailer.php';
require __DIR__ . '/PHPMailer/src/Exception.php';
require __DIR__ . '/PHPMailer/src/SMTP.php';

function send_welcome_email($user_email){
    
    $mail = new PHPMailer(true);

    try{

        $mail ->isSMTP();
        $mail->Host = 'mail.rksoftwares.xyz' ;   //email
        $mail->SMTPAuth = true;
        $mail->Username = 'jekkhjpw@rksoftwares.xyz';   //user email
        $mail->Password = 'qKAz40Y2@2A]dg';   //user password
        $mail->SMTPSecure = 'tls';
        $mail->Port = 587;
        $mail->CharSet = 'UTF-8';
        $mail->Encoding = 'base64';

        $mail->isHTML(false);
        $mail->setFrom('jekkhjpw@rksoftwares.xyz','BDLink Hub');
        $mail->addAddress($user_email);
        $mail->Subject = "BDLink Hub-এ আপনাকে স্বাগতম!";
        $mail->Body = "প্রিয় ব্যবহারকারী,\n\nBDLink Hub-এ আপনাকে আন্তরিক শুভেচ্ছা ও স্বাগতম। আমাদের অ্যাপে রেজিস্ট্রেশনের জন্য ধন্যবাদ। এখনই অ্যাপটি এক্সপ্লোর করুন এবং প্রয়োজনীয় সব তথ্য হাতের মুঠোয় পান।\n\nশুভকামনায়,\nBDLink Hub টিম";

        $mail->send();

    }catch(Exception $e){

        echo "Email sending failed: {$mail->ErrorInfo}";

    }

    return $mail;


}


?>