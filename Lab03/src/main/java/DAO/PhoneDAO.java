package DAO;
import Model.MobilePhone;
import org.hibernate.Session;
import org.example.HibernateUtils;
import org.example.Repository;

import java.sql.SQLException;
import java.util.List;

public class PhoneDAO implements Repository {
    private  Session session;

    public PhoneDAO() {

    }
    public PhoneDAO(Session session) {
        this.session = session;
    }


    @Override
    public boolean add(Object item) {
        try {

            MobilePhone mobilePhone = (MobilePhone) item;
            session.beginTransaction();
            session.save(mobilePhone);
            session.getTransaction().commit(); // Commit giao dịch nếu thành công
            return true;
        } catch (Exception e) {
            if (session.getTransaction() != null && session.getTransaction().isActive()) {
                session.getTransaction().rollback(); // Rollback giao dịch nếu có lỗi
            }
            throw new RuntimeException(e);
        }
    }


    @Override
    public Object get(Object id) {
        session.beginTransaction();
        MobilePhone mobilePhone =session.find(MobilePhone.class, id);
//        if(mobilePhone !=null) {
//            return mobilePhone;
//        }
        return mobilePhone;
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
