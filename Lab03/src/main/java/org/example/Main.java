package org.example;

import org.example.DAO.ManufactureDAO;
import org.example.DAO.PhoneDAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PhoneDAO phoneDAO = new PhoneDAO();
    private static ManufactureDAO manufactureDAO = new ManufactureDAO();

    public static void main(String[] args) {


        boolean isActive = true;
        while (isActive) {
            clearScreen();
            menu();
            System.out.print("You choice: ");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub = scanner.nextInt();
                    if(sub ==1) {
                        System.out.println("List manufacture: ");
                        getAllManufacture();
                        addPhone();
                    }
                    else
                        addManufacture();
                    break;
                case 2:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub1 = scanner.nextInt();
                    if(sub1 ==1)
                        getAllPhone();
                    else
                        getAllManufacture();
                    break;
                case 3:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub2 = scanner.nextInt();
                    if(sub2 ==1) {
                        System.out.print("Enter the phone id to find: ");
                        Long idPhone = scanner.nextLong();
                        getPhoneById(idPhone);
                    }
                    else {
                        System.out.print("Enter the manufacture id to find: ");
                        Long idManufacture = scanner.nextLong();
                        getManufactureById(idManufacture);
                    }

                    break;

                case 4:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub3 = scanner.nextInt();
                    if(sub3 ==1) {
                        System.out.println("List phone: ");
                        getAllPhone();
                        System.out.print("Enter the phone id to remove: ");
                        Long idPhone = scanner.nextLong();
                        deletePhoneById(idPhone);
                    }
                    else {
                        System.out.println("List manufacture: ");
                        getAllManufacture();
                        System.out.print("Enter the manufacture id to remove: ");
                        Long idManufacture = scanner.nextLong();
                        deleteManufactureById(idManufacture);
                    }
                    break;

                case 5:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub4 = scanner.nextInt();
                    if(sub4 ==1) {
                        System.out.println("List phone: ");
                        getAllPhone();
                        updatePhone();
                    }
                    else {
                        System.out.println("List manufacture: ");
                        getAllManufacture();
                        updateManufacture();
                    }
                    break;

                case 6:
                    subMenu();
                    System.out.print("You choice: ");
                    int sub5 = scanner.nextInt();
                    System.out.println();
                    moreMenu(sub5);
                    break;

                case 7:
                    isActive = false;

            }
        }


    }




    private static void menu() {
        System.out.println("1. Add Phone or Manufacture");
        System.out.println("2. Read all Phone or Manufacture");
        System.out.println("3. Read a Phone or Manufacture by id");
        System.out.println("4. Remove a Phone or Manufacture by id");
        System.out.println("5. Update a Phone or Manufacture");
        System.out.println("6. Extended functionality of Phone or Manufacture");
        System.out.println("7. Exit\n\n");
    }
    private static void subMenu() {
        System.out.println("1. Phone");
        System.out.println("2. Manufacture");
    }

    private static void moreMenu(int i) {
        boolean subIsActive = true;
        while (subIsActive) {
            switch (i) {
                case 1:
                    System.out.println("Phone: ");
                    System.out.println("1. The phone with the highest selling price ");
                    System.out.println("2. Read all phone sorted by country name and sort desc by price ");
                    System.out.println("3. The phone costs over 50 million VND ");
                    System.out.println("4. The phone has the colorPink and costs over 15 million ");
                    System.out.println("5. Exit..");

                    System.out.print("You choice: ");
                    int subIndex = scanner.nextInt();
                    switch (subIndex) {
                        case 1:
                            getPriceMax();
                            break;
                        case 2:
                            sort();
                            break;
                        case 3:
                            checkPrice();
                            break;
                        case 4:
                            checkColorAndPrice();
                            break;
                        case 5:
                            subIsActive = false;
                            break;
                    }
                    break;
                case 2:
                    System.out.println("Manufacturers: ");
                    System.out.println("1. All manufacturers have more than 100 employees ");
                    System.out.println("2. The sum of all employees of the manufactures");
                    System.out.println("3. The last manufacturer in the list of manufacturers that meet \n" +
                            "the criteria: based in the US ");
                    System.out.println("4. Exit..");

                    System.out.print("You choice: ");
                    int subIndex1 = scanner.nextInt();
                    switch (subIndex1) {
                        case 1:
                            getAllManufacture();
                            break;
                        case 2:
                            sumOfAllEmployees();
                            break;
                        case 3:
                            lastManufacturebyLocation();
                            break;
                        case 4:
                            subIsActive = false;
                            break;
                    }
                    break;

            }
        }






    }

    private static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addPhone() {
        System.out.print("\n");
        scanner.nextLine();
        System.out.print("Enter name for new phone: ");
        String name = scanner.nextLine();
        System.out.print("Enter price for new phone: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter color for new phone: ");
        String color = scanner.nextLine();
//        scanner.nextLine();
        System.out.print("Enter country for new phone: ");
        String country = scanner.nextLine();
        System.out.print("Enter quantity for new phone: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter manufacture for new phone: ");
        Long manufacture = scanner.nextLong();
        Manufacture manufacture1 =  (Manufacture)manufactureDAO.get(manufacture);
        MobilePhone m1;
        if (manufacture1 == null)
            m1 = new MobilePhone(name, price, color, country, quantity);
        m1 = new MobilePhone(name, price, color, country, quantity, manufacture1);
        phoneDAO.add(m1);
        System.out.println("Add successfully");


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
        if(m != null) {
            System.out.println(m.toString());

        }
        System.out.println("A phone with id "+ id+ " does not exist");

    }

    private static void getManufactureById(Long idManufacture) {
        Manufacture m = (Manufacture) manufactureDAO.get(idManufacture);
        if(m != null) {
            System.out.println(m.toString());
        }

        System.out.println("A manufacturer with id "+ idManufacture+ " does not exist");
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
        System.out.print("Enter id for phone: ");
        Long id = scanner.nextLong();

        System.out.print("Enter new name for phone: ");
        String name = scanner.nextLine();
        scanner.nextLine();
        System.out.print("Enter new price for phone: ");
        int price = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new color for phone: ");
        String color = scanner.nextLine();
        System.out.print("Enter quantity for phone: ");
        int quantity = scanner.nextInt();


        MobilePhone newPhone = new MobilePhone(id, name, price, color,  quantity);
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
    public static void checkPrice() {
        List<MobilePhone> listPhone = phoneDAO.checkPrice();
        if(!listPhone.isEmpty()) {
            for (MobilePhone m: listPhone) {
                System.out.println(m.toString());
            }

        }
        System.out.println("No phone price more than 50 million VND");

    }

    //check price > 15tr  and color = pink
    public static void checkColorAndPrice() {
        MobilePhone mobilePhone = (MobilePhone) phoneDAO.findPhoneByColorAndPrice();
        System.out.println(mobilePhone.toString());

    }

    //add Manufacture
    public static void addManufacture() {
        scanner.nextLine();
        System.out.print("Enter name for new manufacture: ");
        String name = scanner.nextLine();
        System.out.print("Enter location for new manufacture: ");
        String location = scanner.nextLine();
        System.out.print("Enter employee for new manufacture: ");
        int employee = scanner.nextInt();

        Manufacture m1 = new Manufacture(name, location, employee);
        manufactureDAO.add(m1);
        System.out.println("Add successfully");

    }

    //get all Manufacture
    public static void getAllManufacture() {
        List<Manufacture> list = manufactureDAO.getAll();

        for (Manufacture m: list) {
            System.out.println(m.toString());
        }
    }

    // delete Manufacture by id
    public static void deleteManufactureById(Long id) {
        if(manufactureDAO.deleteById(id))
            System.out.println("Delete successfully");
        else
            System.out.println("Delete fail");
    }

    //update Manufacture

    public static void updateManufacture() {
        System.out.print("Enter id for manufacture to update: ");
        Long id = scanner.nextLong();
scanner.nextLine();
        System.out.print("Enter new name for phone: ");
        String name = scanner.nextLine();

        System.out.print("Enter new location for phone: ");
        String location = scanner.nextLine();
        System.out.print("Enter number employees for phone: ");
        int employees = scanner.nextInt();


        Manufacture newManufacture = new Manufacture(id, name,location, employees);
        if(manufactureDAO.update(newManufacture))
            System.out.println("Update successfully");
        else
            System.out.println("Update fail");
    }

    //check number of Employee
    public static void checkNumberOfEmployee() {
        List<Manufacture> result = manufactureDAO.checkNumberOfEmployees();
        for (Manufacture m: result) {
            System.out.println(m.toString());
        }
    }


    //sum of all employees
    public static void sumOfAllEmployees() {
        Long sum = manufactureDAO.sumOfAllEmployees();
        System.out.println("Total employees= "+ sum);
    }

    public static void lastManufacturebyLocation() {
        Manufacture m = manufactureDAO.lastManufacturebyLocation();
        System.out.println(m.toString());
    }


}