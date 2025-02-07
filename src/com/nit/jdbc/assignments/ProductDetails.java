package com.nit.jdbc.assignments;

import java.sql.*;
import java.util.Scanner;

public class ProductDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		
		try(sc;)
		{
			System.out.print("Enter Product-Code : ");
			int pCode=Integer.parseInt(sc.nextLine());
			System.out.print("Enter Product-Name : ");
			String pName=sc.nextLine();
			System.out.print("Enter Product-Price : ");
			float pPrice=Float.parseFloat(sc.nextLine());
			System.out.print("Enter Product-Quantity : ");
			int pQty= Integer.parseInt(sc.nextLine());
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			Statement stmt=con.createStatement();
			int k=stmt.executeUpdate
			("insert into ProductDetails values("+pCode+",'"+pName+"',"+pPrice+","+pQty+")");
			
			if(k > 0)
			{
				System.out.println("Product Details inserted successfully");
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}		
