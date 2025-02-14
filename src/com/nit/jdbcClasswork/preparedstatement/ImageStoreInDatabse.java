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
			PreparedStatement ps=con.prepareStatement("insert into StreamTab values(?,?,?)");
			
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




/* Enter the User-Id to store image :
111
Enter the User-Name :
Swap
Enter the path(Source) User-Image :
C:\\Users\\swapn\\Pictures\\project final\\New folder\\4th sem\\5th Sem\\traja.mp4
Image Stored successfully...


SQL> select *from StreamTab;

ID         NAME
---------- ----------
MFILE
--------------------------------------------------------------------------------
111        Swap
00000018667479706D703432000000006D70343269736F6D000000186265616D0100000001000000
000000000200000000001CFD6D6F6F760000006C6D7668640000000000000000000000000000AC44

*/