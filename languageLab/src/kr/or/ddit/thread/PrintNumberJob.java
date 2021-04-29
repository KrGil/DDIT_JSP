package kr.or.ddit.thread;

public class PrintNumberJob implements Runnable{
	private int number;
		
	@Override
	public void run() {
		while(number<100) {
			System.out.printf("%d - %s(of %d)\n"
					,++number, Thread.currentThread().getName(), Thread.activeCount());
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
