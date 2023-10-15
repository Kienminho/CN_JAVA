package com.example.lab05.DAO;

import com.example.lab05.Model.Product;
import com.example.lab05.Model.User;
import com.example.lab05.Repository.Repository;
import com.example.lab05.Utils.HibernateUtils;
import org.hibernate.Session;

import org.hibernate.query.Query;
import java.util.List;

public class UserDAO implements Repository {
    private static String getUser = "SELECT u FROM User u WHERE u.name = :username";
    @Override
    public boolean add(Object item) {
        try{
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            User newUser = (User) item;
            session.save(newUser);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        catch (Exception e) {
            throw new RuntimeException(e);

        }

    }

    @Override
    public Object get(Object name) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery(getUser);
            query.setParameter("username", name);
            List<User> userList = query.getResultList();
            User userExist = (userList.isEmpty()) ? null : userList.get(0);
            session.close();

            return userExist;
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

    }



    @Override
    public List getAll() {
        return null;
    }

    @Override
    public boolean deleteById(Object id) {
        return false;
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
