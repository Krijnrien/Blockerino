package blockerino.networking;

import blockerino.ConfigProperties;

import java.io.*;
import java.net.Socket;

public class SocketConnection {

	private static String serverAddress = "127.0.0.1"; //TODO Get server IP from user through input;
	private static int serverPort;
	private ObjectOutputStream outputStream = null;

	public SocketConnection() {
		//TODO Check if port is set, otherwise get default one.
		ConfigProperties configProperties = new ConfigProperties();
		//serverPort = (int) configProperties.properties.get("defaultPort");
		Socket client = null;

		try {
			System.out.println("Connecting to " + serverAddress + " on port " + serverPort);
			client = new Socket(serverAddress, serverPort);

			System.out.println("Connected to " + client.getRemoteSocketAddress());
			OutputStream outToServer = client.getOutputStream();
			DataOutputStream out = new DataOutputStream(outToServer);

		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			//TODO Proper error handling
			try {
				if(client != null) {
					client.close();
				}
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}


}
