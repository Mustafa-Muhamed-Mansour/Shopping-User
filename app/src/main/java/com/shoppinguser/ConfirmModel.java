package com.shoppinguser;

public class ConfirmModel
{
    String confirm_id;
    String confirm_address;
    String confirm_phone_number;
    String confirm_building_number;

    public ConfirmModel()
    {
    }

    public ConfirmModel(String confirm_id, String confirm_address, String confirm_phone_number, String confirm_building_number)
    {
        this.confirm_id = confirm_id;
        this.confirm_address = confirm_address;
        this.confirm_phone_number = confirm_phone_number;
        this.confirm_building_number = confirm_building_number;
    }

    public String getConfirm_id() {
        return confirm_id;
    }

    public void setConfirm_id(String confirm_id) {
        this.confirm_id = confirm_id;
    }

    public String getConfirm_address() {
        return confirm_address;
    }

    public void setConfirm_address(String confirm_address) {
        this.confirm_address = confirm_address;
    }

    public String getConfirm_phone_number() {
        return confirm_phone_number;
    }

    public void setConfirm_phone_number(String confirm_phone_number) {
        this.confirm_phone_number = confirm_phone_number;
    }

    public String getConfirm_building_number() {
        return confirm_building_number;
    }

    public void setConfirm_building_number(String confirm_building_number) {
        this.confirm_building_number = confirm_building_number;
    }

}
