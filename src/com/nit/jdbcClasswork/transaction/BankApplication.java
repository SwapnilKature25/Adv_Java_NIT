package com.nit.jdbcClasswork.transaction;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Savepoint;
import java.util.Scanner;

public class BankApplication {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con = DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl", "Swapnil", "swap25");
			PreparedStatement ps1=con.prepareStatement
					("select *from Bank where accno=?");
			PreparedStatement ps2=con.prepareStatement
					("update Bank set balance=balance+? where accno=?");
			
			System.out.println("Commit Status : "+con.getAutoCommit());
			con.setAutoCommit(false);
			
			System.out.println("Commit Status : "+con.getAutoCommit());
			Savepoint sp=con.setSavepoint();
			System.out.println("Enter the HomeAccoNo :");
			long hAccNo = sc.nextLong();
			ps1.setLong(1, hAccNo);
			ResultSet rs1=ps1.executeQuery();
			if(rs1.next())
			{
				
				float bal= rs1.getFloat(3);
				System.out.println("Enter benificieryAccNo : ");
				long bAccNo= sc.nextLong();
				ps1.setLong(1, bAccNo);
				ResultSet rs2=ps1.executeQuery();
				if(rs2.next())
				{
					System.out.println("Enter the amount to be transferred : ");
					float amt=sc.nextFloat();
					
					if(amt <= bal)
					{
						//Statement-1 : Substract amount 5000/- from accNo =2123443  
						ps2.setFloat(1, -amt);
						ps2.setLong(2, hAccNo);
						int p=ps2.executeUpdate();  // Update in Buffer 
						
						//Statement-2 : Add amount 5000/- to accNo =  3123443  
						ps2.setFloat(1, +amt);
						ps2.setLong(2, bAccNo);
						int q=ps2.executeUpdate(); // Update in Buffer 
						
						if(p>=1 && q>=1)
						{
							con.commit();
							System.out.println("Transaction Successfull..");
						}
						else {
							con.rollback(sp);
							System.out.println("Transaction Failed...");
						}
						
						
					}
					else {
						System.out.println("Insufficient fund..");
					}
				}
				else {
					System.out.println("Invalid BankAccNo..");
				}
			}
			else {
				System.out.println("Invalid HomeAccNo..");
			}			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}


/*   Commit Status : true
Commit Status : false
Enter the HomeAccoNo :
2123443
Enter benificieryAccNo : 
3123443
Enter the amount to be transferred : 
5000
Transaction Successfull..

 
 SQL> select *from Bank;

     ACCNO NAME               BALANCE ACCTYPE
---------- --------------- ---------- ---------------
   2123443 Martin               10000 Savings
   3123443 Jane                  5000 Savings

SQL> commit;

Commit complete.

SQL> select *from Bank;

     ACCNO NAME               BALANCE ACCTYPE
---------- --------------- ---------- ---------------
   2123443 Martin                5000 Savings
   3123443 Jane                 10000 Savings


 
 */