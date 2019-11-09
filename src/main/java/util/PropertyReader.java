package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

    Properties properties;

    InputStream inputStream = null;

    public PropertyReader() {
        properties = new Properties();
        loadProperties();
    }

    public void loadProperties() {
        //Retrieve the environment to run test on, default to version1.
        final String environment = System.getProperty("env", "version1");
        try (InputStream inputStream = PropertyReader.class.getResourceAsStream("/test_resources/application-test-" + environment + ".properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.print("Unable to load properties file");
            e.printStackTrace();
        }
    }


    public String readProperty(String key) {
        return properties.getProperty(key);
    }
}
