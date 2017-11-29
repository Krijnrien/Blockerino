package Server.connection;

import Server.util.FileHandler;
import Server.util.ListHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class IncomingConnectionHandler implements Runnable {
	public static boolean listening;
	private static final int PORT = 1978;

	private List<String> whitelistIps;
	private List<String> whitelistNames;
	private List<String> blacklistIps;
	private List<String> blacklistNames;

	private List<String> connectedClients;

	public IncomingConnectionHandler() {

		blacklistIps = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered("blacklistNames.txt"));
		blacklistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered("blacklistNames.txt"));
		blacklistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered("blacklistNames.txt"));
		whitelistNames = ListHandler.StringListByCommaEnd(FileHandler.StringFromFileBuffered("whitelistNames.txt"));
	}

	@Override
	public void run() {
		try {
			Socket socket = null;
			ServerSocket serverSocket = new ServerSocket(PORT);


			if(blacklistIps.contains(socket.getRemoteSocketAddress().toString())){



			}

				listening = true;
			while(listening) {
				try {
					socket = serverSocket.accept();
				} catch(IOException e) {
					System.out.println("I/O error: " + e);
				}
				//	socket.getRemoteSocketAddress()

				// new thread for a client
				new Client(socket).start();
			}
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
