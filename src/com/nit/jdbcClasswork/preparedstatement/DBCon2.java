package com.nit.jdbcClasswork.preparedstatement;

import java.sql.*;

public class DBCon2 {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			PreparedStatement ps=con.prepareStatement("select * from BookDetails71");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
