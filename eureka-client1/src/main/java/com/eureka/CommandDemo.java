package com.eureka;

import com.netflix.hystrix.HystrixCommand;

import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

public class CommandDemo extends HystrixCommand<String> {
	private String name;
	
	public CommandDemo (String name) {
		super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CommandDemoKey"))
				.andCommandPropertiesDefaults(HystrixCommandProperties.defaultSetter()
						//请求缓存开关
						.withRequestCacheEnabled(true)
						//切换资源隔离模式
						//此处选择信号量隔离 ,
						.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.SEMAPHORE)
						// 线程池隔离
						//.withExecutionIsolationStrategy(HystrixCommandProperties.ExecutionIsolationStrategy.THREAD)
						).andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("CommandThreadPool"))
						.andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.defaultSetter()
								.withCoreSize(100)
								.withMaximumSize(100)
								.withAllowMaximumSizeToDivergeFromCoreSize(true)
								)
			);
		this.name=name;
		System.out.println(this.name);
	}
	
	
	@Override
	
	protected String run() throws Exception {
		//需要进行信号量隔离的代码
		CommandDemo demo = new CommandDemo(name);
		System.out.println("xxxxx");
		
		return "1234"+demo.execute();
	}

}
