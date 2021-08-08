package com.shoppinguser.model;

public class UserModel
{
    String user_id;
    String phone_number;

    public UserModel()
    {
    }

    public UserModel(String user_id, String phone_number)
    {
        this.user_id = user_id;
        this.phone_number = phone_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }
}
