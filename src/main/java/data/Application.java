package data;

import entitties.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Date;

public class Application {

    public static void main(String[] args) {
        SessionFactory sessionFactory = null;
        Session session = null;
        org.hibernate.Transaction tx = null;

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            tx = session.beginTransaction();

            User user = User.builder()
                    .age(20)
                    .birthDate(new Date())
                    .emailAddress("redlongcity@gmail.com")
                    .firstName("Vladimir")
                    .lastName("Kharchenko")
                    .lastUpdatedDate(new Date())
                    .build();

            session.save(user);
            tx.commit();

//            UserCredentialView view = (UserCredentialView) session.get(UserCredentialView.class, 1L);
//            System.out.println(view.getFirstName());
//            System.out.println(view.getLastName());

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            session.close();
//            sessionFactory.close();
        }
    }

}
