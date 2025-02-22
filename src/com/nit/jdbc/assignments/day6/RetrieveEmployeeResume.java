package com.nit.jdbc.assignments.day6;

import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RetrieveEmployeeResume {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			PreparedStatement ps=con.prepareStatement
					("select *from Empployee_Info where empId = ?");
			
			System.out.println("==-=-==-Retrieving Employee Resume From Database===-=-=-");
			System.out.println("Enter Id to retrieve emp resume : ");
			String eid=sc.nextLine();
			
			ps.setString(1, eid);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Blob b=rs.getBlob(6);
				byte[] by=b.getBytes(1, (int)b.length());
				System.out.println("Employee Id : "+eid);
				System.out.println("Employee Name  : "+rs.getString(2));
				System.out.println("Employee Address : "+rs.getString(3));
				System.out.println("Employee Mail Id : "+rs.getString(4));
				System.out.println("Employee Phone No : "+rs.getLong(5));
				System.out.println("Enter the fPath & fName(Destination) to	store resume");
				String path=sc.nextLine();
				
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(by);
				System.out.println("Resume Retrived and available in destination...");
				fos.close();
			}
			else {
				System.err.println("Invalid Id...");
			}
			con.close();		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


/* ==-=-==-Retrieving Employee Resume From Database===-=-=-
Enter Id to retrieve emp resume : 
111
Employee Id : 111
Employee Name  : Mike
Employee Address : Hathway
Employee Mail Id : m@gamil.com
Employee Phone No : 887887788
Enter the fPath & fName(Destination) to	store resume
C:\Users\swapn\Resume_JDBC\Swapnil.pdf
Resume Retrived and available in destination...
 */
