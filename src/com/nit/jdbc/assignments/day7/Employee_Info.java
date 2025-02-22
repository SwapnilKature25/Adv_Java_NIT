package com.nit.jdbc.assignments.day7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Employee_Info {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
						
			Statement stmt=con.createStatement();

			
			stmt.addBatch("delete employee_info where empsalary=(select min(empsalary) from employee_info) ");
			
			
			System.out.println("-=-=-=-Insert Employee Details =-=-=-=");
			System.out.println("Enter Employee-Id : ");
			int id=Integer.parseInt(sc.nextLine());
			System.out.println("Enter Employee-Name : ");
			String name=sc.nextLine();
			System.out.println("Enter Employee-Salary : ");
			double sal=Double.parseDouble(sc.nextLine());
			System.out.println("Enter Employee-Address : ");
			String addr=sc.nextLine();
			System.out.println("Enter Employee-Mail Id : ");
			String mid=sc.nextLine();
			System.out.println("Enter Employee-Phone No : ");
			long phno=Long.parseLong(sc.nextLine());
			
			stmt.addBatch("Insert into Employee_Info values("+id+",'"+name+"',"+sal+",'"+addr+"','"+mid+"', "+phno+")");
			
			
			System.out.println("-=-=-=-Update Employee Details =-=-=-=");
			System.out.println("Enter id to update employee details : ");
			int eid=Integer.parseInt(sc.nextLine());
			System.out.println("Enter updated salary of employee-salary :  ");
			double salary=Double.parseDouble(sc.nextLine());
			System.out.println("Enter the new Phone no : ");
			long pno=Long.parseLong(sc.nextLine());
			
			stmt.addBatch("Update Employee_Info set empsalary = "+salary+", empphno = "+pno+" where empid = "+eid+"");
			
			
			
			System.out.println("-=-=-=-Delete Employee Details =-=-=-=");
			
			
			int k[]= stmt.executeBatch();
			
			for(int i: k)
			{
				System.out.println("Query Executed : "+i);
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
