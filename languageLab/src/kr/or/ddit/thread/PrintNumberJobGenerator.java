package kr.or.ddit.thread;

import java.util.concurrent.ThreadPoolExecutor;

public class PrintNumberJobGenerator implements Runnable{
	private ThreadPoolExecutor executor;
	
	public PrintNumberJobGenerator(ThreadPoolExecutor executor) {
		super();
		this.executor = executor;
	}

	@Override
	public void run() {
		while(true) {
//			if((Thread.activeCount() - 2) >=8) break;
			PrintNumberJob job = new PrintNumberJob();
			executor.execute(job);
			
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
