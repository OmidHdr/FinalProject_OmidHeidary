package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.SubServices;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.List;

public class SubServiceRepository extends RepositoryImpl<SubServices, Long> {

    public static List<SubServices> findServices(Long id) {
        Transaction transaction = null;
        Session session = SingleTonConnection.getInstance().openSession();
        try {
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("select * from sub_services where services_id = ?;");
            nativeQuery.setParameter(1,id);
            nativeQuery.addEntity(SubServices.class);
            List<SubServices> items = nativeQuery.getResultList();
            return items;
//            Query allResults = session.createQuery("select services from SubServices s where s.services=:id");
//            allResults.setParameter("id",id);
//            List<Services> results = (List<Services>) allResults.list();
//            return results;
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
        return null;
    }
}
