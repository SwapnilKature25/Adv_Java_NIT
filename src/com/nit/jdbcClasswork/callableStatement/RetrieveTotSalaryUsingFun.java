package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class RetrieveTotSalaryUsingFun {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call ?:= RetrieveTotSalary (?)}");
			
			System.out.println("Enter the EMployee-Id to retrieve total salary : ");
			String eid=sc.nextLine();
			
			cs.setString(2, eid);
			cs.registerOutParameter(1, Types.BIGINT);
			
			cs.execute();
			System.out.println("Employee Id :"+eid);
			System.out.println("Employee Total Salary :"+cs.getLong(1));
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* Enter the EMployee-Id to retrieve total salary : 
121
Employee Id :121
Employee Total Salary :53000 */
