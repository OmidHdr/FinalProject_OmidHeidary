package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.Optional;

public class AdminRepository extends RepositoryImpl<User, Integer> {

    public static boolean login(User user) throws Exception {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from admin where username = ? and password = ?;");
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


}
