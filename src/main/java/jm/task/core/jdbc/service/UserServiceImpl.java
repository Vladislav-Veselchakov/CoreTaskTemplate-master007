package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoJDBCImpl();
//        userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDao.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        userDao.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

}

/** to fix:

1.      113 Добавить транзакции в JDBC
2.      Убрать все sout
3.      Использовать PreparedStatment где необходимо
4.      Удалить из Dao getMysqlConnection метод
5.      @Table обязательно ли?
6.      Переопределить equals hashcode в модели
7.      Добавить rollback
8.      По усмотрению try with resources openSesson
9.      getAllUsers убрать приведение типов
10.     UserServiceImpl использовать UserDao

*/
