/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class connects the program to the database to allow the program to
 * retrieve the saved data using SQL queries
 *
 * @author Ralph McDougall 17/4/2018
 */
public class DBBridge {

    // *****************************************************
    // PRIVATE FIELDS
    // *****************************************************
    
    // SQL objects allowing program to communicate with the database
    private final Connection connection;
    private PreparedStatement statement;

    // The necessary DRIVER for the databases
    private static final String DRIVER = "jdbc:ucanaccess://";

    // The file PATH details of the file
    private static final String PATH = "src/resources/";
    private String name = "";
    private static final String EXTENSION = ".accdb";

    // *****************************************************
    // CONSTRUCTOR
    // *****************************************************
    
    /**
     * The DBBridge constructor. Connects to the given database.
     *
     * @param db The name of the database to connect to
     * @throws SQLException
     */
    public DBBridge(String db) throws SQLException {
        GameLogger.logInfo("Connecting to database: " + db);
        name = db;
        connection = DriverManager.getConnection(DRIVER + PATH + db + EXTENSION);
        GameLogger.logInfo("Successfully connected to database");
    }

    // *****************************************************
    // PUBLIC METHODS
    // *****************************************************
    
    /**
     * Query the database with a given query
     *
     * @param stmt The query to pass to the database
     * @return A ResultSet of all of the data
     * @throws SQLException
     */
    public ResultSet query(String stmt) throws SQLException {
        GameLogger.logInfo("Querying database: " + stmt);
        statement = connection.prepareStatement(stmt);
        return statement.executeQuery();
    }

    /**
     * Update the database using a given update query
     *
     * @param update The query to use to update the database
     * @throws SQLException
     */
    public void update(String update) throws SQLException {
        // Update the database
        GameLogger.logInfo("Updating database: " + update);
        statement = connection.prepareStatement(update);
        statement.executeUpdate();
        statement.close();
    }

    /**
     * Convert the data returned from a query into a list of strings that
     * can be manipulated more easily
     * 
     * @param rs The result set returned by a query
     * @return 
     */
    
    public static ArrayList< ArrayList< String>> processResultSet(ResultSet rs) {
        ArrayList< ArrayList< String>> res = new ArrayList< ArrayList< String>>();

        try {
            ResultSetMetaData meta = rs.getMetaData();
            int size = meta.getColumnCount();

            // Check each line and add it to the string
            while (rs.next()) {
                ArrayList<String> line = new ArrayList<String>();
                for (int i = 1; i <= size; ++i) {
                    String value = rs.getString(i);
                    line.add(value);
                }
                // Add the line onto the final output
                res.add(line);
            }
        } catch (SQLException ex) {
            // A SQL problem occurred while parsing result set to string list
            GameLogger.logError("A SQL error occurred while converting result "
                    + "set into string list.\n" + ex);
        }

        return res;
    }

}
