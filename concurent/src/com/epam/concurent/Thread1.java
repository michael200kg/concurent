package com.epam.concurent;

public class Thread1 implements Runnable {

	int x1;
	int r1;
	
	@Override
	public void run() {
		x1=1;
		r1=x1;
		
	}
   
}
