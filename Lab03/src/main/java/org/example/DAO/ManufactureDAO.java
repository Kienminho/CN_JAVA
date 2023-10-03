package org.example.DAO;
import org.example.Model.Manufacture;
import org.example.Model.MobilePhone;
import org.example.Repository;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;


import javax.management.openmbean.InvalidOpenTypeException;
import java.util.ArrayList;
import java.util.HashSet;
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
            session.close();
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


    // All manufacturers have more than 100 employees
    public List checkNumberOfEmployees() {
        try {
            List<Manufacture> result = new ArrayList<>();
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT m FROM Manufacture m WHERE m.employee > 100";
            Query query = session.createQuery(hql);
            result = query.list();
            if(result != null)
                return result;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public long sumOfAllEmployees() {
        try (Session session = HibernateUtils.getSessionFactory().openSession()) {
            session.beginTransaction();
            String hql = "SELECT SUM(m.employee) FROM Manufacture m";
            Query<Long> query = session.createQuery(hql);
            Long sum = query.uniqueResult();
//            session.getTransaction().commit();
            return sum != null ? sum : 0L;
        } catch (Exception e) {
            e.printStackTrace();
            return 0L;
        }
    }

    public Manufacture lastManufacturebyLocation() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT m FROM Manufacture m WHERE m.location = 'US' ORDER BY m.id DESC";
            Query query = session.createQuery(hql);
            query.setMaxResults(1);
            Manufacture lastManufacturer = (Manufacture) query.getSingleResult();
            if(lastManufacturer != null)
                return lastManufacturer;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new InvalidOpenTypeException("Error retrieving the last manufacturer in the US");
        }
        return null;
    }
}
