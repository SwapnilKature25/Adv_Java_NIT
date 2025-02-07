package com.nit.jdbc.assignments;

import java.sql.*;
import java.util.Scanner;

public class Product71 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			Statement stmt=con.createStatement();
			
			System.out.println("Enter the Product-Code to perform Update/Delete Operations : ");
			int code=sc.nextInt();
			
			ResultSet rs=stmt.executeQuery("select * from Product71 where pCode="+code+"");
			if(rs.next())
			{
				System.out.println("=========Choice=========");
				System.out.println("\t1. Update Operation ");
				System.out.println("\t2. Delete Operation ");
				System.out.print("Enter your Choice : ");
				int choice=sc.nextInt();
				
				switch(choice)
				{
					case 1: 
						System.out.println("====Perform Update Operation====");
						System.out.println("Old Product Price : "+rs.getFloat(3));
						System.out.println("Enter new Product price :");
						Float newPrice=sc.nextFloat();
						System.out.println("Existing Product qty:"+rs.getInt(4));
						System.out.println("Enter the new Book Qty :");
						int newQty=sc.nextInt();
						
						int k=stmt.executeUpdate("update Product71 set pPrice = "+newPrice+",pQty = "+newQty+" where pCode="+code+"");
						
						if(k>0)
						{
							System.out.println("Product71 details updated successfully");
						}
						break;
					case 2:
						System.out.println("====Perform Delete Operation====");
						int k2=stmt.executeUpdate("Delete from Product71 where pCode="+code+"");
						if(k2>0)
						{
							System.out.println("Product71 details deleted successfully");							
						}
						break;
					default:
						System.out.println("Invalid Choice");
				}
			}
			else {
				System.out.println("Invalid Product-Code");
			}
			
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
