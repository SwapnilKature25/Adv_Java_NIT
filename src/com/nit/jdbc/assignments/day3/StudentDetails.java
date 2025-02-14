package com.nit.jdbc.assignments.day3;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class StudentDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			
			CallableStatement cs=con.prepareCall
					("{call InsertStudeDetails (?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("=-=-=-=-=-= Adding Student Details -=-=-=-=-=-=-");
			System.out.print("Enter the  Student- Id : ");
			String sId=sc.nextLine();
			System.out.print("Enter the  Student- Roll No : ");
			int rno=Integer.parseInt(sc.nextLine());
			System.out.print("Enter the  Student- Name : ");
			String name=sc.nextLine();
			System.out.print("Enter the  Student- Branch : ");
			String branch=sc.nextLine();
			System.out.print("Enter the  Student- House no. : ");
			String hno=sc.nextLine();
			System.out.print("Enter the  Student- City : ");
			String city=sc.nextLine();
			System.out.print("Enter the  Student- Pincode : ");
			int pcode=Integer.parseInt(sc.nextLine());
			System.out.print("Enter the  Student- Mail_ID : ");
			String mid=sc.nextLine();
			System.out.print("Enter the  Student- Phone no. : ");
			long phno=Long.parseLong(sc.nextLine());
			
			cs.setString(1, sId);
			cs.setInt(2, rno);
			cs.setString(3, name);
			cs.setString(4, branch);
			cs.setString(5, hno);
			cs.setString(6, city);
			cs.setInt(7, pcode);
			cs.setString(8, mid);
			cs.setLong(9, phno);
			
			cs.execute();
			
			System.out.println("Student Details inserted successfully...");
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}



/* =-=-=-=-=-= Adding Student Details -=-=-=-=-=-=-
Enter the  Student- Id : A121
Enter the  Student- Roll No : 111
Enter the  Student- Name : Swap
Enter the  Student- Branch : CS
Enter the  Student- House no. : 12-34
Enter the  Student- City : Jalna
Enter the  Student- Pincode : 431202
Enter the  Student- Mail_ID : swap@gmail.com
Enter the  Student- Phone no. : 7867876567
Student Details inserted successfully...





SQL> Select *from studData;

ID             ROLLNO NAME            BRANCH
---------- ---------- --------------- ----------
A121              111 Swap            CS

SQL> Select *from studAddress;

ID         HNO             CITY            PCODE
---------- --------------- ---------- ----------
A121       12-34           Jalna          431202

SQL> Select *from studContact;

ID         MID                        PHNO
---------- -------------------- ----------
A121       swap@gmail.com       7867876567




 */
