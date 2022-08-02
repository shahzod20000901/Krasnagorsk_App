package com.example.krasnagorsk_app.Utils;

import android.net.Uri;

import java.io.Serializable;

public class ItemProduct {
    private String product_name, product_price, id_date, image_url, product_description, product_adres;

    public ItemProduct() {
    }




    public ItemProduct(String product_name, String product_price, String id_date, String image_url, String product_description, String product_adres) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.id_date = id_date;
        this.image_url = image_url;
        this.product_description=product_description;
        this.product_adres=product_adres;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public String getId_date() {
        return id_date;
    }

    public void setId_date(String id_date) {
        this.id_date = id_date;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getProduct_adres() {
        return product_adres;
    }

    public void setProduct_adres(String product_adres) {
        this.product_adres = product_adres;
    }
}
