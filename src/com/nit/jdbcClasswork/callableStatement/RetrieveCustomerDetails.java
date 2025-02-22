package com.nit.jdbcClasswork.callableStatement;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Types;
import java.util.Scanner;

//Ex:(Construct Procedure to retrieve Customer details based on Customer-Id)

public class RetrieveCustomerDetails {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		try(sc;)
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil","swap25");
			
			CallableStatement cs=con.prepareCall
					("{call RetrieveCustomer(?,?,?,?,?,?,?,?,?)}");
			
			System.out.println("Enter the Cust-Id to retrieve Details :");
			String cid=sc.nextLine();
			cs.setString(1, cid);
			cs.registerOutParameter(2, Types.VARCHAR);
			cs.registerOutParameter(3, Types.VARCHAR);
			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);
			cs.registerOutParameter(6, Types.VARCHAR);
			cs.registerOutParameter(7, Types.INTEGER);
			cs.registerOutParameter(8, Types.VARCHAR);
			cs.registerOutParameter(9, Types.BIGINT);
			
			cs.execute();	// Procedure executed
			
			System.out.println("++++++++ Customer Details +++++++");
			System.out.println("Cust-Id : "+cid);
			System.out.println("Cust-Name : "+cs.getString(2));
			System.out.println("Cust-HNo : "+cs.getString(3));
			System.out.println("Cust-Sname : "+cs.getString(4));
			System.out.println("Cust-City : "+cs.getString(5));
			System.out.println("Cust-State : "+cs.getString(6));
			System.out.println("Cust-Pincode : "+cs.getInt(7));
			System.out.println("Cust-MailID : "+cs.getString(8));
			System.out.println("Cust-PhoneNo : "+cs.getLong(9));
			con.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}


/* Enter the Cust-Id to retrieve Details :
A111
++++++++ Customer Details +++++++
Cust-Id : A111
Cust-Name : Mike
Cust-HNo : 12-34/h
Cust-Sname : AMP
Cust-City : Hyd
Cust-State : MH
Cust-Pincode : 5000016
Cust-MailID : a@gmail.com
Cust-PhoneNo : 878976878
 */
