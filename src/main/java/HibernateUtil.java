import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static Session session;

    /**
     * Crea la factoria de sesiones
     */
    public static void buildSessionFactory() {
        // Hibernate settings equivalent to hibernate.cfg.xml's properties
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://worka-pop.cupvh1a2vlwz.eu-west-1.rds.amazonaws.com/");
        settings.put(Environment.USER, "admin");
        settings.put(Environment.PASS, "WorkaPOP1234!");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        //settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        //settings.put(Environment.HBM2DDL_AUTO, "create-drop");

        Configuration configuration = new Configuration();
        configuration.setProperties(settings);
        configuration.configure();
        // Se registran las clases que hay que mapear con cada tabla de la base de datos
        // configuration.addAnnotatedClass(Clase1.class);
        // configuration.addAnnotatedClass(Clase2.class);
        // configuration.addAnnotatedClass(Clase3.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                configuration.getProperties()).build();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * Abre una nueva sesión
     */
    public static void openSession() {
        session = sessionFactory.openSession();
    }

    /**
     * Devuelve la sesión actual
     * @return
     */
    public static Session getCurrentSession() {

        if ((session == null) || (!session.isOpen()))
            openSession();

        return session;
    }

    /**
     * Cierra Hibernate
     */
    public static void closeSessionFactory() {

        if (session != null)
            session.close();

        if (sessionFactory != null)
            sessionFactory.close();
    }
}