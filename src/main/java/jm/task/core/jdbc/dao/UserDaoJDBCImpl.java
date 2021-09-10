package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public void createUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "CREATE TABLE IF NOT EXISTS User "
                    +"(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
                    +"name VARCHAR (50),\n"
                    +"lastName VARCHAR (70),\n"
                    +"age INT )";

            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }

    }

    public void dropUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "DROP TABLE IF EXISTS user";
            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String sql = "INSERT INTO user (name, lastname, age) VALUES (?, ?, ?)";
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setString(1, name);
            pstatement.setString(2, lastName);
            pstatement.setByte(3, age);
            int rowCount = pstatement.executeUpdate();
            // connection.createStatement().execute("insert into USER (NAME, LASTNAME, AGE) values (\"from sv()\", \"from sv()\", 5)");
            connection.commit();
            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}

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
        String sql = "DELETE FROM user WHERE id = ?";
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            PreparedStatement pstatement = connection.prepareStatement(sql);
            pstatement.setLong(1, id);
            int rowCount = pstatement.executeUpdate();
            connection.commit();
            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

    public List<User> getAllUsers() {
        List<User> lstUsers = new ArrayList<>();
        Connection connection = null;
        try {
            String sql = "Select id, name, lastName, age from user";
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            ResultSet rs = connection.createStatement().executeQuery(sql);
            connection.commit();
            while (rs.next()) {
                User lvUser = new User(rs.getString("name"), rs.getString("lastName"), rs.getByte("age"));
                lvUser.setId(rs.getLong("id"));
                lstUsers.add(lvUser);
            }

            System.out.println("Selected users:");
            lstUsers.forEach((usr)-> System.out.println(usr.getId() + " " + usr.getName() + " " + usr.getLastName()));
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}

            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }

        return lstUsers;

    }

    public void cleanUsersTable() {
        Connection connection = null;
        try {
            connection = Util.getMySQLConnection();
            connection.setAutoCommit(false);
            String sql = "TRUNCATE TABLE user ";
            connection.createStatement().executeUpdate(sql);
            connection.commit();
        } catch (Exception e) {
            try {
                connection.rollback();
            } catch (Exception do_Nothing) {}
            e.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (Exception e) {
                // do nothing
            }
        }


    }

}
