package com.rk_softwares.bdlinkhub.Model;

public class User_info {

    private String user_id;
    private String name;
    private String status;
    private String message;
    private String email;
    private String password;
    private String new_password;
    private String date_of_birth;
    private String user_reg_code;
    private String user_update_password_code;




    //setter methods-----------------------------------------------
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public void setUser_reg_code(String user_reg_code) {
        this.user_reg_code = user_reg_code;
    }

    public void setUser_update_password_code(String user_update_password_code) {
        this.user_update_password_code = user_update_password_code;
    }

    //Getter methods------------------------------------------------------
    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getName() {
        return name;
    }

    public String getUser_id() {
        return user_id;
    }



}
