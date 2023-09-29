package org.example.DAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Repository;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import java.util.ArrayList;
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
            return  manufacture;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Manufacture> getAll() {
        List<Manufacture> listManufacture = new ArrayList<>();
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM Manufacture";
            Query query = session.createQuery(hql);
            listManufacture = query.list();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
        }
        return listManufacture;
    }

    @Override
    public boolean deleteById(Object id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            Manufacture manufacture = session.find(Manufacture.class, id);
            session.delete(manufacture);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public boolean delete(Object item) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            session.delete(item);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean update(Object item) {

        try {
            Manufacture manufacture = (Manufacture) item;
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE Manufacture SET name = :name, location = :location, employee = :employee WHERE id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("name", manufacture.getName());

            query.setParameter("location", manufacture.getLocation());
            query.setParameter("employee", manufacture.getEmployee());
            query.setParameter("id", manufacture.getId());
            int result = query.executeUpdate();
            if(result > 0)  {
                session.getTransaction().commit();
                return true;
            }

        }
        catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
