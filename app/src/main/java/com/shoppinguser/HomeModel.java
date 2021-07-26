package com.shoppinguser;

public class HomeModel
{
    String category_image;
    String category_name;
    String category_price;

    public HomeModel()
    {
    }

    public HomeModel(String category_image, String category_name, String category_price)
    {
        this.category_image = category_image;
        this.category_name = category_name;
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

    public String getCategory_price() {
        return category_price;
    }

    public void setCategory_price(String category_price) {
        this.category_price = category_price;
    }
}
