package com.nit.jdbcClasswork.transaction;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ParameterMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class TestMetaData {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			
			
			System.out.println("+=+=+=+ DatabaseMetaData +=+=+=+=");
			
			DatabaseMetaData dmd=con.getMetaData();
			System.out.println("Product Version : "+dmd.getDatabaseProductVersion());
			System.out.println("Product Name : "+dmd.getDatabaseProductName());
			
			
			System.out.println("=======================");
			
			System.out.println("+=+=+=+ ParameterMetaData +=+=+=+=");
			PreparedStatement ps=con.prepareStatement
						("Update BookDetails set bprice=?, bqty=?, bname=? where bcode=?");
			
			ParameterMetaData pmd=ps.getParameterMetaData();
			System.out.println("Para-Count : "+pmd.getParameterCount());
			
			
			System.out.println("=======================");

			
			System.out.println("+=+=+=+ ResultSetMetaData +=+=+=+=");
			PreparedStatement ps2=con.prepareStatement
					("select eid,bsal,hra,da,totSal from empSalary");
			ResultSet rs=ps2.executeQuery();
			ResultSetMetaData rsmd=rs.getMetaData();
			System.out.println("Column-Count : "+rsmd.getColumnCount());
			System.out.println("2nd Column Name : "+rsmd.getColumnName(2));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}	

/* +=+=+=+ DatabaseMetaData +=+=+=+=
Product Version : Oracle Database 19c Enterprise Edition Release 19.0.0.0.0 - Production Version 19.3.0.0.0
Product Name : Oracle
=======================
+=+=+=+ ParameterMetaData +=+=+=+=
Para-Count : 4
=======================
+=+=+=+ ResultSetMetaData +=+=+=+=
Column-Count : 5
2nd Column Name : BSAL
 */
