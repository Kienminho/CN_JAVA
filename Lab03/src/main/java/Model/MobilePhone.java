package Model;


import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import lombok.Data;
import java.util.HashSet;
import java.util.List;

@Entity
@Table(name = "mobile_phone")

public class MobilePhone {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
    @Column(name = "phone_name", nullable = false, length = 128)
    @NotNull

    private String name;
    @Column
    @NotNull
    private int price;
    @Column
    @NotNull
    private String color;
    @Column
    @NotNull
    private String country;
    @Column
    @NotNull
    private int quantity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_manufacture")
    private Manufacture manufactures;

    public MobilePhone() {
    }
    public MobilePhone(String id,String name, int price, String color, String country, int quantity, Manufacture manufactures) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.color = color;
        this.country = country;
        this.quantity = quantity;
        this.manufactures = manufactures;
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
