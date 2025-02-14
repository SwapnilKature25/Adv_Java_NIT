package com.nit.jdbc.assignments.day2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProductDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil", "swap25");
			
			PreparedStatement ps=con.prepareStatement
								("insert into Product_Details values(?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement
								("Select *from Product_Details Order By id ASC");
			PreparedStatement ps3=con.prepareStatement
								("Select *from Product_Details Order By id DESC");
//			OFFSET 2 ROWS skips the first two records.
//			FETCH NEXT 1 ROW ONLY retrieves only the 3rd record.

			PreparedStatement ps4=con.prepareStatement
								("Select *from Product_Details Order By id Asc OFFSET 2 rows FETCH next 1 row Only");
			PreparedStatement ps5=con.prepareStatement
								("Select *from Product_Details Order By id Desc OFFSET 2 rows FETCH next 1 row Only");

//			FETCH FIRST 3 ROWS ONLY retrieves only the first three rows 
//			If you want to skip some rows before fetching, use OFFSET:
			PreparedStatement ps6=con.prepareStatement
								("Select *from Product_Details Order By id Desc FETCH first 3 rows Only");
			while(true)
			{
				
				System.out.println("===============Product Details===============");
				System.out.println("\t1.Add Product Details"+
									"\n\t2.View Product Details in Forward Direction "+
									"\n\t3.View Product Details in Backward Direction"+
									"\n\t4.View 3rd record from top"+
									"\n\t5.View 3rd record from bottom"+
									"\n\t6.View last 3 records"+
									"\n\t7.Exit");
				
				System.out.println("\n	Enter your choice : ");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:
						System.out.println("==============Add Product Details===========");
						System.out.println("Enter Product-Id : ");
						String pId=sc.nextLine();
						System.out.println("Enter Product-Name : ");
						String pName=sc.nextLine();
						System.out.println("Enter Product-Price : ");
						float price=Float.parseFloat(sc.nextLine());
						System.out.println("Enter Product-Quantity : ");
						int qty=Integer.parseInt(sc.nextLine());
						
						ps.setString(1, pId);
						ps.setString(2, pName);
						ps.setFloat(3, price);
						ps.setInt(4, qty);
						
						int k1=ps.executeUpdate();
						
						if(k1 > 0)
						{
							System.out.println("Product Details added successfully... ");
						}
						
						break;
						
					case 2:
						System.out.println("===========Reading Product_Details in Forward Direction===========");
						ResultSet rs1=ps2.executeQuery();
						while(rs1.next())
						{
							System.out.println(rs1.getInt(1)+"\t"+
											 	rs1.getString(2)+"\t"+
												rs1.getFloat(3)+"\t"+
											 	rs1.getInt(4));
						}						
						
						break;
						
					case 3:
						System.out.println("===========Reading Product_Details in Backward Direction===========");
						ResultSet rs2=ps3.executeQuery();
						while(rs2.next())
						{
							System.out.println(rs2.getInt(1)+"\t\t"+
											 	rs2.getString(2)+"\t\t"+
												rs2.getFloat(3)+"\t\t"+
											 	rs2.getInt(4));
						}	
						break;
						
					case 4:
						System.out.println("===========Fetching 3rd record from top of Product_Details===========");
						ResultSet rs3=ps4.executeQuery();
						if(rs3.next())
						{
							System.out.println(rs3.getInt(1)+"\t\t"+
											 	rs3.getString(2)+"\t\t"+
												rs3.getFloat(3)+"\t\t"+
											 	rs3.getInt(4));
						}	
						break;
						
					case 5:
						System.out.println("===========Fetching 3rd record from bottom of Product_Details===========");
						ResultSet rs4=ps5.executeQuery();
						if(rs4.next())
						{
							System.out.println(rs4.getInt(1)+"\t\t"+
								 				rs4.getString(2)+"\t\t"+
								 				rs4.getFloat(3)+"\t\t"+
								 				rs4.getInt(4));
						}
						break;
					
					case 6 :
						System.out.println("===========Fetching last 3rd record from Product_Details===========");
						ResultSet rs5=ps6.executeQuery();
						while(rs5.next())
						{
							System.out.println(rs5.getInt(1)+"\t\t"+
					 				rs5.getString(2)+"\t\t"+
					 				rs5.getFloat(3)+"\t\t"+
					 				rs5.getInt(4));
						}
						break;
					
					case 7:
						System.out.println("Exiting program....");
						con.close();
						System.exit(0);
						break;
					default:
						System.out.println("Invalid Choice");
				}
				
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
