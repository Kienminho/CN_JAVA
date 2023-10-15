package com.example.lab05.DAO;

import com.example.lab05.Model.Product;
import com.example.lab05.Model.User;
import com.example.lab05.Repository.Repository;
import com.example.lab05.Utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository {
    private static String getProduct = "SELECT p FROM Product p WHERE p.productName = :name";
    @Override
    public boolean add(Object item) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Product newProduct = (Product) item;
            session.save(newProduct);
            session.getTransaction().commit();
            session.close();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public Object get(Object name) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(getProduct);
            query.setParameter("name", name);
            List<Product> productList = query.getResultList();
            Product userExist = (productList.isEmpty()) ? null : productList.get(0);
            session.close();

            return userExist;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product>getAll() {

        List<Product> listProduct = new ArrayList<>();
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM Product";
            Query query = session.createQuery(hql);
            listProduct = query.list();
            session.close();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
        }
        return listProduct;
    }

    @Override
    public boolean deleteById(Object id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Product p = session.find(Product.class, id);
            session.delete(p);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean delete(Object item) {
        return false;
    }

    @Override
    public boolean update(Object item) {
        return false;
    }
}
