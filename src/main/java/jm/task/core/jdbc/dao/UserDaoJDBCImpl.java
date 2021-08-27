package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        System.out.println(" -- VL we're in createUsersTable() Please, implement it ---------");
        try (Connection connection = getMySQLConnection();
             Statement statement = connection.createStatement()){

            String sql = "CREATE TABLE IF NOT EXISTS User "
                    +"(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,\n"
                    +"name VARCHAR (50),\n"
                    +"lastName VARCHAR (70),\n"
                    +"age INT )";

            // Execute statement
            // executeUpdate(String) using for Insert, Update, Delete statement.
            int rowCount = statement.executeUpdate(sql);

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        System.out.println("------------- VL we're in dropUsersTable() -------------------------");

        try (Connection connection = getMySQLConnection();
             Statement statement = connection.createStatement()){

        String sql = "DROP TABLE IF EXISTS user";

        // Execute statement
        // executeUpdate(String) using for Insert, Update, Delete statement.
        int rowCount = statement.executeUpdate(sql);

        System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        System.out.println(" ----------------------- VL we're in saveUser ---------");
        try (Connection connection = getMySQLConnection();
            Statement statement = connection.createStatement()) {

            String sql = String.format("INSERT INTO user (name, lastname, age) VALUES (\"%s\", \"%s\", %d )", name, lastName, age);

            // Execute statement
            // executeUpdate(String) using for Insert, Update, Delete statement.
            int rowCount = statement.executeUpdate(sql);

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void removeUserById(long id) {
        System.out.println(" ------------------VL we ARE in removeUserById() ---------");
        try (Connection connection = getMySQLConnection();
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM user WHERE id = " + id;
            //PreparedStatement pstatement = connection.prepareStatement(sql);
            //pstatement.setInt(1, (int)id);

            int rowCount = statement.executeUpdate(sql);

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        System.out.println(" ----------------------- VL we ARE in getAllUsers() ------------");
        List<User> lstUsers = new ArrayList<>();

        try (Connection connection = getMySQLConnection();
             Statement statement = connection.createStatement()) {

            String sql = "Select id, name, lastName, age from user";

            // Execute statement
            // executeUpdate(String) using for Insert, Update, Delete statement.
            ResultSet rs = statement.executeQuery(sql);
            int rowCount = rs.getFetchSize();

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
        }

        return lstUsers;

    }

    public void cleanUsersTable() {
        System.out.println(" ------------------VL WE ARE in cleanUsersTable() ---------");
        try (Connection connection = getMySQLConnection();
             Statement statement = connection.createStatement()) {

            String sql = "DELETE FROM user ";

            // Execute statement
            // executeUpdate(String) using for Insert, Update, Delete statement.
            int rowCount = statement.executeUpdate(sql);

            System.out.println("Row Count affected = " + rowCount);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "testbase";
        String userName = "Vladislav";
        String password = "111122223333!";
        return getMySQLConnection(hostName, dbName, userName, password);
    }

    public Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        //Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

            return DriverManager.getConnection(connectionURL, userName, password);
    }

}
