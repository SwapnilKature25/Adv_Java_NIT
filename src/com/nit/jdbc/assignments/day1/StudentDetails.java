package com.nit.jdbc.assignments.day1;

import java.sql.*;
import java.util.Scanner;

public class StudentDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil","swap25");
			PreparedStatement ps1=con.prepareStatement("insert into StudentDetails values(?,?,?,?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("select *from StudentDetails");
			
				
			PreparedStatement ps4=con.prepareStatement("Update StudentDetails set mailId=?, phoneNo=? where rollNo=?");
			
			
			while(true)
			{
				System.out.println("==========Read Student Details==========");
				System.out.println("\n\t1.Student can Register here"+
								   "\n\t2.View Students Details"+
								   "\n\t3.Student got more then 60% "+
								   "\n\t4.Update Student Details "+
								   "\n\t5.Delete Students (30% - 60%)"+
								   "\n\t6.No of Students got more than 80% "+
								   "\n\t7.Exit");
				
				System.out.print("\nEnter your choice :");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:
						System.out.println("==========Student Registration & Login============");
						System.out.println("\t 1.Register \n"
										+ " \t 2. Login");
						
						System.out.println("Enter your Roll No :");
						int rollNo=sc.nextInt();

						System.out.println("Enter your Name :");
						String name=sc.nextLine();
						name=sc.next();

						System.out.println("Enter your Percentage :");
						float percentage=sc.nextFloat();

						System.out.println("Enter your First-Name :");
						String fName=sc.nextLine();
						fName=sc.next();
						
						System.out.println("Enter your Last-Name :");
						String lName=sc.nextLine();
						lName=sc.next();
						
						System.out.println("Enter your Mail-Id :");
						String mailId=sc.nextLine();
						mailId=sc.next();

						System.out.println("Enter your Phone-No :");
						String pNo=sc.nextLine();
						
						ps1.setInt(1, rollNo);
						ps1.setString(2, name);
						ps1.setFloat(3, percentage);
						ps1.setString(4, fName);
						ps1.setString(5, lName);
						ps1.setString(6, mailId);
						ps1.setString(7, pNo);
							
						int k1=ps1.executeUpdate();
						if(k1>0)
						{
							System.out.println("Student Registered Successfully....");
						}
						System.out.println();
					    break;
					    
					case 2:
						System.out.println("===========Student Details=========");
						ResultSet rs1=ps2.executeQuery();
						while(rs1.next())
						{
							System.out.println(rs1.getInt(1)+"\t"+
												rs1.getString(2)+"\t"+
												rs1.getFloat(3)+"\t"+
												rs1.getString(4)+"\t"+
												rs1.getString(5)+"\t"+
												rs1.getString(6)+"\t"+
												rs1.getString(7));							
						}
						System.out.println();
						break;
					case 3:
						break;
					case 4:
						System.out.println("=======Updating Student Details========");
						System.out.println("Enter Student Roll nO=");
						
						System.out.println("Existing Student mailId : ");
						break;
					default:
						System.out.println("Invalid choice...");
				}
				
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
