package com.epam.concurent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.StampedLock;

public class Main {
	   
	static int x;
	static int r;   
	static int v1, v2;
	
	public static final int MAX_KEY = 10_000_000; 

	public static void main1(String[] args) {

		final int MAX_KEY = 10_000_000; 
		
		ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
		StampedLock stLock = new StampedLock(); 
       
	   Thread thread1 = new Thread(new Runnable()  { 
		                                      public void run() {  
		                                    	  
		                                    	  lock.writeLock().lock();
		                                    	  x=1;
		                                    	  lock.writeLock().unlock();
		                                    	  
		                                    	  long stamp = stLock.tryOptimisticRead();
                                                  if(stamp!=0L) {
		                                    	        
		                                    	        r=x;
		                                    	        v1=r;
			                                            if(stLock.validate(stamp)) {
			                                            	System.out.println("Thread1 x="+x+" v1="+v1);
			                                            	return;
			                                            }
                                                   }

		                                    	   stamp = stLock.writeLock();
			                                       x=1;
			                                       r=x;
			                                       v1=r;
			                                       System.out.println("Thread1 lock x="+x+" v1="+v1);	
			                                       stLock.unlockWrite(stamp);

 		                                      } 
		                                      
	   });
	   Thread thread2 = new Thread(new Runnable() { 
                                              public void run() {  
		                                    	  
                                            	  lock.writeLock().lock();
		                                    	  x=2;
		                                    	  lock.writeLock().unlock();
		                                    	  
                                            	  long stamp = stLock.tryOptimisticRead();
                                            	  if(stamp!=0L) {  

		                                            r=x;
		                                            v2=r;
		                                            if(stLock.validate(stamp)) {
		                                            	System.out.println("Thread2 x="+x+" v2="+v2);
		                                            	return;
		                                            }	
                                            	  }
	                                    		 
	                                    		  stamp = stLock.writeLock();
		                                    	  x=2;
		                                    	  r=x;
		                                    	  v2=r;
		                                    	  System.out.println("Thread2 lock x="+x+" v2="+v2);	
		                                    	  stLock.unlockWrite(stamp);
	                                    }  
	                                       
	   });
	   thread1.start();
	   thread2.start();

   }

  public enum LANGUAGE {
  
	  RUSSIAN(1,"Russian","Exelent"), ENGLISH(2,"English","Intermediale"); 	
	    static {
	    	System.out.println("static block");
	    }	
	int nn;
	String name;
	String level;
    {
    	System.out.println("non static block");
    }  	
    LANGUAGE(int nn,String name,String level) {
    	System.out.println("constructor "+name);
    	this.nn=nn;	
    	this.name=name;
    	this.level=level;
    }
  


	
}
	

	public static void main(String[] args) {	   
		
		A a = new B();
		B b = new B();
		
		System.out.println("a getStatic="+a.getStatic());	
		System.out.println("b getStatic="+b.getStatic());	
		
		//Arrays.stream(LANGUAGE.values()).forEach(test->System.out.println("Language="+test.name+" Level="+test.level));
		
		/* B b = new B();
		
		int h = Integer.MAX_VALUE;
		        h ^= (h >>> 20) ^ (h >>> 12);
		        h = h ^ (h >>> 7) ^ (h >>> 4);
			System.out.println("hashcode="+h);
			*/
	   }
	   
	
}

