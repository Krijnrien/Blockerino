package Server;

import Server.util.KeyHandler;
import Server.util.MouseHandler;

public class GameLoop implements Runnable {

	private boolean running = false;

	private MouseHandler mouseHandler; //TODO Instead should be list of all clients with inputs?

	private void init() {
		running = true;
	}

	@Override
	public void run() {
		init();

		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		double deltaTime = 0;
		//TODO Keylistener not working without constant requestFocus
		while(running) {

			//TODO Loop per client? handle individual inputs

			int fps = 60;
			double nanoSecondsPerCount = 1000000000.0 / fps;
			long now = System.nanoTime();
			deltaTime += (now - lastTime) / nanoSecondsPerCount;
			lastTime = now;
			while(deltaTime >= 1) {
				update();
				deltaTime--;
			}
			if(System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
			}
		}
		stop();
	}

	private void input(MouseHandler _mouse, KeyHandler _key) {
		//gameStateManager.input(_mouse, _key);
	}

	private void update() {
		//gameStateManager.update();
	}

	private synchronized void stop() {
		running = false;
//		try {
		System.out.println("Goodbye");
		//TODO thread join
//			thread.join();
//		} catch(InterruptedException e) {
//			e.printStackTrace();
//		}
	}


}
