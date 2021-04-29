package kr.or.ddit.thread.timer;

import java.util.TimerTask;

// 작업
public class PrintNumberTimerTask extends TimerTask{
	private int number;
	@Override
	public void run() {
		if(number==100) cancel();
		System.out.printf("%d - %s(of %d)\n"
				,++number, Thread.currentThread().getName(), Thread.activeCount());
	}
}
