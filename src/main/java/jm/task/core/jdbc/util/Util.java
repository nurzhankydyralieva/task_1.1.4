package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static Util instance;

    public static Util getInstance() {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/task_1_1_4?autoReconnect=true&useSSL=false";
        String userName = "bazilio";
        String password = "root";
        String driver = "com.mysql.cj.jdbc.Driver";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, userName, password);
            return connection;
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/task_1_1_4?autoReconnect=true&useSSL=false");
                settings.put(Environment.USER, "bazilio");
                settings.put(Environment.PASS, "root");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");

                configuration.setProperties(settings);
                configuration.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();
                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
                System.out.println("Connected");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }
}
