package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class RetrieveEmployeeDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			CallableStatement cs=con.prepareCall
					("{call RetrieveEmployee (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter Employee-Id to retrieve details : ");
			String id=sc.nextLine();
			
			cs.setString(1, id);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.VARCHAR);
			cs.registerOutParameter(8, Types.INTEGER);
			cs.registerOutParameter(9, Types.VARCHAR);
			cs.registerOutParameter(10, Types.BIGINT);
			cs.registerOutParameter(11, Types.INTEGER);
			cs.registerOutParameter(12, Types.INTEGER);
			cs.registerOutParameter(13, Types.INTEGER);
			cs.registerOutParameter(14, Types.INTEGER);
			
			cs.execute();
			
			System.out.println("=============Employee Details=============");
			System.out.println("Employee Id : "+id);
			System.out.println("Employee Name : "+cs.getString(2));
			System.out.println("Employee Designation : "+cs.getString(3));
			System.out.println("Employee HNo : "+cs.getString(4));
			System.out.println("Employee Street Name : "+cs.getString(5));
			System.out.println("Employee City : "+cs.getString(6));
			System.out.println("Employee State : "+cs.getString(7));
			System.out.println("Employee Pincode : "+cs.getInt(8));
			System.out.println("Employee MailId : "+cs.getString(9));
			System.out.println("Employee Phone no : "+cs.getLong(10));
			System.out.println("Employee Basic Salary : "+cs.getFloat(11));
			System.out.println("Employee House Rental Allowance : "+cs.getFloat(12));
			System.out.println("Employee Daily allowance : "+cs.getFloat(13));
			System.out.println("Employee Total Salary : "+cs.getFloat(14));
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
