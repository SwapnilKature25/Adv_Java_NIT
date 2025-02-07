package com.nit.jdbc.assignments;

import java.sql.*;
import java.util.Scanner;

public class InsertBookDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			System.out.print("Enter the Book-Code : ");
			int bcode = Integer.parseInt(sc.nextLine());
			System.out.print("Enter the Book-Name : ");
			String bName=sc.nextLine();
			System.out.print("Enter the Book-Author : ");
			String bAuthor = sc.nextLine();
			System.err.print("Enter the Book-Price : ");
			float bPrice=Float.parseFloat(sc.nextLine());
			System.out.print("Enter the Book-Quantity : ");
			int bQty=Integer.parseInt(sc.nextLine());
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			Statement stmt=con.createStatement();
			int k=stmt.executeUpdate("insert into BookDetails values("+bcode+",'"+bName+"','"+bAuthor+"',"+bPrice+","+bQty+")");
			
			if(k>0)
			{
				System.out.println("Book Details inserted successfully");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}
//
//ALTER TABLE users MODIFY username VARCHAR2(100);


/* Enter the Book-Code : 1122
Enter the Book-Name : Trading in the Zone
Enter the Book-Author : Trading Legend
Enter the Book-Price : 1500
Enter the Book-Quantity : 10
Book Details inserted successfully */
