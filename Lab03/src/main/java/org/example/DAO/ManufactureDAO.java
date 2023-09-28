package org.example.DAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Repository;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;


import java.util.List;

public class ManufactureDAO implements Repository {


    public ManufactureDAO() {

    }

    @Override

    public boolean add(Object item) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();

            Manufacture manufacture = (Manufacture) item;
            session.beginTransaction();
            session.save(manufacture);
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
            Manufacture manufacture =session.find(Manufacture.class, id);
            if(manufacture !=null) {
                return manufacture;
            }
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

        return null;
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
