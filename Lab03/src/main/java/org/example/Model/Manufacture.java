package org.example.Model;


import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "manufacture_name", nullable = false, length = 128)
    private String name;

    @Column(nullable = false)

    private String location;

    @Column(nullable = false)
    private int employee;

    @OneToMany(mappedBy = "manufactures")
    private List<MobilePhone> mobilePhone;

    public Manufacture() {
    }

    public Manufacture(String name, String location, int employee) {

        this.name = name;
        this.location = location;
        this.employee = employee;
    }

    public Manufacture(String name, String location, int employee, List<MobilePhone> mobilePhone) {
        this.name = name;
        this.location = location;
        this.employee = employee;
        this.mobilePhone = mobilePhone;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public void setMobilePhone(List<MobilePhone> mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getEmployee() {
        return employee;
    }

    public List<MobilePhone> getMobilePhone() {
        return mobilePhone;
    }

    public String toString() {

        return "Manufacture [ id  = "+this.id+ " name ="+ this.name+ " location = "+this.location+ " employee = "+this.employee+ " ]";
    }
}
