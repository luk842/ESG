package tests.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Environment {

    private static final String CONFIGURATION_PROPERTIES_PATH = System.getProperty("configuration.path");
    public static final Properties properties = init_prop();
    public static final String ENV = System.getProperty("env");
    public static final boolean HEADLESS = Boolean.parseBoolean(properties.getProperty("headless").trim());
    public static final String URL = properties.getProperty("url").trim();

    public static Properties init_prop() {
        Properties properties = new Properties();
        try {
            FileInputStream ip = new FileInputStream(CONFIGURATION_PROPERTIES_PATH);
            properties.load(ip);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }
}