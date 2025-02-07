package com.nit.jdbcClasswork;

// Construct implementation class with name and create implementation Object for above interface
// This implementation class must be anonymous local inner class

interface ITest2
{
	void m1(int x);
	default void m2(int y)
	{
		System.out.println("default method y value is "+y);
	}
}

public class InterfaceAnonymousInnerClass {
	public static void main(String[] args) {
		
        // Creating an anonymous inner class implementing ITest2
        ITest2 obj = new ITest2() {
            @Override
            public void m1(int x) {
                System.out.println("m1 method implemented in anonymous class. x value is " + x);
            }
        };

        obj.m1(10);  
        obj.m2(20);  
    }
}
