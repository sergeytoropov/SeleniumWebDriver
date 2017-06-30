package ru.sergeytoropov.db;

import java.sql.*;

/**
 * @author sergeytoropov
 * @since 21.06.17
 */
public class LiteCartDatabase {

    private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_CONNECTION = "jdbc:mysql://localhost:3306/litecart";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void main(String[] argv) {
        try {
            System.out.println(getNextCustomersId());
        } catch (SQLException e) {

            System.out.println(e.getMessage());

        }
    }

    public static int getNextCustomersId() throws SQLException {
        return getNextId("select max(id) + 1 as id from lc_customers");
    }

    public static int getNextProductId() throws SQLException {
        return getNextId("select max(id) + 1 as id from lc_products_info");
    }

    public static int getNextId(String sql) throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;
        int id = 1;
        String nextIdSQL = sql;

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            ResultSet rs = statement.executeQuery(nextIdSQL);

            while (rs.next()) {
                id = Integer.parseInt(rs.getString("id"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
        return id;
    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;

        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }
}