package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Expert;
import org.example.entity.Expert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExpertRepository extends RepositoryImpl<Expert,Long>{
    public static final Logger logger = LoggerFactory.getLogger(ExpertRepository.class);
    public static Expert login(Expert user) throws Exception {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from users where username = ? and password = ?;");
            nativeQuery.setParameter(1, user.getUsername());
            nativeQuery.setParameter(2, user.getPassword());
            nativeQuery.addEntity(Expert.class);
            Expert result = (Expert) nativeQuery.getSingleResult();
            logger.info("Expert {} Logged in successfully ",result.getUsername());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        } finally {
            session.close();
        }
    }
}
