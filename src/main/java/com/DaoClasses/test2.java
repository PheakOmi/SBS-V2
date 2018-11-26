package com.DaoClasses;



class RunnableDemo implements Runnable {
	private Thread t;
	private String threadName;

	RunnableDemo(String name) {
		threadName = name;

		System.out.println("Creating " +  threadName );
	}

	public void run() {
		System.out.println("Running 1" +  threadName );
		try {
			for(int i = 1000; i > 0; i--) {
				System.out.println("Thread: 1" + threadName + ", " + i);
				// Let the thread sleep for a while.
//				Thread.sleep(1);
			}
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting 1" +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
}


class RunnableDemo2 implements Runnable {
	private Thread t;
	private String threadName;

	RunnableDemo2( String name) {
		threadName = name;
		System.out.println("Creating " +  threadName );
	}

	public void run() {
		System.out.println("Running 2" +  threadName );
		try {
			for(int i = 4; i > 0; i--) {
				System.out.println("Thread: 2" + threadName + ", " + i);
				// Let the thread sleep for a while.
//				Thread.sleep(50);
			}
		} catch (Exception e) {
			System.out.println("Thread " +  threadName + " interrupted.");
		}
		System.out.println("Thread " +  threadName + " exiting.");
	}

	public void start () {
		System.out.println("Starting 2" +  threadName );
		if (t == null) {
			t = new Thread (this, threadName);
			t.start ();
		}
	}
}

public class test2 {

	public static void main(String args[]) {
		RunnableDemo R1 = new RunnableDemo( "Thread-1");
		R1.start();
		System.out.println("return");
		//RunnableDemo2 R2 = new RunnableDemo2( "Thread-2");
		//R2.start();
	}
}