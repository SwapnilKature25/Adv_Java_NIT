package com.nit.jdbc.assignments.day4;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class CallableStatementExample {

	public static int callFunction() throws ClassNotFoundException, SQLException
	{
		Connection con=connect();
		CallableStatement cs=con.prepareCall
				("{call ?:= RetrieveTotNoOfEmp()}");
		
		cs.registerOutParameter(1, Types.INTEGER);
		cs.execute();
		
		int totEmp=cs.getInt(1);
		con.close();
		cs.close();
		return totEmp;	
		
	}
	
	public static Connection connect() throws SQLException, ClassNotFoundException
	{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con=DriverManager.getConnection
				("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");	
		return con;
	}
	
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			
			int noOfEmp=callFunction();
			System.out.println("Total No. Of Employees are : "+noOfEmp);			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

// Total No. Of Employees are : 3