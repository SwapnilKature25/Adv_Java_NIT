package com.nit.jdbcClasswork.transaction;

import java.sql.*;
import java.util.Scanner;

public class BatchExecuting {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			
			Statement stmt =con.createStatement();
			System.out.println("-=-=-=-Insert Operations on Bank=-=-=-=");
			System.out.println("Enter the AccNo : ");
			long accNo =Long.parseLong(sc.nextLine());
			System.out.println("Enter the Cust - Name :");
			String name= sc.nextLine();
			System.out.println("Enter the Cust-Balance : ");
			float bal=Float.parseFloat(sc.nextLine());
			System.out.println("Enter the Cust-Acc Type : ");
			String accType = sc.nextLine();
			
			stmt.addBatch
				("insert into Bank values("+accNo+",'"+name+"',"+bal+",'"+accType+"')");
			
			
			
			System.out.println("-=-=-=-Update Operations on Bank=-=-=-=");
			System.out.println("Enter the BookCode to perform Update Operation : ");
			int bCode=Integer.parseInt(sc.nextLine());
			System.out.println("Enter the new Book Price : ");
			float nPrice=Float.parseFloat(sc.nextLine());
			System.out.println("Enter the new Book Qty : ");
			int nQty=Integer.parseInt(sc.nextLine());
			
			stmt.addBatch
				("update BookDetails set price="+nPrice+",qty="+nQty+" where code="+bCode+"");

			int k[]=stmt.executeBatch();
			for(int i:k)
			{
				System.out.println("Query Executed : "+i);
			}
			stmt.clearBatch();
			con.close();				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
