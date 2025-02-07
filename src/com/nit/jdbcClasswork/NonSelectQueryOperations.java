package com.nit.jdbcClasswork;

import java.sql.*;
import java.util.Scanner;

public class NonSelectQueryOperations {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			Statement stmt=con.createStatement();
			
			System.out.println("Enter the Non-Select Query (Create/Insert/Update/Delete)");
			String qry=sc.nextLine();
			
			int k=stmt.executeUpdate(qry);
			if(k>0)
			{
				System.out.println("Query Executed Successfully....");
				System.out.println("The value in K : "+k);
			}
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


/*
Enter the Non-Select Query (Create/Insert/Update/Delete)
create table DMLCommands(id number(10), name varchar2(10)) 
The value in K : 0
   

Enter the Non-Select Query (Create/Insert/Update/Delete)
insert into DMLCommands values (1, 'Insert')
Query Executed Successfully....
The value in K : 1


Enter the Non-Select Query (Create/Insert/Update/Delete)
Update DMLCommands set name='update' where id=1
Query Executed Successfully....
The value in K : 1

Enter the Non-Select Query (Create/Insert/Update/Delete)
Delete from DMLCommands where id=2
Query Executed Successfully....
The value in K : 1



*/
