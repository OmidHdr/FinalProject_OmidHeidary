package org.example.repository;

import org.example.DataBaseConnection.SingleTonConnection;
import org.example.entity.Services;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

import static org.hibernate.hql.internal.antlr.HqlTokenTypes.FROM;

public class ServicesRepository extends RepositoryImpl<Services, Integer>{
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

}
