package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;

public class Main {
    private static PhoneDAO phoneDAO = new PhoneDAO();
    private static ManufactureDAO manufactureDAO = new ManufactureDAO();

    public static void main(String[] args) {
        //add phone
        //addPhone();
        //getAllPhone();
        //getPhoneById(2L);
        //deletePhoneById(3L);
        //deletePhone(2L);
        //updatePhone();
        //getPriceMax();
        //sort();
        //checkPrice();
        //checkColorAndPrice();
        //add manufacture
        //addManufacture();


    }

    public static void addPhone() {
        //MobilePhone m1 = new MobilePhone("Iphone X", 5200000, "White", "USA", 1000);
        MobilePhone m2 = new MobilePhone("Iphone XSM", 6200000, "Gold", "USA", 1000);
        MobilePhone m3 = new MobilePhone("Iphone 13", 8200000, "Pink", "USA", 1000);
        //phoneDAO.add(m1);
        phoneDAO.add(m2);
        phoneDAO.add(m3);
    }

    //get all phone
    public static void getAllPhone() {
        List<MobilePhone> list = phoneDAO.getAll();

        for (MobilePhone m: list) {
            System.out.println(m.toString());
        }
    }


    //get phone by id
    public static void getPhoneById(Long id) {
        MobilePhone m = (MobilePhone) phoneDAO.get(id);
        System.out.println(m.toString());

    }

    //delete phone by id
    public static void deletePhoneById(Long id) {
        if(phoneDAO.deleteById(id))
            System.out.println("Delete successfully");
        else
            System.out.println("Delete fail");
    }

    //delete phone
    public static void deletePhone(Long id) {
        MobilePhone m = (MobilePhone) phoneDAO.get(id);
        if(m != null) {
            if(phoneDAO.deleteById(id))
                System.out.println("Delete successfully");
            else
                System.out.println("Delete fail");
        }
    }

    //upate phone
    public static void updatePhone() {
        MobilePhone newPhone = new MobilePhone(1L, "Iphone 11", 6600000, "green",  3000);
        if(phoneDAO.update(newPhone))
            System.out.println("Update successfully");
        else
            System.out.println("Update fail");

    }

    //get phone has price max
    public static void getPriceMax() {
        MobilePhone m = (MobilePhone) phoneDAO.getPriceMax();
        System.out.println(m.toString());
    }



    //sort phone
    public static void sort() {
        List<MobilePhone> listPhone = phoneDAO.sortPhone();
        for (MobilePhone m: listPhone) {
            System.out.println(m.toString());
        }
    }
    //check price of phone
    public static boolean checkPrice() {
        List<MobilePhone> listPhone = phoneDAO.checkPrice();
        if(listPhone == null) {
            System.out.println("No phone price more than 50 million VND");
            return false;
        }
        for (MobilePhone m: listPhone) {
            System.out.println(m.toString());
        }
        return true;
    }

    //check price > 15tr  and color = pink
    public static void checkColorAndPrice() {
        MobilePhone mobilePhone = (MobilePhone) phoneDAO.findPhoneByColorAndPrice();
        System.out.println(mobilePhone.toString());

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