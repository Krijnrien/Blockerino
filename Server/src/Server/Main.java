package Server;

import Server.connection.IncomingConnectionHandler;
import Server.game.GameLoop;

public class Main {

	public static void main(String args[]) {
		System.out.println("Starting world on new thread!"); //TODO add proper logging
		GameLoop gameLoop = new GameLoop();
		Thread gameLoopThread = new Thread(gameLoop, "GameThread");
		gameLoopThread.start();

		System.out.println("Listening on incoming connections..."); //TODO add proper logging
		IncomingConnectionHandler incomingConnectionHandler = new IncomingConnectionHandler();
		Thread connectionHandlerThread = new Thread(incomingConnectionHandler, "ConnectionHandlerThread");
		connectionHandlerThread.start();
	}
}
