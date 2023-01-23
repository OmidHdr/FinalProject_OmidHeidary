package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Services;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class ServicesRepository extends RepositoryImpl<Services, Long>{

    public static List<Services> allServices(){
        Session session = SingleTonConnection.getInstance().openSession();
        Transaction transaction = null;
        try {
            final Query<Services> from_loans = session.createQuery("FROM Services");
            final List<Services> list = from_loans.list();
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            throw new RuntimeException();
        } finally {
            session.close();
        }
    }

    public static boolean login(User user) throws Exception {
        if (UserRepository.login(user)){
            System.out.println("User Logged in Successfully");
            return true;
        }
        return false;
    }

}
