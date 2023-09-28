package DAO;
import Model.Manufacture;
import Model.MobilePhone;
import org.example.HibernateUtils;
import org.example.Repository;
import org.hibernate.Session;


import java.util.List;

public class ManufactureDAO implements Repository {

    private Session session;
    public ManufactureDAO() {

    }
    public ManufactureDAO(Session session) {
        this.session = session;
    }

    @Override
    public boolean add(Object item) {
        try {
            Manufacture manufacture = (Manufacture) item;
            session.beginTransaction();
            session.save(manufacture);
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
        Manufacture manufacture =session.find(Manufacture.class, id);
        if(manufacture !=null) {
            return manufacture;
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
