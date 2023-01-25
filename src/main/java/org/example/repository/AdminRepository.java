package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class AdminRepository extends RepositoryImpl<User, Long> {
    public static final Logger logger = LoggerFactory.getLogger(AdminRepository.class);

    public static boolean login(User user) throws Exception {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from admin where username = ? and password = ?;");
            nativeQuery.setParameter(1, user.getUsername());
            nativeQuery.setParameter(2, user.getPassword());
            final Optional first = nativeQuery.getResultList().stream().findFirst();
            logger.info("User " + user.getUsername() + " Logged in successfully ");
            return first.isPresent();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return false;
    }

    public static void confirmExpert(){

    }

}
