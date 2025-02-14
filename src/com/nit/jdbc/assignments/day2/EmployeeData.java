package com.nit.jdbc.assignments.day2;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class EmployeeData {
    int id;
    String name;
    int age;
    double salary;

    // Constructor
    public EmployeeData(int id, String name, int age, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // toString() method for easy printing
    @Override
    public String toString() {
        return id + "\t" + name + "\t" + age + "\t" + salary;
    }

    public static void main(String[] args) {
        List<EmployeeData> lst = new LinkedList<>();

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@localhost:1522:orcl", "swapnil", "swap25");

            PreparedStatement ps = con.prepareStatement("SELECT * FROM Employees");

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                EmployeeData emp = new EmployeeData(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3),
                        rs.getDouble(4)
                );
                lst.add(emp);
            }

            rs.close();
            ps.close();
            con.close();

            System.out.println("Employees older than 25:");
            for (EmployeeData emp : lst) {
                if (emp.age > 25) {
                    System.out.println(emp);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


/* 
Employees older than 25:
3	Raj	    32	4244.5
4	David	27	1544.5
5	Mike	34	3544.5 
*/












/*
public class EmployeeData {
	int id;
	String name;
	int age;
	double salary;
	
	public EmployeeData()
	{
		
	}
	
	public EmployeeData(int id, String name, int age, double salary) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	
	
	public void getEmployeeData(List e)
	{
		if(age > e.get)
	}


	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
	}

	public double getSalary() {
		return salary;
	}


	public static void main(String[] args) {
		List<EmployeeData> lst=new LinkedList();
		EmployeeData e=new EmployeeData();
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con=DriverManager.getConnection
					("jdbc:oracle:thin:@localhost:1522:orcl","swapnil", "swap25");
			
			PreparedStatement ps=con.prepareStatement("select *from Employees");
			
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
				e.id=rs.getInt(1);
				e.name=rs.getString(2);
				e.age=rs.getInt(3);
				e.salary=rs.getFloat(4);
				
				lst.add(new EmployeeData(e.id,e.name,e.age,e.salary));
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
*/