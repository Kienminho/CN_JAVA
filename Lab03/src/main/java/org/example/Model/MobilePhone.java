package org.example.Model;



import org.hibernate.engine.jdbc.Size;

import javax.persistence.*;

@Entity
@Table(name = "mobile_phone")

public class MobilePhone {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "phone_name", nullable = false)
    private String name;
    @Column(nullable = false)
    private int price;
    @Column(nullable = false)

    private String color;
    @Column

    private String country;
    @Column

    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_manufacture")
    private Manufacture manufactures;

    public MobilePhone() {
    }
    public MobilePhone(String name, int price, String color, String country, int quantity, Manufacture manufactures) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
        this.manufactures = manufactures;
    }
    public MobilePhone(String name, int price, String color, String country, int quantity) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Manufacture getManufactures() {
        return manufactures;
    }

    public void setManufactures(Manufacture manufactures) {
        this.manufactures = manufactures;
    }
}
