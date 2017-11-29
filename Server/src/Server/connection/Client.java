package Server.connection;

import java.io.*;
import java.net.Socket;

public class Client extends Thread {

	private Socket socket;
	private ObjectInputStream inputStream;



	Client(Socket clientSocket) {
		this.socket = clientSocket;
		try {
			inputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		InputStream inp = null;
		BufferedReader brinp = null;
		ObjectOutputStream out = null;
		try {
			inp = socket.getInputStream();
			brinp = new BufferedReader(new InputStreamReader(inp));
//			out = new DataOutputStream(socket.getOutputStream());
			//TODO java read object
            //TODO Identify object
		} catch(IOException e) {
			return;
		}

        Commands command = Commands.getInstance();


//		while(true) {
//
//		}

	}
}
