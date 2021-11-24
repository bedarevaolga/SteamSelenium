package framework;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {

    private static FileInputStream fileInputStream;
    private static Properties config;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/selenium.properties");
            config = new Properties();
            config.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    public static String getProperty(String key) {
        return config.getProperty(key);
    }
}

