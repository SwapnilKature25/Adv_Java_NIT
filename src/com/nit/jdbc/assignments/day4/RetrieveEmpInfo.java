package com.nit.jdbc.assignments.day4;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class RetrieveEmpInfo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call ?:= RetrieveEmpInfo (?)}");
			
			System.out.println("Enter Employee-Id to retrieve emp_Info ");
			int id=sc.nextInt();
			
			cs.setInt(2, id);
			cs.registerOutParameter(1, Types.VARCHAR);
			
			cs.execute();
			System.out.println("Emp Id : "+id);
			System.out.println("Emp Name : "+cs.getString(1));
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
}

/* Enter Employee-Id to retrieve emp_Info 
112
Emp Id : 112
Emp Name : Jane */
