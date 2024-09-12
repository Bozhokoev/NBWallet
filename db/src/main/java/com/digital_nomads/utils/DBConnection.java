package com.digital_nomads.utils;

import com.digital_nomads.config_reader.DBConfigReader;
import org.postgresql.ds.PGSimpleDataSource;

import java.sql.*;

public class DBConnection {

    private static Connection connection;
    private static Statement statement;

    private DBConnection() {
        //Singleton pattern
    }

    private static PGSimpleDataSource getBaseDataSource(String database) {
        PGSimpleDataSource pgSimpleDataSource = new PGSimpleDataSource() {{
            setServerName(DBConfigReader.getValue("server"));
            setPortNumber(Integer.parseInt(DBConfigReader.getValue("port")));
            setUser(DBConfigReader.getValue("user"));
            setDatabaseName(database);
        }};
        return pgSimpleDataSource;
    }

    public static void openConnection(String database) throws SQLException {
        if (connection == null) {
            connection = getBaseDataSource(database).getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        }
    }

    public static Connection getConnection() {
        return connection;
    }


    public static ResultSet query(String query, Object... params) throws SQLException {
        if (params.length == 0) {
            return statement.executeQuery(query);
        } else {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            return preparedStatement.executeQuery();
        }
    }

    public static boolean insertInto(String tableName, String[] columns, Object[] values) throws SQLException {
        if (columns.length != values.length) {
            throw new IllegalArgumentException("Number of columns and values must match.");
        }

        StringBuilder columnNames = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (int i = 0; i < columns.length; i++) {
            columnNames.append(columns[i]);
            placeholders.append("?");
            if (i < columns.length - 1) {
                columnNames.append(", ");
                placeholders.append(", ");
            }
        }

        String query = String.format("INSERT INTO %s (%s) VALUES (%s);", tableName, columnNames, placeholders);

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            for (int i = 0; i < values.length; i++) {
                preparedStatement.setObject(i + 1, values[i]);
            }
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;
        }
    }

    public static void close() {
        try {
            if (statement != null) {
                statement.close();
                statement = null;
            }
            if (connection != null) {
                connection.close();
                connection = null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
