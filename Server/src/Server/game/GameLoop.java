package Server.game;

import java.util.Timer;
import java.util.TimerTask;

public class GameLoop implements Runnable {
    //private MousseHandler mousseHandler; //TODO Instead should be list of all clients with inputs?

    GameManager gameManager;

    public GameLoop() {
        gameManager = new GameManager();
    }


    @Override
    public void run() {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                update();
            }
        }, 0, 100);
        stop();
    }

    private void input() {
        //gameStateManager.input(_mouse, _key);
    }

    private void update() {
        //gameStateManager.update();
    }

    private synchronized void stop() {
        //		try {
        System.out.println("Goodbye");
        //TODO thread join
        //			thread.join();
        //		} catch(InterruptedException e) {
        //			e.printStackTrace();
        //		}
    }


}
