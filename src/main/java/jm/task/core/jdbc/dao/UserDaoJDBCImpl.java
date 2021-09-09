package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        System.out.println("---------- vl we're in dao create table()-------");
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "CREATE TABLE IF NOT EXISTS User "
                    +"(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
                    +"name VARCHAR (50),\n"
                    +"lastName VARCHAR (70),\n"
                    +"age INT )";

            int rowCount = connection.createStatement().executeUpdate(sql);
            connection.commit();
            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }

    }

    public void dropUsersTable() {
        System.out.println("------------- VL we're in dropUsersTable() -------------------------");

        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "DROP TABLE IF EXISTS user";
            int rowCount = statement.executeUpdate(sql);
            try {
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println(" ----------------------- VL we're in saveUser ---------");
        String sql = "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?)";
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();

            connection.setAutoCommit(false);
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setByte(3, age);
            int rowCount = pstatement.executeUpdate(sql);
            try {
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

    public void removeUserById(long id) {
        System.out.println(" ------------------VL we ARE in removeUserById() ---------");

        String sql = "DELETE FROM user WHERE id = ?";
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();

            connection.setAutoCommit(false);
            pstatement.setLong(1, id);
            int rowCount = pstatement.executeUpdate();

            try {
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

            System.out.println("Row Count affected = " + rowCount);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

    public List<User> getAllUsers() {
        System.out.println(" ----------------------- VL we ARE in getAllUsers() ------------");
        List<User> lstUsers = new ArrayList<>();

        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();

            connection.setAutoCommit(false);
            String sql = "Select id, name, lastName, age from user";
            ResultSet rs = statement.executeQuery(sql);
            int rowCount = rs.getFetchSize();
            try {
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

            while (rs.next()) {
                User lvUser = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                lvUser.setId(rs.getLong("id"));
                lstUsers.add(lvUser);
            }

            System.out.println("Selected users:");
            lstUsers.forEach((usr)-> System.out.println(usr.getId() + " " + usr.getName() + " " + usr.getLastName()));

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


        return lstUsers;

    }

    public void cleanUsersTable() {
        System.out.println(" ------------------VL WE ARE in cleanUsersTable() ---------");
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();

            connection.setAutoCommit(false);
            String sql = "DELETE FROM user ";
            int rowCount = statement.executeUpdate(sql);
            try {
                connection.commit();
            } catch (Exception e) {
                connection.rollback();
                e.printStackTrace();
            }

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.rollback();
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

}
