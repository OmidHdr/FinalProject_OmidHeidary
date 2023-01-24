package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Expert;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;
import java.util.Optional;

public class UserRepository extends RepositoryImpl<User,Long> {
    public static boolean login(User user) throws Exception {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from users where username = ? and password = ?;");
            nativeQuery.setParameter(1, user.getUsername());
            nativeQuery.setParameter(2, user.getPassword());
            final Optional first = nativeQuery.getResultList().stream().findFirst();
            return first.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public static List<Expert> findInactiveExperts() throws Exception {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("select * from users where status = false and role = 'expert';");
            nativeQuery.addEntity(Expert.class);
            List<Expert> items = nativeQuery.getResultList();
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }

}
