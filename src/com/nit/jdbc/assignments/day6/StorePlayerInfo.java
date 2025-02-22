package com.nit.jdbc.assignments.day6;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class StorePlayerInfo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			PreparedStatement ps=con.prepareStatement("Insert into Player_Info values(?,?,?,?)");
			
			System.out.println("Enter Player Id");
			int id=Integer.parseInt(sc.nextLine());
			System.out.println("Enter Player Name");
			String name=sc.nextLine();
			System.out.println("Enter Player Photo file path");
			String path=sc.nextLine();
			System.out.println("Enter Player Date Of Birth (DOB)");
			String dob=sc.nextLine();
			
			File f=new File(path);
			if(f.exists())
			{
				FileInputStream fis=new FileInputStream(path);
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setBinaryStream(3, fis, f.length());
				ps.setString(4, dob);
				
				int k=ps.executeUpdate();
				if(k>0)
				{
					System.out.println("Player image innserted successfully....");
				}
				fis.close();
			}
			else {
				System.out.println("Invalid file..");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* Enter Player Id
45
Enter Player Name
Rohirr
Enter Player Photo file path
C:\Users\swapn\Resume_JDBC\nature.jpg
Enter Player Date Of Birth (DOB)
14-02-1987
Player image innserted successfully.... */
