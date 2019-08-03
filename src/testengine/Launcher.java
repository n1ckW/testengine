package testengine;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Launcher {
	public static boolean DEBUG = false;
	public static final int FRAMERATE = 60;
	public static final int TICKRATE = 60;	
	public static final String title = "PLACEHOLDER_TITLE";
	
	public static final int tickTimeMicros = 1000000 / TICKRATE;
	public static final int frameTimeMicros = 1000000 / FRAMERATE;	
	
	
	private static ScheduledExecutorService renderingExecutor = Executors.newSingleThreadScheduledExecutor();
	private static ScheduledExecutorService tickExecutor = Executors.newSingleThreadScheduledExecutor();
	private static Game game;
	private static boolean executing = false;
	
	private static final Runnable render = new Runnable() {
		@Override
		public void run() {
			game.render();
		}
	};		
	
	private static final Runnable tick = new Runnable() {
		@Override
		public void run() {
			if (!game.isRunning()) {
				stopExecution();
			} else {
				game.tick();
			}
		}			
	};
	
	public static void main(String[] args) {				
		startExecution();	
	}
	
	private static void startExecution() {
		if (!executing) {
			game = new Game(title, 1280, 768);
			renderingExecutor = Executors.newSingleThreadScheduledExecutor();
			tickExecutor = Executors.newSingleThreadScheduledExecutor();
			tickExecutor.scheduleAtFixedRate(tick, 0, tickTimeMicros, TimeUnit.MICROSECONDS);
			renderingExecutor.scheduleAtFixedRate(render, 0, frameTimeMicros, TimeUnit.MICROSECONDS);
		}
	}
	
	private static void stopExecution(){
		renderingExecutor.shutdownNow();
		renderingExecutor = null;
		tickExecutor.shutdownNow();
		tickExecutor = null;
		game.terminate();
		game = null;
		executing = false;
	}

	public static boolean isExecuting() {
		return executing;
	}
}
