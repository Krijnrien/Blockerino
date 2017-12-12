package Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigProperties {
	public static final String ROOT = "Server/";
	public static final String source = ROOT + "src/";
	public static final String resource = ROOT + "res/";

	public static final Properties properties;

	static {
		properties = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(resource + "config.properties");
			properties.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			//TODO Proper error handling
		}
	}

}
