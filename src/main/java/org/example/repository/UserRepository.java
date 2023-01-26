package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Expert;
import org.example.entity.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class UserRepository extends RepositoryImpl<User,Long> {

   public static final Logger logger = LoggerFactory.getLogger(UserRepository.class);

    public static User login(User user) throws Exception {
        Transaction transaction = null;
        try (Session session = SingleTonConnection.getInstance().openSession()) {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from users where username = ? and password = ?;");
            nativeQuery.setParameter(1, user.getUsername());
            nativeQuery.setParameter(2, user.getPassword());
            nativeQuery.addEntity(User.class);
            try {
                User result = (User) nativeQuery.getSingleResult();
                logger.info("User " + user.getUsername() + " Logged in successfully ");
                return result;
            } catch (Exception e) {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

    public static List<Expert> findInactiveExperts(){
        Transaction transaction = null;
        try (Session session = SingleTonConnection.getInstance().openSession()) {
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("select * from users where status = false and role = 'expert';");
            nativeQuery.addEntity(Expert.class);
            List<Expert> items = nativeQuery.getResultList();
            logger.info("Expert {} Logged in successfully ", items.get(0).getUsername());
            return items;
        } catch (Exception e) {
            e.printStackTrace();
            assert transaction != null;
            transaction.rollback();
            return null;
        }
    }

}
