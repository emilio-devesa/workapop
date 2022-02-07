import org.hibernate.Hibernate;

public class Main {

    public static void main(String[] args){
        HibernateUtil.buildSessionFactory();
        HibernateUtil.openSession();
        TestingViews view = new TestingViews();
        view.verEmpleados();
        HibernateUtil.closeSessionFactory();
    }

}
