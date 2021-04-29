package kr.or.ddit.thread.pool;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import kr.or.ddit.thread.PrintNumberJob;
import kr.or.ddit.thread.PrintNumberJobGenerator;

public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executor = 
				new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(5));
		executor.setRejectedExecutionHandler(new RejectedExecutionHandler() {
			@Override
			public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
				System.err.printf("%s 가 거부당함.", r.getClass().getSimpleName());
			}
		});
		
		PrintNumberJobGenerator job = new PrintNumberJobGenerator(executor);
		executor.execute(job);
	}
}
