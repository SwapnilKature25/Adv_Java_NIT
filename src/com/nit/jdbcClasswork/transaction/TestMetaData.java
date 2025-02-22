package com.nit.jdbcClasswork.transaction;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;

public class TestMetaData {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			
			System.out.println("+=+=+=+ Database MetaData +=+=+=+=");
			DatabaseMetaData dmd=con.getMetaData();
			System.out.println("Product Version : "+dmd.getDatabaseProductVersion());

			System.out.println("Product Name : "+dmd.getDatabaseProductName());
			
			
			
			
			System.out.println("+=+=+=+ Parameter MetaData +=+=+=+=");
			PreparedStatement ps=con.prepareStatement
						("Update BookDetails set price=?, qty=? where code=?");
			
			ParameterMetaData pmd=ps.getParameterMetaData();
			
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}	
