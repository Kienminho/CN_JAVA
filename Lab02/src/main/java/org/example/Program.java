package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Program {
    private static boolean isActive = true;
    public static void main(String[] args) throws SQLException {

        Scanner scanner = new Scanner(System.in);


        if(args.length !=1) {
            System.out.println("Invalid connection url");
            return;
        }

        //hiển thị menu
        menu();
        //connection to database
        Connection connection = ProductDAO.openConnectDatabase(args[0]);
        ProductDAO productDAO = new ProductDAO(connection);

        while (isActive) {
            clearScreen();
            menu();
            System.out.print("You choice: ");
            int n = scanner.nextInt();
            switch (n) {
                case 1:
                    List<Product> listProduct = (List<Product>) productDAO.readAll();
                    Product product = new Product();
                    product.printList(listProduct);
                    break;
                case 2:
                    System.out.println("Enter ID you want search: ");
                    int id = scanner.nextInt();
                    product = (Product) productDAO.read(id);
                    product.printProduct(product);
                    break;
                case 3:
                    scanner.nextLine();
                    System.out.print("Enter name for new product: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter price for new product: ");
                    Double price = scanner.nextDouble();
                    Product newProduct = new Product(name, price);

                    if(productDAO.add(newProduct) != null) {
                        System.out.println("Add successfully...");
                    }
                    break;
                case 4:
                    System.out.print("Enter the product id to update: ");
                    int idUpdate = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter new name: ");
                    String newName = scanner.nextLine();
                    System.out.print("Enter new price: ");
                    Double newPrice = scanner.nextDouble();
                    Product productUpdate = new Product(idUpdate,newName, newPrice);
                    if(productDAO.update(productUpdate)) {
                        System.out.println("Update successfully...");
                    }
                    break;
                case 5:
                    System.out.print("Enter the product id to delete: ");
                    int idDelete = scanner.nextInt();
                    if(productDAO.delete(idDelete)) {
                        System.out.println("Delete successfully...");
                    }
                    break;

                case 6:
                    connection.close();
                    isActive = false;
                    System.out.println("Exiting...");
                    return;
            }

            System.out.println("Press Enter to continue...");
            scanner.nextLine(); // Đọc dòng trống (Enter)
            scanner.nextLine(); // Chờ người dùng nhấn Enter
        }

    }

    public static void menu() {
        System.out.println("1. Read all products");
        System.out.println("2. Read a product by id");
        System.out.println("3. Add a new product");
        System.out.println("4. Update a product");
        System.out.println("5. Delete a product by id");
        System.out.println("6. Exit\n\n");
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
}