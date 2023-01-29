package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Expert;
import org.example.entity.Order;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

public class ExpertRepository extends RepositoryImpl<Expert,Long>{
    public static final Logger logger = LoggerFactory.getLogger(ExpertRepository.class);

    public static List<Order> allJobs(Expert expert){
        Transaction transaction = null;
        Long serviceId = expert.getServices().getId();    //1
        Long subscriptionId = expert.getSubServices().getId(); // 1
        try (Session session = SingleTonConnection.getInstance().openSession()) {
            transaction = session.beginTransaction();
            final NativeQuery nativeQuery = session.createNativeQuery("select * from orders where services_id = ? and subservices_id = ? and jobstatus = 'waitingForExpert';");
            nativeQuery.setParameter(1, serviceId);
            nativeQuery.setParameter(2, subscriptionId);
            nativeQuery.addEntity(Order.class);
            List<Order> result = nativeQuery.getResultList();
            logger.info("Order Logged from user {} ", expert.getUsername());
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
            return null;
        }
    }
}
