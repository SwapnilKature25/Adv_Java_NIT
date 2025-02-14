package com.nit.jdbc.assignments.day3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class RetrieveStudentDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			
			CallableStatement cs=con.prepareCall
					("call RetrieveStudDetais (?)");
			
			System.out.println("=-=-=-=-=-= Retriving Student Details -=-=-=-=-=-=-");
			System.out.println("Enter Student-Id to retrive Details : ");
			String id=sc.nextLine();
			
			cs.setString(1, id);
			cs.execute();
			
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
