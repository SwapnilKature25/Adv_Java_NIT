package com.nit.jdbc.assignments.day6;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StoreEmployeeResume {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			PreparedStatement ps=con.prepareStatement
					("Insert into Empployee_Info values (?,?,?,?,?,?)");
			
			System.out.println("Enter the Employee Id : ");
			String eid=sc.nextLine();
			System.out.println("Enter the Employee Name : ");
			String ename=sc.nextLine();
			System.out.println("Enter the Employee Address : ");
			String eadd=sc.nextLine();
			System.out.println("Enter the Employee Mail-Id : ");
			String emid=sc.nextLine();
			System.out.println("Enter the Employee Phone no : ");
			long pno=Long.parseLong(sc.nextLine());
			System.out.println("Enter the Path of Resume to Store : ");
			String rPath=sc.nextLine();
			
			File f=new File(rPath);
			
			if(f.exists())
			{
				FileInputStream fis=new FileInputStream(rPath);
				ps.setString(1, eid);
				ps.setString(2, ename);
				ps.setString(3, eadd);
				ps.setString(4, emid);
				ps.setLong(5, pno);
				ps.setBinaryStream(6, fis,f.length());
				
				int k=ps.executeUpdate();
				if(k>0)
				{
					System.out.println("Employee Details Inserted Successfully...");
				}
				//"C:\Users\swapn\SwapnilResume.pdf.pdf"
			}
			else {
				System.out.println("Invalid file Path");					
			}			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* Enter the Employee Id : 
111
Enter the Employee Name : 
Mike
Enter the Employee Address : 
Hathway
Enter the Employee Mail-Id : 
m@gamil.com
Enter the Employee Phone no : 
887887788
Enter the Path of Resume to Store : 
C:\\Users\\swapn\\SwapnilResume.pdf
Employee Details Inserted Successfully...
 */
