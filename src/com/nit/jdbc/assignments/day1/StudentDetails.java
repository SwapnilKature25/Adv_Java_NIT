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
			PreparedStatement ps3=con.prepareStatement("select *from StudentDetails where percentage > 60");
			PreparedStatement ps4=con.prepareStatement("Update StudentDetails set mailId=?, phoneNo=? where rollNo=?");
			PreparedStatement ps5=con.prepareStatement("select *from StudentDetails where rollNo=?");
			PreparedStatement ps6=con.prepareStatement("Delete from StudentDetails where percentage > 30 AND percentage < 60");
			PreparedStatement ps7=con.prepareStatement("Select * from StudentDetails where percentage > 80");
			
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
						int rollNo=Integer.parseInt(sc.nextLine());
						
						System.out.println("Enter your Name :");
						String name=sc.nextLine();
						

						System.out.println("Enter your Percentage :");
						float percentage=Float.parseFloat(sc.nextLine());

						System.out.println("Enter your First-Name :");
						String fName=sc.nextLine();
						
						
						System.out.println("Enter your Last-Name :");
						String lName=sc.nextLine();
						
						
						System.out.println("Enter your Mail-Id :");
						String mailId=sc.nextLine();
						

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
						System.out.println("===========Students got more than 60%=========");
						ResultSet rs2=ps3.executeQuery();
						while(rs2.next())
						{
							System.out.println(rs2.getInt(1)+"\t"+
												rs2.getString(2)+"\t"+
												rs2.getFloat(3)+"\t"+
												rs2.getString(4)+"\t"+
												rs2.getString(5)+"\t"+
												rs2.getString(6)+"\t"+
												rs2.getString(7));							
						}
						System.out.println();
						break;
					case 4:
						System.out.println("=======Updating Student Details========");
						System.out.println("Enter Student Roll no to modify student details :");
						int roll=Integer.parseInt(sc.nextLine());
						ps5.setInt(1, roll);
						ResultSet rs3=ps5.executeQuery();
						
						if(rs3.next())
						{
							System.out.println("Existing Student mailId : "+rs3.getString(6));
							System.out.println("Enter new Mail-Id to be Updated : ");
							String nMailId=sc.nextLine();
							
							System.out.println("Existing Student PhoneNo : "+rs3.getString(7));
							System.out.println("Enter new Phone No to be Updated : ");
							String newPhno=sc.nextLine();
							
							ps4.setString(1, nMailId);
							ps4.setString(2, newPhno);
							ps4.setInt(3, roll);
							
							int k3=ps4.executeUpdate();
							if(k3>0)
							{
								System.out.println("Student Details Updated Successfully..");
							}
							
						}	
						System.out.println();										
						break;
						
					case 5:
						System.out.println("Deleting Student whose percentage is below 60");
						
						break;
						
					case 6:
						System.out.println("Below are the Students who got more than 80%");
						ResultSet rs4=ps7.executeQuery();
						int count=0;
						while(rs4.next())
						{
							count++;
						}
						System.out.println(count+" students scored more than 80%");
						break;
						
					case 7:
						System.out.println("Exiting...");
						con.close();
						System.exit(0);
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
