package com.nit.jdbcClasswork;


interface ITest
{
	void m1(int x);
	default void m2(int y)
	{
		System.out.println("In default method y value is "+y);
	}
}

 class IClass implements ITest
{
	 
	@Override
	public void m1(int x) {
		System.out.println("m1 method implemented by IClass and x value is "+x);
	}
	
}
public class InterfaceDemo
{
	public static void main(String[] args) {
		IClass i=new IClass();
		i.m1(2);
		i.m2(3);
	
	}
}
