package com.nit.jdbc.assignments.day7;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;

public class DataBaseMetaDataDemo {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			
			System.out.println("MetaData about Database");
			DatabaseMetaData dmd=con.getMetaData();
			System.out.println("Database Product Name : "+dmd.getDatabaseProductName());
			System.out.println("Database Product Version : "+dmd.getDatabaseProductVersion());
			System.out.println("Database Driver Name : "+dmd.getDriverName());
			System.out.println("Database Maximum column Name : "+dmd.getMaxColumnsInTable());
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
