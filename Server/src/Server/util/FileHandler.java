package Server.util;

import Server.ConfigProperties;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileHandler {

	public static String StringFromFileBuffered(String file) {
		System.out.println(file);
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while(line != null) {
				sb.append(line);
				sb.append(System.lineSeparator());
				line = br.readLine();
			}
			return sb.toString();
		} catch(IOException e) {
			//TODO Add proper file error handling. "Blacklist could not be found, starting server without checking for banned players"  or something.
			e.printStackTrace();
		}
		return null;
	}

}
