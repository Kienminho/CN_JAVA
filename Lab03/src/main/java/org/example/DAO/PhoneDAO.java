package org.example.DAO;
import org.example.Model.MobilePhone;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.example.Repository;

import java.util.List;

public class PhoneDAO implements Repository {
    public PhoneDAO() {

    }

    @Override
    public boolean add(Object item) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();

            MobilePhone mobilePhone = (MobilePhone) item;
            session.beginTransaction();
            session.save(mobilePhone);
            session.getTransaction().commit(); // Commit giao dịch nếu thành công
            return true;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public Object get(Object id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            MobilePhone mobilePhone =session.find(MobilePhone.class, id);
            return  mobilePhone;
        } catch (HibernateException e) {
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
