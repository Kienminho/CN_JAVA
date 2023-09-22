package org.example;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository {


    private String READ_ADD_QUERY = "select * from product";
    private String READ_BY_ID_QUERY = "select * from product where id=?";
    private String ADD_NEW_PRODUCT_QUERY = "insert into product(name, price) values(?,?)";
    private String UPDATE_PRODUCT_QUERY = "update product set name=?, price=? where id =?";
    private String DELETE_PRODUCT_BY_ID = "delete from product where id=?";
    private Connection connection;
    public  ProductDAO(){};


    public ProductDAO(Connection connection) {
        this.connection = connection;
    }

    public static Connection  openConnectDatabase(String url) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            return connection;
        }
        catch (Exception e) {
            System.out.println("Connect fail");
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }


    }

    @Override
    public Object add(Object item) {
        Product newProduct = (Product) item;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_NEW_PRODUCT_QUERY);
            preparedStatement.setObject(1,  newProduct.getName());
            preparedStatement.setObject(2,  newProduct.getPrice());
            int rowsAffected   = preparedStatement.executeUpdate();
            if(rowsAffected >0) {
                return newProduct;
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }

    @Override
    public List readAll() {
        List<Product> listProduct = new ArrayList<>();
        try {
            Statement statement = getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(READ_ADD_QUERY);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, name, price);
                listProduct.add(product);
            }

            resultSet.close();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listProduct;
    }

    @Override
    public Object read(Object id)  {
        Product product = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(READ_BY_ID_QUERY);
            preparedStatement.setObject(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int idProduct = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");

                product = new Product(idProduct, name, price);
            }
            else {
                System.out.println("No product found with id:  " + id);
            }

            preparedStatement.close();
            resultSet.close();

        }
        catch (SQLException e) {

        }

        return product;
    }

    @Override
    public boolean update(Object item) {
        Product newProduct = (Product) item;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PRODUCT_QUERY);
            preparedStatement.setObject(1,  newProduct.getName());
            preparedStatement.setObject(2,  newProduct.getPrice());
            preparedStatement.setObject(3, newProduct.getId());
            int rowsAffected   = preparedStatement.executeUpdate();
            if(rowsAffected >0) {
                return true;
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return false;
    }

    @Override
    public boolean delete(Object id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_PRODUCT_BY_ID);
            preparedStatement.setObject(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if(rowsAffected >0) {
                return true;
            }

        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
