package Server;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

	protected Socket socket;
	ObjectInputStream inputStream;

	Client(Socket clientSocket) {
		this.socket = clientSocket;
		//inputStream = new ObjectInputStream(socket.getInputStream());
	}

	public void run() {
		InputStream inp = null;
		BufferedReader brinp = null;
		ObjectOutputStream out = null;
		try {
			inp = socket.getInputStream();
			brinp = new BufferedReader(new InputStreamReader(inp));
			//out = new DataOutputStream(socket.getOutputStream());
		} catch(IOException e) {
			return;
		}
		while(true) {

		}
	}
}
