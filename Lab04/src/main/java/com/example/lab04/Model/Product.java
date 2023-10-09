package com.example.lab04.Model;

import java.util.List;

public  class Product {
    private String id;
    private String name;
    private int price;

    public Product(String id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Product() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static Product findProductById(List<Product> products, String id) {
        for (Product p: products) {
            if(p.getId().equals(id)) {
                return p;
            }

        }

        return null;
    }
}
