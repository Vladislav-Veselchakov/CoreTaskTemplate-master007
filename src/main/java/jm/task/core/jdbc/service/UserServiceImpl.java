package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserServiceImpl implements UserService {

    // UserDaoJDBCImpl userDao;
    UserDaoHibernateImpl userHiber;

    public UserServiceImpl() {
        // userDao = new UserDaoJDBCImpl();
        userHiber = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {
        //userDao.createUsersTable();
        userHiber.createUsersTable();
    }

    public void dropUsersTable() {
        //userDao.dropUsersTable();
        userHiber.dropUsersTable();
    }

    public void saveUser(String name, String lastName, byte age) {
        //userDao.saveUser(name, lastName, age);
        userHiber.saveUser(name, lastName, age);

    }

    public void removeUserById(long id) {
        //userDao.removeUserById(id);
        userHiber.removeUserById(id);
    }

    public List<User> getAllUsers() {
        //return userDao.getAllUsers();
        return userHiber.getAllUsers();
    }

    public void cleanUsersTable() {
       //userDao.cleanUsersTable();
        userHiber.cleanUsersTable();
    }

}
