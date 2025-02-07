package com.nit.jdbcClasswork;

interface ITest3
{
	void m1();
	default void m2()
	{
		System.out.println("Accessing default method from interface ");
	}
}
public class Access 
{

	public static ITest3 getRef()
	{
		ITest3 ob=new ITest3()
		{
			@Override
			public void m1() {
				System.out.println("Implemented method from interface");
			}
			
		};
		return ob;
	}
	
}
