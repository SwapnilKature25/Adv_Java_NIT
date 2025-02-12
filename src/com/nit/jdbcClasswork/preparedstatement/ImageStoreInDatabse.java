package com.nit.jdbcClasswork.preparedstatement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ImageStoreInDatabse {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			PreparedStatement ps=con.prepareStatement("insert into StreamTab values(?,?,?");
			
			System.out.println("Enter the User-Id to store image :");
			String id=sc.nextLine();
			System.out.println("Enter the User-Name :");
			String name=sc.nextLine();
			System.out.println("Enter the path(Source) User-Image :");
			String path=sc.nextLine();
			
			File f=new File(path);
			
			if(f.exists())
			{
				FileInputStream fis=new FileInputStream(path);
				ps.setString(1, id);
				ps.setString(2, name);
				ps.setBinaryStream(3, fis,f.length());
				int k=ps.executeUpdate();
				if(k>0)
				{
					System.out.println("Image Stored successfully...");
				}
			}			
			else {
				System.out.println("Invalid file Path or fName : ");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
