package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private SessionFactory sessionfactory;
    public UserDaoHibernateImpl() {
        this.sessionfactory = Util.getHibernateFactory();
    }


    @Override
    public void createUsersTable() {
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS User "
                +"(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
                +"name VARCHAR (50),\n"
                +"lastName VARCHAR (70),\n"
                +"age INT )";

        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = sessionfactory.openSession();
        session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionfactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        User user = null;
        Session session = sessionfactory.openSession();
        Transaction tx1 = session.beginTransaction();
        user = (User) session.get(User.class, id);
        session.delete(user);
        tx1.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = sessionfactory.openSession().createQuery("From User").list();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionfactory.openSession();
        Transaction tx1 = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        //session.createSQLQuery("TRUNCATE TABLE user").executeUpdate();
        tx1.commit();
        session.close();
    }
}
