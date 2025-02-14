package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class CustomerDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call InsertCustomer (?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Cust-Id : ");
			String cId=sc.nextLine();
			System.out.println("Enter the Cust-Name : ");
			String cName=sc.nextLine();
			System.out.println("Enter the Cust-House No. : ");
			String hNo=sc.nextLine();
			System.out.println("Enter the Cust-SName : ");
			String sName=sc.nextLine();
			System.out.println("Enter the Cust-City : ");
			String city=sc.nextLine();
			System.out.println("Enter the Cust-State : ");
			String state=sc.nextLine();
			System.out.println("Enter the Cust-Pincode : ");
			int pCode=Integer.parseInt(sc.nextLine());
			System.out.println("Enter the Cust-MailId : ");
			String mId = sc.nextLine();
			System.out.println("Enter the Cust-Phone No : ");
			long phNo=Long.parseLong(sc.nextLine());
			
			cs.setString(1, cId);
			cs.setString(2, cName);
			cs.setString(3, hNo);
			cs.setString(4, sName);
			cs.setString(5, city);
			cs.setString(6, state);
			cs.setInt(7, pCode);
			cs.setString(8, mId);
			cs.setLong(9, phNo);
			
			cs.execute();   // Procedure executed
			System.out.println("Customer Details inserted successfully...");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



/* Enter the Cust-Id : 
A111
Enter the Cust-Name : 
Mike
Enter the Cust-House No. : 
12-34/h
Enter the Cust-SName : 
AMP
Enter the Cust-City : 
Hyd
Enter the Cust-State : 
MH
Enter the Cust-Pincode : 
5000016
Enter the Cust-MailId : 
a@gmail.com
Enter the Cust-Phone No : 
878976878
Customer Details inserted successfully...


SQL> select *from custData;

CID             CNAME
--------------- ----------
A111            Mike

SQL> select *from custAddress;

CID             HNO        SNAME           CITY            STATE         PINCODE
--------------- ---------- --------------- --------------- ---------- ----------
A111            12-34/h    AMP             Hyd             MH            5000016

SQL> select *from custContact;

CID             MAILID                          PHNO
--------------- ------------------------- ----------
A111            a@gmail.com                878976878

SQL>
 
 
 
 */