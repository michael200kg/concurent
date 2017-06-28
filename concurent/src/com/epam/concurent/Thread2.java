package com.epam.concurent;

public class Thread2 implements Runnable {

	int x2;
	int r2;
	
	@Override
	public void run() {
		x2=2;
		r2=x2;	
	}
   
}
