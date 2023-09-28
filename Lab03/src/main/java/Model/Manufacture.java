package Model;


import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;

@Getter
@Entity
@Table(name = "manufacture")
public class Manufacture {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "manufacture_name", nullable = false, length = 128)
    private String name;


    private String location;


    private int employee;

    @OneToMany(mappedBy = "manufactures")
    private List<MobilePhone> mobilePhone;

    public Manufacture() {
    }

    public Manufacture(String id,String name, String location, int employee) {
        this.id = id;
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

    public void setId(String id) {
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
}
