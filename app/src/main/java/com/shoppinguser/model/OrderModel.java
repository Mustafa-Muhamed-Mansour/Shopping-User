package com.shoppinguser.model;


public class OrderModel
{
    String user_id;
    String category_image;
    String category_name;
    String number_quantity;
    String category_price;

    public OrderModel()
    {
    }

    public OrderModel(String user_id, String category_image, String category_name, String number_quantity, String category_price)
    {
        this.user_id = user_id;
        this.category_image = category_image;
        this.category_name = category_name;
        this.number_quantity = number_quantity;
        this.category_price = category_price;
    }

    public String getCategory_image() {
        return category_image;
    }

    public void setCategory_image(String category_image) {
        this.category_image = category_image;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public String getNumber_quantity() {
        return number_quantity;
    }

    public void setNumber_quantity(String number_quantity) {
        this.number_quantity = number_quantity;
    }

    public String getCategory_price() {
        return category_price;
    }

    public void setCategory_price(String category_price) {
        this.category_price = category_price;
    }
}
