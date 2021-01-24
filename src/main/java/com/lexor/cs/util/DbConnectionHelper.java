package com.lexor.cs.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Singleton;

@Singleton
public class DbConnectionHelper {

    ConfigHelper config_helper;

    private static Connection connection = null;
    private String DRIVER = "org.postgresql.Driver";

    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            config_helper = new ConfigHelper();
            try {
                Class.forName(DRIVER);
            } catch (ClassNotFoundException err) {
                err.printStackTrace();
            }
            try {
                String url = this.config_helper.getProperties().getProperty("com.lexor.cs.database.url");
                Properties props = new Properties();
                props.setProperty("user", this.config_helper.getProperties().getProperty("com.lexor.cs.database.user"));
                props.setProperty("password", this.config_helper.getProperties().getProperty("com.lexor.cs.database.password"));
                props.setProperty("ssl", this.config_helper.getProperties().getProperty("com.lexor.cs.database.ssl"));
                connection = DriverManager.getConnection(url, props);
            } catch (SQLException ex) {
                Logger.getLogger(DbConnectionHelper.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return connection;
    }
}
