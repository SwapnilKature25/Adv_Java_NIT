package com.nit.jdbcClasswork;

import java.sql.*;
import java.sql.DriverManager;

public class JDBCDemo {
	public static void main(String[] args) {
		
		try
		{
			 Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","Swapnil", "swap25");
			System.out.println("connection sucess");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from demo");
			while(rs.next())
			{
				System.out.println("enterded");
				System.out.println(rs.getInt(1) + "\t"+rs.getString(2) + "\t");
			}
			
			
			con.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
