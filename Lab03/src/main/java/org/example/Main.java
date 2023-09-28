package org.example;

import DAO.ManufactureDAO;
import DAO.PhoneDAO;
import Model.Manufacture;
import Model.MobilePhone;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();


            //test add Manufacture
            Manufacture manufacture = new Manufacture("Apl", "Apple", "New York", 50);
            ManufactureDAO manufactureDAO = new ManufactureDAO(session);
            manufactureDAO.add(manufacture);

            MobilePhone mobilePhone = new MobilePhone("xs", "Iphone XS", 500000,"Gold", "USA", 100, manufacture);
            PhoneDAO phoneDAO = new PhoneDAO(session);
            phoneDAO.add(mobilePhone);


            MobilePhone m = (MobilePhone) phoneDAO.get(mobilePhone.getId());
            System.out.println(m);


            //session close when user finished or exit of program
            session.close();
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }


    }
}