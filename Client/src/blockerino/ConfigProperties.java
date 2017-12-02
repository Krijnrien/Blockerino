package blockerino;

import java.io.*;
import java.util.Properties;

public class ConfigProperties {

    public static final String ROOT = "/Client/";
    public static final String source = ROOT + "src/blockerino/";
    public static final String resource = ROOT + "res/";
    public static final String FXML = "/blockerino/UI/fxml/";

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
