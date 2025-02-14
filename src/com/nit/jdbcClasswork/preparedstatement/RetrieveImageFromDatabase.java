package com.nit.jdbcClasswork.preparedstatement;

import java.io.FileOutputStream;
import java.sql.*;
import java.sql.DriverManager;
import java.util.Scanner;

public class RetrieveImageFromDatabase {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			PreparedStatement ps1=con.prepareStatement("Select *from StreamTab where id=?");
			
			System.out.println("Enter the User-Id to retrive name and Image");
			String id=sc.nextLine();
			
			ps1.setString(1, id);			
			ResultSet rs=ps1.executeQuery();
			if(rs.next())
			{
				Blob b=rs.getBlob(3);
				byte by[] = b.getBytes(1, (int)b.length());
				System.out.println("User-Id : "+id);
				System.out.println("User-Name : "+rs.getString(2));
				System.out.println("Enter the fPath & fName(Destination) to	store image");
				String path=sc.nextLine();
				
				FileOutputStream fos=new FileOutputStream(path);
				fos.write(by);
				System.out.println("Image Retrived and available in destination...");
				fos.close();
			}
			else {
				System.out.println("Invalid Id!!");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
