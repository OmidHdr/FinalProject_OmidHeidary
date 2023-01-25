package org.example.DataBaseConnection;

import lombok.NoArgsConstructor;
import org.example.entity.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@NoArgsConstructor
public class SingleTonConnection {
    private static class LazyHolder{
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();

            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Services.class)
                    .addAnnotatedClass(SubServices.class)
                    .addAnnotatedClass(Expert.class)
                    .addAnnotatedClass(Order.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }
    public static SessionFactory getInstance(){
        return LazyHolder.INSTANCE;
    }
}
