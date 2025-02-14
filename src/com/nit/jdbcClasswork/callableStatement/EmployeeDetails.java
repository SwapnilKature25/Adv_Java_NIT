package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class EmployeeDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call InsertEmployee (?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
		
			System.out.println("Enter the Employee-Id : ");
			String eid=sc.nextLine();
			System.out.println("Enter the Employee-Name : ");
			String eName=sc.nextLine();
			System.out.println("Enter the Employee-Designation : ");
			String eDesg=sc.nextLine();
			System.out.println("Enter the Employee-House No : ");
			String hno=sc.nextLine();
			System.out.println("Enter the Employee-SName : ");
			String sName=sc.nextLine();
			System.out.println("Enter the Employee-city : ");
			String city=sc.nextLine();
			System.out.println("Enter the Employee-State : ");
			String state=sc.nextLine();
			System.out.println("Enter the Employee-PinCode : ");
			int pCode=Integer.parseInt(sc.nextLine());
			System.out.println("Enter the Employee-Mail Id : ");
			String mId=sc.nextLine();
			System.out.println("Enter the Employee-Phone no : ");
			long phno=Long.parseLong(sc.nextLine());
			System.out.println("Enter the Employee-Basic Salary : ");
			float bSal=Float.parseFloat(sc.nextLine());
			System.out.println("Enter the Employee-Basic hra : ");
			long hra=Long.parseLong(sc.nextLine());
			System.out.println("Enter the Employee-Basic da : ");
			long da=Long.parseLong(sc.nextLine());
			System.out.println("Enter the Employee-Basic hra : ");
			long totSal=Long.parseLong(sc.nextLine());
			
			
			cs.setString(1, eid);
			cs.setString(2, eName );
			cs.setString(3, eDesg );
			cs.setString(4, hno);
			cs.setString(5,sName);
			cs.setString(6, city);
			cs.setString(7, state);
			cs.setInt(8, pCode);
			cs.setString(9, mId);
			cs.setLong(10, phno);
			cs.setFloat(11, bSal);
			cs.setFloat(12, hra);
			cs.setFloat(13, da);
			cs.setFloat(14, totSal);
			
			cs.execute();
			
			System.out.println("Employee Details inserted successfully....");
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* 
SQL> select *from EmpData;

EID        ENAME           EDESG
---------- --------------- ---------------
121        Swap            JD

SQL> select *from EmpAddress;

EID        HNO             SNAME           CITY       STATE         PINCODE
---------- --------------- --------------- ---------- ---------- ----------
121        12-23D          AMP             Pune       MH             500016

SQL> select *from EmpContact;

EID        MID                   PHNO
---------- --------------- ----------
121        swap@gmail.com  8978976567

SQL> select *from EmpSalary;

EID              BSAL        HRA         DA     TOTSAL
---------- ---------- ---------- ---------- ----------
121             50000       1000       2000      53000

SQL> */

