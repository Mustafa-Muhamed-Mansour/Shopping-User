package com.shoppinguser.model;

public class ConfirmModel
{

    String user_id;
    String confirm_id;
    String confirm_user_profile;
    String confirm_total_price;
    String confirm_user_name;
    String confirm_phone_number;
    String confirm_address;
    String confirm_building_number;

    public ConfirmModel()
    {
    }

    public ConfirmModel(String confirm_id, String confirm_total_price, String confirm_user_name, String confirm_phone_number, String confirm_address, String confirm_building_number)
    {
        this.confirm_id = confirm_id;
        this.confirm_total_price = confirm_total_price;
        this.confirm_user_name = confirm_user_name;
        this.confirm_phone_number = confirm_phone_number;
        this.confirm_address = confirm_address;
        this.confirm_building_number = confirm_building_number;
    }

    public ConfirmModel(String user_id, String confirm_id, String confirm_total_price, String confirm_user_name, String confirm_phone_number, String confirm_address, String confirm_building_number)
    {
        this.user_id = user_id;
        this.confirm_id = confirm_id;
        this.confirm_total_price = confirm_total_price;
        this.confirm_user_name = confirm_user_name;
        this.confirm_phone_number = confirm_phone_number;
        this.confirm_address = confirm_address;
        this.confirm_building_number = confirm_building_number;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getConfirm_id() {
        return confirm_id;
    }

    public String getConfirm_total_price() {
        return confirm_total_price;
    }

    public String getConfirm_user_name() {
        return confirm_user_name;
    }

    public String getConfirm_phone_number() {
        return confirm_phone_number;
    }

    public String getConfirm_address() {
        return confirm_address;
    }

    public String getConfirm_building_number() {
        return confirm_building_number;
    }
}
