package com.nit.jdbc.assignments.day6;

import java.io.FileOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class RetrievePlayerInfo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			
			PreparedStatement ps=con.prepareStatement
					("select *from Player_Info where pid = ?");

			System.out.println("==-=-==-Retrieving Player Image From Database===-=-=-");
			System.out.println("Enter player id to retrieve the Image : ");
			int id=Integer.parseInt(sc.nextLine());
			
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			if(rs.next())
			{
				Blob b=rs.getBlob(3);
				byte[] by=b.getBytes(1, (int)b.length());
				System.out.println("Player Id : "+id);
				System.out.println("Player Name : "+rs.getString(2));
				System.out.println("Player DOB : "+rs.getString(4));
				System.out.println("Enter the file path where you want to store Player image : ");
				String path=sc.nextLine();
				
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(by);
				System.out.println("Player image successfully stored...");
				
			}
			else {
				System.out.println("Invalid id!!!");
			}		
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}	

/* ==-=-==-Retrieving Player Image From Database===-=-=-
Enter player id to retrieve the Image : 
45
Player Id : 45
Player Name : Rohirr
Player DOB : 14-02-1987
Enter the file path where you want to store Player image : 
C:\Users\swapn\Resume_JDBC\nature.jpg
Player image successfully stored...
*/