package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

public class RetrieveUsingFunction {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call ?:= RetrievePhonNo (?)}");
			
			System.out.println("Enter the Cust-Id to retrieve Phone No : ");
			String cid=sc.nextLine();
			cs.setString(2, cid);
			cs.registerOutParameter(1, Types.BIGINT);
			cs.execute();
			
			System.out.println("Cust Id : "+cid);
			System.out.println("Cust Phone no : "+cs.getLong(1));
						
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* Enter the Cust-Id to retrieve Phone No : 
A111
Cust Id : A111
Cust Phone no : 878976878 */
