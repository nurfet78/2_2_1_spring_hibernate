package hiber.dao;

import hiber.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {


   @Autowired
   private SessionFactory sessionFactory;


   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }


   @Override
   public List<User> listUsers() {
      return sessionFactory
              .getCurrentSession()
              .createQuery("from User", User.class).getResultList();
   }

   @Override
   public List<User> getUserByCar(String model, int series) {
      Session session = sessionFactory.getCurrentSession();

      String sql = "FROM User u JOIN FETCH u.car where u.car.model = :model and u.car.series = :series";
      //String sql = "from User where car = (from Car where model = :model and series = :series)";

       return session.createQuery(sql, User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .getResultList();
   }
}
