package org.apache.tomcat.jdbc.pool.test;

import org.apache.tomcat.jdbc.pool.DataSourceHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DataSourceTest {

    public static void main(String[] args) throws Exception {
        while (true) {
            Connection connection = null;
            try {
                connection = DataSourceHolder.getDataSource().getConnection();
                PreparedStatement statement = connection.prepareStatement("");
                ResultSet resultSet = statement.executeQuery("select * from user");
                while (resultSet.next()) {
                    System.out.println("Host:" + resultSet.getString("Host") +
                            " User:" + resultSet.getString("User") +
                            " Password:" + resultSet.getString("Password"));
                }
                resultSet.close();
                statement.close();
            } finally {
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            Thread.sleep(100);
        }
    }

}
