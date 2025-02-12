package com.nit.jdbcClasswork.preparedstatement;

import java.sql.*;

public class DBCon2 {
	public static void main(String[] args) {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");
			PreparedStatement ps=con.prepareStatement("select * from BookDetails",ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			ResultSet rs=ps.executeQuery();
			
			System.out.println("==========Absolute(2)===========");
			rs.absolute(2);
			System.out.println(rs.getString(1)+"\t"
								+ rs.getString(2)+"\t"
								+ rs.getString(3)+"\t"
								+rs.getFloat(4)+"\t"
								+rs.getInt(5));
			
			System.out.println();
			System.out.println("=========Relative(-1)========");
			rs.relative(-1);
			System.out.println(rs.getString(1)+"\t"
					+ rs.getString(2)+"\t"
					+ rs.getString(3)+"\t"
					+rs.getFloat(4)+"\t"
					+rs.getInt(5));
			
			System.out.println();
			System.out.println("=========last()========");
			rs.last();
			System.out.println(rs.getString(1)+"\t"
					+ rs.getString(2)+"\t"
					+ rs.getString(3)+"\t"
					+rs.getFloat(4)+"\t"
					+rs.getInt(5));
			
			System.out.println();
			System.out.println("=========afterLast()========");
			rs.afterLast();
			while(rs.previous())
			{
				System.out.println(rs.getString(1)+"\t"
						+ rs.getString(2)+"\t"
						+ rs.getString(3)+"\t"
						+rs.getFloat(4)+"\t"
						+rs.getInt(5));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}

/* 
==========Absolute(2)===========
1144	BCD	XY	1300.0	20 


=========Relative(-1)========
1122	Trading in the Zone	Trading Legend	1800.0	15


=========last()========
1144	BCD	XY	1300.0	20


=========afterLast()========
1144	BCD	XY	1300.0	20
1122	Trading in the Zone	Trading Legend	1800.0	15

*/
