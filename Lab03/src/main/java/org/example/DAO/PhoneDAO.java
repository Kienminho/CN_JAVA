package org.example.DAO;
import org.example.Model.MobilePhone;
import org.example.Utils.HibernateUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.example.Repository;


import java.util.ArrayList;
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
    public List<MobilePhone> getAll() {
        List<MobilePhone> listPhone = new ArrayList<>();
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "FROM MobilePhone";
            Query query = session.createQuery(hql);
            listPhone = query.list();
        } catch (Exception e) {
            // Xử lý ngoại lệ nếu cần
        }
        return listPhone;
    }

    @Override
    public boolean deleteById(Object id) {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            MobilePhone mobilePhone = session.find(MobilePhone.class, id);
            session.delete(mobilePhone);
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
            MobilePhone mobilePhone = (MobilePhone) item;
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();

            String hql = "UPDATE MobilePhone SET color = :color, phone_name = :phone_name, price = :price, quantity = :quantity WHERE id = :id";

            Query query = session.createQuery(hql);
            query.setParameter("color", mobilePhone.getColor());

            query.setParameter("phone_name", mobilePhone.getName());
            query.setParameter("price", mobilePhone.getPrice());
            query.setParameter("quantity", mobilePhone.getQuantity());
            query.setParameter("id", mobilePhone.getId());
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

    public Object getPriceMax() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT m FROM MobilePhone m  WHERE m.price = (SELECT MAX(price) FROM MobilePhone)";
            Query query = session.createQuery(hql);
            MobilePhone m = (MobilePhone) query.getSingleResult();
            if(m != null)
                return m;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<MobilePhone> sortPhone() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT p FROM MobilePhone p " +
                    "ORDER BY p.country ASC, p.price DESC";
            Query query = session.createQuery(hql);
            List<MobilePhone> listPhone = query.list();
            if(listPhone != null)
                return listPhone;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public List<MobilePhone> checkPrice() {

        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT m FROM MobilePhone m  WHERE m.price > 50000000";
            Query query = session.createQuery(hql);
            List<MobilePhone> listPhone = query.list();
            if(listPhone != null)
                return listPhone;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public Object findPhoneByColorAndPrice() {
        try {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            String hql = "SELECT m FROM MobilePhone m  WHERE m.price > 15000000 and m.color = 'Pink'";
            Query query = session.createQuery(hql);
            MobilePhone phone = (MobilePhone) query.getSingleResult();
            if(phone != null)
                return phone;
        } catch (HibernateException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
