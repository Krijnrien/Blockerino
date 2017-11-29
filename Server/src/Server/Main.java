package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static boolean listening;
	private static final int PORT = 1978;

	public static void main(String args[]) {
		System.out.println("starting server");
		GameLoop gameLoop = new GameLoop();
		Thread t = new Thread(gameLoop, "GameThread");
		t.start();

		try {
			Socket socket = null;
			ServerSocket serverSocket = new ServerSocket(PORT);

			listening = true;
			while(listening) {
				try {
					socket = serverSocket.accept();
				} catch(IOException e) {
					System.out.println("I/O error: " + e);
				}
				// new thread for a client
				new Client(socket).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
