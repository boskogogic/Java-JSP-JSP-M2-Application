package net.etfbl.ip.dao;

import java.sql.*;
import java.util.*;
import net.etfbl.ip.helper.PropertiesFile;

public class ConnectionPool {

  public static ConnectionPool getConnectionPool() {
    return connectionPool;
  }

  private static ConnectionPool connectionPool;

  /*
  NAPOMENA: Nije radio tj. nije citao resource boundle kako treba.. Morao ici preko properties fajla
  Takodje NAPOMENA ZA CONNECTION STRING tek kada sam encrypted postavio na false (encrypted=false) tada mi je proradila konekcija sa bazom!
  
  */
  static {
    try {
        System.out.println("LIBRARY PATH: " + System.getProperty("java.library.path"));
        System.out.println("Try to load dll manually");
        System.load("C:\\Program Files\\Microsoft JDBC DRIVER 12.8 for SQL Server\\sqljdbc_12.8\\enu\\auth\\x64\\mssql-jdbc_auth-12.8.1.x64.dll");
    } catch (UnsatisfiedLinkError e) {
        System.err.println("Failed to load DLL: " + e.getMessage());
    }
//    ResourceBundle bundle =
//      PropertyResourceBundle.getBundle("net.etfbl.ip.dao.ConnectionPool");
     
//     ResourceBundle bundleTest =
//      PropertyResourceBundle.getBundle("src\\main\\resources\\SimpleProperties.properties");
//    ResourceBundle bundle =
//      PropertyResourceBundle.getBundle("main.resources.ConnectionPool.properties");
//    String jdbcURL = bundle.getString("jdbcURL");
//    String username = bundle.getString("username");
//    String password = bundle.getString("password");
//    String driver = bundle.getString("driver");
      
    PropertiesFile reader = new PropertiesFile("C:\\programiranje\\backend\\projekatJSPM2\\src\\main\\resources\\ConnectionPool.properties");
    String jdbcURL = reader.get("jdbcURL");
    String username = reader.get("username");
    String password = reader.get("password");
    String driver = reader.get("driver");
    System.out.println("JDBC URL is " + jdbcURL);
    System.out.println("username is " + username);
    System.out.println("password is " + password);
    System.out.println("driver is " + driver);
    int preconnectCount = 0;
    int maxIdleConnections = 10;
    int maxConnections = 10;
    try {
        Class.forName(driver);
      preconnectCount = Integer.parseInt(
        reader.get("preconnectCount"));
      maxIdleConnections = Integer.parseInt(
         reader.get("maxIdleConnections"));
      maxConnections = Integer.parseInt(
        reader.get("maxConnections"));
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    try {
      connectionPool = new ConnectionPool(
        jdbcURL, username, password,
        preconnectCount, maxIdleConnections,
        maxConnections);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  protected ConnectionPool(String aJdbcURL, String aUsername,
    String aPassword, int aPreconnectCount,
    int aMaxIdleConnections,
    int aMaxConnections)
    throws ClassNotFoundException, SQLException {

    freeConnections = new Vector<Connection>();
    usedConnections = new Vector<Connection>();
    jdbcURL = aJdbcURL;
    username = aUsername;
    password = aPassword;
    preconnectCount = aPreconnectCount;
    maxIdleConnections = aMaxIdleConnections;
    maxConnections = aMaxConnections;

    for (int i = 0; i < preconnectCount; i++) {
      Connection conn = DriverManager.getConnection(
        jdbcURL, username, password);
      conn.setAutoCommit(true);
      freeConnections.addElement(conn);
    }
    connectCount = preconnectCount;
  }

  public synchronized Connection checkOut()
    throws SQLException {

    Connection conn = null;
    if (freeConnections.size() > 0) {
      conn = (Connection)freeConnections.elementAt(0);
      freeConnections.removeElementAt(0);
      usedConnections.addElement(conn);
    } else {
      if (connectCount < maxConnections) {
        conn = DriverManager.getConnection(
          jdbcURL, username, password);
        usedConnections.addElement(conn);
        connectCount++;
      } else {
        try {
          wait();
          conn = (Connection)freeConnections.elementAt(0);
          freeConnections.removeElementAt(0);
          usedConnections.addElement(conn);
        } catch (InterruptedException ex) {
          ex.printStackTrace();
        }
      }
    }
    return conn;
  }

  public synchronized void checkIn(Connection aConn) {
    if (aConn ==  null)
      return;
    if (usedConnections.removeElement(aConn)) {
      freeConnections.addElement(aConn);
      while (freeConnections.size() > maxIdleConnections) {
        int lastOne = freeConnections.size() - 1;
        Connection conn = (Connection)
          freeConnections.elementAt(lastOne);
        try { conn.close(); } catch (SQLException ex) { }
        freeConnections.removeElementAt(lastOne);
      }
      notify();
    }
  }

  private String jdbcURL;
  private String username;
  private String password;
  private int preconnectCount;
  private int connectCount;
  private int maxIdleConnections;
  private int maxConnections;
  private Vector<Connection> usedConnections;
  private Vector<Connection> freeConnections;

}
