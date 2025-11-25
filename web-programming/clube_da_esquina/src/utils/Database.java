package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
  private static Connection connection;
  private static final String URL = "jdbc:mysql://localhost:3306/clube_esquina";
  private static final String USER = "root";
  private static final String PASS = "";

  private Database() {
  }

  public static Connection getConnection() {
    if (connection != null) {
      return connection;
    }

    try {
      connection = DriverManager.getConnection(URL, USER, PASS);

      return connection;
    } catch (SQLException e) {
      throw new RuntimeException("Error connecting to the database", e);
    }
  }

  public static void closeConnection() {
    if (connection == null)
      return;

    try {
      if (!connection.isClosed()) {
        connection.close();
      }
    } catch (SQLException ignored) {
    } finally {
      connection = null;
    }
  }
}
