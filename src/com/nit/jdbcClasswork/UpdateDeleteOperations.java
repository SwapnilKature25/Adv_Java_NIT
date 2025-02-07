package com.nit.jdbcClasswork;
import java.sql.*;
import java.util.Scanner;

public class UpdateDeleteOperations {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl","swapnil", "swap25");
			Statement stmt=con.createStatement();
			System.out.println("Enter the Book-Code to perform Update/Delete Operations : ");
			int bCode = sc.nextInt();
			ResultSet rs=stmt.executeQuery("select *from BookDetails where bcode="+bCode+"");
			
			if(rs.next())
			{
				System.out.println("========Choice=======");
				System.out.println("\t1.Update"+"\n\t2.Delete");
				System.out.print("Enter your Choice : ");
				int choice=sc.nextInt();
				
				switch(choice)
				{
					case 1: 
						System.out.println("Perform Update Operation......");
						System.out.println("Old Book Price : "+rs.getFloat(4));
						System.out.println("Enter the new BookPrice ");
						float nPrice=sc.nextFloat();
						System.out.println("Existing book quantity : "+rs.getInt(5));
						System.out.println("Enter the new Book Quantity ");
						int nQty=sc.nextInt();
						
						int k=stmt.executeUpdate("Update BookDetails set bPrice = "+nPrice+",bQty="+nQty+" where bCode="+bCode+"");
						if(k > 0)
						{
							System.out.println("Book Details Updated Successfully");
						}
						
						break;
					case 2:
//						System.out.println("Performing Delete Operation ");
						int k2=stmt.executeUpdate("Delete from BookDetails where bCode="+bCode+"");
						if(k2>0)
						{
							System.out.println("Book Details Deleted Successfully....");
						}
						break;
					default:
						System.out.println("Invalid Choice");
						
				}
				
			}
			else 
			{
				System.out.println("Invalid Book Code");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
