package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Main {
    private static PhoneDAO phoneDAO = new PhoneDAO();
    private static ManufactureDAO manufactureDAO = new ManufactureDAO();

    public static void main(String[] args) {
        //add phone
        addPhone();

        //add manufacture
        addManufacture();


    }

    public static void addPhone() {
        MobilePhone m1 = new MobilePhone("Iphone X", 5200000, "White", "USA", 1000);
        MobilePhone m2 = new MobilePhone("Iphone XSM", 6200000, "Gold", "USA", 1000);
        MobilePhone m3 = new MobilePhone("Iphone 13", 8200000, "Pink", "USA", 1000);
        phoneDAO.add(m1);
        phoneDAO.add(m2);
        phoneDAO.add(m3);
    }


    public static void addManufacture() {
        Manufacture m1 = new Manufacture("Apple", "USA", 1000);
        Manufacture m2 = new Manufacture("Xiaomi", "China", 700);
        Manufacture m3 = new Manufacture("Samsung", "Korea", 800);
        manufactureDAO.add(m1);
        manufactureDAO.add(m2);
        manufactureDAO.add(m3);
    }
}