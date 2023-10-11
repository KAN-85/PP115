package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class UserDaoHibernateImpl implements UserDao {
    private String sql;
    static Logger logger;

    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql = "CREATE TABLE IF NOT EXISTS users" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastName VARCHAR(255), age INT)";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.WARNING,"Не удалось создать таблицу");
            e.printStackTrace();
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql = "DROP TABLE IF EXISTS users";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.WARNING,"Не удалось удалить таблицу");
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.log(Level.WARNING,":(");
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(User user) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            logger.log(Level.WARNING,":(((");
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            session.remove(user);
        } catch (Exception e) {
            logger.log(Level.WARNING,"Не удалось удалить пользователя");
            e.printStackTrace();
        }

    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            return session.createQuery("from User", User.class).list();
        } catch (Exception e) {
            logger.log(Level.WARNING,":((");
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            sql = "TRUNCATE TABLE users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            logger.log(Level.WARNING,"Не удалось очистить таблицу");
            e.printStackTrace();
        }
    }
}

