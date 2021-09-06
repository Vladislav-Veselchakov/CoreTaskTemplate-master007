package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    public static Connection getMySQLConnection() throws SQLException, ClassNotFoundException {
        String hostName = "localhost";

        String dbName = "testbase";
        String userName = "Vladislav";
        String password = "111122223333!";

        return  getMySQLConnection(hostName, dbName, userName, password);
    }

    public static Connection getMySQLConnection(String hostName, String dbName, String userName, String password) throws SQLException, ClassNotFoundException {
        // Declare the class Driver for MySQL DB
        // This is necessary with Java 5 (or older)
        // Java6 (or newer) automatically find the appropriate driver.
        // If you use Java> 5, then this line is not needed.
        //Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;

        Connection conn = DriverManager.getConnection(connectionURL, userName,
                password);
        return conn;
    }

    public static SessionFactory getHibernateFactory() {
        // Настройки hibernate

        String hostName = "localhost";
        String dbName = "testbase";
        String userName = "Vladislav";
        String password = "111122223333!";

        Configuration configuration = new Configuration()
                .setProperty( "hibernate.connection.driver_class",  "com.mysql.jdbc.Driver" )
                .setProperty( "hibernate.connection.url",           "jdbc:mysql://" + hostName+ ":3306/" +dbName )
                .setProperty( "hibernate.connection.username",      userName)
                .setProperty( "hibernate.connection.password",      password)
                .setProperty( "hibernate.connection.pool_size",     "1" )
                .setProperty( "hibernate.connection.autocommit",    "false" )
                .setProperty( "hibernate.cache.provider_class",     "org.hibernate.cache.NoCacheProvider" )
                .setProperty( "hibernate.cache.use_second_level_cache", "false" )
                .setProperty( "hibernate.cache.use_query_cache",    "false" )
                .setProperty( "hibernate.dialect",                  "org.hibernate.dialect.MySQLDialect" )
                .setProperty( "hibernate.show_sql",                 "true" )
                .setProperty( "hibernate.current_session_context_class", "thread" )
                .addPackage( "ru.miralab.db" )
                .addAnnotatedClass(jm.task.core.jdbc.model.User.class)
                ;
        //serviceRegistry = new ServiceRegistryBuilder().applySettings(
        //        configuration.getProperties()).buildServiceRegistry();
        //return configuration.buildSessionFactory(serviceRegistry);
        return configuration.buildSessionFactory();

    }


}
