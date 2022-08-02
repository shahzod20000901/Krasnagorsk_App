package com.example.krasnagorsk_app.Utils;

public class Goods {
    public String product_id, product_name, product_description, product_price, image_uri, currentDate, product_address;

    public Goods() {
    }

    public Goods(String product_id, String product_name, String product_description, String product_price, String image_uri, String currentDate, String product_address) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_description = product_description;
        this.product_price = product_price;
        this.image_uri = image_uri;
        this.currentDate=currentDate;
        this.product_address=product_address;
    }
}
