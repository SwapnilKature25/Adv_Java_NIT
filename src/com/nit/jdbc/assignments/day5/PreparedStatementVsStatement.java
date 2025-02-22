package com.nit.jdbc.assignments.day5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class PreparedStatementVsStatement {
    // Database connection details
    static final String URL = "jdbc:oracle:thin:@localhost:1522:orcl";
    static final String USERNAME = "swapnil";
    static final String PASSWORD = "swap25";
    static final int RECORDS = 1000; // Number of records to insert

    public static void main(String[] args) {
        try {
            // Load JDBC Driver
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            // Create table if not exists
            createTable(con);

            // Measure time for Statement
            long statementTime = insertUsingStatement(con);
            System.out.println("Time taken using Statement: " + statementTime + " ms");

            // Measure time for PreparedStatement
            long preparedStatementTime = insertUsingPreparedStatement(con);
            System.out.println("Time taken using PreparedStatement: " + preparedStatementTime + " ms");

            // Compare results
            System.out.println("PreparedStatement is " +
                    ((double) statementTime / preparedStatementTime) + " times faster than Statement.");

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Create table
    private static void createTable(Connection con) throws Exception {
        Statement st = con.createStatement();
        String sql = "CREATE TABLE test_perf (id NUMBER PRIMARY KEY, name VARCHAR2(50))";
        try {
            st.executeUpdate("DROP TABLE test_perf"); // Drop if exists
        } catch (Exception ignored) {}
        st.executeUpdate(sql); // Create table
        st.close();
    }

    // Insert using Statement
    private static long insertUsingStatement(Connection con) throws Exception {
        Statement st = con.createStatement();
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= RECORDS; i++) {
            String sql = "INSERT INTO test_perf VALUES (" + i + ", 'User" + i + "')";
            st.executeUpdate(sql);
        }

        long endTime = System.currentTimeMillis();
        st.close();
        return endTime - startTime;
    }

    // Insert using PreparedStatement
    private static long insertUsingPreparedStatement(Connection con) throws Exception {
        String sql = "INSERT INTO test_perf VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(sql);
        long startTime = System.currentTimeMillis();

        for (int i = 1; i <= RECORDS; i++) {
            ps.setInt(1, i);
            ps.setString(2, "User" + i);
            ps.executeUpdate();
        }

        long endTime = System.currentTimeMillis();
        ps.close();
        return endTime - startTime;
    }
}
