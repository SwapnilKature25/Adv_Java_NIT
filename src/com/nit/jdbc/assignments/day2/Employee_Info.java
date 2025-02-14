package com.nit.jdbc.assignments.day2;

import java.sql.DriverManager;
import java.util.Scanner;
import java.sql.*;

public class Employee_Info {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			PreparedStatement ps1=con.prepareStatement("insert into Employee_Info values(?,?,?,?,?,?)");
			PreparedStatement ps2=con.prepareStatement("select *from Employee_Info");
			PreparedStatement ps3=con.prepareStatement("select *from Employee_Info where empName Like 'S%'");
			PreparedStatement ps4=con.prepareStatement("select *from Employee_Info where empSalary > 10000 AND empSalary < 20000");
			PreparedStatement ps5=con.prepareStatement("update Employee_Info set empSalary=? where empId=?");
			PreparedStatement ps6=con.prepareStatement("select *from Employee_Info where empId=? ");
			PreparedStatement ps7=con.prepareStatement("Delete from Employee_Info where empSalary = (SELECT MAX(empSalary) FROM Employee_Info) ");
			PreparedStatement ps8=con.prepareStatement("Delete from Employee_Info where empName like '%a'");
			
			while(true)
			{
				System.out.println("==========Employee Information==========");
				System.out.println("\t1.Add Employee"+
									"\n\t2.View Employee Information"+
									"\n\t3.Employees name starts with 'S'"+
									"\n\t4.Employees Salary (10000 - 20000)"+
									"\n\t5.Update Employee salary"+
									"\n\t6.Delete Employee getting (max salary)"+
									"\n\t7.Delete Employee name ends with 'a'"+
									"\n\t8.Exit");
				
				System.out.println("Enter your choice :");
				int choice=Integer.parseInt(sc.nextLine());
				
				switch(choice)
				{
					case 1:
						System.out.println("Enter Employee Id :");
						int eId=Integer.parseInt(sc.nextLine());
						System.out.println("Enter Employee Name :");
						String eName=sc.nextLine();
						System.out.println("Enter Employee Salary :");
						float eSalary=Float.parseFloat(sc.nextLine());
						System.out.println("Enter Employee Address :");
						String eAddr=sc.nextLine();
						System.out.println("Enter Employee MailId :");
						String eMailId=sc.nextLine();
						System.out.println("Enter Employee Phone No :");
						long ePhno=Long.parseLong(sc.nextLine());
						
						ps1.setInt(1, eId);
						ps1.setString(2, eName);
						ps1.setFloat(3, eSalary);
						ps1.setString(4, eAddr);
						ps1.setString(5, eMailId);
						ps1.setLong(6, ePhno);
						
						int k1=ps1.executeUpdate();
						if(k1>0)
						{
							System.out.println("EAmployee Added successfully...");
						}
						System.out.println();
						break;
						
					case 2:
						System.out.println("===================Employee Information==================");
						ResultSet rs1=ps2.executeQuery();
						while(rs1.next())
						{
							System.out.println(rs1.getInt(1)+
										  "\t"+rs1.getString(2)+
										  "\t"+rs1.getFloat(3)+
										  "\t"+rs1.getString(4)+
										  "\t"+rs1.getString(5)+
										  "\t"+rs1.getLong(6));
						}
						System.out.println();
						break;
						
					case 3:
						System.out.println("===========Employee Names starts with 'S' ==============");
						ResultSet rs2=ps3.executeQuery();
						while(rs2.next())
						{
							System.out.println(rs2.getInt(1)+
										  "\t"+rs2.getString(2)+
										  "\t"+rs2.getFloat(3)+
										  "\t"+rs2.getString(4)+
										  "\t"+rs2.getString(5)+
										  "\t"+rs2.getLong(6));
						}
						System.out.println();
						
						break;
					case 4:
						System.out.println("===========Employee Salary btw (10000 - 20000)==============");
						ResultSet rs3=ps4.executeQuery();
						while(rs3.next())
						{
							System.out.println(rs3.getInt(1)+
										  "\t"+rs3.getString(2)+
										  "\t"+rs3.getFloat(3)+
										  "\t"+rs3.getString(4)+
										  "\t"+rs3.getString(5)+
										  "\t"+rs3.getLong(6));
						}
						System.out.println();
						break;
						
					case 5:
						System.out.println("===========Update Employee Salary==============");
						System.out.println("Enter Employee-Id to update the salary : ");
						int empId=Integer.parseInt(sc.nextLine());
						ps6.setInt(1, empId);
						ResultSet rs4=ps6.executeQuery();
						
						if(rs4.next())
						{
							System.out.println("Existing Employee salary : "+rs4.getFloat(3));
							System.out.println("Enter Updated Salary of Employee : ");
							float newSal= Float.parseFloat(sc.nextLine());
							
							ps5.setFloat(1, newSal);
							ps5.setInt(2, empId);
							
							int k2=ps5.executeUpdate();
							if(k2>0)
							{
								System.out.println("Employee Salary Updated Successfully...");
							}
						}
						
						break;
						
					case 6:
						System.out.println("==========Delete Employees Earning Maximum Salary=========== ");
						int k3=ps7.executeUpdate();
						if(k3 > 0)
						{
							System.out.println("Employee with Max Salary deleted successfully...");
						}
						break;
						
					case 7:
						System.out.println("===========Remove Employee Names ends with 'a' ==============");
						int k4=ps8.executeUpdate();
						if(k4>0)
						{
							System.out.println("Employee with names ends with 'a' successfully deleted...");
						}
						else System.out.println("There is no employee whose name ends with 'a'");
						break;
						
					case 8:
						System.out.println("Exiting program...");
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
