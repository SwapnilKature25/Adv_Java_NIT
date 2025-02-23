package com.nit.jdbc.assignments.day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class ResultSetMetaDataDemo {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			
			System.out.println("====== ResultSetMetaData ========");
			
			PreparedStatement ps=con.prepareStatement
					("select * from emp_Info");
			
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData rmd=rs.getMetaData();
			int columnCount = rmd.getColumnCount();
			
			
			System.out.println("No Of Columns : "+rmd.getColumnCount());
			for(int i=1; i<=columnCount; i++)
			{
				System.out.println("Column "+i+" : "+rmd.getColumnName(i));				
			}

			for(int i=1; i<=columnCount; i++)
			{
				System.out.println("Column "+i+" Datatype : "+rmd.getColumnName(i));				
			}	
			
			PreparedStatement ps2=con.prepareStatement
					("insert into emp_Info values(?,?,?,?,?)");
			
			ps2.setInt(1, 444);
			ps2.setString(2, "AAA");
			ps2.setString(3, "Hyd");
			ps2.setDouble(4, 40000D);
			ps2.setLong(5, 876778877);
			
			int k=ps2.executeUpdate();
			if(k>0)
			{
				System.out.println("EMployee details inserted successfully..");
			}
			con.close();		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
/*
 ====== ResultSetMetaData ========
No Of Columns : 5
Column 1 : EMPID
Column 2 : EMPNAME
Column 3 : EMPADDRESS
Column 4 : EMPSALARY
Column 5 : EMPPHNO
Column 1 Datatype : EMPID
Column 2 Datatype : EMPNAME
Column 3 Datatype : EMPADDRESS
Column 4 Datatype : EMPSALARY
Column 5 Datatype : EMPPHNO

 
 */
