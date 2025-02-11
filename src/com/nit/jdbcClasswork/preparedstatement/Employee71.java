package com.nit.jdbcClasswork.preparedstatement;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Employee71 {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			PreparedStatement ps1=con.prepareStatement
					("insert into Employee71 values(?,?,?,?,?,?,?)");  // Compilation
			
			PreparedStatement ps2=con.prepareStatement
					("Select *from Employee71");
			
			PreparedStatement ps3=con.prepareStatement
					("Select *from Employee71 where eid=?");
			
			PreparedStatement ps4=con.prepareStatement
					("update Employee71 set bsal=?, hra=?, da=?, totsal=? where id=? ");
			
			PreparedStatement ps5=con.prepareStatement
					("Delete from Employee71 where id = ?");
			
			while(true)
			{
				System.out.println("======Employee Operations Choice=====");
				System.out.println("\t1.AddEmployee"
								+"\n\t2.ViewAllEmployees"
								+"\n\t3.ViewEMployeeByID"
								+"\n\t4.UpdateEmployee"
								+"\n\t5.DeleteEmployee"
								+"\n\t6.Exit");
				
				System.out.println("Enter your Choice :");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:
						System.out.println("=====Read new Employee Details=====");
						
						System.out.println("Enter Employee-Id :");
						String eId=sc.nextLine();
						System.out.println("Enter Employee-Name :");
						String eName=sc.nextLine();
						System.out.println("Enter Employee-Designation :");
						String eDesg=sc.nextLine();
						System.out.println("Enter Employee-Salary :");
						int eSal=sc.nextInt();
						float hra=0.91f * eSal;
						float da= 0.63f * eSal;
						float totSal= hra+da+eSal;
						
						// Load Data to PreparedStatement Object
						ps1.setString(1, eId);
						ps1.setString(2, eName);
						ps1.setString(3, eDesg);
						ps1.setInt(4, eSal);
						ps1.setFloat(5, hra);
						ps1.setFloat(6, da);
						ps1.setFloat(7, totSal);
						
						int k1=ps1.executeUpdate();
						if(k1>0)
						{
							System.out.println("Employee Added Successfully..");
						}
						System.out.println();
						break;
						
						
						
					case 2:
						ResultSet rs1=ps2.executeQuery();	//execution
						System.out.println("=====Employee Details=====");
						while(rs1.next())
						{
							System.out.println(rs1.getString(1)
											+"\t"+rs1.getString(2) 
											+"\t"+rs1.getString(3)
											+"\t"+rs1.getInt(4)
											+"\t"+rs1.getFloat(5)
											+"\t"+rs1.getFloat(6)
											+"\t"+rs1.getFloat(7));
						}
						System.out.println();
						break;
						
						
						
					case 3:
						System.out.println("Enter the Employee-Id to display Details :");
						String empId=sc.nextLine();
						
						// Load the data to PreparedStatment Object
						ps3.setString(1, empId);
						ResultSet rs2=ps3.executeQuery();
						while(rs2.next())
						{
							System.out.println(rs2.getString(1)
											+"\t"+rs2.getString(2) 
											+"\t"+rs2.getString(3)
											+"\t"+rs2.getInt(4)
											+"\t"+rs2.getFloat(5)
											+"\t"+rs2.getFloat(6)
											+"\t"+rs2.getFloat(7));
						}
						System.out.println();
						break;
						
						
						
						
					case 4:
						System.out.println("Enter the Emp-Id to perform Update Operations :");
						String empId2=sc.nextLine();
						ps3.setString(1, empId2);
						ResultSet rs3=ps3.executeQuery();
						if(rs3.next())
						{
							System.out.println("Existing bSal :"+rs3.getInt(4));
							System.out.println("Enter the new bSal :");
							int newSal=Integer.parseInt(sc.nextLine());
							float hra2=0.92f *newSal;
							float da2=0.63f * newSal;
							float totSal2= newSal + hra2+ da2;
							
							// Load the data to PreparedStatment Object
							ps4.setInt(1, newSal);
							ps4.setFloat(2, hra2);
							ps4.setFloat(3, da2);
							ps4.setFloat(4, totSal2);
							ps4.setString(5, empId2);
							
							int k2=ps4.executeUpdate();
							if(k2 > 0 )
							{
								System.out.println("Emplpoyee Updated Successfully...");
							}
							else 
							{
								System.out.println("Invalid empid....");
							}
							
						}	
						System.out.println();
						break;
						
						
						
					case 5:
						System.out.println("Enter the Employee-Id to perform Delete Operation : ");
						String empId3=sc.nextLine();
						ps3.setString(1, empId3);
						ResultSet rs4=ps3.executeQuery();
						if(rs4.next())
						{
							ps5.setString(1, empId3);
							int k3=ps5.executeUpdate();
							if(k3>0)
							{
								System.out.println("Employee deleted successfully...");
							}
							else {
								System.out.println("Invalid empId...");
							}
						}
						else {
							System.out.println("Invalid empId...");
						}
						
						break;
						
						
						
					case 6:
						System.out.println("Operations Stopped..");
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
			
		}
	}
}



/* ======Employee Operations Choice=====
	1.AddEmployee
	2.ViewAllEmployees
	3.ViewEMployeeByID
	4.UpdateEmployee
	5.DeleteEmployee
	6.Exit
Enter your Choice :
1
=====Read new Employee Details=====
Enter Employee-Id :
124
Enter Employee-Name :
Smith
Enter Employee-Designation :
JD
Enter Employee-Salary :
100000
Employee Added Successfully..

======Employee Operations Choice=====
	1.AddEmployee
	2.ViewAllEmployees
	3.ViewEMployeeByID
	4.UpdateEmployee
	5.DeleteEmployee
	6.Exit
Enter your Choice :





======Employee Operations Choice=====
	1.AddEmployee
	2.ViewAllEmployees
	3.ViewEMployeeByID
	4.UpdateEmployee
	5.DeleteEmployee
	6.Exit
Enter your Choice :
2
=====Employee Details=====
123	Scott	TE	50000	45500.0	31500.0	127000.0
124	Smith	JD	100000	91000.0	63000.0	254000.0
121	Mike	SD	100000	91000.0	63000.0	254000.0



======Employee Operations Choice=====
	1.AddEmployee
	2.ViewAllEmployees
	3.ViewEMployeeByID
	4.UpdateEmployee
	5.DeleteEmployee
	6.Exit
Enter your Choice :
3
Enter the Employee-Id to display Details :
121
121	Mike	SD	100000	91000.0	63000.0	254000.0



======Employee Operations Choice=====
	1.AddEmployee
	2.ViewAllEmployees
	3.ViewEMployeeByID
	4.UpdateEmployee
	5.DeleteEmployee
	6.Exit
Enter your Choice :
6
Operations Stopped..



*/
