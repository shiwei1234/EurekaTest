package com.eureka;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@SpringBootTest
class EurekaClient1ApplicationTests {
	
	@Test
	public void test() throws InterruptedException{
		
		MyThread t1 = new MyThread("t1");
		MyThread t2 = new MyThread("t2");
		MyThread t3 = new MyThread("t3");
		MyThread t4 = new MyThread("t4");
		MyThread t5 = new MyThread("t5");
		
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		Thread.sleep(20000);
		
	}

}
class MyThread extends Thread{
	
	
	
	public MyThread (String name) {
		begin(name);
	}
	
	@HystrixCommand(fallbackMethod = "end")
	public void begin(String name) {
		String result = null;
		try {
			
			result = new CommandDemo(name).run();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
		
	}
	public void end(String name) {
		
		System.out.println("error");
	}
}