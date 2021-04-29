package kr.or.ddit.thread.timer;

import java.util.Timer;

// 시기
// 2초마다 한번하는거 해보기
public class TimerTaskTest {
	public static void main(String[] args) {
		Timer timer = new Timer();
		PrintNumberTimerTask task = new PrintNumberTimerTask();
		timer.schedule(task, 0, 1000);
	}
}
