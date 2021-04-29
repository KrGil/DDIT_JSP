package kr.or.ddit.task;

import java.util.TimerTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

// 작업
@Component
public class PrintNumberTimerTask {
	private int number;
	
//	@Scheduled(initialDelay = 0, fixedRate = 1000)
	@Scheduled(cron = "0 0 3 ? * MON")
	public void run() {
		System.out.printf("%d - %s(of %d)\n"
				,++number, Thread.currentThread().getName(), Thread.activeCount());
	}
}
