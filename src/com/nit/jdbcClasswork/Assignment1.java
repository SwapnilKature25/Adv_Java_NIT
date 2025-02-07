package com.nit.jdbcClasswork;

import java.sql.*;

public class Assignment1 {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil","swap25");
			Statement stmt=con.createStatement();
			ResultSet rs=stmt.executeQuery("select * from Product");
			System.out.println("=========Product Details=========== ");
			while(rs.next())
			{
				System.out.println
						(rs.getInt(1)+"\t"
				  		 +rs.getString(2)+"\t"
						 +rs.getFloat(3)+"\t"
						 +rs.getInt(4));
			}
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
